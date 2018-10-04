package com.example.qaiserpasha.project2017.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.MaxCollectorAdaptor;
//import com.example.qaiserpasha.project2017.DonorApprovedRequest;
import com.example.qaiserpasha.project2017.MaxDonationn;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.Adapters.Donor_ReqListViewAdaptor;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 11/16/2017.
 */

public class Admin_Tab1 extends android.support.v4.app.Fragment {
    Button btnMaxDon;
    ArrayList<Donor_ReqItemObject> list;
    RequestQueue requestQueue;
    Donor_ReqListViewAdaptor donorReqListViewAdaptor;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.admin_tab1, container, false);
        btnMaxDon = (Button) rootview.findViewById(R.id.btnApprove);
        requestQueue = Volley.newRequestQueue(getContext());
        list = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.listViewItemsForAdminRequests);
        donorReqListViewAdaptor =new Donor_ReqListViewAdaptor(getContext(), list);
        listView.setAdapter(donorReqListViewAdaptor);
        getdataFromServer();

        btnMaxDon =(Button) rootview.findViewById(R.id.btnMax);
        btnMaxDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),MaxDonationn.class);
                startActivity(i);
            }
        });



        return rootview;

    }




    public void getdataFromServer() {
        String url = WebServiceObject.GET_ALL_REQUESTS_NEW ;//+ "0";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("DonateRequestAdminResult");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        Donor_ReqItemObject dobj =new Donor_ReqItemObject();
                        dobj.setUserName(object.getString("DUserName"));
                        dobj.setAddress(object.getString("locname"));
                        dobj.setBagId(object.getString("bagId"));
                        dobj.setNoClothes(object.getString("ItemNo"));
                        dobj.setStatus(object.getString("status"));



//                        String AStatus=object.getString("AStatus");
//                        String CStatus=object.getString("CStatus");
//                        if (AStatus.equals("0"))
//                        {
//                            list.add(dobj);
//                        }
                        list.add(dobj);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                donorReqListViewAdaptor.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }






}
