package dz.easy.androidclient.Adapters;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dz.easy.androidclient.Model.Note;
import dz.easy.androidclient.R;


public class NoteAdapter extends ArrayAdapter {
    private Context context;

    public NoteAdapter(Context context, ArrayList<Note> depenses){
        super(context, 0, depenses);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Note site = (Note) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_note, parent, false);
        }
        // Lookup view for data population
        TextView note = (TextView) convertView.findViewById(R.id.note);
        TextView etudiant = (TextView) convertView.findViewById(R.id.etudiant);
        TextView module = (TextView) convertView.findViewById(R.id.module);

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isTwopane()){
                   // printDetails(site);
                }else{
                    /*Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("detail", site);
                    context.startActivity(intent);*/
                }
            }

        });

        // Populate the data into the template view using the data object
        note.setText(site.getNote()+"");
        module.setText(String.valueOf(site.getModule()));
        etudiant.setText(site.getStudent());

        // Return the completed view to render on screen
        return convertView;
    }

    public boolean isTwopane(){
        View view = ((Activity) context).findViewById(R.id.item);
        return (view != null);
    }

    public void printDetails(Note note){
      /*  ImageView imageView = (ImageView)((Activity) context).findViewById(R.id.imageView2);
        TextView textView = (TextView) ((Activity) context).findViewById(R.id.textView);
        TextView textView2 = (TextView) ((Activity) context).findViewById(R.id.textView2);
        TextView textView3 = (TextView) ((Activity) context).findViewById(R.id.textView3);

        textView.setText(site.getNom());
        textView2.setText(site.getDescription());
        textView3.setText(site.getHistorique());
        imageView.setImageResource(site.getImage());*/
    }
}
