package dz.easy.androidclient.Adapters;

        import android.content.Context;
        import android.graphics.drawable.Drawable;
        import android.support.annotation.BoolRes;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.Iterator;
        import java.util.Map;

        import butterknife.BindArray;
        import butterknife.BindDrawable;
        import dz.easy.androidclient.App.App;
        import dz.easy.androidclient.Constants.Constants;
        import dz.easy.androidclient.R;
        import dz.easy.androidclient.Util.CustomRequest;
        import dz.easy.androidclient.Util.CustomRequestArray;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class StudentsListAbsenceAdapter extends RecyclerView.Adapter<StudentsListAbsenceAdapter.MyViewHolder> implements Constants {

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;
    private Boolean enabled= false;
    private String moduleid;
    private String Examnote;
    private AdapterInterface buttonListner;
    private Button valider;
    private String thisdate;
    private String adapterIdAbsence;
    public HashSet<StudentsListAbsenceAdapter.MyViewHolder> getMyList() {
        return myList;
    }
    private JSONArray studentAbsents;
    public void setMyList(HashSet<StudentsListAbsenceAdapter.MyViewHolder> myList) {
        this.myList = myList;
    }

    public HashSet<StudentsListAbsenceAdapter.MyViewHolder> myList ;

    @BindDrawable(R.drawable.circle)
    Drawable btn;

    public StudentsListAbsenceAdapter(JSONArray contents,String id , AdapterInterface listner) {
        this.contents = contents;
        this.buttonListner = listner ;
        moduleid= id;
        myList = new HashSet<>();
        studentAbsents = new JSONArray();
    }


    public interface ModuleAdapterClickListener {
        void recyclerViewClick(String albumID);
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return contents.length();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card_student_absence, parent, false);

        return new MyViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        myList.add(holder);
        try {
            final JSONObject json = contents.getJSONObject(position);
            holder.idStudent.setText(json.getString("_id"));
            holder.title.setText(json.getString("name")+ " "+json.getString("lastname"));
            holder.count.setText(json.getString("matricule"));
            holder.credit.setText( json.getString("mail"));
            holder.module.setText(json.getString("address"));
            holder.abs.setEnabled(enabled);
            for (int i=0;i<studentAbsents.length();i++){
                String etudiant="";
                try {
                    etudiant = studentAbsents.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(holder.idStudent.getText().toString().equals(etudiant))
                    {
                        holder.abs.setChecked(true);
                    }
            }
            System.out.println("BIND VEIW HOLDER : "+json.getString("_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView title, count , module , credit, idStudent;
        public ModuleAdapterClickListener listener;
        public CheckBox abs;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            module = (TextView) view.findViewById(R.id.module);
            credit = (TextView) view.findViewById(R.id.credit);
            idStudent = (TextView) view.findViewById(R.id.studentId);
            abs = (CheckBox) view.findViewById(R.id.absencecheckbox);
        }
    }

    public interface AdapterInterface{
        public void buttonPressed(final JSONObject students,JSONArray notes) throws JSONException;
    }

    public void changedAbsence(Context c,JSONArray etudiants ,String idAbsence) {
        enabled = true;
        adapterIdAbsence = idAbsence;
        studentAbsents = etudiants;
        StudentsListAbsenceAdapter.MyViewHolder child;
        Iterator iter = myList.iterator();
        while (iter.hasNext()) {
            child = (StudentsListAbsenceAdapter.MyViewHolder) iter.next();
            child.abs.setEnabled(true);
            for (int i=0;i<studentAbsents.length();i++){
                String etudiant="";
                try {
                    etudiant = studentAbsents.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    if(child.idStudent.getText().toString().equals(etudiant))
                        child.abs.setChecked(true);
                }
        }
    }

    public void changedDate(String date){
        thisdate = date;
    }

    public void putSeance(String idSeance, String date){

    }
    public void updateAbsences(Context c){
        StudentsListAbsenceAdapter.MyViewHolder child;
        final JSONArray students = new JSONArray();
        Iterator iter = myList.iterator();
        int i=0;
        while (iter.hasNext()) {
            child = (StudentsListAbsenceAdapter.MyViewHolder) iter.next();
            if(child.abs.isChecked()){
                System.out.println("ETUDIANTS CHECKED "+ child.title.getText().toString());
                try {
                    students.put(i,child.idStudent.getText().toString());
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        CustomRequest jsonReq = new CustomRequest(Request.Method.POST, POST_UPDATE_ABSENCES, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                try {
                    JSONObject absence = new JSONObject();
                    absence.put("students",students);
                    absence.put("idabs",adapterIdAbsence);
                    params.put("absence", absence.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return params;
            }
        };
        App.getInstance().addToRequestQueue(jsonReq);
    }
    public void ajoutAbsences(Context c, final String idSeance){
        StudentsListAbsenceAdapter.MyViewHolder child;
        final JSONArray students = new JSONArray();
        Iterator iter = myList.iterator();
        int i=0;
        while (iter.hasNext()) {
            child = (StudentsListAbsenceAdapter.MyViewHolder) iter.next();
            if(child.abs.isChecked()){
                System.out.println("ETUDIANTS CHECKED "+ child.title.getText().toString());
                try {
                    students.put(i,child.idStudent.getText().toString());
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        CustomRequest jsonReq = new CustomRequest(Request.Method.POST, POST_ABSENCES, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

              //  try {
                    JSONObject absence = new JSONObject();
                    params.put("students",students.toString());
                    params.put("seance",idSeance);
                    params.put("date",thisdate);
                    //params.put("absence", absence.toString());
              /*  } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                return params;
            }
        };
        App.getInstance().addToRequestQueue(jsonReq);
    }

    public void enableCase(Boolean enb){
        enabled = enb;
        StudentsListAbsenceAdapter.MyViewHolder child;
        Iterator iter = myList.iterator();
        int i=0;
        while (iter.hasNext()) {
            child = (StudentsListAbsenceAdapter.MyViewHolder) iter.next();
            child.abs.setEnabled(enabled);
        }
    }
}
