package dz.easy.androidclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.Adapters.NoteAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Services.DataReceiver;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;

import static dz.easy.androidclient.Services.GroupService.GET_GROUP_MODULE_TEACHER;
import static dz.easy.androidclient.Services.NoteService.GET_NOTE_STUDENT_MODULE;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class NoteFragment extends Fragment implements Constants, DataReceiver.Receiver {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    public static String userID="";
    public static String moduleID="";
    public static String nomModule="";
    IDialog dialogListner ;
    DataReceiver mReceiver ;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static JSONObject user;

    public static NoteFragment newInstance(JSONObject managerData,String nomModul,String moduleid) {
        user = managerData;
        moduleID = moduleid;
        nomModule = nomModul;
        return new NoteFragment();
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

      getNoteByStudent();
    }

    public void getNoteByStudent() {
        try {
            System.out.println("AVANT ABSENCE 2 ");
            CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_NOTE_BY_MODULES+ "/" + user.getString("_id") + "/"+moduleID, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (GRID_LAYOUT) {
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            } else {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                            mRecyclerView.setHasFixedSize(true);
                            //Use this now
                            mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                            mRecyclerView.setAdapter(new NoteAdapter(response));
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
  public void onReceiveResult(int resultCode, Bundle resultData) {
    switch (resultCode) {
      case STATUS_RUNNING:
        dialogListner.showDialog();
        break;
      case STATUS_FINISHED:
          /* Hide progress & extract result from bundle */
        dialogListner.hideDialog();
        switch (resultData.getString("action")){
          case GET_NOTE_STUDENT_MODULE :
            String jsonString = resultData.getString("result");
            JSONArray response = null;
            try {
              response = new JSONArray(jsonString);
              mRecyclerView.setAdapter(new NoteAdapter(response));
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





