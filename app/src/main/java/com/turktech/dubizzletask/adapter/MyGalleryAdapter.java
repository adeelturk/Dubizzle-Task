package com.turktech.dubizzletask.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TableRow;

import com.turktech.dubizzletask.R;
import com.turktech.dubizzletask.common.Utils;
import com.turktech.dubizzletask.interfaces.DataFilteredCallBack;
import com.turktech.dubizzletask.model.FlickrImage;
import com.turktech.dubizzletask.filters.MyFlickerDataFilter;
import com.turktech.dubizzletask.viewholder.GalleryRowViewHolder;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 14/04/2017.
 */

public class MyGalleryAdapter extends RecyclerView.Adapter implements Filterable{


    private ArrayList<FlickrImage> mDatalist;
    private ArrayList<FlickrImage> filteredList;
    private AppCompatActivity appCompatActivity;
    private DataFilteredCallBack dataFilteredCallBack;

    public MyGalleryAdapter(DataFilteredCallBack dataFilteredCallBack,AppCompatActivity appCompatActivity, ArrayList<FlickrImage> mDatalist) {

        this.mDatalist = mDatalist;
        this.appCompatActivity=appCompatActivity;
        this.dataFilteredCallBack=dataFilteredCallBack;
        filteredList=new ArrayList<>();
        filteredList.addAll(mDatalist);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_layout_row, parent, false);

        return new GalleryRowViewHolder(v);
    }

    public void setFilteredList(ArrayList<FlickrImage> filteredList) {
        this.filteredList = filteredList;
    }

    public void setmDatalist(ArrayList<FlickrImage> mDatalist) {

        this.mDatalist = mDatalist;
        filteredList.addAll(mDatalist);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((GalleryRowViewHolder) holder).invalidate(filteredList.get(position));


        DisplayMetrics displayMetrics= Utils.getDisplayMetrices(appCompatActivity);

         int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, height/3); // (width, height)
        ((GalleryRowViewHolder) holder).container.setLayoutParams(params);


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new MyFlickerDataFilter(this,mDatalist,dataFilteredCallBack);
    }
}
