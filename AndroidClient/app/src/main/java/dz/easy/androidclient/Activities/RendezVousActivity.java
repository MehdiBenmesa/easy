package dz.easy.androidclient.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequest;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.Util.SessionManager;
import dz.easy.androidclient.fragment.DatePickerFragment;

import static dz.easy.androidclient.Constants.Constants.GET_TEACHERS;

public class RendezVousActivity extends AppCompatActivity implements Constants,TeachersAdapter.AdapterInterface {

  MaterialSearchView searcheView;
  RecyclerView lstView;

  private static final boolean GRID_LAYOUT = false;
  IDialog dialogListner;
  LovelyCustomDialog dialog;
  View addDialog;
  DatePickerDialog.OnDateSetListener ondate;
  Calendar calender = Calendar.getInstance();
  int year_x, month_x, day_x;

  JSONObject user = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rende_vous);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Chercher un enseignant");
    toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

    lstView = (RecyclerView) findViewById(R.id.lstView);
    /*ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstSource);
    lstView.setAdapter(adapter);*/

    SessionManager sessionManager = new SessionManager(this);
    try {
      user = new JSONObject(sessionManager.getUser());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    getTeachers();

    searcheView = (MaterialSearchView) findViewById(R.id.searche_view);
    searcheView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {

      }

      @Override
      public void onSearchViewClosed() {
        lstView = (RecyclerView) findViewById(R.id.lstView);
        /*ArrayAdapter adapter = new ArrayAdapter(RendezVousActivity.this, android.R.layout.simple_list_item_1, lstSource);
        lstView.setAdapter(adapter);*/
        //  getTeachers();
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
          getTeachersSearcheed(newText);
        } else {
          getTeachers();
        }
        return true;
      }
    });
  }

  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.searche_menu, menu);
    MenuItem item = menu.findItem(R.id.action_search);
    searcheView.setMenuItem(item);
    return true;
  }

  private void getTeachers() {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_TEACHERS, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          JSONArray teachers = response;

          //setup materialviewpager

          if (GRID_LAYOUT) {
            lstView.setLayoutManager(new GridLayoutManager(RendezVousActivity.this, 2));
          } else {
            lstView.setLayoutManager(new LinearLayoutManager(RendezVousActivity.this));
          }
          lstView.setHasFixedSize(true);

          //Use this now
          lstView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
          lstView.setAdapter(new TeachersAdapter(teachers, (TeachersAdapter.AdapterInterface) RendezVousActivity.this));


        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);

  }

  private void getTeachersSearcheed(final String newTextS) {

    CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_TEACHERS, null,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          JSONArray teachers = response;

          if (GRID_LAYOUT) {
            lstView.setLayoutManager(new GridLayoutManager(RendezVousActivity.this, 2));
          } else {
            lstView.setLayoutManager(new LinearLayoutManager(RendezVousActivity.this));
          }
          lstView.setHasFixedSize(true);

          //Use this now
          lstView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

          //Search action

          JSONArray lstFound = new JSONArray();
          JSONObject item = null;
          for (int i = 0; i < teachers.length(); i++) {
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

          lstView.setAdapter(new TeachersAdapter(lstFound, (TeachersAdapter.AdapterInterface) RendezVousActivity.this));


        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    App.getInstance().addToRequestQueue(jsonReq);

  }

  @Override
  public void buttonPressed(final JSONObject module) {

    addDialog = getLayoutInflater().inflate(R.layout.rdv_dialog, null);
    final TextView nom = (TextView) addDialog.findViewById(R.id.nomdialog);
    final TextView date = (TextView) addDialog.findViewById(R.id.date);
    final EditText heur = (EditText) addDialog.findViewById(R.id.heur);
    final EditText value = (EditText) addDialog.findViewById(R.id.value);

    dialog = new LovelyCustomDialog(this, R.style.EditTextTintTheme)
      .setTopColorRes(R.color.darkRed)
      .setView(addDialog)
      .setTitle(R.string.text_input_title)
      .setIcon(R.drawable.ic_add_alert_black_24dp)
      .setCancelable(false);
    dialog.show();
    Button ok = (Button) addDialog.findViewById(R.id.ok);
    Button annuler = (Button) addDialog.findViewById(R.id.cancel);

    date.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showDatePicker();
      }
    });
    ok.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        try {
          ajouterRdv(module.getString("_id"),date.getText().toString(),value.getText().toString());
        } catch (JSONException e) {
          e.printStackTrace();
        }
        dialog.dismiss();
      }
    });
    annuler.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog.dismiss();
      }
    });
    ondate = new DatePickerDialog.OnDateSetListener() {
      @RequiresApi(api = Build.VERSION_CODES.N)
      @Override
      public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear,
                            int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        date.setText(year + "-" + monthOfYear + 1 + "-" + dayOfMonth);
        calender.set(year, monthOfYear, dayOfMonth);
        int dayOfWeek = 0;
        dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
        System.out.println("DAY_OF_WEEK : +======== " + dayOfWeek);
        //studentsListAdapter.notifyDataSetChanged();

      }
    };

  }

  private void showDatePicker() {

    DatePickerFragment date = new DatePickerFragment();
    Bundle args = new Bundle();
    args.putInt("year", year_x);
    args.putInt("month", month_x);
    args.putInt("day", day_x);

    date.setArguments(args);

    date.setCallBack(ondate);
    date.show(getFragmentManager(), "Date Picker");
  }


  public void ajouterRdv(final String idTeacher,final String date, final String remarque){
    CustomRequest jsonReq = null;
    jsonReq = new CustomRequest(Request.Method.POST, POST_RDV , null,
      new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

        }
      }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        //hidepDialog();
      }
    }) {
      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("teacher", idTeacher);
        params.put("date", date);
        params.put("reason", remarque);

        try {
          params.put("student", user.getString("_id"));
        } catch (JSONException e) {
          e.printStackTrace();
        }
        return params;
      }
    };

    App.getInstance().addToRequestQueue(jsonReq);

  }
}
