package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dz.easy.androidclient.App.BaseActivity;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Services.RegistrationIntentService;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.Util.SessionManager;
import dz.easy.androidclient.fragment.AbsenceFragment;
import dz.easy.androidclient.fragment.ModuleFragment;
import dz.easy.androidclient.fragment.RendeVousFragment;
import dz.easy.androidclient.fragment.RendezVousStatesFragment;
import dz.easy.androidclient.fragment.TimeTableFragment;

public class UserActivity extends BaseActivity implements IDialog{

    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;
    JSONObject user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        ButterKnife.bind(this);

        Intent i = new Intent(this , RegistrationIntentService.class);
        i.putExtra("DEVICE_ID" , "saloh");
        i.putExtra("DEVICE_NAME" , "bokota");
        startService(i);

        registerReceiver();

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("user");

        try {
            user = new JSONObject(jsonString);
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.createLoginSession(user.getString("name"),user.getString("mail"), user.getString("_id"));
            sessionManager.setUser(jsonString);
            Resources res = getResources();
            String text =  user.getString("lastname") + " " +  user.getString("name");

            ((TextView)findViewById(R.id.logo_white)).setText(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 3) {
                    case 0:
                        return ModuleFragment.newInstance(user);
                    case 1:
                        return RendezVousStatesFragment.newInstance(user);
                    case 2:
                        return TimeTableFragment.newInstance(user);
                    default:
                        return ModuleFragment.newInstance(user);
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 3) {
                    case 0:
                        return getResources().getString(R.string.NoteManager);
                    case 1:
                        return getResources().getString(R.string.RdvManager);
                    case 2:
                        return getResources().getString(R.string.TimetableManager);
                    case 3:
                        return  "Gestion .. ";

                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.green,
                            "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.blue,
                            "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.cyan,
                            "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                     case 3:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.red,
                            "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
