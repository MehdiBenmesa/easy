package dz.easy.androidclient.Activities;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import dz.easy.androidclient.R;

public class RendeVous extends AppCompatActivity {

  MaterialSearchView searcheView;

  ListView lstView;
  String[] lstSource = {
    "Harry",
    "Ron",
    "Hermione",
    "Snape",
    "Dembeldor"
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rende_vous);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Chercher un enseignant");
    toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

    lstView = (ListView) findViewById(R.id.lstView);
    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstSource);
    lstView.setAdapter(adapter);

    searcheView = (MaterialSearchView) findViewById(R.id.searche_view);

    searcheView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {

      }

      @Override
      public void onSearchViewClosed() {
        lstView = (ListView) findViewById(R.id.lstView);
        ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstSource);
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

          ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstFound);
          lstView.setAdapter(adapter);
        }
        else {
          ArrayAdapter adapter = new ArrayAdapter(RendeVous.this, android.R.layout.simple_list_item_1, lstSource);
          lstView.setAdapter(adapter);
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
