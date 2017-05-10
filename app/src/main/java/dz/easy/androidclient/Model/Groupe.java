package dz.easy.androidclient.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mon pc on 30/03/2017.
 */

public class Groupe implements Serializable{
    private int number;
    private ArrayList<Student> StudentList;

    public Groupe(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Student> getStudentList() {
        return StudentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        StudentList = studentList;
    }
}
