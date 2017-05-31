package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.Model.OrderStatus;
import dz.easy.androidclient.Model.Orientation;
import dz.easy.androidclient.Model.TimeLineModel;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Adapters.TimeLineAdapter;
import dz.easy.androidclient.Util.CustomRequest;

import static dz.easy.androidclient.App.BaseActivity.TAG;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineActivity extends AppCompatActivity implements Constants {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Orientation mOrientation;
    private boolean mWithLinePadding;
    private JSONObject user ;
    private String  day ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

  /*      if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        mOrientation = Orientation.VERTICAL;
        mWithLinePadding = true;

        setTitle("Emploi de Temps");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        Intent i = getIntent();
        try {
            user  = new JSONObject(i.getStringExtra("user"));
            Log.i("user" , user.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (i.getStringExtra("day")){
            case "Dimanche" :
                day = "sunday";
                break;
            case "Lundi" :
                day = "monday";
                break;
            case "Mardi" :
                day = "tuesday";
                break;
            case "Mercredi" :
                day = "wednesday";
                break;
            case "Jeudi" :
                day = "thursday";
                break;
        };
        try {
            switch (user.getString("_type")){
                case "Teacher" :
                    initViewTeacher();
                    break;
                case "Student" :
                    initViewStudent();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    public void initViewStudent() {
        setDataListItemsStudent(new Itimeline() {
            @Override
            public void onDataRecieved(JSONObject object) {
                JSONArray dayJson = null;
                try {
                    dayJson = object.getJSONArray(day);
                    Log.i(TAG, "Signed in as TIME LIGNE: " + dayJson);
                    String heureDebut , heureFin  , type , prof , salle, module;
                    for (int i = 0; i < dayJson.length(); i++) {
                        JSONObject seance = dayJson.getJSONObject(i);
                        heureDebut = seance.getString("starts");
                        heureFin = seance.getString("ends");
                        type = seance.getString("type");
                        prof = seance.getJSONObject("teacher").getString("name");
                        salle = seance.getJSONObject("salle").getString("name");
                        module = seance.getJSONObject("module").getString("name");
                      Log.i("Signed in aaas" ,heureDebut + heureFin + type );
                        mDataList.add(new TimeLineModel("Mr :" + prof + " , " + "Salle :" + salle + " , " +type + "\n" + "Module : "+module, heureDebut +" -- " +  heureFin , OrderStatus.INACTIVE));

                    }

                    mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
                mRecyclerView.setAdapter(mTimeLineAdapter);
            } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    });
    }

    public void initViewTeacher() {
        setDataListItemsTeacher(new Itimeline() {
            @Override
            public void onDataRecieved(JSONObject object) {
                JSONArray dayJson = null;
                try {
                    dayJson = object.getJSONArray(day);
                    Log.i(TAG, "Signed in as TIME LIGNE: " + dayJson);
                    String heureDebut , heureFin  , type , prof , salle, module;
                    for (int i = 0; i < dayJson.length(); i++) {
                        JSONObject seance = dayJson.getJSONObject(i);
                        heureDebut = seance.getString("starts");
                        heureFin = seance.getString("ends");
                        type = seance.getString("type");
                        prof = seance.getJSONObject("teacher").getString("name");
                        salle = seance.getJSONObject("salle").getString("name");
                        module = seance.getJSONObject("module").getString("name");
                        Log.i("Signed in aaas" ,heureDebut + heureFin + type );
                        mDataList.add(new TimeLineModel("Mr :" + prof + " , " + "Salle :" + salle + " , " +type + "\n" + "Module : "+module , heureDebut +" -- " +  heureFin , OrderStatus.INACTIVE));

                    }

                    mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
                    mRecyclerView.setAdapter(mTimeLineAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setDataListItemsStudent(final Itimeline callback){
        try {
            CustomRequest jsonReq = new CustomRequest(Request.Method.GET, GET_TIME_TABLE_STUDENT + "/" + user.getString("section") + "/" + user.getString("groupe"), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                                callback.onDataRecieved(response);
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

    public void setDataListItemsTeacher(final Itimeline callback){
        try {
            CustomRequest jsonReq = new CustomRequest(Request.Method.GET, GET_TIME_TABLE_TEACHER + "/" + user.getString("_id") , null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onDataRecieved(response);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public interface Itimeline {
        public void onDataRecieved(JSONObject object);
    }

}
