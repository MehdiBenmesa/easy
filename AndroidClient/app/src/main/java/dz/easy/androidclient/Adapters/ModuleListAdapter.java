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

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindDrawable;
import dz.easy.androidclient.R;
import dz.easy.androidclient.Util.SessionManager;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.MyViewHolder>{

    JSONArray contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    String user;
    @BindArray(R.array.androidcolors)
    int[] androidColors;

    public ArrayList<MyViewHolder> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<MyViewHolder> myList) {
        this.myList = myList;
    }

    public ArrayList<ModuleListAdapter.MyViewHolder> myList ;
    private AdapterInterface buttonListner;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public ModuleListAdapter(JSONArray contents ) {
        this.contents = contents;
        myList = new ArrayList<MyViewHolder>();
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
        SessionManager sessionManager = new SessionManager(view.getContext());
        System.out.println("USER SESSION : "+sessionManager.getUser());

        user = sessionManager.getUser();
        return new MyViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            myList.add(holder);
            final JSONObject json = contents.getJSONObject(position);

            holder.title.setText("Nom du Module : " + json.getString("name"));
            holder.count.setText("Coefficient : " + json.getString("coef"));
            holder.module.setText(json.getString("abre"));
            holder.credit.setText("Credit : " + json.getString("credit"));

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
