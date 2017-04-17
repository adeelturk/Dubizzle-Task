package com.turktech.dubizzletask.interfaces;

import com.turktech.dubizzletask.model.FlickrImage;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 16/04/2017.
 */

public interface DataFilteredCallBack {

    void onDataFiltered(String quer, ArrayList<FlickrImage> filteredList);
}
