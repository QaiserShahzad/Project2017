package com.example.qaiserpasha.project2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qaiserpasha.project2017.Objects.SharedPrefHandler;
import com.example.qaiserpasha.project2017.SQLite.AppDatabase;
import com.example.qaiserpasha.project2017.SQLite.ClothItem;
import com.example.qaiserpasha.project2017.SQLite.ClothItemDAO;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.squareup.picasso.Picasso;

public class Donee_Info_Gathering extends AppCompatActivity {
    Button btnSubmit;
    TextView txtCId, tvGenger , tvDoneeeName;
    EditText Quantitty;
    ImageView imgview;
    //  RadioGroup radiogrop;
    Spinner spinAge, spinSeason, spinType, spinSize;
    ArrayAdapter<String> adapterItemType;
    ArrayAdapter<String> adapterSize;
    String[] listOfItems;
    AppDatabase appDatabase;
ClothItemDAO clothItemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donee__info__gathering);

        appDatabase=AppDatabase.getAppDatabase(getBaseContext());
        clothItemDAO=appDatabase.clothItemDAO();

        //  for populate user name in Donee
        final TextView tvuser = (TextView) findViewById(R.id.tvDoneeuserr);
        //Intent inte = getIntent();
       // String data = inte.getStringExtra("name");
        SharedPrefHandler sharedPrefHandler=new SharedPrefHandler(getBaseContext());
        tvuser.setText(sharedPrefHandler.getUserName());







        Quantitty=(EditText)findViewById(R.id.Quantity);

        imgview=(ImageView)findViewById(R.id.imageViewdonee);
        Picasso.with(getBaseContext()).load(WebServiceObject.IMAGE_URL+getIntent().getStringExtra("pix")).resize(100,100).into(imgview);

        txtCId = (TextView) findViewById(R.id.tvClothidd);
        txtCId.setText(getIntent().getStringExtra("id"));

        tvGenger = (TextView) findViewById(R.id.tvGender);
        tvGenger.setText(getIntent().getStringExtra("gndr"));


        // radiogrop = (RadioGroup) rootview.findViewById(R.id.radioBtngroup);
        spinAge = (Spinner) findViewById(R.id.spinnerAge);

        spinSeason = (Spinner) findViewById(R.id.spinnerSeason);
        String spSeason=getIntent().getStringExtra("Season");


        spinType = (Spinner) findViewById(R.id.spinnerType);



        spinSize = (Spinner) findViewById(R.id.spinnerSize);
        btnSubmit = (Button) findViewById(R.id.btnSubmitt);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClothItem clothItem=new ClothItem();
                clothItem.setClotheItemID(Integer.parseInt(txtCId.getText().toString()));
                clothItem.setQuantity(Integer.parseInt(Quantitty.getText().toString()));
                clothItem.setDname(tvuser.getText().toString());

                clothItem.setPic((getIntent().getStringExtra("pix")));
                Toast.makeText(getApplicationContext(), "Item has been added to Box", Toast.LENGTH_LONG).show();
                clothItemDAO.Insert(clothItem);


            }
        });



        listOfItems = getResources().getStringArray(R.array.items_arrays);
        adapterItemType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, listOfItems);
        spinType.setAdapter(adapterItemType);


       // spinSeason.setPrompt(getIntent().getStringExtra("Season"));
        String season = (getIntent().getStringExtra("Season"));

    if (season.equals("Summer"))
    {
        spinSeason.setSelection(1);
    }if (season.equals("Winter"))
    {
        spinSeason.setSelection(0);
    }


            String items = (getIntent().getStringExtra("ItemTypee"));
            for (int i = 0; i < listOfItems.length; i++)
            {
                if (listOfItems[i].equals(items))
                    spinType.setSelection(i);

            }
            spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            adapterSize = null;
                            String[] listShirts = getResources().getStringArray(R.array.uperWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listShirts);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 1:
                            adapterSize = null;
                            String[] listPent = getResources().getStringArray(R.array.lowerWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listPent);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 2:
                            adapterSize = null;
                            String[] listJecket = getResources().getStringArray(R.array.uperWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listJecket);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 3:
                            adapterSize = null;
                            String[] listSweater = getResources().getStringArray(R.array.uperWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listSweater);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 4:
                            adapterSize = null;
                            String[] listPajama = getResources().getStringArray(R.array.lowerWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listPajama);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 5:
                            adapterSize = null;
                            String[] listQameez = getResources().getStringArray(R.array.uperWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listQameez);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 6:
                            adapterSize = null;
                            String[] listShalwar = getResources().getStringArray(R.array.lowerWear_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listShalwar);
                            spinSize.setAdapter(adapterSize);
                            break;
                        case 7:
                            adapterSize = null;
                            String[] listShoes = getResources().getStringArray(R.array.shoes_size_arrays);
                            adapterSize = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listShoes);
                            spinSize.setAdapter(adapterSize);
                            break;
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }


    }

