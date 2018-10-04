package com.example.qaiserpasha.project2017.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.qaiserpasha.project2017.Adapters.AuditShowAdaptor;
import com.example.qaiserpasha.project2017.Adapters.DonationHistoryAdaptor;
import com.example.qaiserpasha.project2017.Adapters.dummy;
import com.example.qaiserpasha.project2017.Objects.Donation_Item_Object;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 2/7/2018.
 */

public class Tab3  extends Fragment {


    ListView listView;
    ArrayList<Donation_Item_Object> listFromServer;
    RequestQueue requestQueue;
    AuditShowAdaptor auditadaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);
        listView = (ListView) rootView.findViewById(R.id.listViewDonorItemsPicsFromServer);
        requestQueue = Volley.newRequestQueue(getContext());
        listFromServer = new ArrayList<>();
        auditadaptor = new AuditShowAdaptor(getContext(), listFromServer);
        listView.setAdapter(auditadaptor);
        getDataFromServer();

        return rootView;
    }


    public void getDataFromServer() {
        //  for populate user name in donor
        Intent inte = getActivity().getIntent();
        String data = inte.getStringExtra("name");
        // tvuser.setText(data);
        String Url = WebServiceObject.IP + "getAllItems/"+data; //listFromServer.get(dummy.bagId).getBagId();
        StringRequest request = new StringRequest(Request.Method.GET, Url.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(jsonObject.getString("getAllItemsResult"));
                    for (int i = 0; i < array.length(); i++) {
                        Donation_Item_Object obj = new Donation_Item_Object();
                        JSONObject o = array.getJSONObject(i);

                      //  obj.setBagId(o.getString("bagId"));
                      //  obj.setDatte(array.getJSONObject(i).getString("DandT"));
                     //   obj.setItems(array.getJSONObject(i).getString("ItemNo"));
                        obj.setPic(array.getJSONObject(i).getString("Pic"));
                        listFromServer.add(obj);

                    }
                    auditadaptor.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }



}
