package dz.easy.androidclient.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import dz.easy.androidclient.Adapters.StudentGridViewAdapter;
import dz.easy.androidclient.Model.Groupe;
import dz.easy.androidclient.Model.Student;
import dz.easy.androidclient.R;

public class StudentList extends AppCompatActivity {

    private GridView gridView;
    private StudentGridViewAdapter gridAdapter;
    private TextView groupeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new StudentGridViewAdapter(this, R.layout.fragment_item_layout_student, getData());
        gridView.setAdapter(gridAdapter);
        groupeName = (TextView) findViewById(R.id.groupename);
        groupeName.setText(getIntent().getStringExtra("group"));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Student item = (Student) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(StudentList.this, StudentInfo.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("prenom", item.getLastname());
                intent.putExtra("groupe",getIntent().getStringExtra("group"));
                //Start details activity
                startActivity(intent);
            }
        });
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<Student> getData() {
        Groupe st = (Groupe) getIntent().getSerializableExtra("groupe");
        final ArrayList<Student> data = new ArrayList<Student>();
        data.addAll(st.getStudentList());
        return data;
    }
}
