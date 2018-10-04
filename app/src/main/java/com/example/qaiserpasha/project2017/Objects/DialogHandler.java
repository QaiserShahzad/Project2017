package com.example.qaiserpasha.project2017.Objects;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.R;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;

/**
 * Created by Qaiser on 1/25/2018.
 */

public class DialogHandler {
    public static void showDialog(final Activity activity,final String rateBy)
    {
      final  RequestQueue requestQueue= Volley.newRequestQueue(activity);


        View dialogView= LayoutInflater.from(activity).inflate(R.layout.ratingdialog,null,false);
        final RatingBar ratingBar=(RatingBar)dialogView.findViewById(R.id.ratingBar);
        final EditText editTextComments=(EditText)dialogView.findViewById(R.id.edittextRatingDialogComment);
        new AlertDialog.Builder(activity).setView(dialogView).setPositiveButton("RATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = WebServiceObject.RATTING+rateBy+"/"+editTextComments.getText().toString()+"/"+ratingBar.getRating();
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url.replaceAll(" ", "%20"), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity.getBaseContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity.getBaseContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);

            }
        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).create().show();

    }
}
