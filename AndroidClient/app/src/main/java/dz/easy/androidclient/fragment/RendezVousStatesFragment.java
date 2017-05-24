package dz.easy.androidclient.fragment;

/**
 * Created by Abderahmane on 03/05/2017.
 */

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

        import org.json.JSONException;
        import org.json.JSONObject;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import dz.easy.androidclient.Adapters.RendezVousStatesAdapter;
        import dz.easy.androidclient.Adapters.TimeTableManagerAdapter;
        import dz.easy.androidclient.Adapters.TimeTableStudentAdapter;
        import dz.easy.androidclient.Adapters.TimeTableTeacherAdapter;
        import dz.easy.androidclient.Constants.Constants;
        import dz.easy.androidclient.R;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RendezVousStatesFragment extends Fragment implements Constants {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static JSONObject user;

    public static RendezVousStatesFragment newInstance(JSONObject managerData) {
        user = managerData;
        return new RendezVousStatesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        try {
            if(user.getString("_type").equals("Student")){
                mRecyclerView.setAdapter(new RendezVousStatesAdapter(user));
            }else {
                mRecyclerView.setAdapter(new RendezVousStatesAdapter(user));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }





}



