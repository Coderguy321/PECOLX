package com.example.avi.pecolx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class my_ads_displaylistview extends AppCompatActivity {
    String json_String;
    JSONObject jsonObject;
    JSONArray jsonArray;
    book_info_adapter bookInfoAdapter;
    ListView listView;
    String json_string;
    String str;
    String name, password, contact,path,uid;
    String bitmapstring;
    Bitmap bitmap;
    String my_ads_jason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Intent i = new Intent();


//        StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(Policy);
//
//        RequestPackage requestPackage = new RequestPackage();
//        requestPackage.setMethod("GET");
//
//        requestPackage.setParam("bORc", str);
//
//        requestPackage.setUri("http://192.168.43.66/pecolx/fetch_bookdetails.php");
//        SignupTask signupTask = new SignupTask();
//        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
//
//        while(json_string==null) {// Toast.makeText(getApplicationContext(), "First get json", Toast.LENGTH_SHORT).show();
//        }


        // Intent i = new Intent(this, display_listview.class);
        //  i.putExtra("json_data", json_string);
//            i.putExtra("message",)
        //startActivity(i);


        setContentView(R.layout.activity_display_listview);
        listView = (ListView) findViewById(R.id.listview);
        bookInfoAdapter = new book_info_adapter(this, R.layout.bookdetails_layout);
        listView.setAdapter(bookInfoAdapter);
        try {
            json_String = getIntent().getExtras().getString("books_data");
            my_ads_jason=getIntent().getExtras().getString("my_books_data");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try {
            //    jsonObject = new JSONObject("{json_String}");
            jsonObject = new JSONObject(my_ads_jason.substring(my_ads_jason.indexOf("{"), my_ads_jason.lastIndexOf("}") + 1));
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            while (count <jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("book_name");
                password = jo.getString("book_price");
                contact = jo.getString("seller_mno");
                path=jo.getString("path");
                uid=jo.getString("uid");


                //   DownloadImage obj=new DownloadImage();
                // obj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                Log.i("path", path);
                String Url = "http://192.168.43.66/" + path;
                try {
                    URLConnection connection = new URL(Url).openConnection();
                    connection.setConnectTimeout(1000 * 30);
                    connection.setReadTimeout(1000 * 30);
                    bitmap = (BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null));
                    Log.i("pavcv", "vdv");
                    //  Bitmap c=x.createScaledBitmap(x, 160, 160, true);
                    //bitmapstring= BitmaptoBinary(c);
                    //return bitmapstring;
                    //  BitmaptoBinary();
                } catch (Exception e) {
                    e.printStackTrace();

                }
                book_details info = new book_details(name,password,contact,bitmap);
                bookInfoAdapter.add(info);
                count++;

            }
        } catch (JSONException e) {




































































































            e.printStackTrace();
        }





//        str = getIntent().getStringExtra("message");



    }





    public class DownloadImage extends AsyncTask<Void,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values[0]);
            setProgressBarVisibility(true);
            setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Void... params) {
            Log.i("path", path);
            String Url = "http://192.168.43.66/" + path;
            try {
                URLConnection connection = new URL(Url).openConnection();
                connection.setConnectTimeout(1000 * 30);
                connection.setReadTimeout(1000 * 30);
                bitmap = (BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null));
                Log.i("pavcv", "vdv");
                //  Bitmap c=x.createScaledBitmap(x, 160, 160, true);
                //bitmapstring= BitmaptoBinary(c);
                //return bitmapstring;
                //  BitmaptoBinary();
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String bitmapString) {

//            super.onPostExecute(bitmapString);
//            Intent i=new Intent(getActivity(),imagetest.class);
//         //   i.putExtra("BitmapString",bitmapstring);
//            startActivity(i);
////            if(bitmap !=null)
////            {
////                x.setImageBitmap(bitmap);
////            }

        }
    }

    String BitmaptoBinary(Bitmap bitmap)
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}

