//package com.example.qaiserpasha.project2017;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ListView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.qaiserpasha.project2017.Adapters.Donor_ReqListViewAdaptor;
//import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
//import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class DonorApprovedRequest extends AppCompatActivity {
//    ArrayList<Donor_ReqItemObject> list;
//    RequestQueue requestQueue;
//    Donor_ReqListViewAdaptor donorReqListViewAdaptor;
//    ListView listView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.donor_approved_request);
//        getSupportActionBar().setTitle("Approved Requests");
//
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        list = new ArrayList<>();
//        listView = (ListView)findViewById(R.id.listViewItemsForDonorApprovalRequests);
//        donorReqListViewAdaptor =new Donor_ReqListViewAdaptor(getApplicationContext(), list);
//        listView.setAdapter(donorReqListViewAdaptor);
//        getdataFromServer();
//    }
//
//    public void getdataFromServer() {
//        String url = WebServiceObject.GET_ALL_ACCEPTED_REQUESTS_NEW ; //+ "1";
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(final String response) {
//                try {
//                    JSONObject jsonObject =new JSONObject(response);
//                    JSONArray jsonArray=jsonObject.getJSONArray("DonationApproveeRequestAdminResult");
//                    for (int i = 0; i <jsonArray.length() ; i++) {
//                        JSONObject object=jsonArray.getJSONObject(i);
//                        Donor_ReqItemObject dobj =new Donor_ReqItemObject();
//                        dobj.setUserName(object.getString("DUserName"));
//                        dobj.setAddress(object.getString("locname"));
//                        dobj.setBagId(object.getString("bagId"));
//                        dobj.setNoClothes(object.getString("ItemNo"));
//
////                        String AStatus=object.getString("AStatus");
////                        String CStatus=object.getString("CStatus");
////                        if (AStatus.equals("1"))
////                        {
////                            list.add(dobj);
////                        }
//                        list.add(dobj);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                }
//
//                donorReqListViewAdaptor.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(request);
//    }
//}
