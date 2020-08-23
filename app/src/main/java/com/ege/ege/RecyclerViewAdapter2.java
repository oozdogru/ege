package com.ege.ege;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.PostHolder> {
    private static final String TAG = "RecViewAdapter2";
    Context mContext;
    ArrayList<Tekrarlar_item> list_tekrar;
    private ArrayList<String> userEmailList;
    private ArrayList<String> userKonuAdıList;
    private ArrayList<Integer> userTeoriList;
    private ArrayList<Integer> userPratikList;
    private ArrayList<Integer> userYüzdeList;
    private ArrayList<Integer> userKaçıncıTekrarList;
    private ArrayList<Integer> userAralıkList;

    public RecyclerViewAdapter2(Context mContext, ArrayList<Tekrarlar_item> list_tekrar, ArrayList<String> userEmailList, ArrayList<String> userKonuAdıList, ArrayList<Integer> userTeoriList, ArrayList<Integer> userPratikList, ArrayList<Integer> userYüzdeList, ArrayList<Integer> userKaçıncıTekrarList, ArrayList<Integer> userAralıkList) {
        this.userEmailList = userEmailList;
        this.userKonuAdıList = userKonuAdıList;
        this.userTeoriList = userTeoriList;
        this.userPratikList = userPratikList;
        this.userYüzdeList = userYüzdeList;
        this.userKaçıncıTekrarList = userKaçıncıTekrarList;
        this.userAralıkList = userAralıkList;
        this.mContext = mContext;
        this.list_tekrar = list_tekrar;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tekrar, parent, false);
        return new PostHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final PostHolder holder, final int position) {
        holder.textviewtekrar.setText(userKonuAdıList.get(position));
        holder.progress.setProgress((userYüzdeList.get(position)));
        holder.textView3.setText(userYüzdeList.get(position).toString());

        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


                String konu_adı = holder.textviewtekrar.getText().toString();
                int teori = (int) holder.ratingBar10.getRating();
                int pratik = (int) holder.ratingBar7.getRating();
                int kaçıncı_tekrar = 1;
                int aralık = ((2 * kaçıncı_tekrar) - 1) * ((teori + pratik) / 2);
                int yüzde = (100 - (100 / 7) * ((2 * kaçıncı_tekrar) - 1));

                HashMap<String, Object> postData = new HashMap<>();
                postData.put("Konu Adı", konu_adı);
                postData.put("Teori", teori);
                postData.put("Pratik", pratik);
                postData.put("Yüzde", yüzde);
                postData.put("Kaçıncı Tekrar", kaçıncı_tekrar);
                postData.put("Aralık", aralık);
                postData.put("date", FieldValue.serverTimestamp());
                firebaseFirestore.collection("Posts").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "onSuccess: ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: ");
                    }
                });

                /*FirebaseFirestore.getInstance().collection("Posts").document()
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "", e);
                            }
                        });*/


            }
        });

    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {
        TextView textviewtekrar;
        ProgressBar progress;
        TextView textView3;
        RatingBar ratingBar10;
        RatingBar ratingBar7;
        Button button3;


        public PostHolder(@NonNull View itemView) {
            super(itemView);

            textviewtekrar = itemView.findViewById(R.id.textviewtekrar);
            progress = itemView.findViewById(R.id.progress);
            textView3 = itemView.findViewById(R.id.textView3);
            ratingBar10 = itemView.findViewById(R.id.ratingBar10);
            ratingBar7 = itemView.findViewById(R.id.ratingBar7);
            button3 = itemView.findViewById(R.id.button3);

        }
    }


}

