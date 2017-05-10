package dz.easy.androidclient.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dz.easy.androidclient.R;

public class StudentInfo extends AppCompatActivity {

    TextView nom;
    TextView prenom;
    TextView groupe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        nom = (TextView) findViewById(R.id.nom);
        prenom = (TextView) findViewById(R.id.prenom);
        groupe = (TextView) findViewById(R.id.groupe);
        nom.setText(getIntent().getStringExtra("name"));
        prenom.setText(getIntent().getStringExtra("prenom"));
        groupe.setText(getIntent().getStringExtra("groupe"));
    }
}
