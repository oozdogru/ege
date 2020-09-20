package com.ege.ege.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ege.ege.R;

import java.util.ArrayList;

public class RecyclerWorks extends RecyclerView.Adapter<RecyclerWorks.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userKonuAdıList;
    private ArrayList<Integer> userTeoriList;
    private ArrayList<Integer> userPratikList;
    private ArrayList<Integer> userYüzdeList;
    private ArrayList<Integer> userKaçıncıTekrarList;
    private ArrayList<Integer> userAralıkList;
    private ArrayList<Integer> userGünSayısıList;

    public RecyclerWorks(ArrayList<String> userEmailList, ArrayList<String> userKonuAdıList, ArrayList<Integer> userTeoriList, ArrayList<Integer> userPratikList, ArrayList<Integer> userYüzdeList, ArrayList<Integer> userKaçıncıTekrarList, ArrayList<Integer> userAralıkList, ArrayList<Integer> userGünSayısıList) {
        this.userEmailList = userEmailList;
        this.userKonuAdıList = userKonuAdıList;
        this.userTeoriList = userTeoriList;
        this.userPratikList = userPratikList;
        this.userYüzdeList = userYüzdeList;
        this.userKaçıncıTekrarList = userKaçıncıTekrarList;
        this.userAralıkList = userAralıkList;
        this.userGünSayısıList = userGünSayısıList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_konu, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostHolder holder, final int position) {
        holder.textviewKonu.setText(userKonuAdıList.get(position));
        holder.progress.setProgress((userYüzdeList.get(position)));
        holder.textView3.setText(userYüzdeList.get(position).toString());


    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {
        TextView textviewKonu;
        ProgressBar progress;
        TextView textView3;


        public PostHolder(@NonNull View itemView) {
            super(itemView);

            textviewKonu = itemView.findViewById(R.id.textviewtekrar);
            progress = itemView.findViewById(R.id.progress);
            textView3 = itemView.findViewById(R.id.textView3);


        }
    }


}

