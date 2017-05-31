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
import dz.easy.androidclient.Activities.StudentNoteAbsenceActivity;
import dz.easy.androidclient.Activities.StudentsActivity;
import dz.easy.androidclient.Activities.UserActivity;
import dz.easy.androidclient.Adapters.ModuleListAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.Services.DataReceiver;
import dz.easy.androidclient.Services.ModuleService;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;

import static android.content.Context.MEDIA_PROJECTION_SERVICE;
import static dz.easy.androidclient.App.BaseActivity.TAG;
import static dz.easy.androidclient.Services.ModuleService.GET_MODULES_STUDENT;
import static dz.easy.androidclient.Services.ModuleService.GET_MODULES_TEACHER;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ModuleFragment extends Fragment implements Constants, ModuleListAdapter.AdapterInterface , DataReceiver.Receiver{

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    IDialog dialogListner ;
    private static  JSONObject user;
    DataReceiver mReceiver ;
    public static ModuleFragment newInstance(JSONObject managerData) {
        user = managerData;
      return new ModuleFragment();
    }

  @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mReceiver = new DataReceiver(new Handler());
        mReceiver.setReceiver(this);

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
          } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
          }
          mRecyclerView.setHasFixedSize(true);

          //Use this now
          mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        try {
            if(user.getString("_type").equals("Teacher")){
              ModuleService.getModulesByTeacher(getContext() , mReceiver);
            }else if (user.getString("_type").equals("Manager")){
              ModuleService.getTeachers(getContext() , mReceiver);
            }else if (user.getString("_type").equals("Student")){
              ModuleService.getModulesByStudent(getContext() , mReceiver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void buttonPressed(JSONObject module) {
      Intent i = null;
      try {
        if (user.getString("_type").equals("Teacher")) {
          i = new Intent(getContext(), GroupActivity.class);
        } else if (user.getString("_type").equals("Student")) {
          i = new Intent(getContext(), StudentNoteAbsenceActivity.class);
        }
      }catch (JSONException e) {
        e.printStackTrace();
      }
        i.putExtra("user" ,user.toString());
        i.putExtra("module" ,module.toString());
        getContext().startActivity(i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dialogListner = (UserActivity) activity ;
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
              case GET_MODULES_TEACHER :
                String jsonStringTeacher = resultData.getString("result");
                JSONArray responseTeacher = null;
                try {
                  responseTeacher = new JSONArray(jsonStringTeacher);
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                mRecyclerView.setAdapter(new ModuleListAdapter(responseTeacher,(ModuleListAdapter.AdapterInterface) this));

                break ;

              case GET_MODULES_STUDENT :
                String jsonStringStudent = resultData.getString("result");
                JSONArray responseStudent = null;
                try {
                  responseStudent = new JSONArray(jsonStringStudent);
                } catch (JSONException e) {
                  e.printStackTrace();
                }
//<<<<<<< HEAD
                mRecyclerView.setAdapter(new ModuleListAdapter(responseStudent,(ModuleListAdapter.AdapterInterface) this));
/*=======
                mRecyclerView.setAdapter(new ModuleListAdapter(ModuleFragment.this  , responseStudent));
>>>>>>> 55388211e7ecbd404a57417f22718441319de1fe*/
                break ;
              case GET_TEACHERS :
                String jsonStringTeachers = resultData.getString("result");
                JSONArray responseTeachers = null;
                try {
                  responseTeachers = new JSONArray(jsonStringTeachers);
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                //mRecyclerView.setAdapter(new TeachersAdapter(responseTeachers));
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




