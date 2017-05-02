package dz.easy.androidclient.Model;

/**
 * Created by Mon pc on 29/03/2017.
 */

public class Note {
    private int note;
    private String module;
    private String Student;

    public Note(int note, String module, String student) {
        this.note = note;
        this.module = module;
        Student = student;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getStudent() {
        return Student;
    }

    public void setStudent(String student) {
        Student = student;
    }
}
