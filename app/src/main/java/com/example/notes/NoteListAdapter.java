package com.example.notes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
// standard listadapter from recycler view which basicaly manage showing the note
    public interface ItemClickListener {
        public void onItemClicked(Note note);
        public void onItemLongClicked(Note note);
    }
    private ItemClickListener listener;


    public class NoteViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{
        private final TextView noteText;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.note_text);
            itemView.findViewById(R.id.item_root).setOnClickListener(this);
            itemView.findViewById(R.id.item_root).setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            NoteListAdapter.this.onItemClicked(this.getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            NoteListAdapter.this.onItemLongClicked(this.getLayoutPosition());
            return true;
        }
    }



    private final LayoutInflater inflater;
    private List<Note> notes;

    public NoteListAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (notes != null){
            Note current = notes.get(position);
            holder.noteText.setText(current.getNoteText());
        }
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (notes != null)
            return notes.size();
        else return 0;
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    private void onItemClicked(int index) {
        if(this.listener != null){
            this.listener.onItemClicked(this.notes.get(index));
        }
    }

    private void onItemLongClicked(int index) {
        if(this.listener != null){
            this.listener.onItemLongClicked(this.notes.get(index));
        }
    }


}
