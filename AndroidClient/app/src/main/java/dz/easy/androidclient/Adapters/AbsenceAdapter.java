package dz.easy.androidclient.Adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import butterknife.BindArray;
import butterknife.BindDrawable;
import dz.easy.androidclient.Activities.ModuleActivity;
import dz.easy.androidclient.R;

/**
 * Created by Mon pc on 10/05/2017.
 */

public class AbsenceAdapter extends RecyclerView.Adapter<AbsenceAdapter.MyViewHolder> {

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public AbsenceAdapter(JSONArray contents) {
        this.contents = contents;
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
                .inflate(R.layout.list_item_card_absence, parent, false);
        return new MyViewHolder(view) {};
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            final JSONObject json = contents.getJSONObject(position);
            final JSONObject seance = json.getJSONObject("seance");
            final JSONObject module = seance.getJSONObject("module");

            final JSONObject teacher = seance.getJSONObject("teacher");
            String date = json.getString("date");

            holder.title.setText(module.getString("name"));

            holder.count.setText(date);

            holder.prof.setText("Enseignant : "+teacher.getString("name")+" "+teacher.getString("lastname"));

            holder.module.setText(module.getString("abre"));

            holder.credit.setText(seance.getString("starts") + "-" + seance.getString("ends")  );
          /*  ShapeDrawable bgShape = (ShapeDrawable)btn;
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            bgShape.getPaint().setColor(randomAndroidColor);
            holder.module.setBackgroundColor(randomAndroidColor);
*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView title, count , module , credit, prof;
        public ModuleAdapterClickListener listener;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            prof = (TextView) view.findViewById(R.id.prof);
            module = (TextView) view.findViewById(R.id.module);
            credit = (TextView) view.findViewById(R.id.credit);
        }

    }
}