package dz.easy.androidclient.App;

/**
 * Created by Abderahmane on 05/04/2017.
 */
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

}
