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
import sn.ept.evaluateurapp.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{
    Context context;
    private List<Note> noteList;
    private NoteAdapter.NoteAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView noteValue, nomCompetence, auteur;

        public MyViewHolder(View view) {
            super(view);
            noteValue = view.findViewById(R.id.note1);
            nomCompetence = view.findViewById(R.id.competence1);
            auteur = view.findViewById(R.id.author1);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected competence in callback

                    listener.onNoteSelected(noteList.get(getAdapterPosition()));
                }
            });
        }
    }

    public NoteAdapter(Context context, List<Note> noteList, NoteAdapter.NoteAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.noteList = noteList;
    }


    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.detail_note, viewGroup, false);

        return new NoteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder myViewHolder, int i) {
        final Note note = noteList.get(i);
        myViewHolder.noteValue.setText(note.getNote()+"");
        myViewHolder.nomCompetence.setText(note.getCompetenceId().getNom());
        myViewHolder.auteur.setText(note.getEvaluateurId().getLastName());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface NoteAdapterListener {
        void onNoteSelected(Note note);
    }
}
