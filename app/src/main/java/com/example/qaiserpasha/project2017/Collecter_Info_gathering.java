package com.example.qaiserpasha.project2017;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qaiserpasha.project2017.Adapters.DoneeRequestAdaptor;
import com.example.qaiserpasha.project2017.Adapters.Donor_ReqListViewAdaptor;
import com.example.qaiserpasha.project2017.Objects.Donor_ReqItemObject;
import com.example.qaiserpasha.project2017.Objects.SharedPrefHandler;
import com.example.qaiserpasha.project2017.Objects.StockkItemObject;
import com.example.qaiserpasha.project2017.WebServices.FileUploader;
import com.example.qaiserpasha.project2017.WebServices.WebServiceObject;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class Collecter_Info_gathering extends AppCompatActivity {


    // variable for passing data
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    ImageView iv;
    Integer SELECT_FILE = 0;
    ImageView btnGallery;
    Bitmap bmp;
    Spinner spinAge, spinSeason, spinType, spinSize, spinColor;
    EditText txtId,txtQuantty;
    Button btnSubmit;
    RadioGroup radiogrop;
    RadioButton radiobutton;
    RequestQueue requestQueue;
    String FileName;

    SharedPrefHandler sharedPrefHandler;

    //Adapter for spinner
    ArrayAdapter<String> adapterItemType;
    ArrayAdapter<String> adapterSizes;
    String[] listOftyes;

    DialogProperties properties = new DialogProperties();
    FilePickerDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecter__info_gathering);

sharedPrefHandler=new SharedPrefHandler(getBaseContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btnGallery = (ImageView) findViewById(R.id.gallerry);
        // listOfSizes=new ArrayList<>();
        // listOftyes=new ArrayList<>();

        //Camera Code
        iv = (ImageView) findViewById(R.id.imageViewCam);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

            }
        });
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;
        dialog = new FilePickerDialog(Collecter_Info_gathering.this,properties);
        dialog.setTitle("Select a Picture");

        // choose pic from gallery
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//               Intent intent = new Intent();
//               intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent.createChooser(intent, "select file"), SELECT_FILE);

                dialog.show();
                          }
        });
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                //files is the array of the paths of files selected by the Application User.

                if(files.length>0)
                {
                    final File f=new File(files[0]);
                    iv.setImageBitmap(BitmapFactory.decodeFile(f.getPath()));
                    FileName=f.getName();
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FileUploader.uploadFile(f);
                        }
                    });
                    thread.start();

                }
            }
        });



        //  for populate user name in collector
        TextView tvuser = (TextView) findViewById(R.id.tvuserr);
        Intent inte = getIntent();
        final String data = inte.getStringExtra("name");
        tvuser.setText(data);

        // get Ids
        txtId = (EditText) findViewById(R.id.editTextBagID);

        txtId.setText(getIntent().getStringExtra("id"));
        radiogrop = (RadioGroup) findViewById(R.id.radioBtngroup);
        spinAge = (Spinner) findViewById(R.id.spinnerAge);
        spinSeason = (Spinner) findViewById(R.id.spinnerSeason);
        spinType = (Spinner) findViewById(R.id.spinnerType);
        spinSize = (Spinner) findViewById(R.id.spinnerSize);
        spinColor = (Spinner) findViewById(R.id.spinnerColor);
        txtQuantty=(EditText)findViewById(R.id.editTextQuantitty);
        btnSubmit = (Button) findViewById(R.id.btnSubmitt);

        listOftyes = getResources().getStringArray(R.array.items_arrays);
    //    adapterItemType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, listOftyes);
        adapterItemType = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOftyes);

       // adapterItemType.setDropDownViewResource(R.layout.spinner_item);

        spinType.setAdapter(adapterItemType);
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        adapterSizes = null;
//                     String[] listShirts=getResources().getStringArray(R.array.uperWear_size_arrays);
//                     adapterSizes=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,listShirts);
//                     spinSize.setAdapter(adapterSizes);
                        break;
                    case 1:
                        adapterSizes = null;
                        String[] listShirts = getResources().getStringArray(R.array.uperWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listShirts);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 2:
                        adapterSizes = null;
                        String[] listPent = getResources().getStringArray(R.array.lowerWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listPent);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 3:
                        adapterSizes = null;
                        String[] listJecket = getResources().getStringArray(R.array.uperWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listJecket);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 4:
                        adapterSizes = null;
                        String[] listSweater = getResources().getStringArray(R.array.uperWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listSweater);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 5:
                        adapterSizes = null;
                        String[] listPajama = getResources().getStringArray(R.array.lowerWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listPajama);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 6:
                        adapterSizes = null;
                        String[] listQameez = getResources().getStringArray(R.array.uperWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listQameez);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 7:
                        adapterSizes = null;
                        String[] listShalwar = getResources().getStringArray(R.array.lowerWear_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listShalwar);
                        spinSize.setAdapter(adapterSizes);
                        break;
                    case 8:
                        adapterSizes = null;
                        String[] listShoes = getResources().getStringArray(R.array.shoes_size_arrays);
                        adapterSizes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listShoes);
                        spinSize.setAdapter(adapterSizes);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // code for data submitt to database
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txid = txtId.getText().toString();
                String spAge = spinAge.getSelectedItem().toString();
                String spSeason = spinSeason.getSelectedItem().toString();
                String spType = spinType.getSelectedItem().toString();
                String spSize = spinSize.getSelectedItem().toString();
                String spColorr = spinColor.getSelectedItem().toString();
                String txtQuaty= txtQuantty.getText().toString();


                StockkItemObject object = new StockkItemObject();
                if (radiogrop.getCheckedRadioButtonId() == R.id.radiobtnMale) {
                    object.setGender("Male");
                } else {

                    object.setGender("Female");
                }

                object.setBagId(txid);
                object.setAge(spAge);
                object.setType(spType);
                object.setSize(spSize);
                object.setSeason(spSeason);
                object.setColor(spColorr);
                object.setQty(txtQuaty);
                object.setPic(FileName);
                submitDataToServer(object);

            }

        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                iv.setImageBitmap(bitmap);



            }

        }
        if (requestCode == SELECT_FILE) {
            if (resultCode == Activity.RESULT_OK) {

                Uri selectedImageUri = data.getData();
                iv.setImageURI(selectedImageUri);
                final File file=new File(getRealPathFromURI(selectedImageUri));
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileUploader.uploadFile(file);
                    }
                });
                thread.start();


            }

        }
    }
    // getting imafe from gallery
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        //This method was deprecated in API level 11
        //Cursor cursor = managedQuery(contentUri, proj, null, null, null);

        CursorLoader cursorLoader = new CursorLoader(
                this,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    //  insertion Code to database
    public void submitDataToServer(final StockkItemObject object) {
     //  String string= getIntent().getStringExtra("id");

        String url = WebServiceObject.ADD_STOCK +object.getBagId()+"/"+sharedPrefHandler.getUserName()+"/"+ object.getPic() +"/"+ object.getGender() + "/" + object.getType() + "/" + object.getAge() + "/" + object.getSize() + "/" + object.getSeason() + "/" + object.getQty()+ "/" + object.getColor()  ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.replaceAll(" ", "%20"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response + "inserteddd", Toast.LENGTH_SHORT).show();
                if(response.contains("1"))
                {
                    Intent i=new Intent(getBaseContext(),WelcomeCollector.class);
                    i.putExtra("id",object.getBagId());
                    startActivity(i);
                    finish();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage() + "not insertedddd", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
