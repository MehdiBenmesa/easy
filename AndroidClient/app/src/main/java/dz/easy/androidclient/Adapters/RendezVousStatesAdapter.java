package dz.easy.androidclient.Adapters;

        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;

        import butterknife.BindArray;
        import butterknife.BindDrawable;
        import dz.easy.androidclient.Activities.RendezVousActivity;
        import dz.easy.androidclient.Activities.RendezVousActivity_;
        import dz.easy.androidclient.R;

/**
 * Created by Abderahmane on 03/05/2017.
 */

public class RendezVousStatesAdapter extends RecyclerView.Adapter<RendezVousStatesAdapter.MyViewHolder> {

    JSONObject contents ;
    HashMap<String,String> states;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    @BindArray(R.array.androidcolors)
    int[] androidColors;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public RendezVousStatesAdapter(JSONObject contents) {
        this.contents = contents;
        states = new HashMap<>() ;
        states.put("Acceptée","accepted");
        states.put("En Attent","enattent");
        states.put("Réfusée","refused");
        states.put("Effectuer","effectuer");
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return states.size();
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

        final String item = (String) states.keySet().toArray()[position];
        final String state =  states.get(states.keySet().toArray()[position]);
        holder.day.setText("Les Rendez-vous " +item);
        holder.abreDay.setText(item.substring(0 , 3).toUpperCase());
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type="";
                try {
                    type = contents.getString("_type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i = null;
                if(type.equals("Teacher")){
                    i = new Intent(view.getContext() , RendezVousActivity_.class);
                }else if (type.equals("Manager")){
                    i = new Intent(view.getContext() , RendezVousActivity_.class);
                }else if(type.equals("Student")){
                    i = new Intent(view.getContext() , RendezVousActivity_.class);
                }
                i.putExtra("user" ,contents.toString() );
                i.putExtra("state" , state );
                view.getContext().startActivity(i);
            }
        });

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
