package sn.ept.evaluateurapp.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.evaluateurapp.R;
import sn.ept.evaluateurapp.adapters.NoteAdapter;
import sn.ept.evaluateurapp.models.Evaluateur;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.models.Note;
import sn.ept.evaluateurapp.webservices.NoteService;

public class ListNotePage extends AppCompatActivity  implements NoteAdapter.NoteAdapterListener{
    private RecyclerView cardListNote;
    private List<Note> noteList;
    private NoteAdapter nAdapter;
    private Memoire memoireActuel;
    private Evaluateur evaluateurActuel;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note_page);

        cardListNote = findViewById(R.id.cardListNote);
        noteList = new ArrayList<>();

        //Shared preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        evaluateurActuel = new Evaluateur(Integer.parseInt(preferences.getString("EVAL_ID", "0")));
        memoireActuel = new Memoire(getIntent().getIntExtra("ID_MEMOIRE", 0), evaluateurActuel.getId());

        nAdapter = new NoteAdapter(this, noteList, this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        cardListNote.setLayoutManager(mLayoutManager);
        cardListNote.setItemAnimator(new DefaultItemAnimator());
        cardListNote.setAdapter(nAdapter);
        setTitle("Notes par catégories/auteurs");

        fetchNote();
    }

    public void fetchNote()
    {
        final NoteService noteService = NoteService.retrofit.create(NoteService.class);
        final Call<List<Note>> call = noteService.getNote();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                Toast.makeText(getApplicationContext(), "List retrieved", Toast.LENGTH_SHORT).show();
                noteList.clear();
                for (int i = 0; i < response.body().size(); i++) {
                    if(memoireActuel.getId() == response.body().get(i).getMemoireId().getId()) {
                        noteList.add(new Note(
                                        response.body().get(i).getId(),
                                        response.body().get(i).getNote(),
                                        response.body().get(i).getCompetenceId(),
                                        response.body().get(i).getEvaluateurId(),
                                        response.body().get(i).getMemoireId()
                                )
                        );
                    }
                }

                nAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "List not retrieved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNoteSelected(Note note) {

    }


}
