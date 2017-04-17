package com.turktech.dubizzletask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Adeel Turk on 14/04/2017.
 */

public class FlickrImage implements Serializable {


    public  long fk_id;
    public @SerializedName("id") long id;
    public @SerializedName("height_s") int height_s;
    public @SerializedName("width_s") int width_s;
    public @SerializedName("title") String  title;
    public @SerializedName("url_s") String  url;
    public @SerializedName("url_m") String  urlMedium;
    public @SerializedName("url_l") String  urlLarge;


}
