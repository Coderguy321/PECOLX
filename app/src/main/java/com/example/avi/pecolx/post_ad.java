package com.example.avi.pecolx;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class post_ad extends AppCompatActivity {
    EditText bname,aname,price;
    Button cap_button,post_button;


    String name, author, captcha;
    EditText editText1, editText2, editText3, editText4;
    static final int request_image = 1;
    ImageView photo;
    Button camera_b;
    int idx;
    View radioButton;
    String selectedtext;
    RadioGroup radioButtonGroup;
    RadioButton temp;
    ////////////
    String encoded_string,image_name;
    Bitmap bitmap;
    File file;
    Uri file_uri;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);

        setContentView(R.layout.activity_post_ad);
        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);

        bname=(EditText)findViewById(R.id.edittext1);
        aname=(EditText)findViewById(R.id.edittext2);
        price=(EditText)findViewById(R.id.edittext3);
        radioButtonGroup=(RadioGroup) findViewById(R.id.layoutG);
        photo = (ImageView) findViewById(R.id.imageView);

        camera_b = (Button) findViewById(R.id.buttonCapture);

        if (!hasCamera()) {
            camera_b.setEnabled(false);
        }
    }

    public void Post_ad(View view)
    {

        if(radioButtonGroup.getCheckedRadioButtonId()==-1)
        {
            Toast.makeText(post_ad.this, "Select Category", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int selectedId=radioButtonGroup.getCheckedRadioButtonId();
            temp = (RadioButton)findViewById(selectedId);
            selectedtext=temp.getText().toString();
            Toast.makeText(post_ad.this, selectedtext, Toast.LENGTH_SHORT).show();

        }


        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");
        String bookname=bname.getText().toString();
        String authname=aname.getText().toString();
        String pric=price.getText().toString();

        //category was already fetched
//        String selectcat= temp.getText().toString();

        requestPackage.setParam("book_name",bookname);
        requestPackage.setParam("author",authname);
        requestPackage.setParam("book_price",pric);
        requestPackage.setParam("category",selectedtext);
        //requestPackage.setParam("encode_string",encoded_string);
      //  requestPackage.setParam("image_name",image_name);

      //  Log.d("encoded String",encoded_string);
        // requestPackage.setUri("http://192.168.43.239/pecolx/login.php");
        requestPackage.setUri("http://192.168.43.170/pecolx/postad.php");
        SignupTask signupTask = new SignupTask();
        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);



    }


    boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getfileuri();
        //storing file in phone
        intent.putExtra(MediaStore.EXTRA_OUTPUT,file_uri);
        startActivityForResult(intent, request_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_image && resultCode == RESULT_OK) {
           // Bundle extras = data.getExtras();
            //Bitmap p = (Bitmap) extras.get("data");
            //photo.setImageBitmap(p);
            new Encode_image().execute();

        }
    }


    private class Encode_image extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void ...voids) {

            try {
                bitmap = BitmapFactory.decodeFile(file_uri.getPath());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] array = stream.toByteArray();
                encoded_string = Base64.encodeToString(array, 0);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }




    private class SignupTask extends AsyncTask<RequestPackage,Void,Void>{

        @Override
        protected Void doInBackground(RequestPackage... params)
        {
            //System.out.println("thread");
            String content = HttpManager.getData(params[0]);
            try {
                Log.d("con", content);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();

            return null;
        }
    }


    public  void getfileuri()
    {
        //this should be unique here just for checking
        image_name="test.jpg";
        file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator +image_name);
        file_uri=Uri.fromFile(file);
    }
}
