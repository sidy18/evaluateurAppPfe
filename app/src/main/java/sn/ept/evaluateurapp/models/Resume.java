package sn.ept.evaluateurapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resume {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("codeLangue")
    @Expose
    private String codeLangue;
    @SerializedName("memoireId")
    @Expose
    private Memoire memoireId;
    @SerializedName("resume")
    @Expose
    private String resume;

    public Resume(Integer id, String codeLangue, Memoire memoireId, String resume) {
        this.id = id;
        this.codeLangue = codeLangue;
        this.memoireId = memoireId;
        this.resume = resume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeLangue() {
        return codeLangue;
    }

    public void setCodeLangue(String codeLangue) {
        this.codeLangue = codeLangue;
    }

    public Memoire getMemoireId() {
        return memoireId;
    }

    public void setMemoireId(Memoire memoireId) {
        this.memoireId = memoireId;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
