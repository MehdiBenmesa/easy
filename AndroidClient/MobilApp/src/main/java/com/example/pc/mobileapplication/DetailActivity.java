package com.example.pc.mobileapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
      List<Bien> liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        Toast.makeText(this, "Fahima is mad", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_bien);
        Intent intent = getIntent();
        Bien biendetail = (Bien) intent.getSerializableExtra("produit");
        /*Reccupérer l'image */
        ImageView image = (ImageView) findViewById(R.id.Imbien);
        image.setImageResource(biendetail.getImage());
        /* Recupérer le type */

        /* Recupérer prop */
        TextView prop = (TextView) findViewById(R.id.PropB);
        prop.setText(biendetail.getProprietaire());
        /* Récupérer wilaya */
        TextView wilaya = (TextView) findViewById(R.id.wilaya);
        wilaya.setText(biendetail.getWilaya());

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DetailActivity.this, AfficherBien.class);
                int image = liste.get(position).getImage();
                String type = liste.get(position).getType();
                String prop = liste.get(position).getProprietaire();
                String wilaya = liste.get(position).getWilaya();
                double prix = liste.get(position).getPrix();
                String nom = liste.get(position).getNom();
                Bien nouveau = new Bien(nom,position,image,prix,prop,type,wilaya);
                intent.putExtra("produit",nouveau);
                startActivity(intent);
            }
        });*/
    }
}
