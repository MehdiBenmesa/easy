package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import dz.easy.androidclient.App.BaseActivity;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.fragment.ModuleDetailFragment;

/**
 * Created by Abderahmane on 10/04/2017.
 */
public class ModuleActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // Show the Up button in the action bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent i = getIntent();
        String moduleid = i.getStringExtra("module");
        String nomModul = i.getStringExtra("namemodule");
        if(i.hasExtra(ModuleDetailFragment.ARG_ITEM_ID)){
            //Fragment fragment =  ModuleDetailFragment.newInstance(nomModul,moduleid,i.getStringExtra(ModuleDetailFragment.ARG_ITEM_ID));
            //getSupportFragmentManager().beginTransaction().add(R.id.module_detail_container , fragment).commit();
        }

    }
}
