package dz.easy.androidclient.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import dz.easy.androidclient.R;

public class RendeVousFragment extends Fragment {

  MaterialSearchView searcheView;

  ListView lstView;
  String[] lstSource = {
    "Harry",
    "Ron",
    "Hermione",
    "Snape",
    "Dembeldor"
  };

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

    try {
      if(user.getString("_type").equals("Teacher")){
        //Toast.makeText(getContext() , "Hi Teacher" , Toast.LENGTH_LONG).show();

      }else if (user.getString("_type").equals("Manager")){
        //Toast.makeText(getContext() , "Hi Manager" , Toast.LENGTH_LONG).show();

      }else if (user.getString("_type").equals("Student")){
        //Toast.makeText(getContext() , "Hi Student" , Toast.LENGTH_LONG).show();
        RdvStudent();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void RdvStudent() {
    Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar3);
    ((ActionBarActivity)getActivity()).setSupportActionBar(toolbar);
    ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("Chercher un enseignant");
    toolbar.setTitleTextColor(Color.parseColor("FFFFFF"));

    lstView = (ListView) getActivity().findViewById(R.id.lstView);
    ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lstSource);
    lstView.setAdapter(adapter);

    searcheView = (MaterialSearchView) getActivity().findViewById(R.id.searche_view);

    searcheView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {

      }

      @Override
      public void onSearchViewClosed() {
        lstView = (ListView) getActivity().findViewById(R.id.lstView);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lstSource);
        lstView.setAdapter(adapter);
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
          List<String> lstFound = new ArrayList<String>();
          for (String item:lstSource) {
            if (item.contains(newText)) {
              lstFound.add(item);
            }
          }

          ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lstFound);
          lstView.setAdapter(adapter);
        }
        else {
          ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lstSource);
          lstView.setAdapter(adapter);
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
