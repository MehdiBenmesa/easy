package dz.easy.androidclient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.Util.SessionManager;

import static dz.easy.androidclient.App.BaseActivity.TAG;

/**
 * Created by Abderahmane on 16/05/2017.
 */

public class GroupFragment extends Fragment implements Constants  , GroupListAdapter.AdapterInterface {

    private static final boolean GRID_LAYOUT = false;

    IDialog dialogListner ;
    private JSONObject  user = null , module = null;
    public GroupFragment newInstance(String modul){
        GroupFragment grF = new GroupFragment();
        Bundle bndl = new Bundle();
        bndl.putString("module" , modul);

        grF.setArguments(bndl);
        return grF;
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);ButterKnife.bind(this, view);
      SessionManager sessionManager = new SessionManager(view.getContext());
      String stringUser = sessionManager.getUser();
      try {
            user = new JSONObject(stringUser);
            module = new JSONObject(getArguments().getString("module"));
            if(user.getString("_type").equals("Teacher")){
                getGroupsByModuleByTeacher();
            }else if (user.getString("_type").equals("Manager")) {
                //getTeachers();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    public void getGroupsByModuleByTeacher(){

        try {
            //dialogListner.showDialog();
            CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_GROUPS_BY_TEACHER_MODULE + "/" + module.getString("_id") + "/"
                     + user.getString("_id"), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            // JSONObject modules = response.getJSONObject("course");
                            JSONArray groups = response;
                            Log.i(TAG, "Signed in as: " + groups);

                            //setup materialviewpager

                            if (GRID_LAYOUT) {
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            } else {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                            mRecyclerView.setHasFixedSize(true);
                            //Use this now
                            mRecyclerView.setAdapter(new GroupListAdapter(groups , (GroupListAdapter.AdapterInterface) GroupFragment.this));

                   // dialogListner.hideDialog();
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
}
