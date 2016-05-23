package com.example.avi.pecolx;


import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dilraj on 4/20/2016.
 */
@TargetApi(17)
public class Begin_main_fragment extends android.app.Fragment {
    book_info_adapter bookInfoAdapter;
    ListView listView;

    JSONObject jsonObject;
    Bitmap bitmap;
    JSONArray jsonArray;
    String json_string;
    String jason_list_trending;
    private Button post_ad;
    SearchView searchView;
    String[] items;
    ImageView x;
    String bitmapstring;
    String name, password, contact,path;
    String uid_sidebar;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      final  View view= inflater.inflate(R.layout.begin_main, container, false);
        try {
            sidebar temp_obj = (sidebar) getActivity();
            jason_list_trending = temp_obj.getjasonString();
            uid_sidebar= temp_obj.getuid();


            Log.i("string dillu ", jason_list_trending);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);

        listView = (ListView) view.findViewById(R.id.listview_trending);
        bookInfoAdapter = new book_info_adapter(this.getActivity(), R.layout.bookdetails_layout);
        listView.setAdapter(bookInfoAdapter);

        try {
            //    jsonObject = new JSONObject("{json_String}");
            jsonObject = new JSONObject(jason_list_trending.substring(jason_list_trending.indexOf("{"), jason_list_trending.lastIndexOf("}") + 1));
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            while (count <3) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("book_name");
                password = jo.getString("book_price");
                contact = jo.getString("book_category");
                path=jo.getString("path");

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
        } catch (Exception e) {
            e.printStackTrace();
        }







//        RequestPackage requestPackage = new RequestPackage();
//        requestPackage.setMethod("GET");
//        requestPackage.setParam("bORc", "sdfs");
//        requestPackage.setUri("http://192.168.43.66/pecolx/trending.php");
//        trending trending = new trending();
//        trending.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);

        // x=(ImageView)view.findViewById(R.id.imageView2);



        searchView=(SearchView)view.findViewById(R.id.searchview);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                String temp = searchView.getQuery().toString();


                RequestPackage requestPackage = new RequestPackage();
                requestPackage.setMethod("GET");
                requestPackage.setParam("bORc", temp);
                //Log.d("beg",temp);
                requestPackage.setUri("http://192.168.43.66/pecolx/fetch_bookdetails.php");
                SignupTask signupTask = new SignupTask();
                signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);

//                while(json_string==null) {// Toast.makeText(getApplicationContext(), "First get json", Toast.LENGTH_SHORT).show();
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
         items = new String[]{"Categories", "CSE", "ECE", "MECH", "CIVIL", "METTA", "ELECTRICAL", "AERO"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);

//        LinearLayout container_temp=(LinearLayout)view.findViewById(R.id.container_horizontal_scroll);

        Spinner spinner=(Spinner)view.findViewById(R.id.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {// your code here

                    RequestPackage requestPackage = new RequestPackage();
                    requestPackage.setMethod("GET");
                    requestPackage.setParam("bORc", items[position]);


                    requestPackage.setUri("http://192.168.43.66/pecolx/fetch_bookdetails.php");
                    SignupTask signupTask = new SignupTask(position);
                    signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//        for (int i = 0; i < 10; i++) {
//            FragmentManager fm = getChildFragmentManager();
//            FragmentTransaction fragmentTransaction = fm.beginTransaction();
//            fragmentTransaction.add(R.id.container_horizontal_scroll, new HorizontalScroll(), "" + i);
//            fragmentTransaction.commit();
//            HorizontalScroll test=(HorizontalScroll)fm.findFragmentByTag("" + i);
//     //       test.function();
//        }


//            View view1 = inflater.inflate(R.layout.horizontal_scroll, container_temp, true);
//
//             TextView b1 = (TextView) view1.findViewById(R.id.book_name_fragment);
//            b1.set;
//            b1.setText("viwas");
//
//        View view2 = inflater.inflate(R.layout.horizontal_scroll, container_temp, true);
//
//        final TextView b2 = (TextView) view2.findViewById(R.id.book_name_fragment);
//
//        b2.setText("vishwas");
//

//
//        }
         post_ad=(Button)view.findViewById(R.id.button);
        post_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.FrameLayout, new Begin_main_fragment(), "Main_window");
                fragmentTransaction.addToBackStack("MainWindow");
                fragmentTransaction.replace(R.id.FrameLayout, new PostAddFragment(), "PostAd");
                fragmentTransaction.commit();
            }
        });


        return view;

    }


    public class SignupTask extends AsyncTask<RequestPackage,Integer, Void> {

        Integer x;
        String content;
        SignupTask()
        {}

        SignupTask(Integer position)
        {

            x=position;
        }

        @Override
        protected Void doInBackground(RequestPackage... params) {

            //System.out.println("thread");
            content = HttpManager.getData(params[0]);
            //System.out.println(content);
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();
            Log.d("con", content);
            json_string = content;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            Intent i = new Intent(getActivity(),display_listview.class);
            Log.i("jsontring",json_string);
             i.putExtra("books_data",json_string);
            startActivity(i);
        }
    }


    public class DownloadImage extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            String Url="http://192.168.43.66/test.jpg";
            try
            {
                URLConnection connection=new URL(Url).openConnection();
                connection.setConnectTimeout(1000 * 30);
                connection.setReadTimeout(1000 * 30);
                Bitmap x=(BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null));
                Bitmap c=x.createScaledBitmap(x, 160, 160, true);
                bitmapstring= BitmaptoBinary(c);
                return bitmapstring;
              //  BitmaptoBinary();
            }
            catch (Exception e)
            {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String bitmapString) {

            super.onPostExecute(bitmapString);
            Intent i=new Intent(getActivity(),imagetest.class);
           i.putExtra("BitmapString",bitmapstring);
            startActivity(i);
//            if(bitmap !=null)
//            {
//                x.setImageBitmap(bitmap);
//            }

        }
    }

    String BitmaptoBinary(Bitmap bitmap)
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b=baos.toByteArray();
        String temp= Base64.encodeToString(b,Base64.DEFAULT);
        return temp;
    }



    }

