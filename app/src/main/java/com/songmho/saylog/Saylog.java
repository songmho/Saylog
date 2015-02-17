package com.songmho.saylog;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by songmho on 2015-02-17.
 */
public class Saylog extends Application{
    private static final String APPLICATION_ID="w15Flam52mNf5HXMprPhKrF2N0W7fMxx6te0XFFX";
    private static final String CLIENT_KEY="ACLv16WoQQNv2kLfrwbSX2R8ZUKO3fKDiau3wtuq";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
        ParseACL defaultACL=new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}
