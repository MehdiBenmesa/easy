package com.example.pc.mobileapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Bien> liste;
    private android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

    public boolean isTwoPane() {
        View view = findViewById(R.id.fragment);
        return (view != null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenFragment authen = new AuthenFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainL, authen);
        fragmentTransaction.commit();
        Button btn;


        btn = (Button) findViewById(R.id.lancer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListView listView = (ListView) v.findViewById(R.id.listelogement);
                liste = new ArrayList<Bien>();
                liste.add(new Bien("Appart", 1, R.drawable.log1, 4000, "Amina", "Villa", "Alger"));
                liste.add(new Bien("Appart1", 2, R.drawable.log2, 4000, "Amin", "Appartement", "Tlemcen"));
                liste.add(new Bien("Appart2", 3, R.drawable.log3, 4000, "Zigy", "Studio", "Tipaza"));
                liste.add(new Bien("Appart3", 4, R.drawable.log4, 4000, "Zakaria", "Appartement", "Tlemcen"));
                liste.add(new Bien("Appart4", 5, R.drawable.log5, 4000, "Slimane", "Villa", "Alger"));
                liste.add(new Bien("Appart5", 6, R.drawable.log6, 4000, "Amin", "Duplex", "Oran"));
              //  CustomAdapter customAdapter = new CustomAdapter(this,liste);
                //listView.setAdapter(customAdapter);
                // listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (isTwoPane()) {
                            TextView textView1 = (TextView) findViewById(R.id.wilayaL);
                            TextView textView2 = (TextView) findViewById(R.id.prixL);
                            ImageView img = (ImageView) findViewById(R.id.imageL);
                            textView1.setText(liste.get(position).getWilaya());
                            textView2.setText("" + liste.get(position).getPrix());
                            img.setImageResource(liste.get(position).getImage());

                            // textView.setText(""+list.get(position));
                        } else {
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            String proprietaire = liste.get(position).getProprietaire();
                            String wiliaya = liste.get(position).getWilaya();
                            double price = liste.get(position).getPrix();
                            int img = liste.get(position).getImage();
                            Bien bien = new Bien();
                            bien.setImage(img);
                            bien.setPrix(price);
                            bien.setProprietaire(proprietaire);
                            //Toast.makeText(MainActivity.this,list.get(position).getNom(), Toast.LENGTH_SHORT).show();

                            intent.putExtra("bien", bien);
                            //intent.putExtra("name","New York");
                            startActivity(intent);
                        }
                    }
                });


            }


        });

   /* public void inscrire(View view)
    {

       Log.e("error","msg");
        InscriptionFragment inscr = new InscriptionFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment2, inscr);
        fragmentTransaction.commit();
    }*/
    }
}
