package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Objects.Donation_Item_Object;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

import java.util.ArrayList;

/**
 * Created by Qaiser on 1/15/2018.
 */

public class DonationHistoryAdaptor extends BaseAdapter {
    ArrayList<Donation_Item_Object> donation_item_objectArrayList;
    Context context;
    RequestQueue requestQueue;
    public DonationHistoryAdaptor(Context context, ArrayList<Donation_Item_Object> list) {
        this.context = context;

        donation_item_objectArrayList = list;
        requestQueue= Volley.newRequestQueue(context);
    }

    @Override
    public int getCount() {
        return donation_item_objectArrayList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.donation_history_design, viewGroup,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.txtDonorName.setText(this.donation_item_objectArrayList.get(i).getBagId());
        dummy.bagId=i;
        viewHolder.txtItems.setText(donation_item_objectArrayList.get(i).getItems());
        viewHolder.txtDate.setText(donation_item_objectArrayList.get(i).getDatte());
        viewHolder.txtAddress.setText(donation_item_objectArrayList.get(i).getAddr());
        //Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "", Toast.LENGTH_SHORT).show();
        return convertView;
    }
    public static class ViewHolder {
        TextView txtDonorName, txtItems, txtDate, txtAddress;
        ImageView auditPic;
        public ViewHolder(View view) {
           // auditPic=(ImageView)view.findViewById()
            txtDonorName = (TextView) view.findViewById(R.id.tvUname);
            txtItems = (TextView) view.findViewById(R.id.tvItemNum);
            txtDate = (TextView) view.findViewById(R.id.tvDate);
            txtAddress = (TextView) view.findViewById(R.id.tvAddresss);
        }
    }
}
