package sn.ept.evaluateurapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sn.ept.evaluateurapp.R;
import sn.ept.evaluateurapp.models.Competence;

public class CompetenceAdapter extends RecyclerView.Adapter<CompetenceAdapter.MyViewHolder>{
    Context context;
    private List<Competence> competenceList;
    private CompetenceAdapter.CompetenceAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomCompetence;

        public MyViewHolder(View view) {
            super(view);
            nomCompetence = view.findViewById(R.id.nomCompetence);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected competence in callback

                    listener.onCompetenceSelected(competenceList.get(getAdapterPosition()));
                }
            });
        }
    }

    public CompetenceAdapter(Context context, List<Competence> competenceList, CompetenceAdapter.CompetenceAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.competenceList = competenceList;
    }


    @NonNull
    @Override
    public CompetenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.detail_competence, viewGroup, false);

        return new CompetenceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompetenceAdapter.MyViewHolder myViewHolder, int i) {
        final Competence competence = competenceList.get(i);
        myViewHolder.nomCompetence.setText(competence.getNom());
    }

    @Override
    public int getItemCount() {
        return competenceList.size();
    }

    public interface CompetenceAdapterListener {
        void onCompetenceSelected(Competence competence);
    }
}
