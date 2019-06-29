package com.example.farmwork;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.sql.Timestamp;
import java.util.Calendar;

public class AddFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {
    TextView date;
    public static TextView test;
    int f = 0;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    int cero1;
    public Timestamp fuckyeah, myStringDataTime;
    Calendar calendar;

    EditText myEditFrom, myEditUntil, myEditFarm, myKeyValue, myKeyValuechange;
    Button myApplyBt;

    String myStringDataFrom, myKeyValueDataFrom, myKeyValueDataTime, myKeyValueklick, myStringDataUntil, myKeyValueDataUntil, myStringDataFarm, myKeyValueDataFarm, myKeyValueCounter, myStringDataDateID, myKeyValueDataDateID, myKeyValueDataIndex;
    Integer myStringCounter, myStringDatamyIndex, myStringklick;
    public String textaus, pos;
    public static Timestamp textaus2;
    Integer update;
    ImageView menuimg;


    Firebase myFirebaseData, myFirebaseInfo, myFirebaseCheck;
    public static TextView textViewus;
    String value;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_add, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();


        menuimg = (ImageView) getView().findViewById(R.id.imageView26);


        EditText selectDate = (EditText) getView().findViewById(R.id.tvSelectedDate);
        date = getView().findViewById(R.id.tvSelectedDate);


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                date.setText(day + "-" + (month + 1) + "-" + year);
                                textaus = day + "-" + (month + 1) + "-" + year;

                                fuckyeah = Timest.convertStringToTimestamp(textaus);
                                textaus2 = fuckyeah;

                            }
                        }, year, month, dayOfMonth);
                Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR, -2); // subtract 2 years from now
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis() - 1000);
                datePickerDialog.show();


            }
        });


        EditText editText = (EditText) getView().findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.setListener(AddFragment.this);
                newFragment.show(getFragmentManager(), "timePicker");
                f = 0;


            }
        });

        EditText EditUntil = (EditText) getView().findViewById(R.id.EditUntil);
        EditUntil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.setListener(AddFragment.this);
                newFragment.show(getFragmentManager(), "timePicker");
                f = 1;
            }
        });


        Integer test2;

        final String DeviceID = Settings.Secure.getString(getActivity().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);


        // Reading Data from Input
        myKeyValue = (EditText) getView().findViewById(R.id.tvSelectedDate);
        myEditFrom = (EditText) getView().findViewById(R.id.editText);
        myEditUntil = (EditText) getView().findViewById(R.id.EditUntil);
        myEditFarm = (EditText) getView().findViewById(R.id.editText5);


        //initiate apply button
        myApplyBt = (Button) getView().findViewById(R.id.button);


        // Initiate Firebase
        Firebase.setAndroidContext(getActivity());

        myKeyValue.addTextChangedListener(DateTextWatcher);
        myEditFrom.addTextChangedListener(DateTextWatcherFrom);
        myEditUntil.addTextChangedListener(DateTextWatcherUntil);
        myEditFarm.addTextChangedListener(DateTextWatcherFarm);


        // Reading Data from Input

        myApplyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ImageView check1 = getView().findViewById(R.id.imageView8);
                final ImageView check2 = getView().findViewById(R.id.imageView9);
                final ImageView check3 = getView().findViewById(R.id.imageView11);
                final ImageView check4 = getView().findViewById(R.id.imageView10);

                final String Keydate = myKeyValue.getText().toString();

                myFirebaseCheck = new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId + "/Data/" + Keydate);
                myFirebaseCheck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {


                            Toast.makeText(getActivity(), Keydate + " already exits!", Toast.LENGTH_SHORT).show();

                        } else {

                            if (check1.getVisibility() == View.VISIBLE && check2.getVisibility() == View.VISIBLE && check3.getVisibility() == View.VISIBLE && check4.getVisibility() == View.VISIBLE) {


                                myFirebaseInfo = new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId + "/Info/Counter");

                                //Counter
                                myFirebaseInfo.child("Daysdone").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            Integer value = dataSnapshot.getValue(Integer.class);
                                            myStringCounter = value;
                                            myStringCounter = myStringCounter + 1;

                                            myKeyValueCounter = "Daysdone";

                                            Firebase myNewChildCounter = myFirebaseInfo.child(myKeyValueCounter);
                                            myNewChildCounter.setValue(myStringCounter);

                                        } else {
                                            myStringCounter = 1;
                                            myKeyValueCounter = "Daysdone";

                                            Firebase myNewChildCounter = myFirebaseInfo.child(myKeyValueCounter);
                                            myNewChildCounter.setValue(myStringCounter);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        Toast.makeText(getActivity(), "Ups", Toast.LENGTH_SHORT).show();
                                    }
                                });


                                final Integer myIndexvalue = HomeFragment.myIndex + 1;


                                myFirebaseData = new Firebase("https://fir-app-bdbe3.firebaseio.com/" + emptydr.UserId + "/Data/" + Keydate);

                                // Block From
                                myStringDataFrom = myEditFrom.getText().toString();
                                myKeyValueDataFrom = "From";

                                Firebase myNewChildFrom = myFirebaseData.child(myKeyValueDataFrom);
                                myNewChildFrom.setValue(myStringDataFrom);


                                // Block Until
                                myStringDataUntil = myEditUntil.getText().toString();
                                myKeyValueDataUntil = "Until";

                                Firebase myNewChildUntil = myFirebaseData.child(myKeyValueDataUntil);
                                myNewChildUntil.setValue(myStringDataUntil);

                                // Block Farm
                                myStringDataFarm = myEditFarm.getText().toString();
                                myKeyValueDataFarm = "Farm";

                                Firebase myNewChildFarm = myFirebaseData.child(myKeyValueDataFarm);
                                myNewChildFarm.setValue(myStringDataFarm);


                                // DateId DateID
                                myStringDataDateID = Keydate;
                                myKeyValueDataDateID = "DateID";

                                Firebase myNewChildDateID = myFirebaseData.child(myKeyValueDataDateID);
                                myNewChildDateID.setValue(myStringDataDateID);

                                // Index
                                myStringDatamyIndex = myIndexvalue;
                                myKeyValueDataIndex = "Index";

                                Firebase myNewChildIndex = myFirebaseData.child(myKeyValueDataIndex);
                                myNewChildIndex.setValue(myStringDatamyIndex);

                                // Timestamp
                                myStringDataTime = textaus2;
                                myKeyValueDataTime = "TimestampID";

                                Firebase myNewChildTime = myFirebaseData.child(myKeyValueDataTime);
                                myNewChildTime.setValue(myStringDataTime);

                                // Doppelklick
                                myStringklick = 0;
                                myKeyValueklick = "Klick";

                                Firebase myNewChildklick = myFirebaseData.child(myKeyValueklick);
                                myNewChildklick.setValue(myStringklick);

//


                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


                            } else {
                                Toast.makeText(getActivity(), "Please fill in all Information", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if (f == 0) {
            textViewus = (TextView) getView().findViewById(R.id.editText);
            textViewus.setText(String.format("%02d:%02d", hourOfDay, minute));
        } else {
            TextView textViewus = (TextView) getView().findViewById(R.id.EditUntil);
            textViewus.setText(String.format("%02d:%02d", hourOfDay, minute));
        }
    }


    private TextWatcher DateTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String DateInput = myKeyValue.getText().toString().trim();

            ImageView iv = (ImageView) getView().findViewById(R.id.imageView8);
            if (DateInput.isEmpty()) {

            } else {
                iv.setVisibility(iv.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String DateInput = myKeyValue.getText().toString().trim();
            ImageView iv = (ImageView) getView().findViewById(R.id.imageView8);
            if (DateInput.isEmpty()) {
                iv.setVisibility(iv.INVISIBLE);
            }
        }
    };

    private TextWatcher DateTextWatcherFrom = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String DateInput = myEditFrom.getText().toString().trim();

            ImageView iv = (ImageView) getView().findViewById(R.id.imageView9);
            if (DateInput.isEmpty()) {

            } else {
                iv.setVisibility(iv.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String DateInput = myEditFrom.getText().toString().trim();
            ImageView iv = (ImageView) getView().findViewById(R.id.imageView9);
            if (DateInput.isEmpty()) {
                iv.setVisibility(iv.INVISIBLE);
            }
        }
    };

    private TextWatcher DateTextWatcherUntil = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String DateInput = myEditUntil.getText().toString().trim();

            ImageView iv = (ImageView) getView().findViewById(R.id.imageView11);
            if (DateInput.isEmpty()) {

            } else {
                iv.setVisibility(iv.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String DateInput = myEditUntil.getText().toString().trim();
            ImageView iv = (ImageView) getView().findViewById(R.id.imageView11);
            if (DateInput.isEmpty()) {
                iv.setVisibility(iv.INVISIBLE);
            }
        }
    };

    private TextWatcher DateTextWatcherFarm = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String DateInput = myEditFarm.getText().toString().trim();

            ImageView iv = (ImageView) getView().findViewById(R.id.imageView10);
            if (DateInput.isEmpty()) {

            } else {
                iv.setVisibility(iv.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String DateInput = myEditFarm.getText().toString().trim();
            ImageView iv = (ImageView) getView().findViewById(R.id.imageView10);
            if (DateInput.isEmpty()) {
                iv.setVisibility(iv.INVISIBLE);
            }
        }


    };


}

