package sn.chaka.evaluateurapp.webservices;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import sn.chaka.evaluateurapp.models.User;
import sn.chaka.evaluateurapp.network.RetrofitInstance;

public interface ConnectionService {
    @FormUrlEncoded
    @GET("evaluateur/login/")
    Call<User> connect(@Field("username") String username, @Field("password") String password);

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
}
