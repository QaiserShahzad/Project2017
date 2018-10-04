package com.example.qaiserpasha.project2017.Fragments;

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
import com.example.qaiserpasha.project2017.Adapters.MaxCollectorAdaptor;
import com.example.qaiserpasha.project2017.Objects.MaxCollItemObject;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.Adapters.StockListViewAdapter;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 12/16/2017.
 */

public class Admin_Tab3 extends android.support.v4.app.Fragment {

//    ListView listView;
//    ArrayList<MaxCollItemObject> listFromServer;
//    RequestQueue requestQueue;
//    MaxCollectorAdaptor maxCollectorAdaptor;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View rootview = inflater.inflate(R.layout.admin_tab3, container, false);
//        listView = (ListView) rootview.findViewById(R.id.listViewItemsForMaxCollection);
//        requestQueue = Volley.newRequestQueue(getContext());
//        listFromServer = new ArrayList<>();
//        maxCollectorAdaptor = new MaxCollectorAdaptor(getContext(), listFromServer);
//        listView.setAdapter(maxCollectorAdaptor);
//        getDataFromServer();
//        return rootview;
//    }
//    public void getDataFromServer()
//    {
//        String Url = WebServiceObject.IP + "returnStock";
//        StringRequest requestt=new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {    //.replace("", "%20")
//            @Override
//            public void onResponse(String response) {
//                try {
//                    // Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray array = new JSONArray(jsonObject.getString("returnStockResult"));
//
//                    for (int i = 0; i < array.length(); i++) {
//                        MaxCollItemObject obj = new MaxCollItemObject();
//                        JSONObject o = array.getJSONObject(i);
//
//                        //  obj.setID(o.getString("Id"));
//                        obj.setCollectorName(array.getJSONObject(i).getString("ColUser"));
//                        obj.setMaxCollection(array.getJSONObject(i).getString("maxCollection"));
//                        listFromServer.add(obj);
//
//                    }
//                    //  Toast.makeText(getContext(), listFromServer.size() + "", Toast.LENGTH_SHORT).show();
//                    maxCollectorAdaptor.notifyDataSetChanged();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage() + "", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(requestt);
//    }


}