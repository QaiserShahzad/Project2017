package com.example.qaiserpasha.project2017;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Objects.SharedPrefHandler;
import com.example.qaiserpasha.project2017.SighUpFolder.AdminSignUp;
import com.example.qaiserpasha.project2017.SighUpFolder.DonarSignUp;
import com.example.qaiserpasha.project2017.SighUpFolder.SignUpOption;

public class Login extends AppCompatActivity {
    RadioButton AsAdmin, AsDonar,AsDistri ,AsCollector ,AsRecipient ;
    Button btn;
    RequestQueue rq;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        btn = (Button) findViewById(R.id.btnLogin);
        rq = Volley.newRequestQueue(getApplicationContext());







        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final EditText etUName = (EditText) findViewById(R.id.txtUserName_login);
                final EditText etPassword = (EditText) findViewById(R.id.txtPassword_login);
                final String values=etUName.getText().toString();
                String url = "http://192.168.1.7:8040/Service1.svc/Login/"+ etUName.getText().toString() + "/" + etPassword.getText().toString();
                StringRequest StrReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPrefHandler sharedPrefHandler=new SharedPrefHandler(getBaseContext());
                        sharedPrefHandler.saveUserName(etUName.getText().toString());
                        sharedPrefHandler.savePassword(etPassword.getText().toString());
                      //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        if (response.contains("Admin")) {
                           // Toast.makeText(getApplicationContext(), "yesss", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getBaseContext(), AdminSignUpOption.class);
                           // i.putExtra("name",values);
                            sharedPrefHandler.saveType("Admin");
                            startActivity(i);
                        }
                       else if (response.contains("Collector")) {
                            Intent i = new Intent(getBaseContext(), WelcomeCollector.class);
                            i.putExtra("name",values);
                            sharedPrefHandler.saveType("Collector");
                            startActivity(i);
                        }
                       else if (response.contains("Donor")) {
                            Intent i = new Intent(getBaseContext(), WelcomeDonar.class);
                            i.putExtra("name",values);
                            sharedPrefHandler.saveType("Donor");
                            startActivity(i);
                        }
                       else if (response.contains("Donee")) {
                            sharedPrefHandler.saveType("Donee");
                            Intent i = new Intent(getBaseContext(), WelcomeDonee.class);
                            i.putExtra("name",values);
                            sharedPrefHandler.saveType("Donee");
                            startActivity(i);
                        }

                        else
                            {
                               // sharedPrefHandler.clearSavedValues();
                            etUName.setError("Not Matched User Or Password");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
Toast.makeText(Login.this,error.toString() , Toast.LENGTH_SHORT).show();
                    }
                });
                rq.add(StrReq);




            }
        });

        TextView textView = (TextView) findViewById(R.id.textViewRegister);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), SignUpOption.class);
                startActivity(i);

            }
        });

//        TextView textVie = (TextView) findViewById(R.id.textViewRegisterAsAdmin);
//        textVie.setPaintFlags(textVie.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        textVie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), AdminSignUp.class);
//                startActivity(i);
//            }
//        });

    }


}
