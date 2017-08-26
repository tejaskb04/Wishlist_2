package com.example.wishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAreaActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewEmail;
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(UserAreaActivity.this, "Failed to Authenticate", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(UserAreaActivity.this, LoginActivity.class));
        }
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        textViewEmail = (TextView) findViewById(R.id.email);
        textViewEmail.setText("Welcome " + firebaseUser.getEmail());
        logoutBtn = (Button) findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(UserAreaActivity.this, LoginActivity.class));
            }
        });
    }
}
