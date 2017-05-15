package dz.easy.androidclient.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dz.easy.androidclient.Model.Year;
import dz.easy.androidclient.R;

public class YearGridViewAdapter extends ArrayAdapter<Year> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Year> data = new ArrayList<Year>();

    public YearGridViewAdapter(Context context, int layoutResourceId, ArrayList<Year> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        Year item = data.get(position);
        holder.imageTitle.setText(item.getName()+"-"+ item.getSpec()+"-"+item.getGroupe());
      //  holder.image.setImageBitmap(item.getImage());
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}