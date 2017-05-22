package dz.easy.androidclient.fragment;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.annotation.RequiresApi;
        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.StaggeredGridLayoutManager;
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
        import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import dz.easy.androidclient.Adapters.GroupeListAdapter;
        import dz.easy.androidclient.Adapters.ModuleListAdapter;
        import dz.easy.androidclient.App.App;
        import dz.easy.androidclient.Constants.Constants;
        import dz.easy.androidclient.Model.Module;
        import dz.easy.androidclient.R;
        import dz.easy.androidclient.TestRecyclerViewAdapter;
        import dz.easy.androidclient.Util.CustomRequest;
        import dz.easy.androidclient.Util.CustomRequestArray;
        import dz.easy.androidclient.Util.SessionManager;

        import static dz.easy.androidclient.App.BaseActivity.TAG;


/**
 * Created by Abderahmane on 10/04/2017.
 */

public class GroupeByModuleFragment extends Fragment implements Constants {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "user";
    private final Context context = getActivity();
    /**
     * The dummy content of this fragment.
     */

    static JSONObject module;
    public static String userID="";
    public static String moduleID="";
    public static String nomModule="";

    @BindView(R.id.backdrop)
    ImageView backdropImg;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.uploadToDrive)
    FloatingActionButton uploadToDrive;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    private static final boolean GRID_LAYOUT = false;


    TextView exam;
    TextView controle;
    TextView intero;
    TextView tp;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(getContext());
        userID = sessionManager.getIdUser();
        //String Module = sessionManager


        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            //accident = AppContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
        //Toast.makeText(getContext() , moduleID , Toast.LENGTH_LONG).show();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_recyclerview , container,false);
        //Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
       // toolbar.setTitle(nomModule);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       //    ButterKnife.bind(this, view);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getStudentsByModule();
    }

/*
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
    }*/

    public static GroupeByModuleFragment newInstance(String nomModul,String moduleid,String user) {
        GroupeByModuleFragment fragment = new GroupeByModuleFragment();
        Bundle args = new Bundle();
        args.putString(GroupeByModuleFragment.ARG_ITEM_ID, user);
        try {
            module = new JSONObject(user);
            moduleID = moduleid;
            nomModule = nomModul;
            //userID = sessionManager.getIdUser();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        return fragment;
    }
    public GroupeByModuleFragment() {
    }


    private void getStudentsByModule() {

        CustomRequestArray jsonReq = new CustomRequestArray(Request.Method.GET, GET_GROUPE_BY_MODULE + "/" + moduleID + "/" + userID , null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "Réponse de Notes : " + response);

                        JSONArray specs;
                        JSONArray sections;
                        JSONObject groupe;
                        JSONArray groupes = new JSONArray();
                        try {
                            for(int i=0; i<response.length();i++){
                                specs = response.getJSONObject(i).getJSONArray("sections");
                                for(int j=0;j<specs.length();j++){
                                    sections = specs.getJSONObject(j).getJSONArray("groupes");
                                    for(int k=0;k<sections.length();k++){
                                        groupe = sections.getJSONObject(k);
                                        groupes.put(groupe);
                                    }
                                }
                            }
                            Log.i(TAG, "La liste des groupes ========================== : :   " + groupes);
                            if (GRID_LAYOUT) {
                            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        } else {
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        }
                        mRecyclerView.setHasFixedSize(true);

                        //Use this now
                        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                        mRecyclerView.setAdapter(new GroupeListAdapter(groupes));
                        //  JSONArray modules = groupe.getJSONArray("module");
                        //    Log.i(TAG, "Notes : " + groupe);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        App.getInstance().addToRequestQueue(jsonReq);

    }
}
