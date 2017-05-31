package dz.easy.androidclient.fragment;

import android.app.Activity;
import android.content.Context;
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
import dz.easy.androidclient.Activities.ModuleActivity;
import dz.easy.androidclient.Activities.StudentsActivity;
import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.Adapters.ModuleListAdapter;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Services.DataReceiver;
import dz.easy.androidclient.Services.GroupService;
import dz.easy.androidclient.Services.ModuleService;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.Util.SessionManager;

import static dz.easy.androidclient.App.BaseActivity.TAG;
import static dz.easy.androidclient.Services.GroupService.GET_GROUP_MODULE_TEACHER;
import static dz.easy.androidclient.Services.ModuleService.GET_MODULES_STUDENT;
import static dz.easy.androidclient.Services.ModuleService.GET_MODULES_TEACHER;

/**
 * Created by Abderahmane on 16/05/2017.
 */

public class GroupFragment extends Fragment implements Constants  , GroupListAdapter.AdapterInterface, DataReceiver.Receiver {

    private static final boolean GRID_LAYOUT = false;

  static Context context;
  private static JSONObject user = null;
  JSONObject module = null;
  static SessionManager sessionManager;
    private IDialog dialogListner ;
    private DataReceiver mReceiver ;
  public static GroupFragment newInstance(Context c,String module){
    context = c;
    sessionManager = new SessionManager(context);
    try {
      user = new JSONObject(sessionManager.getUser());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    System.out.println("NewInstance : ME FIRST ");
        Bundle bndl = new Bundle();
        bndl.putString("module" , module);
      GroupFragment grF = new GroupFragment();
      grF.setArguments(bndl);
      return grF;
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mReceiver = new DataReceiver(new Handler());
        mReceiver.setReceiver(this);

        System.out.println("OnCreate : ME FIRST ");
        try {
            module = new JSONObject(getArguments().getString("module"));
            user = App.getInstance().getUser();
            if(user.getString("_type").equals("Teacher")){
              GroupService.getGroupsByModuleByTeacher(getContext() , mReceiver , module.getString("_id"));
            }else if (user.getString("_type").equals("Manager")) {
                Toast.makeText(getContext() , "Hi Manager" , Toast.LENGTH_LONG).show();
                //getTeachers();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void buttonPressed(JSONArray students) {
        Intent i = new Intent(getContext() , StudentsActivity.class);
        i.putExtra("user" ,user.toString());
        i.putExtra("module" ,module.toString());
        i.putExtra("students" ,students.toString());
        getContext().startActivity(i);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dialogListner = (GroupActivity) activity ;
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
            case GET_GROUP_MODULE_TEACHER :
              String jsonStringTeacherModule = resultData.getString("result");
              JSONArray responseTeacherModule = null;
              try {
                responseTeacherModule = new JSONArray(jsonStringTeacherModule);
              } catch (JSONException e) {
                e.printStackTrace();
              }
              mRecyclerView.setAdapter(new GroupListAdapter(responseTeacherModule , (GroupListAdapter.AdapterInterface) GroupFragment.this));
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
