package com.example.qaiserpasha.project2017.Fragments;

/**
 * Created by Qaiser Pasha on 9/28/2017.
 */

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
import com.example.qaiserpasha.project2017.Adapters.DonationHistoryAdaptor;
import com.example.qaiserpasha.project2017.Objects.Donation_Item_Object;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.Adapters.StockListViewAdapter;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tab2 extends Fragment {
    ListView listView;
    ArrayList<Donation_Item_Object> listFromServer;
    RequestQueue requestQueue;
    DonationHistoryAdaptor donationHistoryAdaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        listView = (ListView) rootView.findViewById(R.id.listViewDonorItemsFromServer);
        requestQueue = Volley.newRequestQueue(getContext());
        listFromServer = new ArrayList<>();
        donationHistoryAdaptor = new DonationHistoryAdaptor(getContext(), listFromServer);
        listView.setAdapter(donationHistoryAdaptor);
        getDataFromServer();

        return rootView;
    }





    public void getDataFromServer() {
        //  for populate user name in donor
        Intent inte = getActivity().getIntent();
        String data = inte.getStringExtra("name");
        // tvuser.setText(data);
        String Url = WebServiceObject.IP + "getDonations/"+ data;
        StringRequest request = new StringRequest(Request.Method.GET, Url.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(jsonObject.getString("getDonationsResult"));
                    for (int i = 0; i < array.length(); i++) {
                        Donation_Item_Object obj = new Donation_Item_Object();
                        JSONObject o = array.getJSONObject(i);

                        obj.setBagId(o.getString("bagId"));
                        obj.setDatte(array.getJSONObject(i).getString("DandT"));
                        obj.setItems(array.getJSONObject(i).getString("ItemNo"));
                        obj.setAddr(array.getJSONObject(i).getString("locname"));
                        listFromServer.add(obj);

                    }
                    donationHistoryAdaptor.notifyDataSetChanged();

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

