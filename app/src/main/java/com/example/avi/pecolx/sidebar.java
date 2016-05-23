//package com.example.avi.pecolx;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//public class sidebar extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        try {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_sidebar);
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//
//            String[] items = new String[]{"Categories", "CSE", "ECE", "Mech", "Civil", "Metallurgy", "Electrical", "Aero"};
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//            Spinner dropdown = (Spinner) findViewById(R.id.spinner);
//            dropdown.setAdapter(adapter);
//
//
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//            drawer.setDrawerListener(toggle);
//            toggle.syncState();
//
//            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//            navigationView.setNavigationItemSelectedListener(this);
//            Intent intent = getIntent();
//            try {
//                SessionManager   session_object = (SessionManager) intent.getSerializableExtra("KEY");
//                session_object.tetsing="abc";
//            } catch (Exception es) {
//                es.printStackTrace();
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//       // editor=getIntent().getExtras().get("editor");
//    }
////////////////////////////
//private boolean doubleBackToExitPressedOnce;
//    private Handler mHandler = new Handler();
//
//    private final Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            doubleBackToExitPressedOnce = false;
//        }
//    };
//
//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//
//        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
//    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        else if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        mHandler.postDelayed(mRunnable, 2000);
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.sidebar, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_logout) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    public void postad123(View view)
//    {
//        Intent i=new Intent(this,post_ad.class);
//        startActivity(i);
//
//
//    }
//
//
//    /**
//     * Clear session details
//     * */
//    public void logoutUser(View view){
//        // Clearing all data from Shared Preferences
//        try {
////            session_object.editor.clear();
////            session_object.editor.commit();
//
//            // After logout redirect user to Loing Activity
//            Intent i = new Intent(this, login.class);
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            startActivity(i);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
//}


package com.example.avi.pecolx;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class sidebar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String PhoneNumber;
    String uid;
    String trending_books_data;
    public  SessionManager session;
    String my_profile_jason;
    public String getjasonString()
    {
        return trending_books_data;
    }
    public String getMyProfiledata(){
        return my_profile_jason;
    }

    public String getuid(){
        return uid;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {



            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sidebar);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            session=new SessionManager(getApplicationContext());
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            //Intent intent = getIntent();I
           uid=getIntent().getExtras().getString("uid");
            trending_books_data=getIntent().getExtras().getString("trending_books_data");


            FragmentManager fm= getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.FrameLayout, new Begin_main_fragment(), "Main_window");
            fragmentTransaction.commit();






//            try {
//                SessionManager   session_object = (SessionManager) intent.getSerializableExtra("KEY");
//                session_object.tetsing="abc";
//            } catch (Exception es) {
//                es.printStackTrace();
//            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout, new Begin_main_fragment(), "Main_window");
            fragmentTransaction.commit();


        }

//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        //   mHandler.postDelayed(mRunnable, 2000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {


            session.logoutUser();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_profile) {

            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setMethod("GET");
            requestPackage.setParam("uid", uid);
            Log.d("post", uid);
            requestPackage.setUri("http://192.168.43.66/pecolx/my_profile.php");
            SignupTask signupTask = new SignupTask("my_profile");
            signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
            FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout, new Begin_main_fragment(), "Main_window");
            fragmentTransaction.addToBackStack("MainWindow");
            fragmentTransaction.replace(R.id.FrameLayout, new MyProfileFragment(), "MyProfile");
            fragmentTransaction.commit();

        }
        else if (id == R.id.my_posts)
        {
try {
   // String uid = getIntent().getExtras().getString("uid");
    RequestPackage requestPackage = new RequestPackage();
    requestPackage.setMethod("GET");
    requestPackage.setParam("ui" +
            "d", uid);
    Log.d("post",uid);
    requestPackage.setUri("http://192.168.43.66/pecolx/my_ads.php");
    SignupTask signupTask = new SignupTask("myads");
    signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);


}
catch(NullPointerException e)
{
    e.printStackTrace();
}


        }
        else if (id == R.id.sell_books) {

            FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout, new Begin_main_fragment(), "Main_window");
            fragmentTransaction.addToBackStack("MainWindow");
            fragmentTransaction.replace(R.id.FrameLayout,new PostAddFragment(),"Post");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_log_out)
        {
            session.logoutUser();
            finish();

        } else if (id == R.id.nav_share) {

        } //else if (id == R.id.nav_send) {

        //}
        else if(id==R.id.aboutpecolx)
        {
            FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout, new Begin_main_fragment(), "Main_window");
            fragmentTransaction.addToBackStack("MainWindow");
            fragmentTransaction.replace(R.id.FrameLayout,new about_us_fragment(),"Post");
            fragmentTransaction.commit();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void postad123(View view)
//    {
//        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.FrameLayout,new PostAddFragment(),"PostAd");
//        fragmentTransaction.commit();
//
//    }


    /**
     * Clear session details
     * */
    public void logoutUser(View view){
        // Clearing all data from Shared Preferences
        try {
//            session_object.editor.clear();
//            session_object.editor.commit();

            // After logout redirect user to Loing Activity
            Intent i = new Intent(this, login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            startActivity(i);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public class SignupTask extends AsyncTask<RequestPackage,Integer, Void> {

        Integer x;
        String content,json_string;
        String check_butoon;
        SignupTask()
        {}

        SignupTask(String position)
        {

            check_butoon=position;
        }

        @Override
        protected Void doInBackground(RequestPackage... params) {

            //System.out.println("thread");
            content = HttpManager.getData(params[0]);
            //System.out.println(content);
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();
            Log.d("con", content);
            json_string = content;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(check_butoon.equals("myads")) {
                Intent i = new Intent(getApplicationContext(), display_listview.class);
                i.putExtra("books_data", json_string);
                startActivity(i);
            }
            else if(check_butoon.equals("my_profile"))
            {
               my_profile_jason=content;
            }

        }
    }




}
