package com.turktech.dubizzletask.filters;

import android.widget.Filter;

import com.turktech.dubizzletask.adapter.MyGalleryAdapter;
import com.turktech.dubizzletask.interfaces.DataFilteredCallBack;
import com.turktech.dubizzletask.model.FlickrImage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adeel Turk on 14/04/2017.
 */

public class MyFlickerDataFilter extends Filter {


    private final MyGalleryAdapter adapter;

    private final List<FlickrImage> originalList;

    private final List<FlickrImage> filteredList;

    private DataFilteredCallBack dataFilteredCallBack;

    public MyFlickerDataFilter(MyGalleryAdapter adapter,
                                List<FlickrImage> originalList,DataFilteredCallBack dataFilteredCallBack) {
        super();
        this.adapter = adapter;
        this.originalList = new LinkedList<>(originalList);
        this.filteredList = new ArrayList<>();
        this.dataFilteredCallBack = dataFilteredCallBack;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (charSequence.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = charSequence.toString().toLowerCase().trim();

            for (final FlickrImage flickrImage : originalList) {
                if (flickrImage.title.contains(filterPattern)) {
                    filteredList.add(flickrImage);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;


    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.setFilteredList((ArrayList<FlickrImage>)filterResults.values);
        adapter.notifyDataSetChanged();

        if(((ArrayList<FlickrImage>) filterResults.values).size()>0){

            dataFilteredCallBack.onDataFiltered(charSequence.toString(),(ArrayList<FlickrImage>)filterResults.values);
        }
    }
}
