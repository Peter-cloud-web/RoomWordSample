package com.seanpetersgichia.roomwordsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {
    private List<Word> mWords;
    private final LayoutInflater mInflater;

    WordListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordListAdapter.WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview,parent,false);
        return new WordListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordListViewHolder holder, int position) {
        if(mWords != null){
            holder.wordItemView.setText(mWords.get(position).getWord());
        }
        holder.wordItemView.setText("No word found");

    }

    void setWords(List<Word> words){
      mWords = words;
      notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class WordListViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        public WordListViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
