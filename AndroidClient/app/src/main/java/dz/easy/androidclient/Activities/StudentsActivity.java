package dz.easy.androidclient.Activities;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.AttributeSet;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import com.astuetz.PagerSlidingTabStrip;
        import com.miguelcatalan.materialsearchview.MaterialSearchView;

        import org.json.JSONObject;

        import dz.easy.androidclient.App.BaseActivity;
        import dz.easy.androidclient.R;
        import dz.easy.androidclient.Util.IDialog;
        import dz.easy.androidclient.fragment.StudentsFragmentAbsence;
        import dz.easy.androidclient.fragment.StudentsFragmentNote;


public class StudentsActivity extends BaseActivity implements IDialog{

    private ViewPager pager;

    MaterialSearchView searcheView;

    Context context = getApplication();
    String moduleid;
    String nomModul;
    JSONObject user;
    String stringuser;
    String stringstudents;
    String stringModule;

    private PagerSlidingTabStrip tabs;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chercher un enseignant");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        Intent i = getIntent();
        stringuser = i.getStringExtra("user");
        stringstudents = i.getStringExtra("students");
        stringModule = i.getStringExtra("module");
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
                    return new StudentsFragmentNote().newInstance(stringuser,stringstudents,stringModule);
                case 1 :
                    return new StudentsFragmentAbsence().newInstance(stringuser,stringstudents,stringModule);
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

    @Override
    public void showDialog() {
        showpDialog();
    }

    @Override
    public void hideDialog() {
        hidepDialog();
    }

}