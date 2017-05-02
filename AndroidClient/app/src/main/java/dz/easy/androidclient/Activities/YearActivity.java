package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import dz.easy.androidclient.Adapters.YearGridViewAdapter;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.Model.Groupe;
import dz.easy.androidclient.Model.Student;
import dz.easy.androidclient.Model.Year;
import dz.easy.androidclient.R;

public class YearActivity extends AppCompatActivity implements Constants{
    private static final String TAG = "YearActivity";

    private GridView gridView;
    private YearGridViewAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new YearGridViewAdapter(this, R.layout.fragment_item_layout_year, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Year item = (Year) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(YearActivity.this, StudentList.class);
                intent.putExtra("groupe", (Serializable) item.getGroup());
                intent.putExtra("group", item.getName()+"-"+ item.getSpec()+"-"+item.getGroupe());
                //intent.putExtra("image", item.getImage());
                //Start details activity
                startActivity(intent);
            }
        });
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<Year> getData() {

        final ArrayList<Year> yearItems = new ArrayList<>();
       // SendfeedbackJob job = new SendfeedbackJob();
       // job.execute();

        Student s = new Student("salah","boukh","179","homme","ds","02");
        Student s1 = new Student("salah","boukh","179","homme","ds","02");
        Student s2 = new Student("salah","boukh","179","homme","ds","02");

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(s);
        students.add(s1);
        students.add(s2);
   //    ArrayList<Groupe> groupes = new ArrayList<Groupe>();
        Groupe g1 = new Groupe(1);
        g1.setStudentList(students);
        final ArrayList<Year> data = new ArrayList<Year>();
        Year y = new Year("2CS1","SiL","G1");
        Year y1 = new Year("2CS","Siq","G1");
        Year y2 = new Year("2CS","SiQ","G2");
        Year y3 = new Year("2CS","SiT","G1");
        Year y4 = new Year("2CS","SiT","G2");
        y.setSectionList(students);
        y.setGroup(g1);
        // s.setImage(R.drawable.a);
        data.add(y);
        data.add(y1);
        data.add(y2);
        data.add(y3);
        data.add(y4);

        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barre_icone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_composant1:
                ShowFragment(0);
                break;
            case R.id.action_composant2:
                ShowFragment(1);
                break;
            case R.id.action_composant3:
                ShowFragment(2);
                break;
            case R.id.action_composant4:
                ShowFragment(3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ShowFragment(int id) {
        Fragment f = getFragment(id);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rel_container, f).commit();

    }

    private Fragment getFragment(int id) {
        return  null;
    }

    private class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(GET_ALL_GROUPS);

            try {
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("http response : "+statusCode);
                final String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject reader = new JSONObject(responseBody);
                //JSONObject groupe = reader.getJSONObject("1");

              //  Log.i(TAG, "Signed in as: " +groupe.getString("numero"));
            } catch (ClientProtocolException e) {
                Log.e(TAG, "Error sending ID token to backend.", e);
            } catch (IOException e) {
                Log.e(TAG, "Error sending ID token to backend.", e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "okauy";
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }
}
