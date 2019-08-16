package sn.ept.evaluateurapp.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import sn.ept.evaluateurapp.models.Resume;
import sn.ept.evaluateurapp.network.RetrofitInstance;

public interface ResumeService {
    @GET("resume/")
    Call<List<Resume>> getResumes();

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
}
