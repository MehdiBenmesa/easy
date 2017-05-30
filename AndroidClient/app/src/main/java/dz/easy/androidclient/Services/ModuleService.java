package dz.easy.androidclient.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.Util.CustomRequestArray;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ModuleService extends IntentService implements Constants {
  // TODO: Rename actions, choose action names that describe tasks that this
  // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
  public static final String GET_MODULES_TEACHER = "dz.easy.androidclient.Services.action.getModuleByTeacher";
  public static final String GET_MODULES_STUDENT = "dz.easy.androidclient.Services.action.getModuleByStudent";
  public static final String GET_TEACHERS = "dz.easy.androidclient.Services.action.getTeachers";


  private static final String TAG = "ModuleService";

  // TODO: Rename parameters
  private static final String DATA_RECEIVER = "dz.easy.androidclient.Services.extra.DataReceiver";

  public ModuleService() {
    super(ModuleService.class.getName());
  }

  /**
   * Starts this service to perform action Foo with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void getModulesByTeacher(Context context, DataReceiver data) {
    Intent intent = new Intent(context, ModuleService.class);
    intent.setAction(GET_MODULES_TEACHER);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }

  public static void getModulesByStudent(Context context, DataReceiver data) {
    Intent intent = new Intent(context, ModuleService.class);
    intent.setAction(GET_MODULES_STUDENT);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }

  public static void getTeachers(Context context, DataReceiver data) {
    Intent intent = new Intent(context, ModuleService.class);
    intent.setAction(GET_TEACHERS);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      switch (intent.getAction()){
        case GET_MODULES_TEACHER :
          final ResultReceiver receiverTeacher = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleTeacher = new Bundle();
          receiverTeacher.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getModulesByTeacher(new IModule() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleTeacher.putString("action" , GET_MODULES_TEACHER);
                bundleTeacher.putSerializable("result",  object.toString());
                receiverTeacher.send(STATUS_FINISHED, bundleTeacher);
              }
            });
          } catch (Exception e) {

                /* Sending error message back to activity */
            bundleTeacher.putString(Intent.EXTRA_TEXT, e.toString());
            receiverTeacher.send(STATUS_ERROR, bundleTeacher);
          }

          break ;
        case GET_MODULES_STUDENT :
          final ResultReceiver receiverStudent = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleStudent = new Bundle();
          receiverStudent.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getModulesByStudent(new IModule() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleStudent.putString("action" , GET_MODULES_STUDENT);
                bundleStudent.putSerializable("result",  object.toString());
                receiverStudent.send(STATUS_FINISHED, bundleStudent);
              }
            });
          } catch (Exception e) {

                /* Sending error message back to activity */
            bundleStudent.putString(Intent.EXTRA_TEXT, e.toString());
            receiverStudent.send(STATUS_ERROR, bundleStudent);
          }
          break;
        case GET_TEACHERS :
          final ResultReceiver receiverTeachers = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleTeachers = new Bundle();
          receiverTeachers.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getModulesByStudent(new IModule() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleTeachers.putString("action" , GET_TEACHERS);
                bundleTeachers.putSerializable("result",  object.toString());
                receiverTeachers.send(STATUS_FINISHED, bundleTeachers);
              }
            });
          } catch (Exception e) {

                /* Sending error message back to activity */
            bundleTeachers.putString(Intent.EXTRA_TEXT, e.toString());
            receiverTeachers.send(STATUS_ERROR, bundleTeachers);
          }
          break;

      }

      this.stopSelf();
    }

    }

  private void  getModulesByTeacher(final IModule callBack) throws JSONException {
    //  dialogListner.showDialog();
        /* Starting Download Service */
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_MODULES_BY_TEACHER + "/" +  App.getInstance().getUser().getString("_id"), null,
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
  }


  public void getModulesByStudent(final IModule callBack) {
    try {

      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_MODULES_BY_STUDENT + "/" + App.getInstance().getUser().getString("section") + "/"+App.getInstance().getUser().getString("groupe") , null,
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

  private void  getTeachers(final IModule callBack) {
    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_TEACHERS , null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
          //setup materialviewpager
          callBack.onDataRecieved(response);
        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);
  }
  public interface IModule {
    public void onDataRecieved(JSONArray object);
  }
}
