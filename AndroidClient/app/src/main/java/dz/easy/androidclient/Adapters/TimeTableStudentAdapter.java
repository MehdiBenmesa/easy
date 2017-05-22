package dz.easy.androidclient.Adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindDrawable;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Activities.TimeLineActivity;

/**
 * Created by Abderahmane on 03/05/2017.
 */

public class TimeTableStudentAdapter extends RecyclerView.Adapter<TimeTableStudentAdapter.MyViewHolder> {

    JSONObject contents ;
    ArrayList<String> dayOfWeek;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public TimeTableStudentAdapter(JSONObject contents) {
        this.contents = contents;
            dayOfWeek = new ArrayList<>() ;
            dayOfWeek.add("Dimanche");
            dayOfWeek.add("Lundi");
            dayOfWeek.add("Mardi");
            dayOfWeek.add("Mercredi");
            dayOfWeek.add("Jeudi");
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return dayOfWeek.size();
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
            final String item = dayOfWeek.get(position);
            holder.day.setText(item);
            holder.abreDay.setText(item.substring(0 , 3).toUpperCase());
            holder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext() , TimeLineActivity.class);
                    i.putExtra("user" ,contents.toString() );
                    i.putExtra("day" ,item );
                    view.getContext().startActivity(i);
                }
            });
            //ShapeDrawable bgShape = (ShapeDrawable)btn;
            //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            //bgShape.getPaint().setColor(randomAndroidColor);
            //holder.module.setBackgroundColor(randomAndroidColor);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        public TextView day , abreDay ;
        public MyViewHolder(View view) {
            super(view);
            cardItem = (CardView) view.findViewById(R.id.card_view_date);
            day = (TextView) view.findViewById(R.id.day);
            abreDay = (TextView) view.findViewById(R.id.abreDay);
        }
    }
}
