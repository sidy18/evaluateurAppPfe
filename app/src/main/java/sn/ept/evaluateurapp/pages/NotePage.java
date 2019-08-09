package sn.ept.evaluateurapp.pages;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.evaluateurapp.R;
import sn.ept.evaluateurapp.models.Competence;
import sn.ept.evaluateurapp.models.Evaluateur;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.models.Note;
import sn.ept.evaluateurapp.webservices.NoteService;

public class NotePage extends AppCompatActivity {
    RatingBar ratingbar;
    Button button;
    private List<Note> noteList;

    private Evaluateur evaluateurActuel;
    private Competence competenceActuelle;
    private Memoire memoireActuel;
    private Note noteActuelle;

    private SharedPreferences preferences;
    private SharedPreferences.Editor pEditor;

    private Boolean noted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Shared preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        evaluateurActuel = new Evaluateur(Integer.parseInt(preferences.getString("EVAL_ID", "0")));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addListenerOnButtonClick();
        noteList = new ArrayList<>();
        competenceActuelle = new Competence(getIntent().getIntExtra("ID_COMPETENCE", 0));
        memoireActuel = new Memoire(getIntent().getIntExtra("ID_MEMOIRE", 0), evaluateurActuel.getId());
//        competenceActuelle = new Competence(1);
//        memoireActuel = new Memoire(1);

        fetchNote();

        for (int i = 0; i < noteList.size(); i++) {
            if(
                            (noteList.get(i).getEvaluateurId().getId() == evaluateurActuel.getId()) &&
                                    (noteList.get(i).getMemoireId().getId() == memoireActuel.getId()) &&
                            (noteList.get(i).getCompetenceId().getId() == competenceActuelle.getId())
            ) {
                noted = true;
                Toast.makeText(getApplicationContext(), "NOTE BIEN EXISTANTE", Toast.LENGTH_SHORT).show();
                break;
            }
            else {
                Toast.makeText(getApplicationContext(), "NOTE NON EXISTANTE", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addListenerOnButtonClick() {
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button);
        //Performing action on Button Click
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it on the toast
//                float rating=ratingbar.getRating()*4;
//                Toast.makeText(getApplicationContext(), rating+"", Toast.LENGTH_SHORT).show();
//
//                setNote();
                for (int i = 0; i < noteList.size(); i++) {
                    if(
                            (noteList.get(i).getEvaluateurId().getId() == evaluateurActuel.getId()) &&
                                    (noteList.get(i).getMemoireId().getId() == memoireActuel.getId()) &&
                                    (noteList.get(i).getCompetenceId().getId() == competenceActuelle.getId())
                    ) {
                        noted = true;
                        break;
                    }
                }
                if(noted) {
                    Toast.makeText(getApplicationContext(), "NOTE BIEN EXISTANTE", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "NOTE NON EXISTANTE", Toast.LENGTH_SHORT).show();
                    setNote();
                }
            }

        });
    }


    public void fetchNote() {
        final NoteService note = NoteService.retrofit.create(NoteService.class);
        final Call<List<Note>> call = note.getNote();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Here we go", Toast.LENGTH_SHORT).show();
                    noteList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
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
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setNote() {
        final NoteService note = NoteService.retrofit.create(NoteService.class);
        noteActuelle = new Note(15, competenceActuelle, evaluateurActuel, memoireActuel);
        final Call<Note> call = note.setNote(
                noteActuelle
        );

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Note has been posted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Note has unfortunately not been posted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "This did not work at all", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
