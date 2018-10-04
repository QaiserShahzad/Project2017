package com.example.qaiserpasha.project2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qaiserpasha.project2017.SighUpFolder.AdminSignUp;
import com.example.qaiserpasha.project2017.SighUpFolder.CollectorSignUp;
import com.example.qaiserpasha.project2017.SighUpFolder.SignUpOption;

public class AdminSignUpOption extends AppCompatActivity {

    //final String values=etUName.getText().toString();
    Button btnGoAsAdmin ,btnCollectorSignUp,btnAdminSignUp , btnReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_sign_up_option);
        getSupportActionBar().setTitle("Admin Pannel");

       // btnReviews=(Button)findViewById(R.id.btnReview);
        btnCollectorSignUp=(Button)findViewById(R.id.buttonCollectorSignUp);
        btnAdminSignUp=(Button)findViewById(R.id.buttonAdminSignUp);
        btnGoAsAdmin=(Button)findViewById(R.id.buttonAsAdmin);
        btnGoAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), WelcomeAdmin.class);
              //  i.putExtra("name",values);
                startActivity(i);
            }
        });
        btnCollectorSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AdminSignUpOption.this,CollectorSignUp.class);
                startActivity(i);

            }
        });
        btnAdminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AdminSignUpOption.this,AdminSignUp.class);
                startActivity(i);

            }
        });
//        btnReviews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(AdminSignUpOption.this,ShowReviews.class);
//                startActivity(i);
//
//            }
//        });
    }
}
