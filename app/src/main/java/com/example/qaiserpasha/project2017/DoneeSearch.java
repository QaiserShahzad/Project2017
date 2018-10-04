package com.example.qaiserpasha.project2017;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.StockListViewAdapter;
import com.example.qaiserpasha.project2017.Objects.SharedPrefHandler;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.SQLite.AppDatabase;
import com.example.qaiserpasha.project2017.SQLite.ClothItem;
import com.example.qaiserpasha.project2017.SQLite.ClothItemDAO;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoneeSearch extends AppCompatActivity {


    ListView listView;
    ArrayList<StockkItemObject> listFromServer;
    RequestQueue requestQueue;
    StockListViewAdapter stockListViewAdapter;
    SearchView searchView;
    AppDatabase appDatabase;
    ClothItemDAO clothItemDAO;
    SharedPrefHandler sharedPrefHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donee_search);
        getSupportActionBar().setTitle("Search");



        sharedPrefHandler = new SharedPrefHandler(getBaseContext());
        appDatabase = AppDatabase.getAppDatabase(getBaseContext());
        clothItemDAO = appDatabase.clothItemDAO();
        listView = (ListView) findViewById(R.id.DoneeSearchListView);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        listFromServer = new ArrayList<>();
        stockListViewAdapter = new StockListViewAdapter(getApplicationContext(), listFromServer);
        listView.setAdapter(stockListViewAdapter);
        searchView = (SearchView) findViewById(R.id.DoneeSearchh);
        searchView.setSubmitButtonEnabled(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                makeDialog(listFromServer.get(position));
                listFromServer.remove(position);
                stockListViewAdapter.notifyDataSetChanged();


                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getDataFromServer(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void makeDialog(StockkItemObject stockkItemObject) {
        final ClothItem clothItem = new ClothItem();
        clothItem.setDname(sharedPrefHandler.getUserName());
        clothItem.setQuantity(1);
        clothItem.setClotheItemID(Integer.parseInt(stockkItemObject.getClothId()));
        clothItem.setPic(stockkItemObject.getPic());
        new AlertDialog.Builder(DoneeSearch.this).setTitle("Add To BOX").setMessage("Do You Want To Add This Item To BOX").setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clothItemDAO.Insert(clothItem);

            }
        }).create().show();
    }


    public void getDataFromServer(String s) {

        String Url = WebServiceObject.IP + "search/" + s;
        listFromServer.clear();
        StringRequest request = new StringRequest(Request.Method.GET, Url.replaceAll(" ", "%20"), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    // Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(jsonObject.getString("searchResult"));

                    for (int i = 0; i < array.length(); i++) {
                        StockkItemObject obj = new StockkItemObject();
                        JSONObject o = array.getJSONObject(i);

                        obj.setGender(o.getString("Gender"));
                        obj.setClothId(o.getString("ClothID"));
                        obj.setAge(array.getJSONObject(i).getString("Age"));
                        obj.setSeason(array.getJSONObject(i).getString("Season"));
                        obj.setType(array.getJSONObject(i).getString("Type"));
                        obj.setSize(array.getJSONObject(i).getString("Size"));
                        obj.setColor(array.getJSONObject(i).getString("Color"));
                        obj.setQty(array.getJSONObject(i).getString("qty"));
                        obj.setPic(array.getJSONObject(i).getString("Pic"));
                        listFromServer.add(obj);

                    }

                    Toast.makeText(getApplicationContext(), listFromServer.size() + "", Toast.LENGTH_SHORT).show();

                    stockListViewAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage() + "went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

}
