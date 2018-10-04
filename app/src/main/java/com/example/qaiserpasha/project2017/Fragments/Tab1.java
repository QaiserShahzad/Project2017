package com.example.qaiserpasha.project2017.Fragments;

/**
 * Created by Qaiser Pasha on 9/28/2017.
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Objects.DialogHandler;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.GPSTracker;
import com.example.qaiserpasha.project2017.Objects.SharedPrefHandler;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static java.util.Calendar.AM;
import static java.util.Calendar.AM_PM;

public class Tab1 extends Fragment {
    // private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    TextView tvLocstion, tvcalender, tvTime, tvuser,tvGivenAdd ;
   EditText editTextAdrress,editTextBagId, editTextNoOfClothes;
    Button btnDonate;
    Calendar currentDate;
    int day, month, year, hours, minuts, type;
    GPSTracker gpsTracker;
    RequestQueue requestQ;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.tab1, container, false);
        requestQ = Volley.newRequestQueue(getContext());
    SharedPrefHandler sharedPrefHandler=new SharedPrefHandler(getContext());

        // Get Ids
        btnDonate=(Button)rootview.findViewById(R.id.DonateButton);
        tvuser = (TextView) rootview.findViewById(R.id.tvuserr);
        editTextAdrress = (EditText) rootview.findViewById(R.id.editAddress);
        tvGivenAdd = (TextView) rootview.findViewById(R.id.givenAdd);
        editTextBagId=(EditText)rootview.findViewById(R.id.editBagId);
        editTextNoOfClothes = (EditText) rootview.findViewById(R.id.editNoOfClothes);
        tvcalender = (TextView) rootview.findViewById(R.id.textViewCallender);
        tvTime = (TextView) rootview.findViewById(R.id.textViewTime);
       tvuser.setText(sharedPrefHandler.getUserName());




        // Date
        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);
        month = month + 1;
        tvcalender.setText("DD" + "-" + "MM" + "-" + "YYYY");
        tvcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int montOfYear, int dayOfMonth) {
                        montOfYear = montOfYear + 1;
                        tvcalender.setText(dayOfMonth + "-" + montOfYear + "-" + year+" ");
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });



        // Time
        hours = currentDate.get(Calendar.HOUR_OF_DAY);
        minuts = currentDate.get(Calendar.MINUTE);
        type = currentDate.get(AM_PM);
        tvTime.setText("Hours" + ":" + "Minuts");
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hours, int minuts ) {
                        tvTime.setText("  "+hours + "." + minuts);
                    }
                }, hours, minuts,true);
                timePickerDialog.show();
            }
        });














        // Use Given Location
        tvGivenAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = getActivity().getIntent();
                String data = inte.getStringExtra("name");
                final String Url = WebServiceObject.IP + "GetAddress"+"/"+inte.getStringExtra("name");

                StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String addres = jsonObject.getString("GetAddressResult");
                            editTextAdrress.setText(addres);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage() + "went wrong", Toast.LENGTH_SHORT).show();

                    }
                });
                requestQ.add(request);
            }
        });


        //  for populate user name in donor
        Intent inte = getActivity().getIntent();
        String data = inte.getStringExtra("name");
       tvuser.setText(data);
      //  sharedPrefHandler = new SharedPrefHandler(getContext());
       // tvuser.setText(sharedPrefHandler.getUserName());


        //  for populate map Address  donor Map
        Intent i = getActivity().getIntent();
        String mapdata = i.getStringExtra("mapValues");
        editTextAdrress.setText(mapdata);


//        tvLocstion = (TextView) rootview.findViewById(R.id.textViewLocation);;
//        gpsTracker = new GPSTracker(getContext());
//        tvLocstion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tvLocstion.setText("Latti :" + gpsTracker.getLatitude() +"Longi :" + gpsTracker.getLongitude());
//                     // Toast.makeText(getContext(),"yessss",Toast.LENGTH_LONG).show();
//            }
//        });


        // code for data submitt to database
        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = tvuser.getText().toString();
                String Address = editTextAdrress.getText().toString();
                String bagid= editTextBagId.getText().toString();
                String Clothes = editTextNoOfClothes.getText().toString();
                String date = tvcalender.getText().toString();
                String time = tvTime.getText().toString();

                Donor_ReqItemObject Dobject = new Donor_ReqItemObject();
                Dobject.setUserName(user);
                Dobject.setAddress(Address);
                Dobject.setBagId(bagid);
                Dobject.setNoClothes(Clothes);
                Dobject.setDate(date);
                Dobject.setTime(time);
                submitDataToServer(Dobject);

            }
        });


        return rootview;
    }

    public void submitDataToServer(Donor_ReqItemObject Dobject) {  //+Dobject.getBagId()+"/"           + "/" + "3"+ "/"
       // String url = WebServiceObject.IP + "saveDonations/" + Dobject.getUserName()+ "/" + Dobject.getNoClothes()  + "/" + Dobject.getDate() + "/" + Dobject.getTime()+ "/" + Dobject.getAddress();
        String url = WebServiceObject.IP + "saveDonations/" + Dobject.getUserName()+ "/" + Dobject.getNoClothes() +"/" + Dobject.getAddress()  + "/" + tvcalender.getText().toString()+" "+tvTime.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),  "inserteddd", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),  "Your BagId is ="+response , Toast.LENGTH_LONG).show();
              //  Toast.makeText(getContext(), response + "BagId", Toast.LENGTH_LONG).show();
            //    DialogHandler.showDialog(getActivity(),"Faisal");
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage() + "not insertedddd", Toast.LENGTH_SHORT).show();
            }
        });
        requestQ.add(stringRequest);
    }



}
