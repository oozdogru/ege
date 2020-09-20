package com.ege.ege.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ege.ege.Items.Works_item;
import com.ege.ege.R;
import com.ege.ege.RecyclerViewAdapters.RecyclerWorks;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Works extends Fragment {

    EditText editText;
    RatingBar ratingBar2;
    RatingBar ratingBar3;
    Button btnAdd;


    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userKonuAdıFromFB;
    ArrayList<Integer> userTeoriFromFB;
    ArrayList<Integer> userPratikFromFB;
    ArrayList<Integer> userYüzdeFromFB;
    ArrayList<Integer> userKaçıncıTekrarFromFB;
    ArrayList<Integer> userAralıkFromFB;
    ArrayList<Integer> userGünSayısı;
    ArrayList<Integer> tarihlist;

    RecyclerWorks feedRecyclerAdapter;
    View v;
    private RecyclerView recyclerview;
    private List<Works_item> works_itemList;


    public Works() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_main, container, false);


        Thread timerThread = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {
                        Date simdikiZaman = new Date();
                        DateFormat df = new SimpleDateFormat("dd");
                        System.out.println(df.format(simdikiZaman));
                        tarihlist.add(Integer.valueOf(df.format(simdikiZaman)));


                    }
                };
                myTimer.schedule(gorev, 60000);
                int i = tarihlist.size();
                if (tarihlist.get(i) != tarihlist.get(i - 1)) {
                    userGünSayısı.add(1);


                }
            }
        };
        timerThread.start();


        editText = v.findViewById(R.id.editText);
        ratingBar2 = v.findViewById(R.id.ratingBar2);
        ratingBar3 = v.findViewById(R.id.ratingBar3);
        btnAdd = v.findViewById(R.id.btnAdd);


        userEmailFromFB = new ArrayList<>();
        userKonuAdıFromFB = new ArrayList<>();
        userTeoriFromFB = new ArrayList<>();
        userPratikFromFB = new ArrayList<>();
        userYüzdeFromFB = new ArrayList<>();
        userKaçıncıTekrarFromFB = new ArrayList<>();
        userAralıkFromFB = new ArrayList<>();
        userGünSayısı = new ArrayList<>();
        tarihlist = new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String userEmail = firebaseUser.getEmail();
                String konu_adı = editText.getText().toString();
                int teori = ratingBar2.getNumStars();
                int pratik = ratingBar3.getNumStars();
                int kaçıncı_tekrar = 1;
                int aralık = ((2 * kaçıncı_tekrar) - 1) * ((teori + pratik) / 2);
                int yüzde = (100 - (100 / 7) * ((2 * kaçıncı_tekrar) - 1));
                int gün = userGünSayısı.size();


                editText.setEnabled(false);
                editText.setText("");
                ratingBar2.setEnabled(false);
                ratingBar2.setRating(0);
                ratingBar2.setEnabled(false);
                ratingBar3.setRating(0);
                ratingBar2.setEnabled(true);
                ratingBar3.setEnabled(true);
                editText.setEnabled(true);
                getDataFromFirestore();


                HashMap<String, Object> postData = new HashMap<>();
                postData.put("useremail", userEmail);
                postData.put("Konu Adı", konu_adı);
                postData.put("Teori", teori);
                postData.put("Pratik", pratik);
                postData.put("Yüzde", yüzde);
                postData.put("Kaçıncı Tekrar", kaçıncı_tekrar);
                postData.put("Aralık", aralık);
                postData.put("Gün", gün);


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


                getDataFromFirestore();
                RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                feedRecyclerAdapter = new RecyclerWorks(userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB, userGünSayısı);
                recyclerView.setAdapter(feedRecyclerAdapter);


            }
        });


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
        setContentView(R.layout.fragment_main);
        feedRecyclerAdapter = new RecyclerWorks(userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB, userGünSayısı);
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {
                        Date simdikiZaman = new Date();
                        DateFormat df = new SimpleDateFormat("dd");
                        System.out.println(df.format(simdikiZaman));
                        tarihlist.add(Integer.valueOf(df.format(simdikiZaman)));


                    }
                };
                myTimer.schedule(gorev, 60000);
                int i = tarihlist.size();
                if (i != 0) {
                    if (i != 1) {
                        if (tarihlist.get(i) != tarihlist.get(i - 1)) {
                            userGünSayısı.add(1);
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userEmail = firebaseUser.getEmail();
                            String konu_adı = editText.getText().toString();
                            int teori = ratingBar2.getNumStars();
                            int pratik = ratingBar3.getNumStars();
                            int kaçıncı_tekrar = 1;
                            int aralık = ((2 * kaçıncı_tekrar) - 1) * ((teori + pratik) / 2);
                            int yüzde = (100 - (100 / 7) * ((2 * kaçıncı_tekrar) - 1));
                            int gün = userGünSayısı.size();

                            HashMap<String, Object> postData = new HashMap<>();
                            postData.put("useremail", userEmail);
                            postData.put("Konu Adı", konu_adı);
                            postData.put("Teori", teori);
                            postData.put("Pratik", pratik);
                            postData.put("Yüzde", yüzde);
                            postData.put("Kaçıncı Tekrar", kaçıncı_tekrar);
                            postData.put("Aralık", aralık);
                            postData.put("Gün", gün);


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
                            getDataFromFirestore();
                            RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            feedRecyclerAdapter = new RecyclerWorks(userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB, userGünSayısı);
                            recyclerView.setAdapter(feedRecyclerAdapter);


                        }
                    }
                }


            }
        };
        timerThread1.start();


    }

    @Override
    public void onPause() {
        super.onPause();
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {
                        Date simdikiZaman = new Date();
                        DateFormat df = new SimpleDateFormat("dd");
                        System.out.println(df.format(simdikiZaman));
                        tarihlist.add(Integer.valueOf(df.format(simdikiZaman)));


                    }
                };
                myTimer.schedule(gorev, 60000);
                int i = tarihlist.size();
                if (tarihlist.get(i) != tarihlist.get(i - 1)) {
                    userGünSayısı.add(1);
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String userEmail = firebaseUser.getEmail();
                    String konu_adı = editText.getText().toString();
                    int teori = ratingBar2.getNumStars();
                    int pratik = ratingBar3.getNumStars();
                    int kaçıncı_tekrar = 1;
                    int aralık = ((2 * kaçıncı_tekrar) - 1) * ((teori + pratik) / 2);
                    int yüzde = (100 - (100 / 7) * ((2 * kaçıncı_tekrar) - 1));
                    int gün = userGünSayısı.size();

                    HashMap<String, Object> postData = new HashMap<>();
                    postData.put("useremail", userEmail);
                    postData.put("Konu Adı", konu_adı);
                    postData.put("Teori", teori);
                    postData.put("Pratik", pratik);
                    postData.put("Yüzde", yüzde);
                    postData.put("Kaçıncı Tekrar", kaçıncı_tekrar);
                    postData.put("Aralık", aralık);
                    postData.put("Gün", gün);


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
                    getDataFromFirestore();
                    RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    feedRecyclerAdapter = new RecyclerWorks(userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB, userGünSayısı);
                    recyclerView.setAdapter(feedRecyclerAdapter);


                }
            }
        };
        timerThread1.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {
                        Date simdikiZaman = new Date();
                        DateFormat df = new SimpleDateFormat("dd");
                        System.out.println(df.format(simdikiZaman));
                        tarihlist.add(Integer.valueOf(df.format(simdikiZaman)));


                    }
                };
                myTimer.schedule(gorev, 60000);
                int i = tarihlist.size();
                if (tarihlist.get(i) != tarihlist.get(i - 1)) {
                    userGünSayısı.add(1);
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String userEmail = firebaseUser.getEmail();
                    String konu_adı = editText.getText().toString();
                    int teori = ratingBar2.getNumStars();
                    int pratik = ratingBar3.getNumStars();
                    int kaçıncı_tekrar = 1;
                    int aralık = ((2 * kaçıncı_tekrar) - 1) * ((teori + pratik) / 2);
                    int yüzde = (100 - (100 / 7) * ((2 * kaçıncı_tekrar) - 1));
                    int gün = userGünSayısı.size();

                    HashMap<String, Object> postData = new HashMap<>();
                    postData.put("useremail", userEmail);
                    postData.put("Konu Adı", konu_adı);
                    postData.put("Teori", teori);
                    postData.put("Pratik", pratik);
                    postData.put("Yüzde", yüzde);
                    postData.put("Kaçıncı Tekrar", kaçıncı_tekrar);
                    postData.put("Aralık", aralık);
                    postData.put("Gün", gün);


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
                    getDataFromFirestore();
                    RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    feedRecyclerAdapter = new RecyclerWorks(userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB, userGünSayısı);
                    recyclerView.setAdapter(feedRecyclerAdapter);


                }
            }
        };
        timerThread1.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {
                        Date simdikiZaman = new Date();
                        DateFormat df = new SimpleDateFormat("dd");
                        System.out.println(df.format(simdikiZaman));
                        tarihlist.add(Integer.valueOf(df.format(simdikiZaman)));


                    }
                };
                myTimer.schedule(gorev, 60000);
                int i = tarihlist.size();
                if (tarihlist.get(i) != tarihlist.get(i - 1)) {
                    userGünSayısı.add(1);
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String userEmail = firebaseUser.getEmail();
                    String konu_adı = editText.getText().toString();
                    int teori = ratingBar2.getNumStars();
                    int pratik = ratingBar3.getNumStars();
                    int kaçıncı_tekrar = 1;
                    int aralık = ((2 * kaçıncı_tekrar) - 1) * ((teori + pratik) / 2);
                    int yüzde = (100 - (100 / 7) * ((2 * kaçıncı_tekrar) - 1));
                    int gün = userGünSayısı.size();

                    HashMap<String, Object> postData = new HashMap<>();
                    postData.put("useremail", userEmail);
                    postData.put("Konu Adı", konu_adı);
                    postData.put("Teori", teori);
                    postData.put("Pratik", pratik);
                    postData.put("Yüzde", yüzde);
                    postData.put("Kaçıncı Tekrar", kaçıncı_tekrar);
                    postData.put("Aralık", aralık);
                    postData.put("Gün", gün);


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
                    getDataFromFirestore();
                    RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    feedRecyclerAdapter = new RecyclerWorks(userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB, userGünSayısı);
                    recyclerView.setAdapter(feedRecyclerAdapter);


                }
            }
        };
        timerThread1.start();
    }

    private void setContentView(int fragment_main) {
    }
}



