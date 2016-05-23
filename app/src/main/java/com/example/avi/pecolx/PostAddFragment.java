package com.example.avi.pecolx;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Dilraj on 4/20/2016.
 */
public class PostAddFragment extends Fragment {

    EditText bname,aname,price;
    Button cap_button,post_button;


    String name, author, captcha;
    EditText editText1, editText2, editText3, editText4;
    static final int request_image = 1;
    ImageView photo;
    Button camera_b;
    int idx;
    View radioButton;
    String selectedtext;
    RadioGroup radioButtonGroup;
    RadioButton temp;
    ////////////
    String encoded_string,image_name;
    Bitmap bitmap,p;
    File file;
    Uri file_uri;
    String uid_fetched_from_sidebar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.post_add_fragment, container, false);
        StrictMode.ThreadPolicy Policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        sidebar temp_obj=(sidebar)getActivity();
        uid_fetched_from_sidebar=temp_obj.getuid();
        bname=(EditText)view.findViewById(R.id.edittext1);
        aname=(EditText)view.findViewById(R.id.edittext2);
        price=(EditText)view.findViewById(R.id.edittext3);
        radioButtonGroup=(RadioGroup)view. findViewById(R.id.layoutG);
        photo = (ImageView) view.findViewById(R.id.imageView);
        post_button=(Button)view.findViewById(R.id.post);
        camera_b = (Button) view.findViewById(R.id.buttonCapture);

        if (!hasCamera()) {
            camera_b.setEnabled(false);
        }
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post_ad(view);
            }
        });
        camera_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera(view);
            }
        });

    return view;
    }


    public void Post_ad(View view)
    {

        if(radioButtonGroup.getCheckedRadioButtonId()==-1)
        {
            Toast.makeText(PostAddFragment.this.getActivity(), "Select Category", Toast.LENGTH_SHORT).show();
       }
        else
        {
            int selectedId=radioButtonGroup.getCheckedRadioButtonId();
            temp = (RadioButton)view.findViewById(selectedId);
            selectedtext=temp.getText().toString();

            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setMethod("POST");
            String bookname=bname.getText().toString();
            String authname=aname.getText().toString();
            String pric=price.getText().toString();

            //category was already fetched
//        String selectcat= temp.getText().toString();

            requestPackage.setParam("book_name",bookname);
            requestPackage.setParam("author",authname);
            requestPackage.setParam("book_price",pric);
            requestPackage.setParam("book_category",selectedtext);
            requestPackage.setParam("uid",uid_fetched_from_sidebar);
            requestPackage.setParam("encoded_string",encoded_string);
            requestPackage.setParam("image_name",image_name);


            Log.d("image_name",image_name);
//
//        Log.d("encoded String", encoded_string);
            // requestPackage.setUri("http://192.168.43.239/pecolx/login.php");
            requestPackage.setUri("http://192.168.43.66/pecolx/Post_ad.php");
            SignupTask signupTask = new SignupTask();
            signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);


            Toast.makeText(PostAddFragment.this.getActivity(), selectedtext, Toast.LENGTH_SHORT).show();
            FragmentManager fm = getFragmentManager();
            fm.popBackStack();



            Toast.makeText(PostAddFragment.this.getActivity()," Successful Post ", Toast.LENGTH_LONG).show();


        }
//
//        RequestPackage requestPackage = new RequestPackage();
//        requestPackage.setMethod("POST");
//        String bookname=bname.getText().toString();
//        String authname=aname.getText().toString();
//        String pric=price.getText().toString();
//
//        //category was already fetched
////        String selectcat= temp.getText().toString();
//
//        requestPackage.setParam("book_name",bookname);
//        requestPackage.setParam("author",authname);
//        requestPackage.setParam("book_price",pric);
//        requestPackage.setParam("book_category",selectedtext);
//        requestPackage.setParam("encoded_string",encoded_string);
//        requestPackage.setParam("image_name",image_name);
//
//        Log.d("image_name",image_name);
////
////        Log.d("encoded String", encoded_string);
//        // requestPackage.setUri("http://192.168.43.239/pecolx/login.php");
//        requestPackage.setUri("http://192.168.43.66/pecolx/Post_ad.php");
//        SignupTask signupTask = new SignupTask();
//        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);

    }

    boolean hasCamera() {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getfileuri();
        //storing file in phone
        intent.putExtra(MediaStore.EXTRA_OUTPUT,file_uri);
//        intent.putExtra(,file_uri);
        PostAddFragment.this.startActivityForResult(intent, request_image);
    }

    @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
    {//                Bundle extras = data.getExtras();
//                p = (Bitmap) extras.get("data");
//                photo.setImageBitmap(p);
        ;
        if (requestCode == request_image && resultCode == getActivity().RESULT_OK) {


                try
                {
                 //   p=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),data.getData());
                   // photo.setImageBitmap(p);

                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Encode_image Enocode_obj= new Encode_image();
            Enocode_obj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }


private class Encode_image extends AsyncTask<Void,Void,Void>
{
    @Override
    protected Void doInBackground(Void ...voids) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;
            bitmap = BitmapFactory.decodeFile(file_uri.getPath());//,options);
            bitmap=bitmap.createScaledBitmap(bitmap, 160, 160, true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

    private class SignupTask extends AsyncTask<RequestPackage,Void,Void>{

    @Override
    protected Void doInBackground(RequestPackage... params)
    {
        //System.out.println("thread");
        String content = HttpManager_post.getData(params[0]);
        try {
            Log.d("con", content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//            Toast.makeText(login.this, "registration successful", Toast.LENGTH_SHORT).show();

        return null;
    }
}

    public  void getfileuri()
    {
        //this should be unique here just for checking
        image_name="books.jpg";
        file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator +image_name);
        file_uri=Uri.fromFile(file);
    }
//    private String pathFromUri(Uri imageUri) {
//        String[] filePathColumn = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(imageUri, filePathColumn,
//                null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        String filePath = cursor.getString(columnIndex);
//        return filePath ;
//    }
}
