package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import dz.easy.androidclient.App.BaseActivity;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.fragment.GroupFragment;

public class GroupActivity extends BaseActivity implements IDialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_);
        Intent i = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.groupFragment , new GroupFragment().newInstance(this,i.getStringExtra("module")) )
                .commit();
    }

    @Override
    public void showDialog() {
        showpDialog();
    }

    @Override
    public void hideDialog() {
        hidepDialog();
    }

}
