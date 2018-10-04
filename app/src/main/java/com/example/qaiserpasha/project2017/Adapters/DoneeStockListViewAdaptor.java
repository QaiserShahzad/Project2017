package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qaiserpasha.project2017.Collecter_Info_gathering;
import com.example.qaiserpasha.project2017.Donee_Info_Gathering;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.example.qaiserpasha.project2017.WelcomeDonee;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Qaiser on 1/16/2018.
 */

public class DoneeStockListViewAdaptor extends BaseAdapter {
    ArrayList<StockkItemObject> doneestockkItemObjectArrayList;
    Context context;

    public DoneeStockListViewAdaptor(Context context, ArrayList<StockkItemObject> list) {
        this.context = context;
        doneestockkItemObjectArrayList = list;
    }
    @Override
    public int getCount() {
        return doneestockkItemObjectArrayList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.donee_stok_design, viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        // viewHolder.txtID.setText(stockkItemObjectArrayList.get(i).getClothId());
        viewHolder.txtGender.setText(doneestockkItemObjectArrayList.get(i).getGender());
        viewHolder.txtSeasson.setText(doneestockkItemObjectArrayList.get(i).getSeason());
        viewHolder.txtType.setText(doneestockkItemObjectArrayList.get(i).getType());
        viewHolder.txtclthID.setText(doneestockkItemObjectArrayList.get(i).getClothId());
        Picasso.with(context).load(WebServiceObject.IMAGE_URL+doneestockkItemObjectArrayList.get(i).getPic()).resize(50,50).into(viewHolder.image);

        //  Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "mmm", Toast.LENGTH_SHORT).show();


        viewHolder.Clickkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Donee_Info_Gathering.class);
                intent.putExtra("id",doneestockkItemObjectArrayList.get(i).getClothId());
                intent.putExtra("gndr",doneestockkItemObjectArrayList.get(i).getGender());
                intent.putExtra("ItemTypee",doneestockkItemObjectArrayList.get(i).getType());
                intent.putExtra("Season",doneestockkItemObjectArrayList.get(i).getSeason());
                intent.putExtra("pix",doneestockkItemObjectArrayList.get(i).getPic());

                context.startActivity(intent);
            }
        });
        return view;
    }

    static class ViewHolder {
        TextView txtclthID, txtGender,  txtSeasson, txtType;
        LinearLayout Clickkk;
        ImageView image;
        public ViewHolder(View view) {
            // txtBagId = (TextView) view.findViewById(R.id.tvvvvvvvvID);
            txtGender = (TextView) view.findViewById(R.id.tvGender);
            txtSeasson = (TextView) view.findViewById(R.id.tvSeason);
            txtType = (TextView) view.findViewById(R.id.tvType);
            Clickkk=(LinearLayout)view.findViewById(R.id.Clickk);
            txtclthID = (TextView) view.findViewById(R.id.tvClothId);
            image=(ImageView)view.findViewById(R.id.imageViewListDonee);
        }
    }
}
