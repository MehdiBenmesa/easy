package dz.easy.androidclient;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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

import java.util.List;
import java.util.Random;

import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindView;
import dz.easy.androidclient.Activities.ModuleActivity;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyViewHolder> {

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public TestRecyclerViewAdapter(JSONArray contents) {
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
                        .inflate(R.layout.list_item_card_big, parent, false);
        return new MyViewHolder(view) {};
    }




    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            final JSONObject json = contents.getJSONObject(position);
            holder.title.setText("Nom du Module : " + json.getString("name"));
            holder.count.setText("Coefficient : " + json.getString("coef"));
            holder.module.setText(json.getString("abre"));
            holder.credit.setText("Credit : " + json.getString("credit"));
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext() , ModuleActivity.class);
                    i.putExtra("user" ,json.toString() );
                    view.getContext().startActivity(i);
                }
            });
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
}