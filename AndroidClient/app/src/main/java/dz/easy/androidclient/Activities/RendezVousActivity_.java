package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.fragment.ModuleDetailFragment;
import dz.easy.androidclient.fragment.RendeVousFragment;
import dz.easy.androidclient.fragment.RendezVousFragment;

/**
 * Created by Mon pc on 20/05/2017.
 */

public class RendezVousActivity_ extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.i("Rendez vous Activity ","Welconme _______ ");
        // Show the Up button in the action bar.
        Intent i = getIntent();
        String state = i.getStringExtra("state");
        String userstring = i.getStringExtra("user");

        JSONObject user = null;

        try {
            user = new JSONObject(userstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Rendez vous Activity ",user.toString());
            Fragment fragment =  RendezVousFragment.newInstance(user,state);
            getSupportFragmentManager().beginTransaction().add(R.id.module_detail_container , fragment).commit();

    }
}
