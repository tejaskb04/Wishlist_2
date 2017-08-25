package com.example.wishlist;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Button registerBtn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(MainActivity.this);
        registerBtn = (Button) findViewById(R.id.register);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        textViewRoute = (TextView) findViewById(R.id.route);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        textViewRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and Route to LoginActivity
            }
        });
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "There are one or more empty fields.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setMessage("Registering User...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registered Successfully",
                                        Toast.LENGTH_SHORT).show();
                                // Create UserAreaActivity
                            } else {
                                Toast.makeText(MainActivity.this, "Failed to Register",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
