package dz.easy.androidclient.Model;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Mon pc on 30/03/2017.
 */

public class Student extends AppCompatActivity implements Serializable{

    private String name;
    private String lastname;
    private HashMap<Module,Note> notemodule;
    private String matricule;
    private String sexe;
    private String mail;
    private String tell;

    public Student(String name, String lastname, String matricule, String sexe, String mail, String tell) {
        this.name = name;
        this.lastname = lastname;
        this.matricule = matricule;
        this.sexe = sexe;
        this.mail = mail;
        this.tell = tell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public HashMap<Module, Note> getNotemodule() {
        return notemodule;
    }

    public void setNotemodule(HashMap<Module, Note> notemodule) {
        this.notemodule = notemodule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }
}
