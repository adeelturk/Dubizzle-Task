package com.turktech.dubizzletask.network;

import org.json.JSONObject;

/**
 * Created by Malik on 7/3/15.
 */
public interface NetworkCallBack {
    public void onServiceCallback(JSONObject response, Exception error);

}
