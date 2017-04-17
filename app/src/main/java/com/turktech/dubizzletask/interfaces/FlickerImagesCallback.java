package com.turktech.dubizzletask.interfaces;

import com.turktech.dubizzletask.model.FlickrImage;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 13/04/2017.
 */

public interface FlickerImagesCallback {

     void onResponse(ArrayList<FlickrImage>flickrImages);
    void onError(Exception e);

}
