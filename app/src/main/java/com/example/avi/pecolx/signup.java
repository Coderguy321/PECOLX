package com.example.avi.pecolx;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends Activity {
    EditText S_NAME;
    Button Bsubmit;

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
       // System.out.println("fudu1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        S_NAME=(EditText)findViewById(R.id.editText);
        Bsubmit=(Button)findViewById(R.id.button);

//        Bsubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=""+S_NAME.getText().toString();
//                //List<NameValuePair>  nameValuePairs=new ArrayList<NameValuePair>(1);
//                List<String> X=new ArrayList<String>(1);
//                X.add(name);
//
//                HttpClient httpClient=new DefaultHttpClient;
//            }
//        })
//System.out.println("fudu");
        }





    String name;
    public void registration(View view ) {
     RequestPackage requestPackage = new RequestPackage();
     requestPackage.setMethod("GET");
     String arg = S_NAME.getText().toString();
     Log.d("arg",arg);
     requestPackage.setParam("name", arg);

     requestPackage.setUri("http://192.168.0.100/pecolx/reg.php");

     SignupTask signupTask = new SignupTask();
     signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,requestPackage);

    }

    private class SignupTask extends AsyncTask<RequestPackage,Void,Void>{

        @Override
        protected Void doInBackground(RequestPackage... params) {
           String content = HttpManager.getData(params[0]);
            Log.d("con",content);
            return null;
        }
    }


}
