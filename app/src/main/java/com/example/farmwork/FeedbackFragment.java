package com.example.farmwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_feedback,container,false);


    }

    @Override
    public void onStart() {
        super.onStart();

        Button button = (Button) getView().findViewById(R.id.buttonsupport);
        final EditText message = (EditText) getView().findViewById(R.id.editmessage);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String mess = message.getText().toString();
              Intent email = new Intent(Intent.ACTION_SENDTO);

              email.setType("message/rfc822");
              email.setData(Uri.parse("mailto:farmwork88help@gmail.com"));
              email.putExtra(Intent.EXTRA_TEXT ,mess);
              email.putExtra(Intent.EXTRA_SUBJECT ,"Farmwork Support");


              startActivity(Intent.createChooser(email, "Send mail using..."));

            }
        });




    }


}
