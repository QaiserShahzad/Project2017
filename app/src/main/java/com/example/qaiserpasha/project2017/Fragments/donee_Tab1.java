package com.example.qaiserpasha.project2017.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.DoneeStockListViewAdaptor;
import com.example.qaiserpasha.project2017.Adapters.StockListViewAdapter;
import com.example.qaiserpasha.project2017.Donee_Info_Gathering;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 11/15/2017.
 */

public class donee_Tab1 extends android.support.v4.app.Fragment {
    ListView listView;
    ArrayList<StockkItemObject> listFromServer;
    RequestQueue requestQueue;
    DoneeStockListViewAdaptor doneeStockListViewAdaptor;


    Spinner spinCity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.donee_tab1, container, false);
        listView = (ListView) rootview.findViewById(R.id.listViewDoneeItemsFromServer);
        requestQueue = Volley.newRequestQueue(getContext());
        listFromServer = new ArrayList<>();
        doneeStockListViewAdaptor = new DoneeStockListViewAdaptor(getContext(), listFromServer);
        listView.setAdapter(doneeStockListViewAdaptor);



        //  for populate user name in collector
        TextView tvDonee = (TextView)rootview.findViewById(R.id.tvuserr);
           // String data=getActivity().getIntent().getExtras().getString("name");
       // Intent inte = getIntent();
       final String data = getActivity().getIntent().getStringExtra("name");
        tvDonee.setText(data);

spinCity=(Spinner)rootview.findViewById(R.id.spinnerCity);
spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getDataFromServer(spinCity.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        return rootview;
    }

    public void getDataFromServer(String loc) {
        String Url = WebServiceObject.IP + "getAllItemsByLocation/"+loc;
        listFromServer.clear();
        StringRequest request = new StringRequest(Request.Method.GET, Url.replaceAll(" ", "%20"), new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    // Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(jsonObject.getString("getAllItemsByLocationResult"));

                    for (int i = 0; i < array.length(); i++) {
                        StockkItemObject obj = new StockkItemObject();
                        JSONObject o = array.getJSONObject(i);

                        obj.setGender(o.getString("Gender"));
                        obj.setSeason(array.getJSONObject(i).getString("Season"));
                        obj.setType(array.getJSONObject(i).getString("Type"));
                        obj.setClothId(array.getJSONObject(i).getString("ClothID"));
                        obj.setPic(array.getJSONObject(i).getString("Pic"));
                        listFromServer.add(obj);

                    }

                    Toast.makeText(getContext(), listFromServer.size() + "", Toast.LENGTH_SHORT).show();
                    doneeStockListViewAdaptor.notifyDataSetChanged();

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

        requestQueue.add(request);
    }
}
