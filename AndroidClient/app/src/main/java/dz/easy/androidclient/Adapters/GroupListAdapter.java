package dz.easy.androidclient.Adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static dz.easy.androidclient.App.BaseActivity.TAG;

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
            final JSONArray students = json.getJSONArray("students");
            Log.i(TAG, "Signed in as: " + json.getString("groupeName"));
            holder.title.setText("Groupe : " + json.getString("spec")+ json.getString("section"));
            holder.count.setText("Nombre des etudiants : " + students.length());
            holder.credit.setText( json.getString("groupeName"));
            holder.module.setText(json.getString("groupeName"));
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonListner.buttonPressed(students);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView title, count , module , credit;
        public ModuleAdapterClickListener listener;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            module = (TextView) view.findViewById(R.id.module);
            credit = (TextView) view.findViewById(R.id.credit);
        }
    }

    public interface AdapterInterface{
        public void buttonPressed(JSONArray students);
    }
}
