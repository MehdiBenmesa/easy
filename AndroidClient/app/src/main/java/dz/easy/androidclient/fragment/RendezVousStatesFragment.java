package dz.easy.androidclient.fragment;

/**
 * Created by Abderahmane on 03/05/2017.
 */

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.annotation.RequiresApi;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
        import com.yarolegovich.lovelydialog.LovelyCustomDialog;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.Calendar;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import dz.easy.androidclient.Activities.RendezVousActivity;
        import dz.easy.androidclient.Activities.RendezVousActivity_;
        import dz.easy.androidclient.Adapters.RendezVousStatesAdapter;
        import dz.easy.androidclient.Adapters.TimeTableManagerAdapter;
        import dz.easy.androidclient.Adapters.TimeTableStudentAdapter;
        import dz.easy.androidclient.Adapters.TimeTableTeacherAdapter;
        import dz.easy.androidclient.Constants.Constants;
        import dz.easy.androidclient.R;
        import dz.easy.androidclient.Util.IDialog;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RendezVousStatesFragment extends Fragment implements Constants, RendezVousStatesAdapter.AdapterInterface {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    IDialog dialogListner;
    LovelyCustomDialog dialog;
    View addDialog ;
    DatePickerDialog.OnDateSetListener ondate;
    Calendar calender = Calendar.getInstance();
    int year_x, month_x, day_x;
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
                mRecyclerView.setAdapter(new RendezVousStatesAdapter(user,(RendezVousStatesAdapter.AdapterInterface) this));
            }else {
                mRecyclerView.setAdapter(new RendezVousStatesAdapter(user,(RendezVousStatesAdapter.AdapterInterface) this));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

  @Override
  public void buttonPressed(String etat){
    Intent i = null;
    if(etat.equals("new")){

  /*
      addDialog = getActivity().getLayoutInflater().inflate(R.layout.rdv_dialog, null);
      final TextView nom = (TextView) addDialog.findViewById(R.id.nomdialog);
      final TextView date = (TextView) addDialog.findViewById(R.id.date);
      final EditText heur = (EditText) addDialog.findViewById(R.id.heur);
      final EditText value = (EditText) addDialog.findViewById(R.id.value);

      dialog = new LovelyCustomDialog(getContext(), R.style.EditTextTintTheme)
        .setTopColorRes(R.color.darkRed)
        .setView(addDialog)
        .setTitle(R.string.text_input_title)
        .setIcon(R.drawable.ic_add_alert_black_24dp)
        .setCancelable(false);
      dialog.show();
      Button ok = (Button) addDialog.findViewById(R.id.ok);
      Button annuler = (Button) addDialog.findViewById(R.id.cancel);

      date.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          showDatePicker();
        }
      });
      ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          dialog.dismiss();
        }
      });
      annuler.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          dialog.dismiss();
        }
      });
      ondate = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
          monthOfYear = monthOfYear + 1;
          date.setText(year + "-" + monthOfYear+1 + "-" +dayOfMonth);
          calender.set(year,monthOfYear,dayOfMonth);
          int dayOfWeek= 0;
          dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
          System.out.println("DAY_OF_WEEK : +======== "  + dayOfWeek );
          //studentsListAdapter.notifyDataSetChanged();

        }
      };
    */
      i = new Intent(getContext(), RendezVousActivity.class);
    }else {
      i = new Intent(getContext(), RendezVousActivity_.class);
    }
    i.putExtra("user", user.toString());
    i.putExtra("state", etat);
    startActivity(i);

  }

  private void showDatePicker() {

    DatePickerFragment date = new DatePickerFragment();
    Bundle args = new Bundle();
    args.putInt("year", year_x);
    args.putInt("month", month_x);
    args.putInt("day", day_x);

    date.setArguments(args);

    date.setCallBack(ondate);
    date.show(getActivity().getFragmentManager(), "Date Picker");
  }
}



