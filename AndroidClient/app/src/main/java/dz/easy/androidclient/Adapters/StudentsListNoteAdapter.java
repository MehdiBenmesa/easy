package dz.easy.androidclient.Adapters;

        import android.content.Context;
        import android.graphics.drawable.Drawable;
        import android.support.v4.util.ArraySet;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
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
        import dz.easy.androidclient.Util.CustomRequestNote;

        import static java.security.AccessController.getContext;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class StudentsListNoteAdapter extends RecyclerView.Adapter<StudentsListNoteAdapter.MyViewHolder> implements Constants {

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    private String moduleid;
    private String Examnote;


    public HashSet<MyViewHolder> getMyList() {
        return myList;
    }

    public void setMyList(HashSet<MyViewHolder> myList) {
        this.myList = myList;
    }

    public HashSet<MyViewHolder> myList ;
    private AdapterInterface buttonListner;
    private Button valider;

    @BindDrawable(R.drawable.circle)
    Drawable btn;

    public StudentsListNoteAdapter(JSONArray contents, String id , AdapterInterface listner) {
        this.contents = contents;
        this.buttonListner = listner ;
        moduleid= id;
        myList = new HashSet<>();
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
                .inflate(R.layout.list_item_card_student_note, parent, false);
        return new MyViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        myList.add(holder);
        try {
            final JSONObject json = contents.getJSONObject(position);
            final JSONArray notes = json.getJSONArray("notes");
            holder.idStudent.setText(json.getString("_id"));
            holder.title.setText(json.getString("name")+ " "+json.getString("lastname"));
            holder.count.setText(json.getString("matricule"));
            holder.credit.setText( json.getString("mail"));
            holder.module.setText(json.getString("address"));
            String examnote="";
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        buttonListner.buttonPressed(json,notes);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            try{
            for (int i =0;i<notes.length();i++) {
                JSONObject note = notes.getJSONObject(i);
                if(note.getString("module").equals(moduleid))
                    if(note.getString("reason").equals(Examnote)) {
                        examnote = note.getString("value");
                    }
            }} catch (JSONException e){
                e.printStackTrace();
            }
            holder.note.setText(examnote);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView title, count , module , credit,idStudent;
        public EditText note;
        public ModuleAdapterClickListener listener;

        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            module = (TextView) view.findViewById(R.id.module);
            credit = (TextView) view.findViewById(R.id.credit);
            note = (EditText) view.findViewById(R.id.note);
            idStudent = (TextView) view.findViewById(R.id.studentId);
        }
    }

    public interface AdapterInterface{
        public void buttonPressed(final JSONObject students,JSONArray notes) throws JSONException;
    }

    public void changedNoteLabel(String note, Context c){
        Examnote = note;
    }
    public void updateNotes(Context c,String idModule){
        StudentsListNoteAdapter.MyViewHolder child;
        final JSONArray students = new JSONArray();
        Iterator iter = myList.iterator();
        while (iter.hasNext()) {
            child = (MyViewHolder) iter.next();
            while (Float.parseFloat(child.note.getText().toString())>20) iter.next();
            JSONObject student = new JSONObject();

            try {
                student.put("reason", Examnote);
                student.put("value", child.note.getText().toString());
                student.put("student", child.idStudent.getText().toString());
                student.put("module", moduleid);
                students.put(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, POST_NOTES, null,
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
                    params.put("students", students.toString());
                    return params;
                }
            };
            App.getInstance().addToRequestQueue(jsonReq);
    }
}
