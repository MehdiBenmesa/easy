package dz.easy.androidclient.Adapters;

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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindView;
import dz.easy.androidclient.Activities.ModuleActivity;
import dz.easy.androidclient.Activities.NoteAbsenceActivity;
import dz.easy.androidclient.R;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyViewHolder>{

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
        JSONObject json = null;
        JSONObject student = null;
        try {
            json = contents.getJSONObject(position);
            student = json.getJSONObject("student");

        } catch (JSONException e) {
            //e.printStackTrace();
            try {
                json = contents.getJSONObject(position);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            try {
                student = json.getJSONObject("teacher");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        try {
            holder.title.setText("Avec : " + student.getString("name") + " "+student.getString("lastname"));
            holder.count.setText("Reason : " + json.getString("reason"));
            holder.module.setText(json.getString("date").substring(0,10));
            holder.credit.setText("Credit : " + json.getString("state"));

            final JSONObject finalJson = json;
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(view.getContext() , NoteAbsenceActivity.class);
                    i.putExtra("user" , finalJson.toString());
                    try {
                        i.putExtra("module", finalJson.getString("_id"));
                        i.putExtra("namemodule", finalJson.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    view.getContext().startActivity(i);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
            //ShapeDrawable bgShape = (ShapeDrawable)btn;
            //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            //bgShape.getPaint().setColor(randomAndroidColor);
            //holder.module.setBackgroundColor(randomAndroidColor);

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