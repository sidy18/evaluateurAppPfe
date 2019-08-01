package sn.ept.evaluateurapp.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.network.RetrofitInstance;

public interface MemoireService {
    @GET("memoire/")
    Call<List<Memoire>> getMemoires();

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();

}
