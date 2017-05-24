package dz.easy.androidclient.App;

/**
 * Created by Abderahmane on 05/04/2017.
 */
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;


public class BaseActivity extends AppCompatActivity implements Constants {

    public static final String TAG = "ActivityBase";

    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initpDialog();
    }




    protected void initpDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(false);
    }

    protected void showpDialog() {

        if (!pDialog.isShowing()) {

            try {

                pDialog.show();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) {

            try {

                pDialog.dismiss();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }



    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(App.REGISTRATION_PROCESS)){

                String result  = intent.getStringExtra("result");
                String message = intent.getStringExtra("message");
                Log.d(App.TAG, "onReceive: "+result+message);
                Toast.makeText(getBaseContext(),result + " : " + message,Toast.LENGTH_SHORT).show();
            } else if (intent.getAction().equals(App.MESSAGE_RECEIVED)){

                String message = intent.getStringExtra("message");
                showAlertDialog(message);
            }
        }
    };

    protected void registerReceiver(){

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(App.REGISTRATION_PROCESS);
        intentFilter.addAction(App.MESSAGE_RECEIVED);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private void showAlertDialog(String message){

        LovelyCustomDialog dialog =   new LovelyCustomDialog(this, R.style.EditTextTintTheme)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setTitle("Message Received !")
                .setMessage(message)
                .setIcon(R.drawable.ic_add_alert_black_24dp)
                .setCancelable(false);
        dialog.show();
    }

}
