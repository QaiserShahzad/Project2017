package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
//import com.example.qaiserpasha.project2017.Objects.RequestObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 12/5/2017.
 */

public class Donor_ReqListViewAdaptor extends BaseAdapter {

    ArrayList<Donor_ReqItemObject> reqListViewAdaptors;
    Context context;
    RequestQueue requestQueue;
    public Donor_ReqListViewAdaptor(Context context, ArrayList<Donor_ReqItemObject> list) {
        this.context = context;
        reqListViewAdaptors = list;
    requestQueue= Volley.newRequestQueue(context);
    }

    @Override
    public int getCount() {
        return reqListViewAdaptors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listvew_deign_for_doner_req, viewGroup,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.txtDonorName.setText(this.reqListViewAdaptors.get(i).getUserName());
        viewHolder.txtCityName.setText(reqListViewAdaptors.get(i).getAddress());
        viewHolder.txtBagId.setText(reqListViewAdaptors.get(i).getBagId());
        viewHolder.txtItemsNo.setText(reqListViewAdaptors.get(i).getNoClothes());
        //Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "", Toast.LENGTH_SHORT).show();
        viewHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String Url= WebServiceObject.APPROVAL_UPDATE + reqListViewAdaptors.get(i).getBagId()+"/1";
            updateStat(Url);
                Toast.makeText(context, "approved", Toast.LENGTH_SHORT).show();
                reqListViewAdaptors.remove(i);
                notifyDataSetChanged();

            }
        });



                         // Change Button Color
//        if (reqListViewAdaptors.get(i).getStatus().equals("3"))
//        {
//            viewHolder.btnAccept.setBackgroundColor(Color.RED);
//            viewHolder.btnAccept.setTextColor(Color.WHITE);
//        }


        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url= WebServiceObject.APPROVAL_UPDATE + reqListViewAdaptors.get(i).getBagId()+"/2";
                updateStat(Url);
//                reqListViewAdaptors.remove(i);
//                notifyDataSetChanged();
                Toast.makeText(context, "Request Deleted ", Toast.LENGTH_SHORT).show();
                reqListViewAdaptors.remove(i);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        TextView txtDonorName, txtCityName, txtBagId , txtItemsNo;
        Button btnAccept,btnDelete;

        public ViewHolder(View view) {
            btnAccept=(Button)view.findViewById(R.id.btnAccept);
            btnDelete=(Button)view.findViewById(R.id.btnDelete);
            txtDonorName = (TextView) view.findViewById(R.id.tvDNme);
            txtCityName = (TextView) view.findViewById(R.id.tvCityName);
            txtBagId = (TextView) view.findViewById(R.id.tvBagId);
            txtItemsNo = (TextView) view.findViewById(R.id.tvItems);
        }
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
                Toast.makeText(context, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
