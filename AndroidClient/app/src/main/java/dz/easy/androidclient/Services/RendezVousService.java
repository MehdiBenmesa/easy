package dz.easy.androidclient.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;

import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.Util.CustomRequestArray;

import static dz.easy.androidclient.App.BaseActivity.TAG;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RendezVousService extends IntentService implements Constants {
  // TODO: Rename actions, choose action names that describe tasks that this
  // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
  public static final String GET_RENDEZVOUS_TEACHER = "dz.easy.androidclient.Services.action.getRendeVousByTeacher";
  public static final String GET_RENDEZVOUS_STUDENT = "dz.easy.androidclient.Services.action.getRendeVousByStudent";
  public static final String GET_RENDEZVOUS_MANAGER = "dz.easy.androidclient.Services.action.getRendeVousByManager";


  private static final String TAG = "ModuleService";

  // TODO: Rename parameters
  private static final String DATA_RECEIVER = "dz.easy.androidclient.Services.extra.DataReceiver";

  public RendezVousService() {
    super(RendezVousService.class.getName());
  }

  /**
   * Starts this service to perform action Foo with the given parameters. If
   * the service is already performing a task this action will be queued.
   *
   * @see IntentService
   */
  // TODO: Customize helper method
  public static void getRendezVousByTeacher(Context context, DataReceiver data , String rdvState) {
    Intent intent = new Intent(context, ModuleService.class);
    intent.setAction(GET_RENDEZVOUS_TEACHER);
    intent.putExtra(DATA_RECEIVER, data);
    intent.putExtra("rdvState", rdvState);
    context.startService(intent);
  }

  public static void getRendeVousByStudent(Context context, DataReceiver data , String rdvState) {
    Intent intent = new Intent(context, ModuleService.class);
    intent.setAction(GET_RENDEZVOUS_STUDENT);
    intent.putExtra(DATA_RECEIVER, data);
    intent.putExtra("rdvState", rdvState);
    context.startService(intent);
  }

  public static void getRendezvousByManager(Context context, DataReceiver data) {
    Intent intent = new Intent(context, ModuleService.class);
    intent.setAction(GET_RENDEZVOUS_MANAGER);
    intent.putExtra(DATA_RECEIVER, data);
    context.startService(intent);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      switch (intent.getAction()){
        case GET_RENDEZVOUS_TEACHER :
          final ResultReceiver receiverTeacher = intent.getParcelableExtra(DATA_RECEIVER);
          final String rdvState = intent.getParcelableExtra("rdvState");
          final Bundle bundleTeacher = new Bundle();
          receiverTeacher.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getRendezVousByTeacher(rdvState , new IRendezVous() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleTeacher.putString("action" , GET_RENDEZVOUS_TEACHER);
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
        case GET_RENDEZVOUS_STUDENT :
          final ResultReceiver receiverStudent = intent.getParcelableExtra(DATA_RECEIVER);
          final String rdv = intent.getParcelableExtra("rdvState");
          final Bundle bundleStudent = new Bundle();
          receiverStudent.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getRendeVousByStudent(rdv , new IRendezVous() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleStudent.putString("action" , GET_RENDEZVOUS_STUDENT);
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
        case GET_RENDEZVOUS_MANAGER :
          final ResultReceiver receiverTeachers = intent.getParcelableExtra(DATA_RECEIVER);
          final Bundle bundleTeachers = new Bundle();
          receiverTeachers.send(STATUS_RUNNING, Bundle.EMPTY);
          try {
            getRendezvousByManager(new IRendezVous() {
              @Override
              public void onDataRecieved(JSONArray object) {
                bundleTeachers.putString("action" , GET_RENDEZVOUS_MANAGER);
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

  private void  getRendezvousByManager(final IRendezVous callBack) {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_RDV_BY_TEACHER, null,
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

  private void  getRendezVousByTeacher(String rdvState , final IRendezVous callBack) throws JSONException {
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_RDV_BY_TEACHER + "/"+ rdvState + "/" + App.getInstance().getUser().getString("_id"), null,
        new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
            callBack.onDataRecieved(response);
            //   recyclerViewRendeVous.setAdapter(new TestRecyclerViewAdapter(response));
          }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
      });

      App.getInstance().addToRequestQueue(jsonReq);
    }

  private void getRendeVousByStudent(String rdvState , final IRendezVous callBack) throws JSONException {
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_RDV_BY_STUDENT + "/"  + rdvState + "/" + App.getInstance().getUser().getString("_id"), null,
        new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
              callBack.onDataRecieved(response);
            //  recyclerViewRendeVous.setAdapter(new TestRecyclerViewAdapter(response));


          }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
      });
      App.getInstance().addToRequestQueue(jsonReq);
  }

  public interface IRendezVous {
    public void onDataRecieved(JSONArray object);
  }
}
