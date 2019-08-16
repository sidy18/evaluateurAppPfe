package sn.ept.evaluateurapp.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.evaluateurapp.R;
import sn.ept.evaluateurapp.models.Evaluateur;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.models.Note;
import sn.ept.evaluateurapp.webservices.NoteService;

public class DeliberationPage extends AppCompatActivity {
    private Memoire memoireActuel;
    private Evaluateur evaluateurActuel;
    private SharedPreferences preferences;
    private float totalPoints = 0;
    private Integer totalCoeff = 0;
    TextView noteView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliberation_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Shared preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        evaluateurActuel = new Evaluateur(Integer.parseInt(preferences.getString("EVAL_ID", "0")));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fetchNote();
        memoireActuel = new Memoire(getIntent().getIntExtra("ID_MEMOIRE", 0), evaluateurActuel.getId());
        noteView = findViewById(R.id.noteActu);

    }



    public void fetchNote() {
        final NoteService note = NoteService.retrofit.create(NoteService.class);
        final Call<List<Note>> call = note.getNote();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Here we go", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < response.body().size(); i++) {
                        if((response.body().get(i).getMemoireId().getId() == memoireActuel.getId())) {
                            totalPoints += response.body().get(i).getNote()*response.body().get(i).getCompetenceId().getCoefficient();
                            totalCoeff += response.body().get(i).getCompetenceId().getCoefficient();
                        }
                    }
                    noteView.setText(((float)totalPoints/(float)totalCoeff)+"");
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadNotes(View v) {
        Intent i1 = new Intent(getApplicationContext(), ListNotePage.class);
        i1.putExtra("ID_MEMOIRE", getIntent().getIntExtra("ID_MEMOIRE", 0));
        startActivity(i1);
    }

}
