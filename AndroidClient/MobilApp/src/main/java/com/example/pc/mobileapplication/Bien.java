package com.example.pc.mobileapplication;

import java.io.Serializable;

/**
 * Created by pc on 21/04/2017.
 */

public class Bien implements Serializable {
    private int identifiant;
    private String nom;
    private String type;
    private String proprietaire;
    private String wilaya;
    private double prix;
    private int image;

    public Bien() {
    }

    public Bien(String nom, int identifiant, int image, double prix, String proprietaire, String type, String wilaya) {
        this.nom = nom;
        this.identifiant = identifiant;
        this.image = image;
        this.prix = prix;
        this.proprietaire = proprietaire;
        this.type = type;
        this.wilaya = wilaya;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }
}
