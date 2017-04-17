package com.turktech.dubizzletask.network;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adeel Turk on 13/04/2017.
 */
public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "b12fa7250a55807d0e00eb10a727bf96";
/*
    public byte[] getUrlBytes(String urlAddress) throws IOException{
        URL url = new URL(urlAddress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            InputStream in = connection.getInputStream();
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() + ":with" + urlAddress);
            }

            int bytesRead = 0;
            byte buffer[] = new byte[1024];

            while((bytesRead = in.read(buffer))>0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally{
            connection.disconnect();

        }

    }

    public String getURLString(String urlAddress) throws IOException{
        return new String(getUrlBytes(urlAddress));
    }

    public List<GalleryItem> fetchItems(){
        List<GalleryItem> items = new ArrayList<GalleryItem>();
        try {
            //String url = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=" + API_KEY + "&format="json"";
            String url = Uri.parse("https://api.flickr.com/services/rest/").buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s").build().toString();
            String jsonString = getURLString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        }catch(JSONException jex){
            Log.e(TAG,"Failed to fetch items", jex);
        }catch(IOException ioe){
            Log.e(TAG,"Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody) throws JSONException, IOException{

        JSONObject picturesJsonObject = jsonBody.getJSONObject("photos");
        JSONArray pictureJsonArray = picturesJsonObject.getJSONArray("photo");

        for(int i=0; i<pictureJsonArray.length(); i++){
            JSONObject pictureJsonObject = pictureJsonArray.getJSONObject(i);
            GalleryItem galleryItem = new GalleryItem();
            galleryItem.setCaption(pictureJsonObject.getString("title"));
            galleryItem.setId(pictureJsonObject.getString("id"));
            if(!pictureJsonObject.has("url_s")){
                continue;
            }
            galleryItem.setUrl(pictureJsonObject.getString("url_s"));
            items.add(galleryItem);
        }


    }*/
}
