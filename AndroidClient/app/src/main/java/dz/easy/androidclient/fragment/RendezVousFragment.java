package dz.easy.androidclient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dz.easy.androidclient.Activities.GroupActivity;
import dz.easy.androidclient.Activities.RendezVousActivity_;
import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.Services.DataReceiver;
import dz.easy.androidclient.Services.RendezVousService;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;

import static dz.easy.androidclient.App.BaseActivity.TAG;
import static dz.easy.androidclient.Services.GroupService.GET_GROUP_MODULE_TEACHER;
import static dz.easy.androidclient.Services.RendezVousService.GET_RENDEZVOUS_MANAGER;
import static dz.easy.androidclient.Services.RendezVousService.GET_RENDEZVOUS_STUDENT;
import static dz.easy.androidclient.Services.RendezVousService.GET_RENDEZVOUS_TEACHER;

public class RendezVousFragment extends Fragment implements Constants, DataReceiver.Receiver {

  private static final boolean GRID_LAYOUT = false;
  private static final int ITEM_COUNT = 100;

  @BindView(R.id.recyclerView)
  RecyclerView recyclerViewRendeVous;

  private static JSONObject user;
  private static String rdvState;
  private IDialog dialogListner ;
  private DataReceiver mReceiver ;

  public static RendezVousFragment newInstance(JSONObject managerData,String state) {
    user = managerData;
    rdvState = state;
    return new RendezVousFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_recyclerview, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mReceiver = new DataReceiver(new Handler());
    mReceiver.setReceiver(this);

    ButterKnife.bind(this, view);
    if (GRID_LAYOUT) {
      recyclerViewRendeVous.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    } else {
      recyclerViewRendeVous.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    recyclerViewRendeVous.setHasFixedSize(true);

    //Use this now
    recyclerViewRendeVous.addItemDecoration(new MaterialViewPagerHeaderDecorator());

    try {
      if(user.getString("_type").equals("Teacher")){
        //Toast.makeText(getContext() , "Hi Teacher" , Toast.LENGTH_LONG).show();
        RendezVousService.getRendezVousByTeacher(getContext() , mReceiver , rdvState);
      }else if (user.getString("_type").equals("Manager")){
        //Toast.makeText(getContext() , "Hi Manager" , Toast.LENGTH_LONG).show();
        RendezVousService.getRendezvousByManager(getContext() ,mReceiver );
      }else if (user.getString("_type").equals("Student")){
        //Toast.makeText(getContext() , "Hi Student" , Toast.LENGTH_LONG).show();
        RendezVousService.getRendeVousByStudent(getContext() , mReceiver , rdvState);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }


  }




  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    dialogListner = (RendezVousActivity_) activity ;
  }

  @Override
  public void onReceiveResult(int resultCode, Bundle resultData) {
    switch (resultCode) {
      case STATUS_RUNNING:
        dialogListner.showDialog();
        break;
      case STATUS_FINISHED:
          /* Hide progress & extract result from bundle */
        dialogListner.hideDialog();
        switch (resultData.getString("action")){
          case GET_RENDEZVOUS_MANAGER :
            String jsonStringManager = resultData.getString("result");
            JSONArray response = null;
            try {
              response = new JSONArray(jsonStringManager);
              recyclerViewRendeVous.setAdapter(new TeachersAdapter(response));

            } catch (JSONException e) {
              e.printStackTrace();
            }
            break ;
          case GET_RENDEZVOUS_STUDENT :
            String jsonStringStudent = resultData.getString("result");
            JSONArray responseStudent = null;
            try {
              responseStudent = new JSONArray(jsonStringStudent);
              //recyclerViewRendeVous.setAdapter(new TeachersAdapter(responseStudent));

            } catch (JSONException e) {
              e.printStackTrace();
            }
            break ;
          case GET_RENDEZVOUS_TEACHER :
            String jsonStringTeacher = resultData.getString("result");
            JSONArray responseTeacher = null;
            try {
              responseTeacher = new JSONArray(jsonStringTeacher);
              //recyclerViewRendeVous.setAdapter(new TeachersAdapter(responseTeacher));

            } catch (JSONException e) {
              e.printStackTrace();
            }
            break ;
        }

        break;
      case STATUS_ERROR:
                  /* Handle the error */
        String error = resultData.getString(Intent.EXTRA_TEXT);
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
        break;
    }
  }
}
