package dz.easy.androidclient.Services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Abderahmane on 24/05/2017.
 */

public class DataReceiver extends ResultReceiver {
  private Receiver mReceiver;

  public DataReceiver(Handler handler) {
    super(handler);
  }

  public void setReceiver(Receiver receiver) {
    mReceiver = receiver;
  }


  @Override
  protected void onReceiveResult(int resultCode, Bundle resultData) {
    if (mReceiver != null) {
      mReceiver.onReceiveResult(resultCode, resultData);
    }
  }

  public interface Receiver {
    public void onReceiveResult(int resultCode, Bundle resultData);
  }
}
