package com.example.avi.pecolx;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_up extends AppCompatActivity {

    boolean f1=false,f2=false,f3=false,f4=false,f5=true;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword,inputlayoutcPassword,inputlayoutcaptcha,inputlayoutcontact;
    private EditText inputName, inputEmail, inputPassword,inputcPassword,inputcontact,inputcaptcha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputlayoutcPassword = (TextInputLayout) findViewById(R.id.input_layout_cpassword);
        inputlayoutcontact=(TextInputLayout)findViewById(R.id.input_layout_contact);
      //  inputlayoutcaptcha=(TextInputLayout)findViewById(R.id.input_layout_captcha);


        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputcPassword = (EditText) findViewById(R.id.input_cpassword);
        inputcontact=(EditText)findViewById((R.id.contactText));
        inputcaptcha=(EditText)findViewById(R.id.contactText);




        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputcPassword.addTextChangedListener(new MyTextWatcher(inputcPassword));
        inputcontact.addTextChangedListener(new MyTextWatcher(inputcontact));
        inputcaptcha.addTextChangedListener(new MyTextWatcher(inputcaptcha));





    }



    public void submitForm(View view) {


        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");
        final   String  n = inputName.getText().toString();
        String e=inputEmail.getText().toString();
        String p=inputPassword.getText().toString();
        String cp=inputcPassword.getText().toString();
        String contct=inputcontact.getText().toString();
        String cptcha=inputcaptcha.getText().toString();


//     Log.d("arg", n);
        ///should be same in php
        requestPackage.setParam("name",n);
        requestPackage.setParam("email",e);
        requestPackage.setParam("contact", contct);
        requestPackage.setParam("password", p);


        // requestPackage.setUri("http://192.168.43.239/pecolx/login.php");
        requestPackage.setUri("http://192.168.43.66/pecolx/reg.php");
        SignupTask signupTask = new SignupTask();
        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,requestPackage);


        if(f1==true&&f2==true&&f3==true&&f4==true&&f5==true) {
            Toast.makeText(getApplicationContext(), "registration successful", Toast.LENGTH_SHORT).show();
            finish();
        }








        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        if(!validate_c_password())
        {
            return ;
        }
//        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }



    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            f1=false;
            return false;
        } else {
                f1=true;
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            f2=false;
            return false;
        } else {
            f2=true;
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            f3=false;
            return false;
        }
        else if(IsValidPassword(inputPassword.getText().toString().trim()))
        {
            Log.i(" password not valid", inputPassword.getText().toString());
            inputLayoutPassword.setError(getString(R.string.err_msg_vpassword));
            requestFocus(inputPassword);
            f3=false;
            return false;
        }

        else {
            f3=true;
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    boolean validate_c_password()
    {
        if (inputcPassword.getText().toString().trim().isEmpty())
        {
            inputlayoutcPassword.setError(getString(R.string.err_msg_cepassword));
            requestFocus(inputcPassword);
            f4=false;
            return false;
        }
        else if(!inputPassword.getText().toString().equals(inputcPassword.getText().toString()))
        {

            inputlayoutcPassword.setError(getString(R.string.err_msg_cpassword));
            requestFocus(inputcPassword);
            f4=false;
            return false;
        }

        else {
            f4=true;
            inputlayoutcPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

//    static boolean IsLetter(char c)
//    {
//        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
//    }
//
//    static boolean IsDigit(char c)
//    {
//        return c >= '0' && c <= '9';
//    }
//
//    static boolean IsSymbol(char c)
//    {
//        return c > 32 && c < 127 && !IsDigit(c) && !IsLetter(c);
//    }

     boolean IsValidPassword(String password)
    {

//        Password Rule:
//
//        One capital letter
//        One number   regenx
//        One symbol (@,$,%,&,#,) whatever normal symbols that are acceptable.
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        //   Pattern p = Pattern.compile("[\\p{Alpha}]*[\\p{Punct}][\\p{Alpha}]*");
        Matcher m = p.matcher(password);
        boolean b = m.matches();

        if (b == true) {
          //  f5=false;
            return false;

        }
        else {
            //f5=true;
            return true;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_cpassword:
                    validate_c_password();
            }
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


    ////////////////////////////
    public void registration(View view )
    {

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

         //   Toast.makeText(Sign_up.this,"registration successful", Toast.LENGTH_SHORT).show();
        }
    }

}
