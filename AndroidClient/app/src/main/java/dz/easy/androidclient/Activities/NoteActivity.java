package dz.easy.androidclient.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import dz.easy.androidclient.R;
import dz.easy.androidclient.Services.SocketActivity;
import io.socket.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Mon pc on 26/03/2017.
 */

public class NoteActivity extends Activity {

    private io.socket.client.Socket mSocket;
    private Emitter.Listener getData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            System.out.println("callback from the server !! ");
        }
    };
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_note);

            findViewById(R.id.socketstest).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SocketActivity app = new SocketActivity();
                    mSocket = app.getSocket();
                    mSocket.connect();
  //                  mSocket.emit("getD", "salah .. mchat wa9ill ? ");
//                    mSocket.on("getData", getData);
                    mSocket.emit("sendToken", "Token ;) ");
                    mSocket.on("login", onLogin);

                }
            });

       // s.setImage(R.drawable.a);
        //data.add(y);

       // GridView listView = (GridView) findViewById(R.id.listV);
     //   listView.setAdapter(new YearAdapter(this, data));

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
    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            try {
                System.out.println("callback from the server !! "+data.getString("_id"));
                System.out.println("callback from the server !! "+data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}
