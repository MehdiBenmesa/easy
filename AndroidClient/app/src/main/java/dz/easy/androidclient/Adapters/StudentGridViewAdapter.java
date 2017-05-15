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

import dz.easy.androidclient.Model.Student;
import dz.easy.androidclient.R;

/**
 * Created by Mon pc on 01/04/2017.
 */

public class StudentGridViewAdapter extends ArrayAdapter<Student> {

private Context context;
private int layoutResourceId;
private ArrayList<Student> data = new ArrayList<Student>();

public StudentGridViewAdapter(Context context, int layoutResourceId, ArrayList<Student> data) {
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


        Student item = data.get(position);
        holder.imageTitle.setText(item.getName());
        //  holder.image.setImageBitmap(item.getImage());
        return row;
        }

        static class ViewHolder {
            TextView imageTitle;
            ImageView image;
        }
}
