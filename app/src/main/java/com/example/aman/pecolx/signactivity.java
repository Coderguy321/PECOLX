package com.example.aman.pecolx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class signactivity extends AppCompatActivity {

    EditText fname,lname,e_id,p_word,cp_word,m_no;
    String sfname,slname,se_id,sp_word,scp_word,sm_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signactivity);
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

fname=(EditText)findViewById(R.id.editText3);
        lname=(EditText)findViewById(R.id.editText4);
        e_id=(EditText)findViewById(R.id.editText5);
        p_word=(EditText)findViewById(R.id.editText6);
        cp_word=(EditText)findViewById(R.id.editText8);
        m_no=(EditText)findViewById(R.id.editText7);

        sfname=fname.getText().toString();
        slname=lname.getText().toString();
        se_id=e_id.getText().toString();
        sp_word=p_word.getText().toString();
        scp_word=cp_word.getText().toString();
        sm_no=m_no.getText().toString();

        String method="register";
        background_login bg=new background_login(this);
        bg.execute(method,sfname,slname,se_id,sp_word,sm_no);
          finish();


    }
/*public void click(View v)
{
    Intent i=new Intent(this,cameratest.class);
    startActivity(i);
}*/
}
