package com.example.avi.pecolx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

public class imagetest extends AppCompatActivity {


    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagetest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        imageView=(ImageView)findViewById(R.id.imageView3);
    }

    public void put_img(View v)
    {
         String bitmapString= getIntent().getExtras().getString("BitmapString");
        Bitmap bm=stringtobitmap(bitmapString);
        imageView.setImageBitmap(bm);
    }
    public Bitmap stringtobitmap(String encodedString)
    {
        try{
            byte[]encodebyte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodebyte,0, encodebyte.length);
            return bitmap;
        }
        catch(Exception e)
        {
            e.getMessage();
            return null;
        }
    }


}
