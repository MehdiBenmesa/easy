package dz.easy.androidclient.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dz.easy.androidclient.Adapters.TeachersAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.CustomRequestArray;

import static dz.easy.androidclient.Constants.Constants.GET_TEACHERS;

public class RendezVousActivity extends AppCompatActivity {

  MaterialSearchView searcheView;
  RecyclerView lstView;

  private static final boolean GRID_LAYOUT = false;

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

//    getTeachers();

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
        //  getTeachersSearcheed(newText);
        }
        else {
          //getTeachers();
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



}
