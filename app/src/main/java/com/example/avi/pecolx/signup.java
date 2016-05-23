package com.example.avi.pecolx;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends Activity {
    EditText NAME,pass,cpass,contact,captcha;
    Button Bsubmit;
    Context obj;

    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword,inputlayoutcPassword;


    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
       // System.out.println("fudu1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up_new);
        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        //getting refernece
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputlayoutcPassword = (TextInputLayout) findViewById(R.id.input_layout_cpassword);
        





        //////////

        NAME=(EditText)findViewById(R.id.nameText);
        pass=(EditText)findViewById(R.id.pass1);
        cpass=(EditText)findViewById(R.id.pass);
        contact=(EditText)findViewById(R.id.contactText);
        captcha=(EditText)findViewById(R.id.captchatext);
        Bsubmit=(Button)findViewById(R.id.signupbutton);

//        NAME = (EditText) findViewById(R.id.nameText);
//        inputEmail = (EditText) findViewById(R.id.input_email);
//        inputPassword = (EditText) findViewById(R.id.input_password);
//        inputcPassword = (EditText) findViewById(R.id.input_cpassword);
//        btnSignUp = (Button) findViewById(R.id.btn_signup);

         NAME.getText().toString();


        }


//    String name;
//    public void registration(View view )
//    {
//         name = S_NAME.getText().toString();
//        background backgroundtask=new background(this);
//        backgroundtask.execute("register",name);
//    }

//    private boolean doublebacktoexitpressedonce=false;
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        doublebacktoexitpressedonce=false;
//    }
//
//    @Override
//public void onBackPressed() {
//        if(doublebacktoexitpressedonce)
//        {
//            super.onBackPressed();
//        }
//    Toast.makeText(this, "press back agai nto exit", Toast.LENGTH_SHORT).show();
//
//}

    public void registration(View view )
    {
     RequestPackage requestPackage = new RequestPackage();
     requestPackage.setMethod("GET");
      final   String  n = NAME.getText().toString();
        String p=pass.getText().toString();
        String cp=cpass.getText().toString();
        String contct=contact.getText().toString();
        String cptcha=captcha.getText().toString();

//     Log.d("arg", n);
        ///should be same in php
        requestPackage.setParam("name",n);
        requestPackage.setParam("password", p);
        requestPackage.setParam("contact", contct);

    // requestPackage.setUri("http://192.168.43.239/pecolx/login.php");
       requestPackage.setUri("http://192.168.43.66/pecolx/reg.php");
     SignupTask signupTask = new SignupTask();
     signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,requestPackage);

      //  Toast.makeText(obj,"registration successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    private class SignupTask extends AsyncTask<RequestPackage,Void,Void>{
        @Override
        protected Void doInBackground(RequestPackage... params) {
           String content = HttpManager.getData(params[0]);

            try {
                Log.d("con", content);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            Toast.makeText(signup.this,"registration successful", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }



}
