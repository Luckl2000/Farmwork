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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText txtEmail,txtPassword,txtConfirmPassword;
    ProgressBar progress;
    private FirebaseAuth mAuth;
    public static String UserID = "";
    TextView mysignup;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button btn_reg = findViewById(R.id.reg);
        progress = (ProgressBar)findViewById(R.id.progress);
        txtEmail = findViewById(R.id.email_reg);
        txtPassword = findViewById(R.id.password_reg);
        txtConfirmPassword = findViewById(R.id.confirm_reg);
        mysignup = (TextView) findViewById(R.id.signup);


        mAuth = FirebaseAuth.getInstance();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                final String confirmpass = txtConfirmPassword.getText().toString().trim();
                progress.setVisibility(View.VISIBLE);
                btn_reg.setEnabled(false);

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.INVISIBLE);
                    btn_reg.setEnabled(true);
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.INVISIBLE);
                    btn_reg.setEnabled(true);
                }
                if (TextUtils.isEmpty(confirmpass)){
                    Toast.makeText(RegisterActivity.this, "Please enter Confirm Password", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.INVISIBLE);
                    btn_reg.setEnabled(true);
                }

                if (password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.INVISIBLE);
                    btn_reg.setEnabled(true);
                }

                if (password.equals(confirmpass)){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    progress.setVisibility(View.INVISIBLE);
                                                    UserID = mAuth.getUid();
                                                    startActivity(new Intent(getApplicationContext(), SignActivity.class));
                                                    Toast.makeText(RegisterActivity.this, "Registration successful" + mAuth.getUid(),
                                                            Toast.LENGTH_SHORT).show();
                                                } else {
                                                    progress.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(RegisterActivity.this, "Authentication failed",
                                                            Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed. There might be an Account with this Email",
                                                Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.INVISIBLE);
                                        btn_reg.setEnabled(true);
                                    }

                                    // ...
                                }
                            });
                }

            }


        });

        mysignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SignActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


}
