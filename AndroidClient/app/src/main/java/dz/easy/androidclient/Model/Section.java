package dz.easy.androidclient.Model;

import java.util.ArrayList;

/**
 * Created by Mon pc on 30/03/2017.
 */

public class Section {
    private String name;
    private String spec;
    private int semestre;
    private ArrayList<Groupe> groupeList;

    public Section(String name, String spec, int semestre) {
        this.name = name;
        this.spec = spec;
        this.semestre = semestre;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public ArrayList<Groupe> getGroupeList() {
        return groupeList;
    }

    public void setGroupeList(ArrayList<Groupe> groupeList) {
        this.groupeList = groupeList;
    }
}
