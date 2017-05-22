package dz.easy.androidclient.Adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindArray;
import butterknife.BindDrawable;
import dz.easy.androidclient.R;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.MyViewHolder> {

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public TeachersAdapter(JSONArray contents) {
        this.contents = contents;
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
                .inflate(R.layout.list_item_teacher, parent, false);
        return new MyViewHolder(view) {};
    }




    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }*/
        try {
            JSONObject json = contents.getJSONObject(position);
            holder.nom.setText("Nom : " + json.getString("name"));
            holder.email.setText("Email : " + json.getString("mail"));
            holder.abreTeacher.setText(json.getString("name").substring(0 , 1).toUpperCase());
            holder.prenom.setText("Prenom : " + json.getString("lastname"));
            //ShapeDrawable bgShape = (ShapeDrawable)btn;
            //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            //bgShape.getPaint().setColor(randomAndroidColor);
            //holder.module.setBackgroundColor(randomAndroidColor);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView nom, email , abreTeacher , prenom;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view_teacher);
            nom = (TextView) view.findViewById(R.id.nom);
            email = (TextView) view.findViewById(R.id.email);
            abreTeacher = (TextView) view.findViewById(R.id.abreTeacher);
            prenom = (TextView) view.findViewById(R.id.prenom);
        }
    }
}