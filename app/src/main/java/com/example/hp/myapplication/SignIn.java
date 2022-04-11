package com.example.hp.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class
SignIn extends AppCompatActivity {
    EditText edtPhone;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(SignIn.this, Home.class));
            finish();
        }
        setContentView(R.layout.activity_sign_in);

        edtPhone = findViewById(R.id.edtPhone);

        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Phonenumber= edtPhone.getText().toString();
                if (Phonenumber.isEmpty())
                    Toast.makeText( SignIn.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText( SignIn.this, "otp send", Toast.LENGTH_SHORT).show();

                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                                    .setPhoneNumber(Phonenumber)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(SignIn.this)                 // Activity (for callback binding)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            signInUser(phoneAuthCredential);
                                            Log.d("TAG", "onVerificationCompleted:");

                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            Log.d("TAG", "onVerificationFailed:" +e.getLocalizedMessage());
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            Log.d("TAG", "onCodeSent:");

                                            super.onCodeSent(verificationId, forceResendingToken);

                                            Dialog dialog=new Dialog(SignIn.this);
                                            dialog.setContentView(R.layout.verify_popup);

                                            dialog.setCanceledOnTouchOutside(false);
                                            EditText verifycode = dialog.findViewById(R.id.verifycode);
                                            Button btnverifyotp = dialog.findViewById(R.id.btnverifyotp);
                                            btnverifyotp.setOnClickListener(v1 -> {
                                                String verificationCode = verifycode.getText().toString();
                                                if(verificationId.isEmpty()) return;
                                                //create a credential
                                                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                                signInUser(credential);
                                            });

                                            dialog.show();
                                        }
                                    })          // OnVerificationStateChangedCallbacks

                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });
    }

private void signInUser(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
        .addOnCompleteListener(task -> {
        if(task.isSuccessful()){
        startActivity(new Intent(SignIn.this, Home.class));
        finish();

        } else{
        Log.d("TAG", "onComplete:"+ Objects.requireNonNull(task.getException()).getLocalizedMessage());
        }
        });
        }
}
