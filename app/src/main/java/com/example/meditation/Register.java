package com.example.meditation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText login;
    private EditText password;
    private EditText repeatPassword;
    private Button registerBtn;
    private TextView toLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        repeatPassword = findViewById(R.id.editTextRepeatPassword);
        registerBtn = findViewById(R.id.register);
        toLoginBtn = findViewById(R.id.toLogin);

        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin(view);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!repeatPassword.getText().toString().equals(password.getText().toString())) {
                    Toast.makeText(Register.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                } else if (login.getText().toString().isEmpty()||password.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                toMain(view);
                            } else {
                                Toast.makeText(Register.this, "Email is already taken or incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private void toMain(View v) {
        Intent toMain = new Intent(Register.this, Main.class);
        startActivity(toMain);
        finish();
    }

    private void toLogin(View v) {
        Intent toLogin = new Intent(Register.this, Login.class);
        startActivity(toLogin);
        finish();
    }
}