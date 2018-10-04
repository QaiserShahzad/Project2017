package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Objects.Donation_Item_Object;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Qaiser on 2/7/2018.
 */

public class AuditShowAdaptor extends BaseAdapter {

    ArrayList<Donation_Item_Object> donation_pic_objectArrayList;
    Context context;
    RequestQueue requestQueue;

    public AuditShowAdaptor(Context context, ArrayList<Donation_Item_Object> list) {
        this.context = context;

        donation_pic_objectArrayList = list;
        requestQueue= Volley.newRequestQueue(context);
    }


    @Override
    public int getCount() {
        return donation_pic_objectArrayList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.auditdesign, viewGroup,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



       // viewHolder.txtDonorName.setText(this.donation_pic_objectArrayList.get(i).getBagId());

       // Picasso.with(context).load(WebServiceObject.IMAGE_URL+donation_pic_objectArrayList.get(i).getPic()).resize(50,50).into(viewHolder.auditPic);
        Picasso.with(context).load(WebServiceObject.IMAGE_URL+donation_pic_objectArrayList.get(i).getPic()).into(viewHolder.auditPic);

       // Toast.makeText(context, donation_pic_objectArrayList.get(i).getPic() + "", Toast.LENGTH_SHORT).show();
        return convertView;
    }


    public static class ViewHolder {

        ImageView auditPic;
        public ViewHolder(View view) {
             auditPic=(ImageView)view.findViewById(R.id.imgvwAudit) ;

        }
    }
}
