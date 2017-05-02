package dz.easy.androidclient.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;


/**
 * Created by Abderahmane on 10/04/2017.
 */

public class ModuleDetailFragment extends Fragment implements Constants {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "user";
    /**
     * The dummy content of this fragment.
     */

    static JSONObject module;

    @BindView(R.id.quote)
    TextView quote;

    @BindView(R.id.content)
    TextView content;


    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.backdrop)
    ImageView backdropImg;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.uploadToDrive)
    FloatingActionButton uploadToDrive;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            //accident = AppContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
        Toast.makeText(getContext() , module.toString() , Toast.LENGTH_LONG).show();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_article_detail , container);


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

    public static ModuleDetailFragment newInstance(String user) {
        ModuleDetailFragment fragment = new ModuleDetailFragment();
        Bundle args = new Bundle();
        args.putString(ModuleDetailFragment.ARG_ITEM_ID, user);
        try {
            module = new JSONObject(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        return fragment;
    }

    public ModuleDetailFragment() {

    }





}
