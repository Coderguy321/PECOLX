package com.example.avi.pecolx;






























































import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class book_info_adapter extends ArrayAdapter
{List list=new ArrayList();
    public book_info_adapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(book_details object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View row;
//        row=convertView;
        Studentholder studentholder;

           if( convertView==null) {
               LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
               convertView=layoutInflater.inflate(R.layout.bookdetails_layout,parent,false);
               studentholder=new Studentholder();
               studentholder.tx_name=((TextView) convertView.findViewById(R.id.Layout_bname));
               studentholder.tx_password = (TextView) convertView.findViewById(R.id.Laoyut_bprice);
               studentholder.tx_contact = (TextView) convertView.findViewById(R.id.layout_contact);

               studentholder.b_image = (ImageView)convertView.findViewById(R.id.Layout_image1);
               convertView.setTag(studentholder);
           }
//            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
//            studentholder=new Studentholder();
//            try {
//
//
//                row.setTag(studentholder);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
        else
        {
            studentholder=(Studentholder)convertView.getTag();
        }
        book_details studentInfo=(book_details)this.getItem(position);
        studentholder.tx_name.setText(studentInfo.getName());
        studentholder.tx_password.setText(studentInfo.getPassword());
        studentholder.tx_contact.setText(studentInfo.getContact());
        studentholder.b_image.setImageBitmap(studentInfo.getBitmap());

        return convertView;
    }
    static class Studentholder
    {
        private TextView tx_name;TextView tx_password;TextView tx_contact;
        ImageView b_image;
   }
}

