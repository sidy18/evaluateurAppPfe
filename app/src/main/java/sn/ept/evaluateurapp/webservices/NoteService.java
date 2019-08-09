package sn.ept.evaluateurapp.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import sn.ept.evaluateurapp.models.Competence;
import sn.ept.evaluateurapp.models.Evaluateur;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.models.Note;
import sn.ept.evaluateurapp.network.RetrofitInstance;

public interface NoteService {
    @GET("note/")
    Call<List<Note>> getNote();

    @POST("note/")
    Call<Note> setNote(
            @Body Note note
    );

    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
}
