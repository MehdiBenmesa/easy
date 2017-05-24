package com.example.pc.mobileapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 23/04/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Bien> list;
    private Context context;

    public CustomAdapter(Context context, List<Bien> list) {
        this.context = context;
        this.list = list;
    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null)
            convertView = parent.inflate(context,R.layout.list_layout,null);

        TextView textView = (TextView) convertView.findViewById(R.id.propL);
        TextView textView2 = (TextView) convertView.findViewById(R.id.wilayaL);
        TextView textView1=  (TextView) convertView.findViewById(R.id.prixL);
        ImageView imageview = (ImageView) convertView.findViewById(R.id.imageL);
        textView.setText(list.get(position).getProprietaire());
        textView1.setText(""+list.get(position).getPrix());
        textView2.setText(list.get(position).getWilaya());
        imageview.setImageResource(list.get(position).getImage());
        return convertView;
    }
}
