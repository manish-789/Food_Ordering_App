package com.example.hp.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hp.myapplication.Admin;
import com.example.hp.myapplication.AdminActivity;
import com.example.hp.myapplication.MainActivity;
import com.example.hp.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login);
        loginButton = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton.setOnClickListener(v -> {
            Query query = FirebaseDatabase.getInstance().getReference().child("admin").orderByChild("username").equalTo(username.getText().toString().trim());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot is the "issue" node with all children with id 0

                        for (DataSnapshot user : dataSnapshot.getChildren()) {
                            // do something with the individual "issues"
                            Admin userValue = user.getValue(Admin.class);

                            if (userValue.getPassword().equals(password.getText().toString().trim())) {
                                Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AdminLoginActivity.this, "Password is wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "User not found", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        });
    }
}