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
import dz.easy.androidclient.TestRecyclerViewAdapter;
import dz.easy.androidclient.Util.CustomRequestArray;

import static dz.easy.androidclient.App.BaseActivity.TAG;

public class RendezVousFragment extends Fragment implements Constants{

  private static final boolean GRID_LAYOUT = false;
  private static final int ITEM_COUNT = 100;

  @BindView(R.id.recyclerView)
  RecyclerView recyclerViewRendeVous;

  private static JSONObject user;
  private static String rdvState;
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
    ButterKnife.bind(this, view);
    try {
      if(user.getString("_type").equals("Teacher")){
        //Toast.makeText(getContext() , "Hi Teacher" , Toast.LENGTH_LONG).show();
        getRendeVousByTeacher();
      }else if (user.getString("_type").equals("Manager")){
        //Toast.makeText(getContext() , "Hi Manager" , Toast.LENGTH_LONG).show();
        getRendeVousByManager();
      }else if (user.getString("_type").equals("Student")){
        //Toast.makeText(getContext() , "Hi Student" , Toast.LENGTH_LONG).show();
        getRendeVousByStudent();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }


  }

  private void getRendeVousByStudent() {
    try {
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_RDV_BY_STUDENT + "/"  + rdvState + "/" + user.getString("_id"), null,
        new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {

            // JSONObject modules = response.getJSONObject("course");
            //setup materialviewpager

            if (GRID_LAYOUT) {
              recyclerViewRendeVous.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            } else {
              recyclerViewRendeVous.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            recyclerViewRendeVous.setHasFixedSize(true);
            //Use this now
            recyclerViewRendeVous.addItemDecoration(new MaterialViewPagerHeaderDecorator());
            recyclerViewRendeVous.setAdapter(new TestRecyclerViewAdapter(response));


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

  private void  getRendeVousByTeacher() {
    Log.i(TAG, "Signed in as: ");

    try {
      CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_RDV_BY_TEACHER + "/"+ rdvState + "/" + user.getString("_id"), null,
        new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response) {
            //setup materialviewpager

            if (GRID_LAYOUT) {
              recyclerViewRendeVous.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            } else {
              recyclerViewRendeVous.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            recyclerViewRendeVous.setHasFixedSize(true);

            //Use this now
            recyclerViewRendeVous.addItemDecoration(new MaterialViewPagerHeaderDecorator());
         //   recyclerViewRendeVous.setAdapter(new TestRecyclerViewAdapter(response));


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


  private void  getRendeVousByManager() {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_RDV_BY_TEACHER, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
          //setup materialviewpager

          if (GRID_LAYOUT) {
            recyclerViewRendeVous.setLayoutManager(new GridLayoutManager(getActivity(), 2));
          } else {
            recyclerViewRendeVous.setLayoutManager(new LinearLayoutManager(getActivity()));
          }
          recyclerViewRendeVous.setHasFixedSize(true);

          //Use this now
          recyclerViewRendeVous.addItemDecoration(new MaterialViewPagerHeaderDecorator());
          recyclerViewRendeVous.setAdapter(new TeachersAdapter(response));


        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);

  }

}
