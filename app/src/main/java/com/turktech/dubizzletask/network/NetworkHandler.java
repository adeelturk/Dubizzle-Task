package com.turktech.dubizzletask.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.turktech.dubizzletask.MainApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adeel Turk on 13/04/2017.
 */
public class NetworkHandler
{

    private static NetworkHandler instance = new NetworkHandler();

    public static NetworkHandler getInstance()
    {
        return instance;
    }

    // Public Methods
    public void get(String path, NetworkCallBack callback)
    {
        sendRequestJson(path, null, callback);
    }

    public void get(String path, NetworkStringCallback callback)
    {
        sendRequest(Request.Method.GET,path, null, callback);
    }

    public void post(String path, HashMap<String, String> parameters, NetworkCallBack callback)
    {
        JSONObject tempJsonObject = new JSONObject(parameters);
        sendRequestJson(path, tempJsonObject, callback);
    }


    private void sendRequest(final int method, String path, final HashMap<String, Object> parameters, final NetworkStringCallback callback)
    {

        String params = "";
        if (method == Request.Method.GET && parameters != null)
        {
            path += "&" + getUrlString(parameters);
        }
        else if (parameters != null)
        {
            params = new JSONObject(parameters).toString();
        }

        final String finalParams = params;
        final String finalPath = path;
        StringRequest request = new StringRequest(method, path, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.i("Stat", "Response" + response);
                callback.onServiceCallback(response, null);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                /*parseError(method, finalPath, parameters, error, callback);*/

                System.out.print("");
            }
        })
        {


            @Override
            public byte[] getBody() throws AuthFailureError
            {
                return finalParams.getBytes();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response)
            {
                if (response == null || response.statusCode != 200) // If there is no response or server is sending error.
                {
                    return Response.error(new VolleyError(response));
                }
                else
                {
                    return super.parseNetworkResponse(response);
                }
            }
        };
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        MainApplication.getInstance().getRequestQueue().add(request);
    }

    private void sendRequestJson(final String path, final JSONObject parameters, final NetworkCallBack callback)
    {
        JsonObjectRequest request = new JsonObjectRequest(path, parameters, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {
                Log.i("Stat", response.toString());
                callback.onServiceCallback(response, null);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                parseError(path, parameters, error, callback);
            }
        })
        {
            @Override
            public String getBodyContentType()
            {
                return "application/json; charset=UTF-8";
            }

          /*  @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", String.format("Bearer %s", SharedPrefHandler.getString(MyConstants.PrefAccessToken, null)));
                return params;
            }*/
        };

        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        MainApplication.getInstance().getRequestQueue().add(request);

    }

    private void parseError(final String path, final JSONObject parameters, VolleyError error, final NetworkCallBack callBack)
    {
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null)
        {
            String str = new String(response.data);
            Log.e("Stat", str);
            if (error instanceof ServerError || error instanceof AuthFailureError)
            {
                try
                {
                    JSONObject jsonStr = new JSONObject(str);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        callBack.onServiceCallback(null, error);
    }

    private String getUrlString(HashMap<String, Object> parameters)
    {
        String urlParams = "";
        if (parameters != null)
        {
            for (String key : parameters.keySet())
            {
                Object obj = parameters.get(key);
                if (urlParams.equals(""))
                {
                    urlParams = key + "=" + obj;
                }
                else
                {
                    urlParams += "&" + key + "=" + obj;
                }
            }
        }
        return urlParams;
    }



}
