package com.example.avi.pecolx;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class login extends AppCompatActivity implements Serializable {

    EditText email,pass;
    Button login;
//   public  SessionManager session;
    String json_string_trending;
    String x[];
    String emaill,passs;
    String json_string;
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder)
    {
        super.onPrepareNavigateUpTaskStack(builder);
        // System.out.println("fudu1");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  session = new SessionManager(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);

        email=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");
        requestPackage.setParam("bORc", "sdfs");
        requestPackage.setUri("http://192.168.43.66/pecolx/trending.php");
        trending trending = new trending();
        trending.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
//        try {
//            String x = getIntent().getExtras().getString("LOUT");
//            Log.d("logout message", x);
//            if (x.equals("logout")) {
//                Log.d("andar", x);
//                session.editor.clear();
//                session.editor.commit();
//                HashMap<String, String> user = new HashMap<String, String>();
//                user=session.getUserDetails();
//                Log.i("email", user.get("KEY_EMAIL"));
//                Log.i("pass", user.get("KEY_PASS"));
//                Log.d("bahar", x);
//            }
//        }
//
//            catch(Exception n) {
//                n.printStackTrace();
//            }

       // session.checkLogin();
    }





    public void login(View view )
    {

//        session.editor.clear();
//        session.editor.commit();
        // get user data from session
     //   System.out.println("login");7









        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");
         emaill=email.getText().toString();
        passs=pass.getText().toString();
        //session.createLoginSession(emaill,passs);

//          Integer x= Integer.parseInt(passs);

        requestPackage.setParam("email",emaill);
        requestPackage.setParam("password", passs);

     //   HashMap<String, String> user = session.getUserDetails();


        // email
       // String e = user.get(SessionManager.KEY_EMAIL);
        //String p = user.get(SessionManager.KEY_PASS);
//        try {
//            Log.i("email", e);
//            Log.i("pass", p);
//        }
//        catch (Exception es)
//        {
//            es.printStackTrace();
//        }

        Log.d("arg", emaill);
        // requestPackage.setUri("http://192.168.43.239/pecolx/login.php");
        requestPackage.setUri("http://192.168.43.66/pecolx/login.php");
        SignupTask signupTask = new SignupTask();
        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
    }
    private class SignupTask extends AsyncTask<RequestPackage,Void,Void>{

    String content;

        @Override
        protected Void doInBackground(RequestPackage... params)
        {

            //System.out.println("thread");
         content = HttpManager.getData(params[0]);
             x=content.split(">");

            //System.out.println(content);
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();
            try {
                Log.d("con",x[0]);
                Log.d("con",x[1]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(content.indexOf("welcome")!=-1) {

                //session.createLoginSession(emaill,passs);
                Toast.makeText(login.this, "login successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(login.this,sidebar.class);
                while(json_string_trending==null)
                {}
                i.putExtra("trending_books_data",json_string_trending);
                i.putExtra("uid",x[1]);
                startActivity(i);
               // mainp(email);

            }
            else
                Toast.makeText(login.this, "Invalid details", Toast.LENGTH_SHORT).show();

        }
    }
    public  void signup(View view)
    {

        Intent i=new Intent(this,Sign_up.class);
        startActivity(i);
    }

    public void mainp(View view)
    {
        Intent it=new Intent(this,sidebar.class);
        it.putExtra("uid",x[1]);
        startActivity(it);
    }

    public class trending extends AsyncTask<RequestPackage,Void, Void> {

        Integer x;
        String content;

        @Override
        protected Void doInBackground(RequestPackage... params) {
            //System.out.println("thread");
            content = HttpManager.getData(params[0]);
            //System.out.println(content);
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();
            Log.d("con", content);
            json_string_trending= content;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {



        }
    }

}


