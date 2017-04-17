package com.turktech.dubizzletask.dao;

import com.turktech.dubizzletask.MainApplication;

/**
 * Created by Adeel Turk on 16/04/2017.
 */

public class MyDatabaseHelper  {


    private MyDatabseHandler myDatabseHandler;
    private static MyDatabaseHelper myDatabaseHelper;

    private MyDatabaseHelper(){

        myDatabseHandler=new MyDatabseHandler(MainApplication.getAppContext());
    }



    public static MyDatabaseHelper getInstance(){

        if(myDatabaseHelper==null){

            myDatabaseHelper=new MyDatabaseHelper();
        }

        return myDatabaseHelper;

    }


    public MyDatabseHandler getMyDatabseHandler() {
        return myDatabseHandler;
    }
}
