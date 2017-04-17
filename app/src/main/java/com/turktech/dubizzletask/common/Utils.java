package com.turktech.dubizzletask.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;

import com.turktech.dubizzletask.MainApplication;
import com.turktech.dubizzletask.enums.ScreenSizeCategory;

import java.util.Calendar;


public class Utils
{


    public static boolean isEmailValid(CharSequence target)
    {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    static Context uContext = MainApplication.getAppContext();


    public static DisplayMetrics getDisplayMetrices(AppCompatActivity appCompatActivity){


        DisplayMetrics displayMetrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        /*int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;*/
        return displayMetrics;
    }


    public static int getScreenSize(){

        if ((MainApplication.getAppContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE) {

            return ScreenSizeCategory.EXTRA_LARGE_SCREEN.val;
        }else if ((MainApplication.getAppContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE) {

            return ScreenSizeCategory.LARGE_SCREEN.val;
        }
        else if ((MainApplication.getAppContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            return ScreenSizeCategory.NORMAL_SCREEN.val;
        }
        else if ((MainApplication.getAppContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            return ScreenSizeCategory.SMALL_SCREEN.val;
        }
        else {
            return ScreenSizeCategory.OTHER_SCREEN.val;
        }
    }


    public static void showError(Activity activity, Exception exception)
    {
        String message = exception.getMessage();
        if (message == null || message.isEmpty())
        {
            message = "Some error occurred while performing your request. Please try again.";
        }
        showAlert(activity, message, "Error");
    }

    public static void showAlert(Context context, String message, String title)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        if (title != null)
        {
            alertDialog.setTitle(title);
        }
        alertDialog.setMessage(message).setPositiveButton(android.R.string.yes, null).show();
    }
    private static Gson gson;

    public static Gson getGson()
    {
        if (gson == null)
        {
            gson = new Gson();
        }
        return gson;
    }
}

