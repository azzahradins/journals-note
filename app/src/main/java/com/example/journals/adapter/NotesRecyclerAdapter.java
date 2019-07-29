package com.example.journals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journals.R;
import com.example.journals.data.Notes;

import org.w3c.dom.Text;

import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.NotesItemHolder>{

    private Context context;
    public List<Notes> notesList;
    private onItemClickListener listener;

    public NotesRecyclerAdapter(Context context)
    {
        this.context = context;
    }

    public List<Notes> getNotesList()
    {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList){
        this.notesList = notesList;
        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);
        // Render view pakai inflater
        View notesItemView = inflater.inflate(R.layout.list_notes, parent, false);
        // View-nya dilempar ke ViewHolder
        NotesItemHolder vhNotesItem = new NotesItemHolder(notesItemView);
        // Jadikan nilai balik method ini
        return vhNotesItem;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesItemHolder holder, int position) {
        Notes currentNotes = this.notesList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String title = currentNotes.getTitle();
        String body = currentNotes.getBody();

        holder.tvTitle.setText(title);
        holder.tvBody.setText(body);
    }

    @Override
    public int getItemCount() {
        if(this.notesList == null) return 0;
        else return notesList.size();
    }

    public Notes getPositionAt(int position){
        return notesList.get(position);
    }

    class NotesItemHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle = itemView.findViewById(R.id.tv_title);
        private TextView tvBody = itemView.findViewById(R.id.tv_desc);


        public NotesItemHolder (View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon = getAdapterPosition();
                    listener.onItemListener(notesList.get(positon));
                }
            });
        }
    }
    public interface onItemClickListener{
        void onItemListener(Notes notes);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }
}
 // 