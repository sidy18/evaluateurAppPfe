package sn.ept.evaluateurapp.webservices;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sn.ept.evaluateurapp.models.User;
import sn.ept.evaluateurapp.network.RetrofitInstance;

public interface ConnectionService {

    @GET("evaluateur/login/")
    Call<User> connect(@Query("username") String username, @Query("password") String password);

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
}
