package com.example.qaiserpasha.project2017.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.CollecterListViewAdaptor;
import com.example.qaiserpasha.project2017.Adapters.DoneeSideCollectorAdaptor;
import com.example.qaiserpasha.project2017.Adapters.DoneeStockListViewAdaptor;
import com.example.qaiserpasha.project2017.Objects.DoneeReq_Item_Object;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 1/30/2018.
 */

public class Collector_Tab3 extends android.support.v4.app.Fragment {

    String[] listOfSizes;



    Button btnApproval;
    ArrayList<DoneeReq_Item_Object> list;
    RequestQueue requestQueue;
    DoneeSideCollectorAdaptor doneeReqListViewAdaptor;
    ListView listView;
    String id="";
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.collector_tab3, container, false);

      //  ArrayList<DoneeReq_Item_Object> doneeReq_ArrayList ;

        requestQueue = Volley.newRequestQueue(getContext());
        list = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.listViewItemsForCollectorDoneeRequests);
        doneeReqListViewAdaptor =new DoneeSideCollectorAdaptor(getContext(), list);
        listView.setAdapter(doneeReqListViewAdaptor);
        getdataFromServer();


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                String Url= WebServiceObject.DONEE_ACCEPT_REQ+doneeReq_ArrayList.get(position).getClothId()+"/2";
//                updateStat(Url);
                Toast.makeText(getContext(), "Request Deleted", Toast.LENGTH_SHORT).show();

                  list.remove(position);
                doneeReqListViewAdaptor.notifyDataSetChanged();
                return false;
            }
        });



        return rootview;
    }

    public void getdataFromServer() {
        String url = WebServiceObject.GET_ALL_ACCEPTED_DONEE_REQUESTS_NEW ; //+ "1";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("DoneeApproveeRequestAdminResult");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        DoneeReq_Item_Object dobj =new DoneeReq_Item_Object();
                        dobj.setDisName(object.getString("DoneeUserName"));
                        dobj.setCityName(object.getString("Address"));
                        dobj.setClothId(object.getString("clothId"));
                        dobj.setQuantty(object.getString("Quantity"));

                        list.add(dobj);


//        list.remove(0);
//        donorReqListViewAdaptor.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                doneeReqListViewAdaptor.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }












    public void updateStat(String url)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }








}
