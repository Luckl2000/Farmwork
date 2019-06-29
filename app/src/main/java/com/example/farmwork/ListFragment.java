package com.example.farmwork;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ListFragment extends Fragment {

    ListView lv;
    Firebase ref2;
    FirebaseListAdapter adapter;
    Firebase myFirebaseInfo, myFirebaseUpdate;
    public static String myFrom,pos;
   public static String myItemRef;
    ImageView menuimg;
    String myKeyValueklickupdate;
    Integer k = 0;
    Integer kk;
    public LinearLayout linear;
    Integer myStringklickupdate;
    public Integer ll=0;
    public static int att[];
    Firebase myFirebaseKlick,myFirebaseKlick2;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.activity_data_list,container,false);

    }

    @Override
    public void onStart() {
        super.onStart();


        HomeFragment.check = 1;

        att = new int[HomeFragment.myIndex];
        Firebase.setAndroidContext(getActivity());

        final String DeviceID = Settings.Secure.getString(getActivity().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);


        lv = (ListView) getView().findViewById(R.id.ListViewZoom);
        ref2 = new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId +"/Data");

        myFirebaseInfo = new Firebase( "https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId +"/Data");

        Query query = FirebaseDatabase.getInstance().getReference().child( emptydr.UserId + "/Data").orderByChild("TimestampID");
        FirebaseListOptions<Dateraw> options = new FirebaseListOptions.Builder<Dateraw>()
                .setLayout(R.layout.entitydata)
                .setQuery(query,Dateraw.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            public void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView dates = v.findViewById(R.id.DateID);
                TextView farms = v.findViewById(R.id.Farm);
                TextView froms = v.findViewById(R.id.From);
                TextView untils = v.findViewById(R.id.Until);
                LinearLayout linears = v.findViewById(R.id.Linear);







                Dateraw dar = (Dateraw) model;
                dates.setText(dar.getDateID().toString());
                farms.setText(dar.getFarm().toString());
                froms.setText(dar.getFrom().toString());
                untils.setText(dar.getUntil().toString());
                Integer klicks = dar.getKlick();
                if (klicks == 0) {
                    linears.setBackgroundResource(R.drawable.recht);
                } else {
                    linears.setBackgroundResource(R.drawable.paid);
                }

            }
        };
        lv.setAdapter(adapter);

    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            DatabaseReference itemRef = adapter.getRef(position);
            myItemRef = itemRef.getKey();
            myFirebaseKlick = new Firebase( "https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId +"/Data/"+ myItemRef);
            myFirebaseKlick2 = new Firebase( "https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId +"/Data/"+ myItemRef+"/Klick");
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


            } else  {
                view.setBackgroundResource(R.drawable.recht);

                myStringklickupdate = 0;
                myKeyValueklickupdate = "Klick";

                Firebase myNewChildklickupdate = myFirebaseKlick.child(myKeyValueklickupdate);
                myNewChildklickupdate.setValue(myStringklickupdate);


            }

            return true;
        }
    });

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


                        emptydr.checker = 1;
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).commit();

                    }
                },500);


            }


        });



        adapter.startListening();
    }





    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}




