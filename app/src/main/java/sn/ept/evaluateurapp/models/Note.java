package sn.ept.evaluateurapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("note")
    @Expose
    private float note;
    @SerializedName("competenceId")
    @Expose
    private Competence competenceId;
    @SerializedName("evaluateurId")
    @Expose
    private Evaluateur evaluateurId;
    @SerializedName("memoireId")
    @Expose
    private Memoire memoireId;


    public Note(Integer id, float note, Competence competenceId, Evaluateur evaluateurId, Memoire memoireId) {
        this.id = id;
        this.note = note;
        this.competenceId = competenceId;
        this.evaluateurId = evaluateurId;
        this.memoireId = memoireId;
    }

    public Note(float note, Competence competenceId, Evaluateur evaluateurId, Memoire memoireId) {
        this.note = note;
        this.competenceId = competenceId;
        this.evaluateurId = evaluateurId;
        this.memoireId = memoireId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Competence getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Competence competenceId) {
        this.competenceId = competenceId;
    }

    public Evaluateur getEvaluateurId() {
        return evaluateurId;
    }

    public void setEvaluateurId(Evaluateur evaluateurId) {
        this.evaluateurId = evaluateurId;
    }

    public Memoire getMemoireId() {
        return memoireId;
    }

    public void setMemoireId(Memoire memoireId) {
        this.memoireId = memoireId;
    }
}
