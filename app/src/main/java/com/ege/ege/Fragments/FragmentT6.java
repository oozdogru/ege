package com.ege.ege.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ege.ege.Items.Tekrarlar_item;
import com.ege.ege.R;
import com.ege.ege.RecyclerViewAdapters.RecyclerT6;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FragmentT6 extends Fragment {
    Locale locale = new Locale("tr");
    TextView textviewKonu;
    RatingBar ratingBar10;
    RatingBar ratingBar7;
    Button button3;


    RecyclerT6 feedRecyclerAdapter;
    private FirebaseAuth firebaseAuth;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userKonuAdıFromFB;
    ArrayList<Integer> userTeoriFromFB;
    ArrayList<Integer> userPratikFromFB;
    ArrayList<Integer> userYüzdeFromFB;
    ArrayList<Integer> userKaçıncıTekrarFromFB;
    ArrayList<Integer> userAralıkFromFB;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Tekrarlar_item> list_tekrar;

    View v;
    private RecyclerView recyclerTekrar7;
    private List<Tekrarlar_item> listtekrar;


    public FragmentT6() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.t6, container, false);
        textviewKonu = v.findViewById(R.id.textviewtekrar);
        ratingBar10 = v.findViewById(R.id.ratingBar10);
        ratingBar7 = v.findViewById(R.id.ratingBar7);
        button3 = v.findViewById(R.id.button3);

        list_tekrar = new ArrayList<>();


        userEmailFromFB = new ArrayList<>();
        userKonuAdıFromFB = new ArrayList<>();
        userTeoriFromFB = new ArrayList<>();
        userPratikFromFB = new ArrayList<>();
        userYüzdeFromFB = new ArrayList<>();
        userKaçıncıTekrarFromFB = new ArrayList<>();
        userAralıkFromFB = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        /*button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String userEmail = firebaseUser.getEmail();
                String konu_adı =textviewKonu.getText().toString();
                int teori = ratingBar10.getNumStars();
                int pratik = ratingBar7.getNumStars();
                int kaçıncı_tekrar = 1;
                int aralık = ((2*kaçıncı_tekrar)-1)*((teori + pratik)/2);
                int yüzde = (100-(100/7)*((2*kaçıncı_tekrar)-1));

                HashMap<String, Object> postData = new HashMap<>();
                postData.put("useremail",userEmail);
                postData.put("Konu Adı",konu_adı);
                postData.put("Teori",teori);
                postData.put("Pratik",pratik);
                postData.put("Yüzde",yüzde);
                postData.put("Kaçıncı Tekrar",kaçıncı_tekrar);
                postData.put("Aralık",aralık);

                postData.put("date", FieldValue.serverTimestamp());
                firebaseFirestore.collection("Posts").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });*/
        getDataFromFirestore();
        RecyclerView recyclerView = v.findViewById(R.id.recyclerTekrar7);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        feedRecyclerAdapter = new RecyclerT6(getContext(), list_tekrar, userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB);
        recyclerView.setAdapter(feedRecyclerAdapter);

        return v;
    }

    public void getDataFromFirestore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        //Casting
                        String userEmail = (String) data.get("userEmail");
                        String konu_adı = (String) data.get("Konu Adı");
                        long y = (long) data.get("Yüzde");
                        int yüzde = (int) y;


                        userEmailFromFB.add(userEmail);
                        userKonuAdıFromFB.add(konu_adı);
                        userYüzdeFromFB.add(yüzde);


                        feedRecyclerAdapter.notifyDataSetChanged();

                    }


                }

            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listtekrar = new ArrayList<>();

    }
}
