package dz.easy.androidclient.fragment;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.Adapters.TestRecyclerViewAdapter;
import dz.easy.androidclient.Util.CustomRequest;
import dz.easy.androidclient.Util.CustomRequestArray;

import static dz.easy.androidclient.App.BaseActivity.TAG;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ModuleFragment extends Fragment implements Constants{

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

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

    private void getModulesByStudent() {
        try {
            CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_MODULES_BY_SPEC + "/" + user.getString("section"), null,
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
                            mRecyclerView.setAdapter(new TestRecyclerViewAdapter(modules));


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

    private void  getModulesByTeacher() {

        try {
            CustomRequest jsonReq = new CustomRequest(Request.Method.GET, GET_MODULES_BY_TEACHER + "/" + user.getString("_id"), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray modules = response.getJSONArray("modules");
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
                                mRecyclerView.setAdapter(new TestRecyclerViewAdapter(modules));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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

    }



