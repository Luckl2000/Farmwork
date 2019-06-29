package com.example.farmwork;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeFragment extends Fragment {

    private TextView transzoom;
    DatabaseReference myRef, mytest;
    Firebase ref2,myFirebaseKlick, myFirebaseKlick2, mytest2;
    ProgressBar progressup;
    public static Integer myIndex;
    FirebaseListAdapter adapter;
    ListView lv;
    ImageView myadd, ham;
    ImageView menuimg;
    TextView mytextstart, mytextend;
    public static String myItemRef;
    public static Integer check, ing;
    private DrawerLayout drawer;

    Integer myStringklickupdate;
    String myKeyValueklickupdate;
    public Integer ll = 0;
    Integer k = 0;
    Button testbutton,test;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Firebase.setAndroidContext(getActivity());

        ing = 0;

        progressup = getView().findViewById(R.id.progressbar);

        mytextstart = (TextView) getView().findViewById(R.id.textstart);
        mytextend = (TextView) getView().findViewById(R.id.textend);

        // Initiate Firebase

        mytest = FirebaseDatabase.getInstance().getReference().child(emptydr.UserId +
                "/Info/Counter/Daysdone");
        mytest2 = new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId + "/Info/Counter/Daysdone");
        mytest.keepSynced(true);



        //button zu change site

        myadd = (ImageView) getView().findViewById(R.id.imageView13);

        myadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddData();
            }
        });





        refreshcounter();

        lv = (ListView) getView().findViewById(R.id.ListViewmain);
        ref2 = new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId + "/Data");


        Query query = FirebaseDatabase.getInstance().getReference().child(emptydr.UserId +
                "/Data").orderByChild("TimestampID");
        FirebaseListOptions<Dateraw> options = new FirebaseListOptions.Builder<Dateraw>()
                .setLayout(R.layout.entitydata)
                .setQuery(query, Dateraw.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView dates = v.findViewById(R.id.DateID);
                TextView farms = v.findViewById(R.id.Farm);
                TextView froms = v.findViewById(R.id.From);
                TextView untils = v.findViewById(R.id.Until);
                LinearLayout linears = v.findViewById(R.id.Linear);


                Dateraw dar = (Dateraw) model;
                dates.setText(dar.getDateID());
                farms.setText(dar.getFarm());
                froms.setText(dar.getFrom());
                untils.setText(dar.getUntil());
                /*Integer klicks = dar.getKlick();
                if (klicks == 0) {
                    linears.setBackgroundResource(R.drawable.recht);
                } else {
                    linears.setBackgroundResource(R.drawable.paid);
                }*/


            }
        };
        lv.setAdapter(adapter);

        /*myFirebaseKlick =
                new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId +
                        "/Data/" + myItemRef);
        myFirebaseKlick2 =
                new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId +
                        "/Data/" + myItemRef + "/Klick");




        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                           long id) {
                DatabaseReference itemRef = adapter.getRef(position);
                myItemRef = itemRef.getKey();


                myFirebaseKlick2.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Integer value = dataSnapshot.getValue(Integer.class);
                        ll = value;

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


                if (ll == 0) {
                    view.setBackgroundResource(R.drawable.paid);

                    myStringklickupdate = 1;
                    myKeyValueklickupdate = "Klick";

                    Firebase myNewChildklickupdate = myFirebaseKlick.child(myKeyValueklickupdate);
                    myNewChildklickupdate.setValue(myStringklickupdate);


                } else {
                    view.setBackgroundResource(R.drawable.recht);

                    myStringklickupdate = 0;
                    myKeyValueklickupdate = "Klick";

                    Firebase myNewChildklickupdate = myFirebaseKlick.child(myKeyValueklickupdate);
                    myNewChildklickupdate.setValue(myStringklickupdate);


                }

                return true;
            }
        });*/

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public EditText dateconv;

            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long id) {

                k++;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        DatabaseReference itemRef = adapter.getRef(position);
                        myItemRef = itemRef.getKey();

                        emptydr.checker = 0;
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).commit();

                    }
                }, 500);
            }


        });


        transzoom = getView().findViewById(R.id.textView);
        transzoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();



        adapter.startListening();



    }


    public void refreshcounter() {



        mytest.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer value = dataSnapshot.getValue(Integer.class);
                    myIndex = value;
                    mytextstart.setText(value.toString());
                    Integer restdays = 88 - value;
                    mytextend.setText(String.valueOf(restdays));

                    int helpprogress = (int) (1.136 * (value + 1));
                    progressup.setProgress(helpprogress);
                } else {
                    mytest.setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void openaddData() {


        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddFragment()).commit();


    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.getActivity().onBackPressed();
        }

    }
}
