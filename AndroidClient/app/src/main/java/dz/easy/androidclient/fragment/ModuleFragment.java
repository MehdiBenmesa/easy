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
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dz.easy.androidclient.Activities.GroupActivity;
import dz.easy.androidclient.Activities.ModuleActivity;
import dz.easy.androidclient.Activities.UserActivity;
import dz.easy.androidclient.Adapters.ModuleListAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.App.BaseActivity;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.Adapters.TestRecyclerViewAdapter;
import dz.easy.androidclient.Util.CustomRequest;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;

import static dz.easy.androidclient.App.BaseActivity.TAG;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ModuleFragment extends Fragment implements Constants, ModuleListAdapter.AdapterInterface{

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    IDialog dialogListner ;
    private static  JSONObject user;

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

        try {
            if(user.getString("_type").equals("Teacher")){
                //Toast.makeText(getContext() , "Hi Teacher" , Toast.LENGTH_LONG).show();
                getModulesByTeacher();
            }else if (user.getString("_type").equals("Manager")){
                //Toast.makeText(getContext() , "Hi Manager" , Toast.LENGTH_LONG).show();
                getTeachers();
            }else if (user.getString("_type").equals("Student")){
                //Toast.makeText(getContext() , "Hi Student" , Toast.LENGTH_LONG).show();
                getModulesByStudent();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void getModulesByStudent() {
        try {

            CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_MODULES_BY_STUDENT + "/" + user.getString("section") + "/"+user.getString("groupe") , null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // JSONObject modules = response.getJSONObject("course");
                            JSONArray modules = response;
                            Log.i(TAG, "Signed in as: " + modules);

                            //setup materialviewpager

                            if (GRID_LAYOUT) {
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            } else {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                            mRecyclerView.setHasFixedSize(true);
                            //Use this now
                            mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                            mRecyclerView.setAdapter(new ModuleListAdapter(modules, ModuleFragment.this));

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
/*
    private void  getModulesByTeacher() {
        try {
            CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_MODULES_BY_TEACHER + "/" + user.getString("_id"), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            //   JSONArray modules = response.getJSONArray("modules");
                            Log.i(TAG, "Signed in as: " + response);

                            //setup materialviewpager

                            if (GRID_LAYOUT) {
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            } else {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                            mRecyclerView.setHasFixedSize(true);

                            //Use this now
                            mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                            mRecyclerView.setAdapter(new ModuleListAdapter(response, ModuleFragment.this));


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
    }*/


    private void  getTeachers() {

        CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_TEACHERS , null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONArray teachers = response;
                        Log.i(TAG, "Signed in as: " + response.toString());

                        //setup materialviewpager

                            if (GRID_LAYOUT) {
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            } else {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                            mRecyclerView.setHasFixedSize(true);

                            //Use this now
                            mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                            mRecyclerView.setAdapter(new TeachersAdapter(teachers));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        App.getInstance().addToRequestQueue(jsonReq);
    }
    @Override
    public void buttonPressed(JSONObject module) {
        Intent i = new Intent(getContext() , GroupActivity.class);
        i.putExtra("user" ,user.toString());
        i.putExtra("module" ,module.toString());
        getContext().startActivity(i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dialogListner = (UserActivity) activity ;
    }

    private void  getModulesByTeacher() {
      //  dialogListner.showDialog();
        try {
            CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_MODULES_BY_TEACHER + "/" + user.getString("_id"), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            //   JSONArray modules = response.getJSONArray("modules");
                            Log.i(TAG, "Signed in as: " + response);

                            //setup materialviewpager

                            if (GRID_LAYOUT) {
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            } else {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                            mRecyclerView.setHasFixedSize(true);

                            //Use this now
                            mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                            final ModuleListAdapter module = new ModuleListAdapter(response, ModuleFragment.this);
                            mRecyclerView.setAdapter(module);
                            /*
                            mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {

                                    ModuleListAdapter.MyViewHolder child;
                                    ArrayList<ModuleListAdapter.MyViewHolder> myList = module.getMyList();
                                    Toast.makeText(getContext() , "Hello From Module Fragment "+ myList.size() +" hihi " , Toast.LENGTH_LONG).show();
                                    for (int i = 0; i < myList.size(); i++) {
                                        child = myList.get(i);
                                        Toast.makeText(getContext() , child.title.getText() , Toast.LENGTH_LONG).show();
                                    }
                                }
                            });*/

                     //       dialogListner.hideDialog();
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

}



