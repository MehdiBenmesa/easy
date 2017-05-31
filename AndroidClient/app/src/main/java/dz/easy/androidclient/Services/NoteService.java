package dz.easy.androidclient.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.Adapters.ModuleListAdapter;
import dz.easy.androidclient.Adapters.NoteAdapter;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequest;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.fragment.GroupFragment;
import dz.easy.androidclient.fragment.ModuleFragment;

import static dz.easy.androidclient.App.BaseActivity.TAG;
import static dz.easy.androidclient.Constants.Constants.GET_MODULES_BY_STUDENT;
import static dz.easy.androidclient.Constants.Constants.GET_MODULES_BY_TEACHER;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NoteService extends IntentService implements Constants {
  // TODO: Rename actions, choose action names that describe tasks that this
  // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
  public static final String PUT_STUDENT_NOTE = "dz.easy.androidclient.Services.action.putNoteStudent";
  public static final String GET_NOTE_STUDENT = "dz.easy.androidclient.Services.action.getNoteByStudent";
  public static final String GET_NOTE_STUDENT_MODULE = "dz.easy.androidclient.Services.action.getNoteByStudentByModule";

  private static final String TAG = "NoteService";

  // TODO: Rename parameters
  private static final String DATA_RECEIVER = "dz.easy.androidclient.Services.extra.DataReceiver";

  public NoteService() {
    super(NoteService.class.getName());
  }

  /**
   * Starts this service to perform action Foo with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void putNoteStudent(Context context, DataReceiver data , JSONObject dataToSend) {
    Intent intent = new Intent(context, NoteService.class);
    intent.setAction(PUT_STUDENT_NOTE);
    intent.putExtra(DATA_RECEIVER, data);
    intent.putExtra("dataToSend", dataToSend.toString());
    context.startService(intent);
  }

  public static void getNoteByStudent(Context context, DataReceiver data ) {
    Intent intent = new Intent(context, NoteService.class);
    intent.setAction(GET_NOTE_STUDENT);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }

  public static void getNoteByStudentByModule(Context context, DataReceiver data , String moduleId ) {
    Intent intent = new Intent(context, NoteService.class);
    intent.setAction(GET_NOTE_STUDENT_MODULE);
    intent.putExtra(DATA_RECEIVER, data);
    intent.putExtra("moduleId", moduleId);
    context.startService(intent);
  }


  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      switch (intent.getAction()){
        case PUT_STUDENT_NOTE :
          final ResultReceiver receiverNote = intent.getParcelableExtra(DATA_RECEIVER);
          Bundle noteStudent = new Bundle();
          receiverNote.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            JSONObject  dataToSend = new JSONObject(intent.getStringExtra("dataToSend"));
            putNoteStudent(dataToSend);
            receiverNote.send(STATUS_FINISHED, noteStudent);
          } catch (Exception e) {
                /* Sending error message back to activity */
            noteStudent.putString(Intent.EXTRA_TEXT, e.toString());
            receiverNote.send(STATUS_ERROR, noteStudent);
          }
          break ;
        case GET_NOTE_STUDENT :
          final ResultReceiver receiver = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle note = new Bundle();
          receiver.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getNotesByStudent(new INote() {
              @Override
              public void onDataReceived(JSONArray data) {
                note.putString("action" , GET_NOTE_STUDENT);
                note.putSerializable("result",  data.toString());
                receiver.send(STATUS_FINISHED, note);
              }
            });
          } catch (Exception e) {
                /* Sending error message back to activity */
            note.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, note);
          }
          break ;
        case GET_NOTE_STUDENT_MODULE :
          final ResultReceiver receiverStudentModule = intent.getParcelableExtra(DATA_RECEIVER);
          final String moduleId = intent.getParcelableExtra("moduleId");
          final Bundle noteStudentModule = new Bundle();
          receiverStudentModule.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getNoteByStudentByModule(moduleId , new INote() {
              @Override
              public void onDataReceived(JSONArray data) {
                noteStudentModule.putString("action" , GET_NOTE_STUDENT);
                noteStudentModule.putSerializable("result",  data.toString());
                receiverStudentModule.send(STATUS_FINISHED, noteStudentModule);
              }
            });
          } catch (Exception e) {
                /* Sending error message back to activity */
            noteStudentModule.putString(Intent.EXTRA_TEXT, e.toString());
            receiverStudentModule.send(STATUS_ERROR, noteStudentModule);
          }
          break ;

      }

      this.stopSelf();
    }

  }


  private void  putNoteStudent(final JSONObject dataToSend) {

    CustomRequest jsonReq = new CustomRequest(Request.Method.POST, POST_NOTE, null,
      new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        //hidepDialog();
      }
    }) {
      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        try {
          params.put("reason", dataToSend.getString("reason"));
          params.put("value",dataToSend.getString("note") );
          params.put("student", dataToSend.getString("student"));
          params.put("module", dataToSend.getString("_id"));
          params.put("modulename", dataToSend.getString("_name"));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        return params;
      }
    };
    App.getInstance().addToRequestQueue(jsonReq);
  }



  private void getNotesByStudent(final INote callBack) throws JSONException {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_NOTE_BY_STUDENT + "/" + App.getInstance().getUser().get("_id"), null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
          callBack.onDataReceived(response);
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);

  }


  public void getNoteByStudentByModule(String moduleId , final INote callBack) throws JSONException {
      System.out.println("AVANT ABSENCE 2 ");
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_NOTE_BY_MODULES+ "/" + App.getInstance().getUser().getString("_id") + "/"+ moduleId , null,
        new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
            callBack.onDataReceived(response);
          }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
      });

      App.getInstance().addToRequestQueue(jsonReq);

  }
  public interface INote{
    public void onDataReceived(JSONArray data);
  }
}
