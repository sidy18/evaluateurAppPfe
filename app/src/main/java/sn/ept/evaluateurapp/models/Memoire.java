package sn.ept.evaluateurapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Memoire {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sujet")
    @Expose
    private String sujet;
    @SerializedName("langue")
    @Expose
    private String langue;
    @SerializedName("lieu")
    @Expose
    private String lieu;
    @SerializedName("etudiantId")
    @Expose
    private Etudiant etudiantId;

    public Memoire() {
    }

    public Memoire(Integer id, Integer idEtudiant) {
        this.id = id;
        this.sujet = "";
        this.langue = "";
        this.lieu = "";
        this.etudiantId = new Etudiant(idEtudiant);
    }

    public Memoire(Integer id, String sujet, String langue, String lieu, Etudiant etudiantId) {
        this.id = id;
        this.sujet = sujet;
        this.langue = langue;
        this.lieu = lieu;
        this.etudiantId = etudiantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Etudiant getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Etudiant etudiantId) {
        this.etudiantId = etudiantId;
    }
}
