package com.example.avi.pecolx;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class json_ala_pnga extends AppCompatActivity {
    TextView t;
    Button b;
    String x;
    String json_string;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_ala_pnga);

        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        //getting refernece

        t=(TextView)findViewById(R.id.t2);

         str=getIntent().getStringExtra("message");
      //  t.setText(str);

    }


//    public void fn(View view)
//    {
//
//    }


    private class SignupTask extends AsyncTask<RequestPackage,Void,Void>{

        String content;
        @Override
        protected Void doInBackground(RequestPackage... params)
        {

            //System.out.println("thread");
            content = HttpManager.getData(params[0]);
            //System.out.println(content);
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();
            Log.d("con", content);
            json_string=content;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           t.setText(content);
          //  json_string=content;

        }
    }



    public  void send_data(View view)
    {

        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");

        requestPackage.setParam("bORc",str);

        requestPackage.setUri("http://192.168.43.170/pecolx/fetch_bookdetails.php");
        SignupTask signupTask = new SignupTask();
        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);

        while(json_string==null)
        {// Toast.makeText(getApplicationContext(), "First get json", Toast.LENGTH_SHORT).show();
        }

            Intent i=new Intent(this,display_listview.class);
            i.putExtra("json_data",json_string);
//            i.putExtra("message",)
            startActivity(i);
    }
}
