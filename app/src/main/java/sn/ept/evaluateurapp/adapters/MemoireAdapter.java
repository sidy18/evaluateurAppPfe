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
import sn.ept.evaluateurapp.models.Memoire;

public class MemoireAdapter  extends RecyclerView.Adapter<MemoireAdapter.MyViewHolder>{
    Context context;
    private List<Memoire> memoireList;
    private MemoireAdapter.MemoireAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected memoire in callback

                    listener.onMemoireSelected(memoireList.get(getAdapterPosition()));
                }
            });
        }
    }

    public MemoireAdapter(Context context, List<Memoire> memoireList, MemoireAdapter.MemoireAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.memoireList = memoireList;
    }


    @NonNull
    @Override
    public MemoireAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.detail_memoire, viewGroup, false);

        return new MemoireAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoireAdapter.MyViewHolder myViewHolder, int i) {
        final Memoire memoire = memoireList.get(i);
        myViewHolder.title.setText(memoire.getSujet());
        myViewHolder.author.setText(memoire.getEtudiantId().getEmail());
    }

    @Override
    public int getItemCount() {
        return memoireList.size();
    }

    public interface MemoireAdapterListener {
        void onMemoireSelected(Memoire memoire);
    }
}
