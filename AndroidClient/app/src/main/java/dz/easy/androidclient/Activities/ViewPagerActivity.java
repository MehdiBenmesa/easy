package dz.easy.androidclient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONException;
import org.json.JSONObject;

import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.SessionManager;
import dz.easy.androidclient.fragment.AbsenceDetailFragment;
import dz.easy.androidclient.fragment.AbsenceFragment;
import dz.easy.androidclient.fragment.ModuleDetailFragment;
import dz.easy.androidclient.fragment.NoteFragment;

import static android.R.id.tabs;


public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager pager;
    Context context = getApplication();
    String moduleid;
    String nomModul;
    JSONObject user;
    Intent i;
    private PagerSlidingTabStrip tabs;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);


        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("user");
        System.out.println("USER :====== ! "+ jsonString);
        try {
            user = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("USER :====== ! "+ user);
        //SessionManager sessionManager = new SessionManager(context);

        moduleid = intent.getStringExtra("module");
        nomModul = intent.getStringExtra("namemodule");
        try {
            user = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    public class MyAdapter extends FragmentPagerAdapter {
        private String[] titles = { "Notes",
                "Absences" };

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){

                case 0 :
                    return NoteFragment.newInstance(user,nomModul,moduleid);
                case 1 :
                    return AbsenceFragment.newInstance(user,nomModul,moduleid);
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
