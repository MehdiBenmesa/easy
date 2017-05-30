package dz.easy.androidclient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.github.florent37.materialviewpager.MaterialViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import dz.easy.androidclient.App.BaseActivity;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.Util.SessionManager;
import dz.easy.androidclient.fragment.AbsenceDetailFragment;
import dz.easy.androidclient.fragment.AbsenceFragment;
import dz.easy.androidclient.fragment.ModuleDetailFragment;
import dz.easy.androidclient.fragment.NoteFragment;

import static android.R.id.tabs;


public class StudentNoteAbsenceActivity extends BaseActivity implements IDialog {

    private ViewPager pager;
    Context context = getApplication();
    String moduleid;
    String nomModul;
    JSONObject user;
    Intent i;
    private PagerSlidingTabStrip tabs;

    MaterialViewPager mViewPager;

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
        SessionManager sessionManager = new SessionManager(context);
        Intent intent = getIntent();
        try {
          user = new JSONObject(sessionManager.getUser());
        } catch (JSONException e) {
            e.printStackTrace();
        }
      JSONObject module = null;
      try {
        module = new JSONObject(intent.getStringExtra("module"));
        moduleid = module.getString("_id");
        nomModul = module.getString("name");
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

  @Override
  public void showDialog() {
    showpDialog();
  }

  @Override
  public void hideDialog() {
    hidepDialog();
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
