package com.turktech.dubizzletask;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Adeel Turk on 13/04/2017.
 */
public class MainApplication extends Application {

    private static MainApplication sInstance;

    private RequestQueue mRequestQueue;
    public static Context context;



    @Override
    public void onCreate() {
        super.onCreate();
      //  FlurryAgent.onStartSession(this, MyConstants.FLURRY_API_KEY);
        MainApplication.context = getApplicationContext();

        mRequestQueue = Volley.newRequestQueue(context);

        sInstance = this;



        // init Flurry


    }


    public synchronized static MainApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
    public static Context getAppContext() {
        return MainApplication.context;
    }

    public static boolean isDebug(){
        return true;//BuildConfig.ENVIRONMENT != ENV.PRODUCTION;
    }

}