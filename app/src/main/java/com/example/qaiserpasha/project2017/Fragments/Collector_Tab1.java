package com.example.qaiserpasha.project2017.Fragments;

        //  import android.support.v4.app.Fragment;
        import android.content.Intent;
                import android.os.Bundle;
                import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
                import android.widget.Button;
                import android.widget.ListView;

                import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.example.qaiserpasha.project2017.Adapters.CollecterListViewAdaptor;
        //import com.example.qaiserpasha.project2017.DonorApprovedRequest;
        import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
                import com.example.qaiserpasha.project2017.R;
        import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

                import java.util.ArrayList;

/**
 * Created by Qaiser on 11/10/2017.
 */

public class Collector_Tab1 extends android.support.v4.app.Fragment {


    String[] listOfSizes;


    ///



    Button btnApproval;
    ArrayList<Donor_ReqItemObject> list;
    RequestQueue requestQueue;
    CollecterListViewAdaptor donorReqListViewAdaptor;
    ListView listView;
    String id="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.collector_tab1, container, false);
        btnApproval = (Button) rootview.findViewById(R.id.btnApprove);
        requestQueue = Volley.newRequestQueue(getContext());
        list = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.listViewItemsForDonorRequests);
        donorReqListViewAdaptor =new CollecterListViewAdaptor(getContext(), list);
         id=getActivity().getIntent().getStringExtra("id");

      listView.setAdapter(donorReqListViewAdaptor);
        getdataFromServer();




        return rootview;
    }

    public void getdataFromServer() {
        String url = WebServiceObject.GET_ALL_ACCEPTED_REQUESTS_NEW ; //+ "1";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("DonationApproveeRequestAdminResult");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object=jsonArray.getJSONObject(i);
                        Donor_ReqItemObject dobj =new Donor_ReqItemObject();
                        dobj.setUserName(object.getString("DUserName"));
                        dobj.setAddress(object.getString("locname"));
                        dobj.setBagId(object.getString("bagId"));
                        dobj.setStatus(object.getString("status"));
                        dobj.setNoClothes(object.getString("ItemNo"));

                        list.add(dobj);


//        list.remove(0);
//        donorReqListViewAdaptor.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                donorReqListViewAdaptor.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }


}