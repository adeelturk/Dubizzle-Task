package com.turktech.dubizzletask.common;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by Adeel Turk on 13/04/2017.
 */

public class MyProgressDialog
{

    private static ProgressDialog progressDialog;

    public static void show(Activity activity, String message)
    {

        try {
            if (activity == null || activity.isFinishing()) {
                return;
            }
            dismiss();
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public static void dismiss()
    {

        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}