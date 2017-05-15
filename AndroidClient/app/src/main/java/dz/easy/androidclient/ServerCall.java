package dz.easy.androidclient;

import android.os.AsyncTask;

/**
 * Created by Mon pc on 04/04/2017.
 */

class ServerCall extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        return "some message";
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
    }
}