package dz.easy.androidclient.Services;
import android.app.Application;
import java.net.URISyntaxException;

import dz.easy.androidclient.Constants.Constants;
import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketActivity extends Application {

        private Socket mSocket;
        {
            try {
                mSocket = IO.socket(Constants.SERVER_URL);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        public Socket getSocket() {
            return mSocket;
        }

}
