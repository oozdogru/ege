package com.ege.ege.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ege.ege.Items.Tekrarlar_item;
import com.ege.ege.R;

import java.util.ArrayList;

public class RecyclerT2 extends RecyclerView.Adapter<RecyclerT2.PostHolderT2> {

    Context mContext;
    ArrayList<Tekrarlar_item> list_tekrar;

    private ArrayList<String> userEmailList;
    private ArrayList<String> userKonuAdıList;
    private ArrayList<Integer> userTeoriList;
    private ArrayList<Integer> userPratikList;
    private ArrayList<Integer> userYüzdeList;
    private ArrayList<Integer> userKaçıncıTekrarList;
    private ArrayList<Integer> userAralıkList;

    public RecyclerT2(Context mContext, ArrayList<Tekrarlar_item> list_tekrar, ArrayList<String> userEmailList, ArrayList<String> userKonuAdıList, ArrayList<Integer> userTeoriList, ArrayList<Integer> userPratikList, ArrayList<Integer> userYüzdeList, ArrayList<Integer> userKaçıncıTekrarList, ArrayList<Integer> userAralıkList) {
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
    public PostHolderT2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tekrar, parent, false);

        return new PostHolderT2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolderT2 holder, int position) {
        holder.textviewKonu.setText(userKonuAdıList.get(position));
        holder.progress.setProgress((userYüzdeList.get(position)));
        holder.textView3.setText(userYüzdeList.get(position).toString());


    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    public static class PostHolderT2 extends RecyclerView.ViewHolder {


        TextView textviewKonu;
        ProgressBar progress;
        TextView textView3;
        RatingBar ratingBar10;
        RatingBar ratingBar7;
        Button button3;


        public PostHolderT2(@NonNull View itemView) {
            super(itemView);

            textviewKonu = itemView.findViewById(R.id.textviewtekrar);
            progress = itemView.findViewById(R.id.progress);
            textView3 = itemView.findViewById(R.id.textView3);
            ratingBar10 = itemView.findViewById(R.id.ratingBar10);
            ratingBar7 = itemView.findViewById(R.id.ratingBar7);
            button3 = itemView.findViewById(R.id.button3);

        }
    }
}
