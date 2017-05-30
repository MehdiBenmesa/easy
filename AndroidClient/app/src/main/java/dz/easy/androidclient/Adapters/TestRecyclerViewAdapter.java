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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindArray;
import butterknife.BindDrawable;
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

    private AdapterInterface buttonListner;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public TestRecyclerViewAdapter(JSONArray contents, TestRecyclerViewAdapter.AdapterInterface listner) {
        this.contents = contents;
        buttonListner = listner;
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
            holder.module.setText(json.getString("date"));
            holder.credit.setText("Credit : " + json.getString("state"));
            final JSONObject finalJson = json;
          holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  buttonListner.buttonPressed(finalJson);
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
      void buttonPressed(JSONObject module);
    }
}
