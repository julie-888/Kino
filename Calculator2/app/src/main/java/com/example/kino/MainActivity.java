package com.example.kino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void signUp(View view){
        mAuth = FirebaseAuth.getInstance();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, user.getEmail(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void register(View view){
        mAuth = FirebaseAuth.getInstance();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
       mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                           FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, user.getEmail(),Toast.LENGTH_SHORT).show();
                        }
                        else {
                           Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
    }
}