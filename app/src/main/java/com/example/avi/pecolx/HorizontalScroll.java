package com.example.avi.pecolx;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class HorizontalScroll extends Fragment {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;


    public void function()
    {
        TextView t= (TextView)getActivity().findViewById(R.id.book_name_fragment);
        t.setText("Hello");
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_scroll, container, false);
        final TextView B_name = (TextView) view.findViewById(R.id.book_name_fragment);
        TextView bprice_text =(TextView)view.findViewById(R.id.bprice);
        TextView bcategory_text =(TextView)view.findViewById(R.id.bcategory);


        view.setClickable(true);
        ImageButton im = (ImageButton) view.findViewById(R.id.space);

//        RequestPackage requestPackage = new RequestPackage();
//        requestPackage.setMethod("GET");
//        //    requestPackage.setParam("uid", uid);
//        //  Log.d("post", uid);
//        requestPackage.setUri("http://192.168.43.66/pecolx/trending.php");
//        SignupTask signupTask = new SignupTask();
//        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
//
//
//          //  Log.i("json",json_string);
//            try {
//
//                jsonObject = new JSONObject(json_string.substring(json_string.indexOf("{"), json_string.lastIndexOf("}")+1));
//                jsonArray = jsonObject.getJSONArray("server_response");
//                int count = 0;
//                String bname,bprice,bcategory;
//                while (count < jsonArray.length()) {
//                    JSONObject jo = jsonArray.getJSONObject(count);
//                    bname = jo.getString("book_name");
//                    bprice = jo.getString("book_price");
//                    bcategory = jo.getString("book_category");
//                    B_name.setText(bname);
//                    bprice_text.setText(bprice);
//                    bcategory_text.setText(bcategory);
//
//                    //book_details info = new book_details(name, password, contact);
//               //     studentInfoAdapter.add(info);
//                    count++;
//                }
//            }
//            catch (JSONException e) {
//                e.printStackTrace();
//            }


            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  B_name.setText("Book Name " + getTag());

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
            i.putExtra("books_data",json_string);
            startActivity(i);

        }
    }

}