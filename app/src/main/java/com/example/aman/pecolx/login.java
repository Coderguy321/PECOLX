package com.example.aman.pecolx;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sign_up(View v) {
        if (v.getId() == R.id.button2) {
            Intent i = new Intent(this, signactivity.class);
            startActivity(i);
        }
    }
    public void log_in(View v)
    {
        if (v.getId() == R.id.button)
        {
            Intent i = new Intent(this, buysellactivity.class);
            startActivity(i);
        }
    }
}
