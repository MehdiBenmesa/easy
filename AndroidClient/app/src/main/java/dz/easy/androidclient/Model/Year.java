package dz.easy.androidclient.Model;

import java.util.ArrayList;

/**
 * Created by Mon pc on 30/03/2017.
 */

public class Year {
    private String name;
    private String spec;
    private String groupe;
    private Groupe group;
    private ArrayList<Student> sectionList;

    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
    }


    public Year(String name, String spec, String groupe) {
        this.name = name;
        this.spec = spec;
        this.groupe = groupe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Student> sectionList) {
        this.sectionList = sectionList;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
