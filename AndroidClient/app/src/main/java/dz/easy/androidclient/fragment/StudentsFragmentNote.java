    package dz.easy.androidclient.fragment;

    import android.app.Activity;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.design.widget.Snackbar;
    import android.support.v4.app.Fragment;
    import android.support.v7.widget.GridLayoutManager;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.ViewTreeObserver;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.android.volley.Request;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.jaredrummler.materialspinner.MaterialSpinner;
    import com.yarolegovich.lovelydialog.LovelyCustomDialog;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    import butterknife.BindView;
    import butterknife.ButterKnife;
    import dz.easy.androidclient.Activities.GroupActivity;
    import dz.easy.androidclient.Adapters.ModuleListAdapter;
    import dz.easy.androidclient.Adapters.StudentsListNoteAdapter;
    import dz.easy.androidclient.App.App;
    import dz.easy.androidclient.Constants.Constants;
    import dz.easy.androidclient.R;
    import dz.easy.androidclient.Util.CustomRequest;
    import dz.easy.androidclient.Util.IDialog;

    /**
     * Created by florentchampigny on 24/04/15.
     */
    public class StudentsFragmentNote extends Fragment implements Constants, StudentsListNoteAdapter.AdapterInterface {

        private static final boolean GRID_LAYOUT = false;
        private static final int ITEM_COUNT = 100;
        private MaterialSpinner spinner;
        StudentsListNoteAdapter studentsListNoteAdapter = null;
        @BindView(R.id.recyclerView)
        RecyclerView mRecyclerView;

        private Button valider;

        IDialog dialogListner;
        LovelyCustomDialog dialog;
        View addDialog ;


        private static  JSONObject user;
        private static  JSONArray students;
        private static JSONObject module;
        public static StudentsFragmentNote newInstance(String user_, String student, String module_) {

            try {
                user = new JSONObject(user_);
                students = new JSONArray(student);
                module = new JSONObject(module_);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new StudentsFragmentNote();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_student_note, container, false);

            return view;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.bind(this, view);
            addItemsOnSpinner();
            if (GRID_LAYOUT) {
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            } else {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            mRecyclerView.setHasFixedSize(true);
            //Use this now

            try {
                studentsListNoteAdapter = new StudentsListNoteAdapter(students ,module.getString("_id"), (StudentsListNoteAdapter.AdapterInterface) StudentsFragmentNote.this);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            mRecyclerView.setAdapter(studentsListNoteAdapter);
/*
            mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    System.out.println("VALIER /// 2 ");
                    StudentsListNoteAdapter.MyViewHolder child;
                    ArrayList<StudentsListNoteAdapter.MyViewHolder> myList = studentsListNoteAdapter.getMyList();
                    Toast.makeText(getContext() , "Hello From Module Fragment "+ myList.size() +" hihi " , Toast.LENGTH_LONG).show();
                    for (int i = 0; i < myList.size(); i++) {
                        child = myList.get(i);
                        Toast.makeText(getContext() , child.title.getText() , Toast.LENGTH_LONG).show();
                    }
                }
            });*/

            valider = (Button) view.findViewById(R.id.valider);
            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("VALIER /// ");
                    try {
                        studentsListNoteAdapter.updateNotes(getActivity(),module.getString("_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            //mRecyclerView.
        }
        public void startFragment() {
                          // JSONObject modules = response.getJSONObject("course");
        }


        @Override
        public void buttonPressed(final JSONObject student, final JSONArray notes) throws JSONException {

            addDialog = getActivity().getLayoutInflater().inflate(R.layout.adddialog, null);
            final TextView nom = (TextView) addDialog.findViewById(R.id.nomdialog);
            final Spinner etat = (Spinner) addDialog.findViewById(R.id.okdialog);
            final EditText value = (EditText) addDialog.findViewById(R.id.value);
            ArrayAdapter adap = ArrayAdapter.createFromResource(getContext(),
                    R.array.snipperdialog, R.layout.item_spinner);
            etat.setAdapter(adap);
            nom.setText(student.getString("name") + " " + student.getString("lastname"));
            dialog = new LovelyCustomDialog(getContext(), R.style.EditTextTintTheme)
                    .setTopColorRes(R.color.darkRed)
                    .setView(addDialog)
                    .setTitle(R.string.text_input_title)
                    .setIcon(R.drawable.ic_add_alert_black_24dp)
                    .setCancelable(false);
            dialog.show();
            Button ok = (Button) addDialog.findViewById(R.id.ok);
            Button annuler = (Button) addDialog.findViewById(R.id.cancel);
            etat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    String item = adapterView.getItemAtPosition(position).toString();
                    value.setText("");
                    try{
                        for (int i =0;i<notes.length();i++) {
                            JSONObject note = notes.getJSONObject(i);
                                if(note.getString("module").equals(module.getString("_id")))
                                if(note.getString("reason").equals(item)) {
                                    value.setText(note.getString("value"));
                                }
                        }} catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String valeur = value.getText().toString();
                    String reason = etat.getSelectedItem().toString();
                    try {
                        putNoteStudent(valeur, student.getString("_id"), reason);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });
            annuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

        }


        private void  putNoteStudent(final String note, final String idStudent, final String reason) {

            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, POST_NOTE, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //hidepDialog();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("reason", reason);
                    params.put("value", note);
                    params.put("student", idStudent);
                    try {
                        params.put("module", module.getString("_id"));
                        params.put("modulename", module.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return params;
                }
            };

            App.getInstance().addToRequestQueue(jsonReq);

        }




        public class MyOnItemSelectedListener implements MaterialSpinner.OnItemSelectedListener{

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                // On selecting a spinner item
                //String item = view.getItemAtPosition(position).toString();
                // Showing selected spinner item
                studentsListNoteAdapter.changedNoteLabel(item.toString(),getActivity());
                studentsListNoteAdapter.notifyDataSetChanged();
            }

        }
        public void addItemsOnSpinner() {

          //  MaterialSpinner spinner = (MaterialSpinner) getActivity().findViewById(R.id.spinner);
            // spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
           /* spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                }
            });*/


            spinner = (MaterialSpinner) getActivity().findViewById(R.id.spinner);
            spinner.setItems("exam","controle","intero","tp");
            spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
/*
            List<String> list = new ArrayList<String>();
            list.add("exam");
            list.add("controle");
            list.add("intero");
            list.add("tp");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);*/
        }
    }



