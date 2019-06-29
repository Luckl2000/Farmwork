package com.example.farmwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;


public class Forgotpassword extends AppCompatActivity {

    EditText forgotpassword;
    public String myemail;
    Button btn_reset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgotpassword = (EditText) findViewById(R.id.email_forgot);
        btn_reset = (Button) findViewById(R.id.reset);
        mAuth = FirebaseAuth.getInstance();
        myemail = forgotpassword.getText().toString().trim();

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 myemail = forgotpassword.getText().toString().trim();
                if (TextUtils.isEmpty(myemail)) {
                    Toast.makeText(Forgotpassword.this, "Please enter Email", Toast.LENGTH_SHORT).show();

                } else {
                    check(myemail);
                }
            }
        });


    }

    public void check(String myemail){
       final String my = myemail;
        mAuth.fetchSignInMethodsForEmail(myemail)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean check = task.getResult().getSignInMethods().isEmpty();
                        if (!check){
                            FirebaseAuth.getInstance().sendPasswordResetEmail(my)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Forgotpassword.this, "Email send.",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Forgotpassword.this, SignActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(Forgotpassword.this, "Email does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
