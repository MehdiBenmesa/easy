package dz.easy.androidclient;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Mon pc on 26/03/2017.
 */

public class NoteActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_note);
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_barre, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
/*
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
*/
            return super.onOptionsItemSelected(item);
        }


}
