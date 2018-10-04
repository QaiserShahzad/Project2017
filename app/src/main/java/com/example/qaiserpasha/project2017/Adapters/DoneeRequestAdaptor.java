package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Collecter_Info_gathering;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.SQLite.ClothItem;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Qaiser on 1/23/2018.
 */

public class DoneeRequestAdaptor extends BaseAdapter {

    ArrayList<ClothItem> doneeRequestAdaptor;
    Context context;
    RequestQueue requestQueue;

    public DoneeRequestAdaptor(Context context, ArrayList<ClothItem> list) {
        this.context = context;
        doneeRequestAdaptor = list;
        requestQueue = Volley.newRequestQueue(context);

    }

    @Override
    public int getCount() {
        return doneeRequestAdaptor.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.donee_box_request_design, viewGroup,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtClothId.setText(doneeRequestAdaptor.get(i).getClotheItemID()+"");
        viewHolder.txtQuantityy.setText(doneeRequestAdaptor.get(i).getQuantity()+"");
        viewHolder.txtDoneeName.setText(doneeRequestAdaptor.get(i).getDname()+"");
        Picasso.with(context).load(WebServiceObject.IMAGE_URL+doneeRequestAdaptor.get(i).getPic()).into(viewHolder.imageDoneeReq);


        return convertView;
    }
    public static class ViewHolder {
        TextView txtClothId, txtQuantityy, txtDoneeName;
        ImageView imageDoneeReq;

        public ViewHolder(View view) {
            txtClothId = (TextView) view.findViewById(R.id.tvDoneeReqClothId);
            txtQuantityy = (TextView) view.findViewById(R.id.tvDoneeBoxQuantity);
            txtDoneeName=(TextView)view.findViewById(R.id.tvDoneename) ;
            imageDoneeReq=(ImageView)view.findViewById(R.id.imageViewListDoneeRequest);
        }
    }
}
