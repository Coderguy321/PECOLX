package com.example.avi.pecolx;

import android.graphics.Bitmap;

/**
 * Created by avi on 19-Apr-16.
 */
public class book_details {

    private String bname, price, contact;
    Bitmap bitmap;

    public book_details(String bname, String price, String contact, Bitmap bitmap)
    {
        this.bname = bname;
        this.price = price;

        this.contact = contact;
        this.bitmap =bitmap;
    }

    public String getPassword() {
        return price;
    }

    public void setPassword(String price) {
        this.price = price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return bname;
    }

    public void setName(String name) {
        this.bname = name;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap=bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}