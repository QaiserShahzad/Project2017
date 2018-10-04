package com.example.qaiserpasha.project2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.DoneeStockListViewAdaptor;
import com.example.qaiserpasha.project2017.Adapters.ReviewsAdaptor;
import com.example.qaiserpasha.project2017.Objects.ReviewItemObject;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowReviews extends AppCompatActivity {
//    ListView ReviewlistView;
//    ArrayList<ReviewItemObject> listFromServer;
//    RequestQueue requestQueue;
//    ReviewsAdaptor reviewsAdaptor;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.show_reviews);
//
//
//
//        ReviewlistView = (ListView)findViewById(R.id.ReviewsLists );
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        listFromServer = new ArrayList<>();
//        reviewsAdaptor = new ReviewsAdaptor(getApplicationContext(), listFromServer);
//        ReviewlistView.setAdapter(reviewsAdaptor);
//
//        getDataFromServer();
//    }
//
//
//    public void getDataFromServer() {
//        String Url = WebServiceObject.IP + "showRating";
//        StringRequest request = new StringRequest(Request.Method.GET, Url.replaceAll(" ", "%20"), new Response.Listener<String>()
//        {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    // Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray array = new JSONArray(jsonObject.getString("showRatingResult"));
//
//                    for (int i = 0; i < array.length(); i++) {
//                        ReviewItemObject obj = new ReviewItemObject();
//                        JSONObject o = array.getJSONObject(i);
//
//                        obj.setRatedBy(o.getString("RatedBy"));
//                        obj.setRatedComment(array.getJSONObject(i).getString("RatingComments"));
//                        obj.setRatedValues(array.getJSONObject(i).getString("RatingValues"));
//                        listFromServer.add(obj);
//
//                    }
//
//                    Toast.makeText(getApplicationContext(), listFromServer.size() + "", Toast.LENGTH_SHORT).show();
//                    reviewsAdaptor.notifyDataSetChanged();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getApplicationContext(), error.getMessage() + "went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        requestQueue.add(request);
//    }
}
