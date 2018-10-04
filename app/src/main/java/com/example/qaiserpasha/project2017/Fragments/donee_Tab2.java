package com.example.qaiserpasha.project2017.Fragments;

import android.arch.persistence.room.Dao;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.DoneeRequestAdaptor;
import com.example.qaiserpasha.project2017.Objects.DoneeReq_Item_Object;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.SQLite.AppDatabase;
import com.example.qaiserpasha.project2017.SQLite.ClothItem;
import com.example.qaiserpasha.project2017.SQLite.ClothItemDAO;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.example.qaiserpasha.project2017.WelcomeCollector;

import java.util.ArrayList;

/**
 * Created by Qaiser on 11/15/2017.
 */

public class donee_Tab2 extends android.support.v4.app.Fragment
{
    Button btnRequest;
    ListView listViewDonee;
    DoneeRequestAdaptor doneeRequestAdaptor;
    ClothItemDAO clothItemDAO;
    AppDatabase appDatabase;
    ArrayList<ClothItem> list;
    RequestQueue requestQueue;


    TextView textViewName, textViewCID,textViewQuantity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.donee_tab2, container, false);


requestQueue= Volley.newRequestQueue(getContext());
        textViewName=(TextView)rootview.findViewById(R.id.tvDoneename);
        textViewCID=(TextView)rootview.findViewById(R.id.tvDoneeReqClothId);
        textViewQuantity=(TextView)rootview.findViewById(R.id.tvDoneeBoxQuantity);

        btnRequest=(Button)rootview.findViewById(R.id.btnRequest);

        listViewDonee=(ListView)rootview.findViewById(R.id.listView_Donee_Box);
        appDatabase=AppDatabase.getAppDatabase(getContext());
        clothItemDAO=appDatabase.clothItemDAO();
        list=new ArrayList<>();
        list= (ArrayList<ClothItem>) clothItemDAO.getAllItemFromBox();
        doneeRequestAdaptor=new DoneeRequestAdaptor(getContext(),list);
        listViewDonee.setAdapter(doneeRequestAdaptor);

        listViewDonee.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                clothItemDAO.deleteAll(list.get(position).clotheItemID);
                list.remove(position);
                doneeRequestAdaptor.notifyDataSetChanged();
                return false;
            }
        });





      btnRequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        for (int i = 0; i <list.size() ; i++) {



            DoneeReq_Item_Object object=new DoneeReq_Item_Object();
            object.setDisName(list.get(i).getDname());
            object.setClothId(list.get(i).getClotheItemID()+"");
            object.setQuantty(list.get(i).getQuantity()+"");
            submitDataToServer(object);
        }


    }
});
        return rootview;
    }
    //  insertion Code to database
    public void submitDataToServer(final DoneeReq_Item_Object object) {

        String url = WebServiceObject.DONEE_SEND_REQ+ object.getClothId()+"/" +object.getDisName() +"/"+ object.getQuantty() ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response + "inserteddd", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage() + "not insertedddd", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }


}
