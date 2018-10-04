package com.example.qaiserpasha.project2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qaiserpasha.project2017.Objects.MaxCollItemObject;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Qaiser on 2/7/2018.
 */

public class MaxCollectorAdaptor extends BaseAdapter {
    ArrayList<MaxCollItemObject> maxCollItemObjectArrayList ;
    Context context;

    public MaxCollectorAdaptor(Context context, ArrayList<MaxCollItemObject> list) {
        this.context = context;
        maxCollItemObjectArrayList = list;
    }
    @Override
    public int getCount() {
        return maxCollItemObjectArrayList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.max_collection_design, viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        // viewHolder.txtID.setText(stockkItemObjectArrayList.get(i).getClothId());
        viewHolder.txtCollName.setText(maxCollItemObjectArrayList.get(i).getCollectorName());
        viewHolder.txtMaxColl.setText(maxCollItemObjectArrayList.get(i).getMaxCollection());


        //  Toast.makeText(context, stockkItemObjectArrayList.get(i).getAge() + "mmm", Toast.LENGTH_SHORT).show();
        return view;
    }



    static class ViewHolder {
        TextView txtCollName, txtMaxColl;


        public ViewHolder(View view) {

            txtCollName = (TextView) view.findViewById(R.id.CollName);
            txtMaxColl = (TextView) view.findViewById(R.id.MaxColl);


        }
    }
}
