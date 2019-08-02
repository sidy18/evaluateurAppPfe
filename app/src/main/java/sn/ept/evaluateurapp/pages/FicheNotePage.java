package sn.ept.evaluateurapp.pages;

import android.content.Intent;
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
import sn.ept.evaluateurapp.adapters.CompetenceAdapter;
import sn.ept.evaluateurapp.models.Competence;
import sn.ept.evaluateurapp.webservices.CompetenceService;

public class FicheNotePage extends AppCompatActivity implements CompetenceAdapter.CompetenceAdapterListener{

    private RecyclerView cardList2;
    private List<Competence> competenceList;
    private CompetenceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_note_page);

        getSupportActionBar().setTitle("FICHE DE NOTES (Competences)");

        cardList2 = findViewById(R.id.cardList2);
        competenceList = new ArrayList<>();

        mAdapter = new CompetenceAdapter(this, competenceList, this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        cardList2.setLayoutManager(mLayoutManager);
        cardList2.setItemAnimator(new DefaultItemAnimator());
        cardList2.setAdapter(mAdapter);

        fetchCompetences();
    }

    public void fetchCompetences()
    {
        final CompetenceService memoire = CompetenceService.retrofit.create(CompetenceService.class);
        final Call<List<Competence>> call = memoire.getCompetences();

        call.enqueue(new Callback<List<Competence>>() {
            @Override
            public void onResponse(Call<List<Competence>> call, Response<List<Competence>> response) {
                Toast.makeText(getApplicationContext(), "List retrieved", Toast.LENGTH_SHORT).show();
                competenceList.clear();
                for (int i = 0; i < response.body().size(); i++) {
                    competenceList.add(new Competence(
                                    response.body().get(i).getId(),
                                    response.body().get(i).getNom(),
                                    response.body().get(i).getCoefficient()
                            )
                    );
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Competence>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "List not retrieved", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCompetenceSelected(Competence competence) {
        Intent i = new Intent(getApplicationContext(), MemoireDetailPage.class);
        i.putExtra("ID_COMPETENCE", competence.getId());
        startActivity(i);
    }
}
