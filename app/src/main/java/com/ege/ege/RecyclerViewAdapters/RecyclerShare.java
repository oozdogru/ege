package com.ege.ege.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ege.ege.Items.Share_item;
import com.ege.ege.R;

import java.util.ArrayList;

public class RecyclerShare extends RecyclerView.Adapter<RecyclerShare.PostHolderShare> {

    Context mContextShare;
    ArrayList<Share_item> list_share;

    private ArrayList<String> userEmailList;
    private ArrayList<String> userKonuAdıList;
    private ArrayList<Integer> userTeoriList;
    private ArrayList<Integer> userPratikList;
    private ArrayList<Integer> userYüzdeList;
    private ArrayList<Integer> userKaçıncıTekrarList;
    private ArrayList<Integer> userAralıkList;

    public RecyclerShare(Context mContextShare, ArrayList<Share_item> list_share, ArrayList<String> userEmailList, ArrayList<String> userKonuAdıList, ArrayList<Integer> userTeoriList, ArrayList<Integer> userPratikList, ArrayList<Integer> userYüzdeList, ArrayList<Integer> userKaçıncıTekrarList, ArrayList<Integer> userAralıkList) {
        this.mContextShare = mContextShare;
        this.list_share = list_share;
        this.userEmailList = userEmailList;
        this.userKonuAdıList = userKonuAdıList;
        this.userTeoriList = userTeoriList;
        this.userPratikList = userPratikList;
        this.userYüzdeList = userYüzdeList;
        this.userKaçıncıTekrarList = userKaçıncıTekrarList;
        this.userAralıkList = userAralıkList;
    }

    @NonNull
    @Override
    public PostHolderShare onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_share, parent, false);
        return new PostHolderShare(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolderShare holder, int position) {
        holder.textviewtekrar.setText(userKonuAdıList.get(position));
        holder.progress.setProgress((userYüzdeList.get(position)));
        holder.textView3.setText(userYüzdeList.get(position).toString());


    }

    @Override
    public int getItemCount() {
        return list_share.size();
    }

    public static class PostHolderShare extends RecyclerView.ViewHolder {

        TextView textviewtekrar;
        ProgressBar progress;
        TextView textView3;


        public PostHolderShare(@NonNull View itemView) {
            super(itemView);

            textviewtekrar = itemView.findViewById(R.id.textviewtekrar);
            progress = itemView.findViewById(R.id.progress);
            textView3 = itemView.findViewById(R.id.textView3);

        }
    }
}
