package dz.easy.androidclient.fragment;
/**
* Created by Mon pc on 05/05/2017.
*/



import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import dz.easy.androidclient.Activities.GroupActivity;
import dz.easy.androidclient.Activities.StudentNoteAbsenceActivity;
import dz.easy.androidclient.Adapters.GroupListAdapter;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Services.AbsenceService;
import dz.easy.androidclient.Services.DataReceiver;
import dz.easy.androidclient.Util.CustomRequestArray;
import dz.easy.androidclient.Util.IDialog;
import dz.easy.androidclient.Util.SessionManager;

import static dz.easy.androidclient.App.BaseActivity.TAG;
import static dz.easy.androidclient.Services.AbsenceService.GET_ABSENCE_STUDENT;
import static dz.easy.androidclient.Services.GroupService.GET_GROUP_MODULE_TEACHER;


/**
 * Created by Abderahmane on 10/04/2017.
 */
    public class AbsenceDetailFragment extends Fragment implements Constants, DataReceiver.Receiver {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "user";
    /**
     * The dummy content of this fragment.
     */
    static JSONObject module;
    public static String userID="";
    public static String moduleID="";
    public static String nomModule="";
    private DataReceiver mReceiver ;
  private IDialog dialogListner ;

    @BindView(R.id.backdrop)
    ImageView backdropImg;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.uploadToDrive)
    FloatingActionButton uploadToDrive;


    TextView exam;
    TextView controle;
    TextView intero;
    TextView tp;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exam = (TextView) getActivity().findViewById(R.id.exam);
        controle = (TextView) getActivity().findViewById(R.id.control);
        intero = (TextView) getActivity().findViewById(R.id.intero);
        tp = (TextView) getActivity().findViewById(R.id.tps);
        mReceiver = new DataReceiver(new Handler());
        mReceiver.setReceiver(this);

        SessionManager sessionManager = new SessionManager(getContext());
        userID = sessionManager.getIdUser();
        //String Module = sessionManager
      AbsenceService.getAbsenceByStudent(getContext() , mReceiver);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            //accident = AppContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
        //Toast.makeText(getContext() , moduleID , Toast.LENGTH_LONG).show();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_article_detail , container,false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(nomModule);
        return rootView;
    }

    private void loadBackdrop() {
        Glide.with(this).load("").centerCrop().into(backdropImg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sample_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    public static ModuleDetailFragment newInstance(String nomModul, String moduleid, String user) {
        ModuleDetailFragment fragment = new ModuleDetailFragment();
        Bundle args = new Bundle();
        args.putString(ModuleDetailFragment.ARG_ITEM_ID, user);
        try {
            module = new JSONObject(user);
            moduleID = moduleid;
            nomModule = nomModul;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        return fragment;
    }
    public AbsenceDetailFragment() {
    }
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    dialogListner = (StudentNoteAbsenceActivity) activity ;
  }

  @Override
  public void onReceiveResult(int resultCode, Bundle resultData) {
    switch (resultCode) {
      case STATUS_RUNNING:
        dialogListner.showDialog();
        break;
      case STATUS_FINISHED:
          /* Hide progress & extract result from bundle */
        dialogListner.hideDialog();
        switch (resultData.getString("action")){
          case GET_ABSENCE_STUDENT :
            String jsonString = resultData.getString("result");
            JSONArray response = null;
            try {
              response = new JSONArray(jsonString);
               Log.i(TAG, "Réponse de Notes : " + response);

                for (int i=0;i<response.length();i++){
                  try {
                    JSONObject note = response.getJSONObject(i);
                    if(moduleID.equals(note.getString("module"))){
                      if(note.getString("reason").equals("exam")){
                        exam.setText(note.getInt("value")+"");
                      }
                      if(note.getString("reason").equals("controle")){
                        controle.setText(note.getInt("value")+"");
                      }
                      if(note.getString("reason").equals("intero")){
                        intero.setText(note.getInt("value")+"");
                      }
                      if(note.getString("reason").equals("tp")){
                        tp.setText(note.getInt("value")+"");
                      }
                    }
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }

                }

            } catch (JSONException e) {
              e.printStackTrace();
            }
            break ;
        }

        break;
      case STATUS_ERROR:
                  /* Handle the error */
        String error = resultData.getString(Intent.EXTRA_TEXT);
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
        break;
    }
  }
}

