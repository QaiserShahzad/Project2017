package com.example.qaiserpasha.project2017.SighUpFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class DonarSignUp extends AppCompatActivity {
    private Button btnSubmit;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_sign_up);
        getSupportActionBar().setTitle("Donar SignUp");
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        queue = Volley.newRequestQueue(getBaseContext());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,uname, password, phonenumber, address;
                // name=password=phonenumber=address="";
                EditText etName,etUserName,etConPassword, etPassword, etPhone, etAddress;

                etUserName = (EditText) findViewById(R.id.txtDUserName_signup);
                if( etUserName.getText().toString().length() == 0 )
                    etUserName.setError( "User name is required!" );

                etName = (EditText) findViewById(R.id.txtDName_signup);
                if( etName.getText().toString().length() == 0 )
                    etName.setError( "User name is required!" );

                etPassword = (EditText) findViewById(R.id.txtDPassword_signup);
                if( etPassword.getText().toString().length() == 0 )
                    etPassword.setError( "Password is required!" );

                etConPassword = (EditText) findViewById(R.id.txtDConfirmPassword_signup);
                if( etConPassword.getText().toString().length() == 0 )
                    etConPassword.setError( "Password is required!" );

                etPhone = (EditText) findViewById(R.id.txtDPhone_signup);
                if( etPhone.getText().toString().length() == 0 )
                    etPhone.setError( "Phone is required!" );

                etAddress = (EditText) findViewById(R.id.txtDAddress_signup);
                if( etAddress.getText().toString().length() == 0 )
                    etAddress.setError( "Address is required!" );

                name = etName.getText().toString();
                uname = etUserName.getText().toString();
                password = etPassword.getText().toString();
                phonenumber = etPhone.getText().toString();
                address = etAddress.getText().toString();
                //validation perform karni hai name, password, phonenumber, address
                String url = "http://192.168.1.7:8040/Service1.svc/DonorSignUp/" + name + "/" + address + "/" + phonenumber +"/" + uname + "/" + password ;
// Add a request (in this example, called stringRequest) to your RequestQueue.
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
                        Toast.makeText(DonarSignUp.this, error.getMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);
            }
        });
    }
}
