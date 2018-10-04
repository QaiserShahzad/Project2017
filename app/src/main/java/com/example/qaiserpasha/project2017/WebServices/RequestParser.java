//package com.example.qaiserpasha.project2017.WebServices;
//
//import com.example.qaiserpasha.project2017.Objects.RequestObject;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * Created by Qaiser on 12/17/2017.
// */
//
//public class RequestParser {
//    public static ArrayList<RequestObject> responceToList(String res)
//    {
//        ArrayList<RequestObject> list=new ArrayList<>();
//        try {
//            JSONObject jsonObject=new JSONObject(res);
//            JSONArray jsonArray=jsonObject.getJSONArray("GetRequestsResult");
//            for (int i = 0; i <jsonArray.length() ; i++) {
//                JSONObject jOb=new JSONObject();
//                jOb=jsonArray.getJSONObject(i);
//                RequestObject requestObject=new RequestObject();
//                requestObject.setId(jOb.getString("R_ID"));
//                requestObject.setDonorId(jOb.getString("DonorID"));
//                requestObject.setCity(jOb.getString("CityName"));
//                requestObject.setStat(jOb.getString("Status"));
//                requestObject.setTime(jOb.getString("Time"));
//                requestObject.setDonorName(jOb.getString("DonorName"));
//                list.add(requestObject);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        return list;
//    }
//}
