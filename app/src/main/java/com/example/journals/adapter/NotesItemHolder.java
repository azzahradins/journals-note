package com.example.journals.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journals.R;
import com.example.journals.data.Notes;

import java.util.List;

public class NotesItemHolder extends RecyclerView.ViewHolder{

    private TextView tvTitle, tvBody;
    NotesRecyclerAdapter.onItemClickListener listener;
    List<Notes> notesList;

    public NotesItemHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
        this.tvBody = itemView.findViewById(R.id.tv_desc);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positon = getAdapterPosition();
                listener.onItemListener(notesList.get(positon));
            }
        });
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvBody() {
        return tvBody;
    }

}
