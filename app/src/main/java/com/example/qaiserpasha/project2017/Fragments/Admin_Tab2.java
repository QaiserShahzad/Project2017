package com.example.qaiserpasha.project2017.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.DonationHistoryAdaptor;
import com.example.qaiserpasha.project2017.Adapters.DoneeReqAdaptor;
import com.example.qaiserpasha.project2017.Adapters.DoneeRequestAdaptor;
import com.example.qaiserpasha.project2017.Adapters.MaxCollectorAdaptor;
import com.example.qaiserpasha.project2017.Objects.Donation_Item_Object;
import com.example.qaiserpasha.project2017.Objects.DoneeReq_Item_Object;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.Objects.MaxCollItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 11/16/2017.
 */



public class Admin_Tab2 extends android.support.v4.app.Fragment {
    ListView listView;
    ArrayList<DoneeReq_Item_Object> listFromDoneeReqServer;
    RequestQueue requestQueue;
    DoneeReqAdaptor doneeReqAdaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.admin_tab2, container, false);

        listView = (ListView) rootview.findViewById(R.id.listViewItemsForDoneeRequests);
        requestQueue = Volley.newRequestQueue(getContext());
        listFromDoneeReqServer = new ArrayList<>();
        doneeReqAdaptor = new DoneeReqAdaptor  (getContext(), listFromDoneeReqServer);
        listView.setAdapter(doneeReqAdaptor);
        getDataFromServer();

        return rootview;
    }

    public void getDataFromServer() {
        //  for populate user name in donor
//        Intent inte = getActivity().getIntent();
//        String data = inte.getStringExtra("name");
        // tvuser.setText(data);

        String Url = WebServiceObject.IP + "DistRequestAdmin";//+ "0";
        StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("DistRequestAdminResult");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        DoneeReq_Item_Object dobj =new DoneeReq_Item_Object();
                        dobj.setDisName(object.getString("UserName"));
                       // dobj.setAddress(object.getString("locname"));
                        dobj.setClothId(object.getString("clothidd"));
                        dobj.setQuantty(object.getString("Tqty"));
                        listFromDoneeReqServer.add(dobj);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                doneeReqAdaptor.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}
