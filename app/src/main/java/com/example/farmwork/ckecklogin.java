package com.example.farmwork;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ckecklogin extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebasseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebasseAuth.getCurrentUser();

        if(firebaseUser != null){
            Intent init = new Intent(this, emptydr.class);
            init.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(init);
        }
    }
}
