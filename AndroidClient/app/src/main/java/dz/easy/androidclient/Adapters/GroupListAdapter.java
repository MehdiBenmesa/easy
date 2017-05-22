package dz.easy.androidclient.Adapters;

import android.content.Intent;
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
public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.MyViewHolder>{

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    private AdapterInterface buttonListner;
    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public GroupListAdapter(JSONArray contents , AdapterInterface listner) {
        this.contents = contents;
        this.buttonListner = listner ;
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
                .inflate(R.layout.list_item_card_big, parent, false);
        return new MyViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            final JSONObject json = contents.getJSONObject(position);
            //  final JSONObject course = json.getJSONObject("name");
            holder.nom.setText("Groupe : " + json.getString("number"));
                holder.nombre.setText("Nombre des etudiants : " + json.getString("students"));
            holder.groupe.setText(json.getString("Gr") + json.getString("number"));
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonListner.buttonPressed(json);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView nombre, nom , groupe , credit;
        public ModuleAdapterClickListener listener;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view_groupe);
            nombre = (TextView) view.findViewById(R.id.nombre);
            nom = (TextView) view.findViewById(R.id.nom);
            groupe = (TextView) view.findViewById(R.id.groupe);
        }

    }

    public interface AdapterInterface{
        public void buttonPressed(JSONObject module);
    }
}
