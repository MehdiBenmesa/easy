package dz.easy.androidclient.Adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindDrawable;
import dz.easy.androidclient.App.App;
import dz.easy.androidclient.Constants.Constants;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Activities.TimeLineActivity;
import dz.easy.androidclient.Util.CustomRequest;

/**
 * Created by Abderahmane on 03/05/2017.
 */

public class TimeTableManagerAdapter extends RecyclerView.Adapter<TimeTableManagerAdapter.MyViewHolder> implements Constants{

    JSONObject contents ;
    ArrayList<String> timeTableOf;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public TimeTableManagerAdapter(JSONObject contents) {
        this.contents = contents;

        getAllGroups();
        timeTableOf = new ArrayList<>() ;
        timeTableOf.add("Salles");
        timeTableOf.add("Groupes");
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return timeTableOf.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date, parent, false);
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
        final String item = timeTableOf.get(position);
        holder.data.setText(item);
        holder.abredata.setText(item.substring(0 , 2).toUpperCase());
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext() , TimeLineActivity.class);
                i.putExtra("user" ,contents.toString() );
                i.putExtra("timeTableOf" ,item );
               // view.getContext().startActivity(i);
            }
        });
        //ShapeDrawable bgShape = (ShapeDrawable)btn;
        //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        //bgShape.getPaint().setColor(randomAndroidColor);
        //holder.module.setBackgroundColor(randomAndroidColor);

    }

    public void getAllGroups(){

        CustomRequest jsonReq = new CustomRequest(Request.Method.GET, GET_ALL_GROUPS , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        App.getInstance().addToRequestQueue(jsonReq);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView data, abredata;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view_date);
            data = (TextView) view.findViewById(R.id.day);
            abredata = (TextView) view.findViewById(R.id.abreDay);
        }
    }
}
