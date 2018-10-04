package com.example.qaiserpasha.project2017.Fragments;

        // import android.support.v4.app.Fragment;
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
        import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
        import com.example.qaiserpasha.project2017.Adapters.StockListViewAdapter;
        import com.example.qaiserpasha.project2017.R;
        import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

/**
 * Created by Qaiser on 11/10/2017.
 */

public class Collector_Tab2 extends android.support.v4.app.Fragment {

    ListView listView;
    ArrayList<StockkItemObject> listFromServer;
    RequestQueue requestQueue;
    StockListViewAdapter stockListViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.collector_tab2, container, false);
        listView = (ListView) rootView.findViewById(R.id.listViewCollectorItemsFromServer);
        requestQueue = Volley.newRequestQueue(getContext());
        listFromServer = new ArrayList<>();
        stockListViewAdapter = new StockListViewAdapter(getContext(), listFromServer);
        listView.setAdapter(stockListViewAdapter);
        getDataFromServer();
        return rootView;
    }



        public void getDataFromServer() {
        String Url = WebServiceObject.IP + "getAllItems";
        StringRequest request = new StringRequest(Request.Method.GET, Url.replaceAll(" ", "%20"), new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                   // Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(jsonObject.getString("getAllItemsResult"));

                    for (int i = 0; i < array.length(); i++) {
                        StockkItemObject obj = new StockkItemObject();
                        JSONObject o = array.getJSONObject(i);

                        obj.setGender(o.getString("Gender"));
                        obj.setAge(array.getJSONObject(i).getString("Age"));
                        obj.setSeason(array.getJSONObject(i).getString("Season"));
                        obj.setType(array.getJSONObject(i).getString("Type"));
                        obj.setSize(array.getJSONObject(i).getString("Size"));
                        obj.setColor(array.getJSONObject(i).getString("Color"));
                        obj.setQty(array.getJSONObject(i).getString("qty"));
                        obj.setPic(array.getJSONObject(i).getString("Pic"));
                        listFromServer.add(obj);

                    }

                   Toast.makeText(getContext(), listFromServer.size() + "", Toast.LENGTH_SHORT).show();
                    stockListViewAdapter.notifyDataSetChanged();

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
