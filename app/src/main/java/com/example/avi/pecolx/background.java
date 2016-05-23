package com.example.avi.pecolx;
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
 * Created by avi on 12-Feb-16.
 */
public class background extends AsyncTask<String,String,String> {
    Context obj;
    background(Context ctx)
    {
        obj=ctx;
    }
    String xyz;
    @Override
    protected String doInBackground(String... params) {
        String method=params[0];
        String name=params[1];
        String reg_url="http://192.168.43.239/pecolx/reg.php" ;//folder  inside public directory of wamp server
        if(method.equals("register"))
        {

            System.out.println("abcd");
            //String name =params[1];
            try {


                URL url = new URL(reg_url);
                HttpURLConnection httpURLconnection;
                httpURLconnection = (HttpURLConnection)url.openConnection();
                httpURLconnection.setDoInput(true);
                httpURLconnection.setDoOutput(true);
               // htt
                //System.out.println("oooooooooo");
                httpURLconnection.setRequestMethod("POST");
                httpURLconnection.setRequestProperty("Content-Type", "application/string;UTF-8");
                //System.out.println("tttttttt");
               // httpURLconnection.setDoOutput(true);
                //System.out.println("qqqqqq");
                OutputStream os=httpURLconnection.getOutputStream();///aha line nhin chaldiiii

                //System.out.println("eeeeee");
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("name","UTF-8") +"="+URLEncoder.encode(name,"UTF-8");
                bufferedWriter.write(data);//writing data
                bufferedWriter.flush();
                bufferedWriter.close();
                //System.out.println("pppppppppppppppppppppppp");
                /*to get response fro mthe server*/
                InputStream is=httpURLconnection.getInputStream();
                Integer read = is.read();
                xyz = read.toString();
                System.out.println("fffff"+xyz+"ffff");
                is.close();
            }
            catch(MalformedURLException obj)
            {
               // System.out.println("FUDU FUDU\\n\n");
                obj.getMessage();
            } catch (IOException e) {
                //System.out.println("saala saaalalalalalla\\n\n");
                e.printStackTrace();
            }
        }
        return xyz;
    }

    protected void onPreExecute(String result) {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
      //  super.onPostExecute(s);
        Toast.makeText(obj, result, Toast.LENGTH_SHORT).show();//making it visible by show() method
    }
}
