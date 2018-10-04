package com.example.qaiserpasha.project2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MaxDonationn extends AppCompatActivity {
    ListView listView;
    ArrayList<MaxCollItemObject> listFromServer;
    RequestQueue requestQueue;
    MaxCollectorAdaptor maxCollectorAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_donationn);
        listView = (ListView) findViewById(R.id.listViewItemsForMaxCollection);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        listFromServer = new ArrayList<>();
        getDataFromServer();
        }
    public void getDataFromServer()
    {
        String Url = WebServiceObject.IP + "returnStock/"+"tamoor";
        StringRequest requestt=new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {    //.replace("", "%20")
            @Override
            public void onResponse(String response) {
                try {
                    // Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(jsonObject.getString("returnStockResult"));

                    for (int i = 0; i < array.length(); i++) {
                        MaxCollItemObject obj = new MaxCollItemObject();
                        JSONObject o = array.getJSONObject(i);

                        //  obj.setID(o.getString("Id"));
                        obj.setCollectorName(array.getJSONObject(i).getString("ColUser"));
                        obj.setMaxCollection(array.getJSONObject(i).getString("maxCollection"));
                        listFromServer.add(obj);

                    }
                    //  Toast.makeText(getContext(), listFromServer.size() + "", Toast.LENGTH_SHORT).show();
             //       maxCollectorAdaptor.notifyDataSetChanged();

                    maxCollectorAdaptor = new MaxCollectorAdaptor(getApplicationContext(), listFromServer);
                    listView.setAdapter(maxCollectorAdaptor);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(requestt);
    }
}
