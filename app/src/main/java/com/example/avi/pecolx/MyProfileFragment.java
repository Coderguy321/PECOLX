package com.example.avi.pecolx;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Dilraj on 4/20/2016.
 */
public class MyProfileFragment extends Fragment {

    String name, email, contact;
    JSONObject jsonObject;
    JSONArray jsonArray;


    TextView my_profile_name,my_profile_email,my_profile_contact;
    String my_profile_data_jason_string;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View view=inflater.inflate(R.layout.my_profile_fragment,container,false);
        my_profile_name=(TextView)view.findViewById(R.id.my_profile_name);
        my_profile_email=(TextView)view.findViewById(R.id.my_profile_email);
        my_profile_contact=(TextView)view.findViewById(R.id.my_profile_contact);

//        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.FrameLayout,new MyProfileFragment());
//        fragmentTransaction.commit();

        sidebar temp_object=(sidebar)getActivity();
try
{
        my_profile_data_jason_string=temp_object.getMyProfiledata();
        jsonObject = new JSONObject(my_profile_data_jason_string.substring(my_profile_data_jason_string.indexOf("{"), my_profile_data_jason_string.lastIndexOf("}") + 1));
        jsonArray = jsonObject.getJSONArray("server_response");

        JSONObject jo = jsonArray.getJSONObject(0);
        name = jo.getString("name");
        email = jo.getString("email");
        contact = jo.getString("contact");
        my_profile_name.setText(name);
        my_profile_email.setText(email);
        my_profile_contact.setText(contact);




}
catch (Exception e)
{
    e.printStackTrace();

}



        return view;



    }
}
