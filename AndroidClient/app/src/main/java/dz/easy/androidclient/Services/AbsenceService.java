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
import android.view.View;
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

import dz.easy.androidclient.Adapters.AbsenceAdapter;
import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.Adapters.ModuleListAdapter;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequest;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.fragment.GroupFragment;
import dz.easy.androidclient.fragment.ModuleFragment;
import dz.easy.androidclient.fragment.StudentsFragmentAbsence;

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
public class AbsenceService extends IntentService implements Constants {
  // TODO: Rename actions, choose action names that describe tasks that this
  // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
  public static final String GET_SEANCE_TEACHER = "dz.easy.androidclient.Services.action.getSeanceByTeacher";
  public static final String GET_ABSENCE_SEANCE = "dz.easy.androidclient.Services.action.getAbsneceBySeance";
  public static final String GET_ABSENCE_STUDENT = "dz.easy.androidclient.Services.action.getAbsenceByStudent";
  public static final String GET_ABSENCE_STUDENT_MODULE = "dz.easy.androidclient.Services.action.getAbsenceByStudentByModule";

  private static final String TAG = "AbsenceService";

  // TODO: Rename parameters
  private static final String DATA_RECEIVER = "dz.easy.androidclient.Services.extra.DataReceiver";

  public AbsenceService() {
    super(AbsenceService.class.getName());
  }

  /**
   * Starts this service to perform action Foo with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void getSeanceByTeacher(Context context, DataReceiver data ) {
    Intent intent = new Intent(context, AbsenceService.class);
    intent.setAction(GET_SEANCE_TEACHER);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }

  public static void getAbsenceByStudentByModule(Context context, DataReceiver data , String moduleId ) {
    Intent intent = new Intent(context, AbsenceService.class);
    intent.setAction(GET_ABSENCE_STUDENT_MODULE);
    intent.putExtra(DATA_RECEIVER, data);
    intent.putExtra("moduleId", moduleId);
    context.startService(intent);
  }


  public static void getAbsenceByStudent(Context context, DataReceiver data ) {
    Intent intent = new Intent(context, AbsenceService.class);
    intent.setAction(GET_ABSENCE_STUDENT);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }


  public static void getAbsneceBySeance(Context context, DataReceiver data  , JSONObject myData) {
    Intent intent = new Intent(context, AbsenceService.class);
    try {
      intent.putExtra("idSeance",myData.getString("idSeance") );
      intent.putExtra("date",myData.getString("date") );
    } catch (JSONException e) {
      e.printStackTrace();
    }
    intent.setAction(GET_ABSENCE_SEANCE);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }



  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      switch (intent.getAction()){
        case GET_SEANCE_TEACHER :
          final ResultReceiver receiverSeance = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleSeance = new Bundle();
          receiverSeance.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getSeanceByTeacher(new IAbsence() {
              @Override
              public void onDataReceived(JSONObject data) {
                bundleSeance.putString("action" , GET_SEANCE_TEACHER);
                bundleSeance.putSerializable("result",  data.toString());
                receiverSeance.send(STATUS_FINISHED, bundleSeance);
              }
              @Override
              public void onDataReceived(JSONArray data) {}
            });

          } catch (Exception e) {
                /* Sending error message back to activity */
            bundleSeance.putString(Intent.EXTRA_TEXT, e.toString());
            receiverSeance.send(STATUS_ERROR, bundleSeance);
          }
          break ;

        case GET_ABSENCE_SEANCE :
          final ResultReceiver receiverAbsence = intent.getParcelableExtra(DATA_RECEIVER);
          final String seanceId = intent.getStringExtra("idSeance");
          final String date = intent.getStringExtra("date");
          final Bundle bundleAbsence = new Bundle();
          receiverAbsence.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getAbsneceBySeance(seanceId , new IAbsence() {
              @Override
              public void onDataReceived(JSONObject data) {
              }

              @Override
              public void onDataReceived(JSONArray data) {
                bundleAbsence.putString("action" , GET_ABSENCE_SEANCE);
                bundleAbsence.putSerializable("result",  data.toString());
                bundleAbsence.putSerializable("date", date);
                receiverAbsence.send(STATUS_FINISHED, bundleAbsence);
              }
            });

          } catch (Exception e) {
                /* Sending error message back to activity */
            bundleAbsence.putString(Intent.EXTRA_TEXT, e.toString());
            receiverAbsence.send(STATUS_ERROR, bundleAbsence);
          }
          break ;
        case GET_ABSENCE_STUDENT_MODULE :
          final ResultReceiver receiverStudent = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleStudent = new Bundle();
          final String moduleId = intent.getStringExtra("moduleId");
          receiverStudent.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getAbsenceByStudentByModule(moduleId , new IAbsence() {
              @Override
              public void onDataReceived(JSONArray data) {
                bundleStudent.putString("action" , GET_SEANCE_TEACHER);
                bundleStudent.putSerializable("result",  data.toString());
                receiverStudent.send(STATUS_FINISHED, bundleStudent);
              }
              @Override
              public void onDataReceived(JSONObject data) {}
            });

          } catch (Exception e) {
                /* Sending error message back to activity */
            bundleStudent.putString(Intent.EXTRA_TEXT, e.toString());
            receiverStudent.send(STATUS_ERROR, bundleStudent);
          }
          break ;
        case GET_ABSENCE_STUDENT :
          final ResultReceiver receiver = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundle = new Bundle();
          receiver.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getAbsenceByStudent( new IAbsence() {
              @Override
              public void onDataReceived(JSONArray data) {
                bundle.putString("action" , GET_SEANCE_TEACHER);
                bundle.putSerializable("result",  data.toString());
                receiver.send(STATUS_FINISHED, bundle);
              }
              @Override
              public void onDataReceived(JSONObject data) {}
            });

          } catch (Exception e) {
                /* Sending error message back to activity */
            bundle.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, bundle);
          }
          break ;

      }

      this.stopSelf();
    }

  }



  public void getSeanceByTeacher(final IAbsence callBack) throws JSONException {
      CustomRequest jsonReq = new CustomRequest(Request.Method.GET, GET_TIME_TABLE_TEACHER+ "/" + App.getInstance().getUser().getString("_id"), null,
        new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
            callBack.onDataReceived(response);
          }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
      });
      App.getInstance().addToRequestQueue(jsonReq);
  }


  public void getAbsneceBySeance(String idSeance ,final IAbsence callBack){

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_ABSENCE_BY_SEANCE+ "/" + idSeance, null,
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


  public void getAbsenceByStudentByModule(String moduleID , final IAbsence callBack) throws JSONException {
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_ABSENCE_BY_STUDENT+ "/" + App.getInstance().getUser().getString("_id") + "/"+moduleID, null,
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


  private void getAbsenceByStudent(final IAbsence callBack) throws JSONException {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_ABSENCE_BY_STUDENT + "/" + App.getInstance().getUser().get("_id"), null,
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
  public interface IAbsence{
    public void onDataReceived(JSONObject data);
    public void onDataReceived(JSONArray data);
  }
}
