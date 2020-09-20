package com.ege.ege.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ege.ege.Items.Share_item;
import com.ege.ege.R;
import com.ege.ege.RecyclerViewAdapters.RecyclerShare;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class Share extends Fragment {
    Locale locale = new Locale("tr");

    View v;
    RecyclerView myrecycylerviewshare;
    TextView textviewtekrar;
    ProgressBar progress;
    TextView textView3;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userKonuAdıFromFB;
    ArrayList<Integer> userTeoriFromFB;
    ArrayList<Integer> userPratikFromFB;
    ArrayList<Integer> userYüzdeFromFB;
    ArrayList<Integer> userKaçıncıTekrarFromFB;
    ArrayList<Integer> userAralıkFromFB;
    RecyclerShare feedRecyclerAdapter;
    ArrayList<Share_item> list_share;
    private RecyclerView.Adapter myadaptershare;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private TextView textView6;
    private TextView textView7;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerTekrar;


    public Share() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.share, container, false);
        textviewtekrar = v.findViewById(R.id.textviewtekrar);
        textView3 = v.findViewById(R.id.textView3);
        progress = v.findViewById(R.id.progress);


        list_share = new ArrayList<>();


        userEmailFromFB = new ArrayList<>();
        userKonuAdıFromFB = new ArrayList<>();
        userTeoriFromFB = new ArrayList<>();
        userPratikFromFB = new ArrayList<>();
        userYüzdeFromFB = new ArrayList<Integer>();
        userKaçıncıTekrarFromFB = new ArrayList<>();
        userAralıkFromFB = new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {

                        getDataFromFirestore();

                    }
                };
                myTimer.schedule(gorev, 60000);

            }
        };
        timerThread1.start();

        RecyclerView myrecyclerviewshare = v.findViewById(R.id.myrecycylerviewshare);
        myrecyclerviewshare.setLayoutManager(new LinearLayoutManager(getContext()));
        feedRecyclerAdapter = new RecyclerShare(getContext(), list_share, userEmailFromFB, userKonuAdıFromFB, userTeoriFromFB, userPratikFromFB, userYüzdeFromFB, userKaçıncıTekrarFromFB, userAralıkFromFB);
        myrecyclerviewshare.setAdapter(feedRecyclerAdapter);


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
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {

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

                                        long k = (long) data.get("Kaçıncı Tekrar");
                                        int kaçıncı_tekrar = (int) k;

                                        //long g = (long) data.get("Geçen Gün");
                                        //int gün= (int) g;


                                        userEmailFromFB.add(userEmail);
                                        userKonuAdıFromFB.add(konu_adı);
                                        userYüzdeFromFB.add(yüzde);
                                        userKaçıncıTekrarFromFB.add(kaçıncı_tekrar);
                                        //userGeçenGünFromFB.add(gün);


                                        feedRecyclerAdapter.notifyDataSetChanged();

                                    }


                                }

                            }
                        });


                    }
                };
                myTimer.schedule(gorev, 60000);

            }
        };
        timerThread1.start();


    }

    @Override
    public void onStart() {
        super.onStart();
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {

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

                                        long k = (long) data.get("Kaçıncı Tekrar");
                                        int kaçıncı_tekrar = (int) k;

                                        //long g = (long) data.get("Geçen Gün");
                                        //int gün= (int) g;


                                        userEmailFromFB.add(userEmail);
                                        userKonuAdıFromFB.add(konu_adı);
                                        userYüzdeFromFB.add(yüzde);
                                        userKaçıncıTekrarFromFB.add(kaçıncı_tekrar);
                                        //userGeçenGünFromFB.add(gün);


                                        feedRecyclerAdapter.notifyDataSetChanged();

                                    }


                                }

                            }
                        });


                    }
                };
                myTimer.schedule(gorev, 60000);

            }
        };
        timerThread1.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        Thread timerThread1 = new Thread() {
            public void run() {
                Timer myTimer = new Timer();

                TimerTask gorev = new TimerTask() {

                    @Override
                    public void run() {

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

                                        long k = (long) data.get("Kaçıncı Tekrar");
                                        int kaçıncı_tekrar = (int) k;

                                        //long g = (long) data.get("Geçen Gün");
                                        //int gün= (int) g;


                                        userEmailFromFB.add(userEmail);
                                        userKonuAdıFromFB.add(konu_adı);
                                        userYüzdeFromFB.add(yüzde);
                                        userKaçıncıTekrarFromFB.add(kaçıncı_tekrar);
                                        //userGeçenGünFromFB.add(gün);


                                        feedRecyclerAdapter.notifyDataSetChanged();

                                    }


                                }

                            }
                        });


                    }
                };
                myTimer.schedule(gorev, 60000);

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

                                        long k = (long) data.get("Kaçıncı Tekrar");
                                        int kaçıncı_tekrar = (int) k;

                                        //long g = (long) data.get("Geçen Gün");
                                        //int gün= (int) g;


                                        userEmailFromFB.add(userEmail);
                                        userKonuAdıFromFB.add(konu_adı);
                                        userYüzdeFromFB.add(yüzde);
                                        userKaçıncıTekrarFromFB.add(kaçıncı_tekrar);
                                        //userGeçenGünFromFB.add(gün);


                                        feedRecyclerAdapter.notifyDataSetChanged();

                                    }


                                }

                            }
                        });


                    }
                };
                myTimer.schedule(gorev, 60000);

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

                                        long k = (long) data.get("Kaçıncı Tekrar");
                                        int kaçıncı_tekrar = (int) k;

                                        //long g = (long) data.get("Geçen Gün");
                                        //int gün= (int) g;


                                        userEmailFromFB.add(userEmail);
                                        userKonuAdıFromFB.add(konu_adı);
                                        userYüzdeFromFB.add(yüzde);
                                        userKaçıncıTekrarFromFB.add(kaçıncı_tekrar);
                                        //userGeçenGünFromFB.add(gün);


                                        feedRecyclerAdapter.notifyDataSetChanged();

                                    }


                                }

                            }
                        });


                    }
                };
                myTimer.schedule(gorev, 60000);

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

                                        long k = (long) data.get("Kaçıncı Tekrar");
                                        int kaçıncı_tekrar = (int) k;

                                        //long g = (long) data.get("Geçen Gün");
                                        //int gün= (int) g;


                                        userEmailFromFB.add(userEmail);
                                        userKonuAdıFromFB.add(konu_adı);
                                        userYüzdeFromFB.add(yüzde);
                                        userKaçıncıTekrarFromFB.add(kaçıncı_tekrar);
                                        //userGeçenGünFromFB.add(gün);


                                        feedRecyclerAdapter.notifyDataSetChanged();

                                    }


                                }

                            }
                        });


                    }
                };
                myTimer.schedule(gorev, 60000);

            }
        };
        timerThread1.start();
    }
}




