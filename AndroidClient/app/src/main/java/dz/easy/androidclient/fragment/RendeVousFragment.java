package dz.easy.androidclient.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequestArray;

import static dz.easy.androidclient.Constants.Constants.GET_TEACHERS;

public class RendeVousFragment extends Fragment {


  MaterialSearchView searcheView;

  RecyclerView lstView;

  private static final boolean GRID_LAYOUT = false;

  private OnFragmentInteractionListener mListener;

  public RendeVousFragment() {
    // Required empty public constructor
  }

  private static  JSONObject user;

  // TODO: Rename and change types and number of parameters
  public static RendeVousFragment newInstance(JSONObject managerData) {
    user = managerData;
    return new  RendeVousFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_rende_vous, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar3);
    ((ActionBarActivity)getActivity()).setSupportActionBar(toolbar);
    ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Chercher un enseignant");
    toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

    lstView = (RecyclerView) getActivity().findViewById(R.id.lstView);
    /*ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstSource);
    lstView.setAdapter(adapter);*/

    getTeachers();

    searcheView = (MaterialSearchView) getActivity().findViewById(R.id.searche_view);

    searcheView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {

      }

      @Override
      public void onSearchViewClosed() {
        lstView = (RecyclerView) getActivity().findViewById(R.id.lstView);
        /*ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstSource);
        lstView.setAdapter(adapter);*/
        getTeachers();
      }
    });

    searcheView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (newText != null && !newText.isEmpty()) {
          /*List<String> lstFound = new ArrayList<String>();
          for (String item:lstSource) {
            if (item.contains(newText)) {
              lstFound.add(item);
            }
          }

          ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstFound);
          //lstView.setAdapter(adapter);*/

          getTeachersSearcheed(newText);
        }
        else {
          /*ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstSource);
          //lstView.setAdapter(adapter);*/
          getTeachers();
        }
        return true;
      }
    });
  }



  public boolean onCreateOptionsMenu(Menu menu) {
    getActivity().getMenuInflater().inflate(R.menu.searche_menu, menu);
    MenuItem item = menu.findItem(R.id.action_search);
    searcheView.setMenuItem(item);
    return true;
  }

  private void  getTeachers() {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_TEACHERS , null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          JSONArray teachers = response;

          //setup materialviewpager

          if (GRID_LAYOUT) {
            lstView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
          } else {
            lstView.setLayoutManager(new LinearLayoutManager(getActivity()));
          }
          lstView.setHasFixedSize(true);

          //Use this now
          lstView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
          lstView.setAdapter(new TeachersAdapter(teachers));


        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);

  }

  private void  getTeachersSearcheed(final String newTextS) {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_TEACHERS , null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          JSONArray teachers = response;

          if (GRID_LAYOUT) {
            lstView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
          } else {
            lstView.setLayoutManager(new LinearLayoutManager(getActivity()));
          }
          lstView.setHasFixedSize(true);

          //Use this now
          lstView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

          //Search action

          JSONArray lstFound = new JSONArray();
          JSONObject item = null;
          for (int i =0 ; i < teachers.length() ; i++) {
            try {
              item = teachers.getJSONObject(i);
            } catch (JSONException e) {
              e.printStackTrace();
            }
            try {
              if (item.getString("name").contains(newTextS) || item.getString("lastname").contains(newTextS)) {
                lstFound.put(item);
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }

          /*ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstFound);
          lstView.setAdapter(adapter);*/

          lstView.setAdapter(new TeachersAdapter(lstFound));


        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);

  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
        + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}

