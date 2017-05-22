package dz.easy.androidclient.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Parcelable;
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

import java.util.List;
import java.util.Random;

import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindView;
import dz.easy.androidclient.Activities.GroupeByModuleActivity;
import dz.easy.androidclient.Activities.ModuleActivity;
import dz.easy.androidclient.Activities.ViewPagerActivity;
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

    private AdapterInterface buttonListner;

    @BindDrawable(R.drawable.circle)
    Drawable btn;
    public ModuleListAdapter(JSONArray contents , AdapterInterface listner) {
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
        SessionManager sessionManager = new SessionManager(view.getContext());
        System.out.println("USER SESSION : "+sessionManager.getUser());

            user = sessionManager.getUser();
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


                    try {
                        JSONObject userjson = new JSONObject(user);
                        if(userjson.getString("_type").equals("Teacher")){
                            //Toast.makeText(getContext() , "Hi Teacher" , Toast.LENGTH_LONG).show();
                            buttonListner.buttonPressed(json);
                        }else if (userjson.getString("_type").equals("Manager")){
                            //Toast.makeText(getContext() , "Hi Manager" , Toast.LENGTH_LONG).show();
                            //getTeachers();
                        }else if (userjson.getString("_type").equals("Student")){
                            //Toast.makeText(getContext() , "Hi Student" , Toast.LENGTH_LONG).show();
                            Intent i = new Intent(view.getContext() , ViewPagerActivity.class);
                            try {
                                i.putExtra("module",json.getString("_id"));
                                i.putExtra("namemodule",json.getString("name"));
                                i.putExtra("user", user);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            view.getContext().startActivity(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        public void buttonPressed(JSONObject module);
    }
}