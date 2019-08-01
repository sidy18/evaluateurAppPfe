package sn.ept.evaluateurapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Etudiant {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("anneeNaissance")
    @Expose
    private int anneeNaissance;
    @SerializedName("lieuNaissance")
    @Expose
    private String lieuNaissance;
    @SerializedName("promoAnnee")
    @Expose
    private int promoAnnee;
    @SerializedName("email")
    @Expose
    private String email;

    public Etudiant() {
    }

    public Etudiant(Integer id) {
        this.id = id;
    }

    public Etudiant(Integer id, int anneeNaissance, String lieuNaissance, int promoAnnee, String email) {
        this.id = id;
        this.anneeNaissance = anneeNaissance;
        this.lieuNaissance = lieuNaissance;
        this.promoAnnee = promoAnnee;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public int getPromoAnnee() {
        return promoAnnee;
    }

    public void setPromoAnnee(int promoAnnee) {
        this.promoAnnee = promoAnnee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
