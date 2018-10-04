package com.example.qaiserpasha.project2017.SighUpFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qaiserpasha.project2017.R;

public class SignUpOption extends AppCompatActivity {
Button btnDonarSignUp , btnDoneeSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_option);
        getSupportActionBar().setTitle("SignUp Option");
        btnDonarSignUp=(Button)findViewById(R.id.buttonDonarSignUp);
        btnDoneeSignUp=(Button)findViewById(R.id.buttonDoneeSignUp);
        btnDonarSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SignUpOption.this,DonarSignUp.class);
                startActivity(i);
            }
        });


        btnDoneeSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(SignUpOption.this,DoneeSignUp.class);
                startActivity(i);
            }
        });
    }
}
