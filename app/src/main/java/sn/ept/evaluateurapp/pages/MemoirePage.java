package sn.ept.evaluateurapp.pages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.evaluateurapp.R;
import sn.ept.evaluateurapp.adapters.MemoireAdapter;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.webservices.MemoireService;

public class MemoirePage extends AppCompatActivity implements MemoireAdapter.MemoireAdapterListener{

    private RecyclerView cardList;
    private List<Memoire> memoireList;
    private MemoireAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoire_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Liste des sujets de PFE");

        cardList = findViewById(R.id.cardList);
        memoireList = new ArrayList<>();

        mAdapter = new MemoireAdapter(this, memoireList, this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        cardList.setLayoutManager(mLayoutManager);
        cardList.setItemAnimator(new DefaultItemAnimator());
        cardList.setAdapter(mAdapter);

        fetchMemoires(getIntent().getIntExtra("FILTER", 0));

    }

    public void fetchMemoires(int idCategorie)
    {
        final MemoireService memoire = MemoireService.retrofit.create(MemoireService.class);
        final Call<List<Memoire>> call = memoire.getMemoires();

        call.enqueue(new Callback<List<Memoire>>() {
            @Override
            public void onResponse(Call<List<Memoire>> call, Response<List<Memoire>> response) {
                Toast.makeText(getApplicationContext(), "List retrieved", Toast.LENGTH_SHORT).show();
                memoireList.clear();
                for (int i = 0; i < response.body().size(); i++) {
                    memoireList.add(new Memoire(
                                    response.body().get(i).getId(),
                                    response.body().get(i).getSujet(),
                                    response.body().get(i).getLangue(),
                                    response.body().get(i).getLieu(),
                                    response.body().get(i).getEtudiantId()
                            )
                    );
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Memoire>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "List not retrieved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMemoireSelected(Memoire memoire) {
        
    }
}
