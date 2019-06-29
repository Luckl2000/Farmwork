package com.example.farmwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText txtEmail,txtPassword;
    Button btn_login;
    TextView v_reg;
    TextView mypassforgot;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail =(EditText)findViewById(R.id.email_edit);
        txtPassword =(EditText)findViewById(R.id.password_edit);
        btn_login =(Button)findViewById(R.id.login);
        v_reg =(TextView)findViewById(R.id.Signup);
        mypassforgot = (TextView) findViewById(R.id.passwordforgot);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(SignActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(SignActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                }


               mAuth.signInWithEmailAndPassword(email,password)
                       .addOnCompleteListener(SignActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()){
                                   if(mAuth.getCurrentUser().isEmailVerified()){
                                       FirebaseUser user = mAuth.getCurrentUser();
                                       Toast.makeText(SignActivity.this, "hat geklappt" , Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(getApplicationContext(),emptydr.class));
                                   } else {
                                       Toast.makeText(SignActivity.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                   }

                               }else{
                                   String t = task.toString().trim();
                                   Toast.makeText(SignActivity.this, t, Toast.LENGTH_SHORT).show();
                                   //Toast.makeText(SignActivity.this, "Login failed or User not availabe", Toast.LENGTH_SHORT).show();
                               }

                           }
                       });

            }
        });

        v_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


        mypassforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SignActivity.this, Forgotpassword.class);
                    startActivity(intent);
                finish();
            }
        });

    }

    private void updateUI(FirebaseUser currentUser) {
    }


}
