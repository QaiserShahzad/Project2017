package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
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
import com.example.qaiserpasha.project2017.Objects.Donation_Item_Object;
import com.example.qaiserpasha.project2017.Objects.DoneeReq_Item_Object;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 1/26/2018.
 */

public class DoneeReqAdaptor extends BaseAdapter {



    ArrayList<DoneeReq_Item_Object> doneeReq_ArrayList;
    Context context;
    RequestQueue requestQueue;
    public DoneeReqAdaptor(Context context, ArrayList<DoneeReq_Item_Object> list) {
        this.context = context;

        doneeReq_ArrayList = list;
        requestQueue= Volley.newRequestQueue(context);
    }

    @Override
    public int getCount() {
        return doneeReq_ArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {


       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listiew_design_for_donee_req, viewGroup,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.txtReqDoneeName.setText(this.doneeReq_ArrayList.get(i).getDisName());
        viewHolder.txtClothId.setText(doneeReq_ArrayList.get(i).getClothId());
        viewHolder.txtQuant.setText(doneeReq_ArrayList.get(i).getQuantty());
     //   viewHolder.txtDoneeCity.setText(doneeReq_ArrayList.get(i).getCityName());
     //   viewHolder.txtReqId.setText(doneeReq_ArrayList.get(i).getReqId());
        viewHolder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url= WebServiceObject.DONEE_ACCEPT_REQ+doneeReq_ArrayList.get(i).getClothId()+"/1";
                updateStat(Url);
                Toast.makeText(context, "approved", Toast.LENGTH_SHORT).show();
                doneeReq_ArrayList.remove(i);
                notifyDataSetChanged();
            }
        });

        viewHolder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url= WebServiceObject.DONEE_ACCEPT_REQ+doneeReq_ArrayList.get(i).getClothId()+"/2";
                updateStat(Url);
                Toast.makeText(context, "Request Deleted", Toast.LENGTH_SHORT).show();
                doneeReq_ArrayList.remove(i);
                notifyDataSetChanged();
            }
        });





        //Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "", Toast.LENGTH_SHORT).show();
        return convertView;
    }
    public static class ViewHolder {
        Button btnConfirm, btnReject;
        TextView txtReqDoneeName, txtClothId , txtQuant,txtDoneeCity,txtReqId;
        public ViewHolder(View view) {
//            txtReqId=(TextView)view.findViewById(R.id.tvDoneeReqID);
//            txtDoneeCity = (TextView) view.findViewById(R.id.tvDoneeReqCityName);
            btnConfirm=(Button)view.findViewById(R.id.btnDoneeReqAccept);
            btnReject=(Button)view.findViewById(R.id.btnDoneeReqDelete);

            txtReqDoneeName = (TextView) view.findViewById(R.id.tvDoneeReqNme);
            txtClothId = (TextView) view.findViewById(R.id.tvAdminDoneeReqClothId);
            txtQuant=(TextView)view.findViewById(R.id.tvDoneeReqQuantity);
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
