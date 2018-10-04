package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Collecter_Info_gathering;
import com.example.qaiserpasha.project2017.Objects.DoneeReq_Item_Object;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 1/30/2018.
 */

public class DoneeSideCollectorAdaptor extends BaseAdapter {
    ArrayList<DoneeReq_Item_Object> doneereqqListViewAdaptors;
    Context context;
    RequestQueue requestQueue;

    ListView listView;
    public DoneeSideCollectorAdaptor(Context context, ArrayList<DoneeReq_Item_Object> list) {
        this.context = context;
        doneereqqListViewAdaptors = list;
        requestQueue= Volley.newRequestQueue(context);
    }

    @Override
    public int getCount()
    {
        return doneereqqListViewAdaptors.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_collector_donee_request, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.txtDoneeName.setText(this.doneereqqListViewAdaptors.get(i).getDisName());
        viewHolder.txtAddress.setText(doneereqqListViewAdaptors.get(i).getCityName());
        viewHolder.txtClothId.setText(doneereqqListViewAdaptors.get(i).getClothId());
        viewHolder.txtQuatity.setText(doneereqqListViewAdaptors.get(i).getQuantty());
        //Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "", Toast.LENGTH_SHORT).show();


//        viewHolder.Click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, Collecter_Info_gathering.class);
//                intent.putExtra("id",doneereqListViewAdaptors.get(i).getBagId());
//                context.startActivity(intent);
//            }
//        });






        return convertView;
    }
    public static class ViewHolder {
        TextView txtDoneeName, txtAddress, txtClothId , txtQuatity;
        LinearLayout Click;
        public ViewHolder(View view) {
            txtDoneeName = (TextView) view.findViewById(R.id.tvCollectorDoneeNme);
            txtAddress = (TextView) view.findViewById(R.id.tvdoneeCollectorCityName);
            txtClothId = (TextView) view.findViewById(R.id.tvDoneeCollectorClothId);
            txtQuatity = (TextView) view.findViewById(R.id.tvDoneeCollectorItems);
          //  Click=(LinearLayout)view.findViewById(R.id.Click);
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

