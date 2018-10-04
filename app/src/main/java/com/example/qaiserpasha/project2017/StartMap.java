package com.example.qaiserpasha.project2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartMap extends AppCompatActivity {
    Button btnSetLocation;
    TextView txt1st,txt2nd,txt3rd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_map);
        btnSetLocation=(Button)findViewById(R.id.btnsetLocat);
        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),DonarMap.class);
                startActivity(i);
            }
        });
    }
}
