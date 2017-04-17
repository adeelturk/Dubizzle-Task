package com.turktech.dubizzletask.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turktech.dubizzletask.R;
import com.turktech.dubizzletask.adapter.MyGalleryAdapter;
import com.turktech.dubizzletask.adapter.MySearchResultsGalleryAdapter;
import com.turktech.dubizzletask.dao.MyDatabaseHelper;
import com.turktech.dubizzletask.model.FlickrImage;
import com.turktech.dubizzletask.model.SearchQuery;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 17/04/2017.
 */

public class SearchResultsFragment extends Fragment {


    private RecyclerView dataListview;
    private ArrayList<FlickrImage> dataList;

    private MySearchResultsGalleryAdapter adapter;
    private GridLayoutManager mGridLayoutManager;


    private SearchQuery query;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_results_fragment,null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        dataList=new ArrayList<>();
        dataListview=(RecyclerView)view.findViewById(R.id.dataListview);

        mGridLayoutManager= new GridLayoutManager(getActivity(), 3);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 2) {
                    return 3;
                }
                switch (position % 4) {
                    case 1:
                    case 3:
                        return 1;
                    case 0:
                    case 2:
                        return 2;
                    default:
                        //never gonna happen
                        return  -1 ;
                }
            }
        });

        query=(SearchQuery)getArguments().getSerializable("queryobj");

        dataList= MyDatabaseHelper.getInstance().getMyDatabseHandler().getAllSearchResults(query.id);

        adapter=new MySearchResultsGalleryAdapter(((AppCompatActivity)getActivity()),dataList);

        dataListview.setLayoutManager(mGridLayoutManager);
        dataListview.setAdapter(adapter);


    }




}
