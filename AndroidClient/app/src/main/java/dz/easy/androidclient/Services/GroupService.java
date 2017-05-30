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

import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.Adapters.ModuleListAdapter;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
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
public class GroupService extends IntentService implements Constants {
  // TODO: Rename actions, choose action names that describe tasks that this
  // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
  public static final String GET_GROUP_MODULE_TEACHER = "dz.easy.androidclient.Services.action.getGroupsByModuleByTeacher";

  private static final String TAG = "GroupService";

  // TODO: Rename parameters
  private static final String DATA_RECEIVER = "dz.easy.androidclient.Services.extra.DataReceiver";
  private static final String EXTRA_PARAM2 = "dz.easy.androidclient.Services.extra.PARAM2";

  public GroupService() {
    super(GroupService.class.getName());
  }

  /**
   * Starts this service to perform action Foo with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void getGroupsByModuleByTeacher(Context context, DataReceiver data , String moduleId) {
    Intent intent = new Intent(context, GroupService.class);
    intent.setAction(GET_GROUP_MODULE_TEACHER);
    intent.putExtra(DATA_RECEIVER, data);
    intent.putExtra("moduleId", moduleId);
    context.startService(intent);
  }


  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      switch (intent.getAction()){
        case GET_GROUP_MODULE_TEACHER :
          String  moduleId = intent.getStringExtra("moduleId");
          final ResultReceiver receiverTeacher = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleTeacher = new Bundle();
          receiverTeacher.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getGroupsByModuleByTeacher(new IGroupe() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleTeacher.putString("action" , GET_GROUP_MODULE_TEACHER);
                bundleTeacher.putSerializable("result",  object.toString());
                receiverTeacher.send(STATUS_FINISHED, bundleTeacher);
              }
            } , moduleId );
          } catch (Exception e) {

                /* Sending error message back to activity */
            bundleTeacher.putString(Intent.EXTRA_TEXT, e.toString());
            receiverTeacher.send(STATUS_ERROR, bundleTeacher);
          }

          break ;

      }

      this.stopSelf();
    }

  }

  public void getGroupsByModuleByTeacher(final IGroupe callBack , String moduleId){

    try {
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_GROUPS_BY_TEACHER_MODULE + "/" + moduleId + "/"
        + App.getInstance().getUser().getString("_id"), null,
        new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
            callBack.onDataRecieved(response);
          }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
      });

      App.getInstance().addToRequestQueue(jsonReq);

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }



  public interface IGroupe {
    public void onDataRecieved(JSONArray object);
  }
}
