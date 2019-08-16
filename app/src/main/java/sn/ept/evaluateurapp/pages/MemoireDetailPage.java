package sn.ept.evaluateurapp.pages;

import android.content.Intent;
import android.os.Bundle;
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
import sn.ept.evaluateurapp.models.Resume;
import sn.ept.evaluateurapp.webservices.ResumeService;

public class MemoireDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoire_detail_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getIntent().getStringExtra("NOM_AUTEUR"));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), FicheNotePage.class);
                i1.putExtra("ID_MEMOIRE", getIntent().getIntExtra("ID_MEMOIRE", 0));

                startActivity(i1);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(getApplicationContext(), DeliberationPage.class);
                i2.putExtra("ID_MEMOIRE", getIntent().getIntExtra("ID_MEMOIRE", 0));

                startActivity(i2);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fetchResume();
    }

    public void fetchResume()
    {
        final ResumeService memoire = ResumeService.retrofit.create(ResumeService.class);
        final Call<List<Resume>> call = memoire.getResumes();

        call.enqueue(new Callback<List<Resume>>() {
            @Override
            public void onResponse(Call<List<Resume>> call, Response<List<Resume>> response) {
                Toast.makeText(getApplicationContext(), "Resume retrieved", Toast.LENGTH_SHORT).show();
                TextView resume = findViewById(R.id.resume);
                for (int i = 0; i < response.body().size(); i++) {
                    if(getIntent().getIntExtra("ID_MEMOIRE", 0) == response.body().get(i).getMemoireId().getId()) {
                        resume.setText(response.body().get(i).getResume());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Resume>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Resumes retrieved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
