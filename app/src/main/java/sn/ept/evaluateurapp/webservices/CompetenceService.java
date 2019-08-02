package sn.ept.evaluateurapp.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import sn.ept.evaluateurapp.models.Competence;
import sn.ept.evaluateurapp.network.RetrofitInstance;

public interface CompetenceService {
    @GET("competence/")
    Call<List<Competence>> getCompetences();

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
}
