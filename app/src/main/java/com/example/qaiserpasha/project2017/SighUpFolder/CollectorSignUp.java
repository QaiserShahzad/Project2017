package com.example.qaiserpasha.project2017.SighUpFolder;

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
import com.example.qaiserpasha.project2017.R;

public class CollectorSignUp extends AppCompatActivity {
    Button btnSubmit;
    RequestQueue RQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector_sign_up);
        getSupportActionBar().setTitle("Collector SignUp");
        RQ = Volley.newRequestQueue(getBaseContext());
        btnSubmit = (Button) findViewById(R.id.btnSubmitt);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), MapsActivity.class));
//                finish();
                String name, uname, cnic, password, phone, address;
                EditText etName, etUserName, etCinc, etPassword, etPhone, etAddress;

                etName = (EditText) findViewById(R.id.txtColName_signup);
                if (etName.getText().toString().length() == 0)
                    etName.setError("plz enter Name..!");

                etUserName = (EditText) findViewById(R.id.txtColUserName_signup);
                if (etUserName.getText().toString().length() == 0)
                    etUserName.setError("plz enter User Name..!");

                etCinc = (EditText) findViewById(R.id.txtColCNIC);
                if (etCinc.getText().toString().length() == 0)
                    etCinc.setError("plz enter CNIC..!");

                etPassword = (EditText) findViewById(R.id.txtColPassword_signup);
                if (etPassword.getText().toString().length() == 0)
                    etPassword.setError("plz enter Password..!");

                etPhone = (EditText) findViewById(R.id.txtColPhone_signup);
                if (etPhone.getText().toString().length() == 0)
                    etPhone.setError("plz enter Phone..!");

                etAddress = (EditText) findViewById(R.id.txtColAddress_signup);
                if (etAddress.getText().toString().length() == 0)
                    etAddress.setError("plz enter Address..!");

                name = etName.getText().toString();
                uname = etUserName.getText().toString();
                cnic = etCinc.getText().toString();
                password = etPassword.getText().toString();
                phone = etPhone.getText().toString();
                address = etAddress.getText().toString();


                String url = "http://192.168.2.23:8040/Service1.svc/CollectorSignUp/" +"/"+ name +"/"+ address +"/"+  phone +"/"+ cnic +"/"+ uname +"/"+  password;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CollectorSignUp.this, error.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                });
                RQ.add(stringRequest);
            }


        });
    }
}
