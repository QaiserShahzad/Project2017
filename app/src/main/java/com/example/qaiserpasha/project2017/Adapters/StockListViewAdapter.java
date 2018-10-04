package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Qaiser Pasha on 10/10/2017.
 */

public class StockListViewAdapter extends BaseAdapter {

    ArrayList<StockkItemObject> stockkItemObjectArrayList;
    Context context;

    public StockListViewAdapter(Context context, ArrayList<StockkItemObject> list) {
        this.context = context;
        stockkItemObjectArrayList = list;
    }

    @Override
    public int getCount() {
        return stockkItemObjectArrayList.size();
    }

    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_view_design, viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
            {
                   viewHolder = (ViewHolder) view.getTag();
             }

       // viewHolder.txtID.setText(stockkItemObjectArrayList.get(i).getClothId());
        viewHolder.txtGender.setText(stockkItemObjectArrayList.get(i).getGender());
        viewHolder.txtAge.setText(stockkItemObjectArrayList.get(i).getAge());
        viewHolder.txtSeasson.setText(stockkItemObjectArrayList.get(i).getSeason());
        viewHolder.txtType.setText(stockkItemObjectArrayList.get(i).getType());
        viewHolder.txtSize.setText(stockkItemObjectArrayList.get(i).getSize());
        viewHolder.txtColor.setText(stockkItemObjectArrayList.get(i).getColor());
        viewHolder.txtQuanty.setText(stockkItemObjectArrayList.get(i).getQty());
        Picasso.with(context).load(WebServiceObject.IMAGE_URL+stockkItemObjectArrayList.get(i).getPic()).resize(50,50).into(viewHolder.imag);

      //  Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "mmm", Toast.LENGTH_SHORT).show();
        return view;
    }

    static class ViewHolder {
        TextView txtBagId, txtGender, txtAge, txtSeasson, txtType ,txtSize, txtColor, txtQuanty;
        ImageView imag;

        public ViewHolder(View view) {
           // txtBagId = (TextView) view.findViewById(R.id.tvvvvvvvvID);
            txtGender = (TextView) view.findViewById(R.id.tvGender2);
            txtAge = (TextView) view.findViewById(R.id.tvAge2);
            txtSeasson = (TextView) view.findViewById(R.id.tvSeason2);
            txtType = (TextView) view.findViewById(R.id.tvType2);
            txtSize = (TextView) view.findViewById(R.id.tvSize2);
            txtColor = (TextView) view.findViewById(R.id.tvColor2);
            txtQuanty = (TextView) view.findViewById(R.id.tvQantityy2);
            imag=(ImageView)view.findViewById(R.id.imageViewListDesign2);
        }
    }
}