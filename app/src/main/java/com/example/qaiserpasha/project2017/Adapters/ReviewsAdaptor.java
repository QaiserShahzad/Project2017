package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Objects.DoneeReq_Item_Object;
import com.example.qaiserpasha.project2017.Objects.ReviewItemObject;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Qaiser on 1/30/2018.
 */

public class ReviewsAdaptor extends BaseAdapter {

    ArrayList<ReviewItemObject> rating_ArrayList;
    Context context;

    public ReviewsAdaptor(Context context, ArrayList<ReviewItemObject> list) {
        this.context = context;
        rating_ArrayList = list;
    }

    @Override
    public int getCount() {
        return rating_ArrayList.size();
    }

    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


        @Override
        public View getView ( int i, View view, ViewGroup viewGroup){

            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.reviews_design, viewGroup, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            // viewHolder.txtID.setText(stockkItemObjectArrayList.get(i).getClothId());
            viewHolder.txtratedBy.setText(rating_ArrayList.get(i).getRatedBy());
            viewHolder.txtComents.setText(rating_ArrayList.get(i).getRatedComment());
            viewHolder.txtratedValue.setText(rating_ArrayList.get(i).getRatedValues());

            //  Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "mmm", Toast.LENGTH_SHORT).show();
            return view;
        }

        static class ViewHolder {
            TextView txtratedBy, txtComents, txtratedValue;
            ImageView imag;

            public ViewHolder(View view) {
                txtratedBy = (TextView) view.findViewById(R.id.RatedBy);
                txtComents = (TextView) view.findViewById(R.id.RatedComments);
                txtratedValue = (TextView) view.findViewById(R.id.RatedValue);
            }
        }
    }

