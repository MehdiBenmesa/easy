package dz.easy.androidclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import dz.easy.androidclient.Adapters.NoteAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequestArray;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class NoteFragment extends Fragment implements Constants {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    public static String userID="";
    public static String moduleID="";
    public static String nomModule="";

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
}





