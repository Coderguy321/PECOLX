package com.example.aman.pecolx;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by aman on 11/2/16.
 */
public class background_login extends AsyncTask<String,Void,String>
{
    Context ctx;
background_login(Context ctx)
{
    this.ctx=ctx;
}

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://172.31.21.19/html/register.php";
        //String login_url="http://10.0.2.2/";
        String method=params[0];
        if(method.equals("register"))
        {
        String fname=params[1];
            String lname=params[2];
            String e_id=params[3];
            String p_word=params[4];
            String m_no=params[5];


            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode("fname","UTF-8")+"&"+
                        URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode("lname","UTF-8")+"&"+
                        URLEncoder.encode("e_id","UTF-8")+"="+URLEncoder.encode("e_id","UTF-8")+"&"+
                        URLEncoder.encode("p_word","UTF-8")+"="+URLEncoder.encode("p_word","UTF-8")+"&"+
                        URLEncoder.encode("m_no","UTF-8")+"="+URLEncoder.encode("m_no","UTF-8");
                        bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return "Registration Successful...";



            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();

    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
