package com.example.qaiserpasha.project2017.SighUpFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Login;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WelcomeAdmin;

public class AdminSignUp extends AppCompatActivity {
    private Button btnSubmit;
    RequestQueue RQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);
        getSupportActionBar().setTitle("Admin SignUp");
        RQ = Volley.newRequestQueue(getBaseContext());
        btnSubmit = (Button) findViewById(R.id.btnAdminSubmitt);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), MapsActivity.class));
//                finish();
                String name, uname, cnic, password, phone, address;
                EditText etName, etUserName, etCinc, etPassword, etPhone, etAddress;
                etName = (EditText) findViewById(R.id.txtName_signup);
                if (etName.getText().toString().length() == 0)
                    etName.setError("plz enter User Name..!");

                etUserName = (EditText) findViewById(R.id.txtUserName_signup);
                if (etUserName.getText().toString().length() == 0)
                    etUserName.setError("plz enter User Name..!");

                etCinc = (EditText) findViewById(R.id.txtCNIC);
                if (etCinc.getText().toString().length() == 0)
                    etCinc.setError("plz enter CNIC..!");

                etPassword = (EditText) findViewById(R.id.txtPassword_signup);
                if (etPassword.getText().toString().length() == 0)
                    etPassword.setError("plz enter Password..!");

                etPhone = (EditText) findViewById(R.id.txtPhone_signup);
                if (etPhone.getText().toString().length() == 0)
                    etPhone.setError("plz enter Phone..!");

                etAddress = (EditText) findViewById(R.id.txtAddress_signup);
                if (etAddress.getText().toString().length() == 0)
                    etPhone.setError("plz enter Phone..!");

                name = etName.getText().toString();
                uname = etUserName.getText().toString();
                cnic = etCinc.getText().toString();
                password = etPassword.getText().toString();
                phone = etPhone.getText().toString();
                address = etAddress.getText().toString();

                String url = "http://192.168.10.12:8040/Service1.svc/AdminSignUp/" + name  +"/"+  address +"/"+ phone  +"/"+ cnic  +"/"+  uname +"/"+ password  ;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminSignUp.this, error.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                });
                RQ.add(stringRequest);
            }


        });
    }
}
