package com.turktech.dubizzletask.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.turktech.dubizzletask.R;
import com.turktech.dubizzletask.adapter.MyGalleryAdapter;
import com.turktech.dubizzletask.common.MyProgressDialog;
import com.turktech.dubizzletask.common.Utils;
import com.turktech.dubizzletask.dao.MyDatabaseHelper;
import com.turktech.dubizzletask.interfaces.DataFilteredCallBack;
import com.turktech.dubizzletask.interfaces.FlickerImagesCallback;
import com.turktech.dubizzletask.model.FlickrImage;
import com.turktech.dubizzletask.network.AppNetworkCalls;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 13/04/2017.
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
        , View.OnClickListener
        ,SearchView.OnQueryTextListener
,MenuItemCompat.OnActionExpandListener{



    private ArrayList<FlickrImage>dataList;

    private RecyclerView dataListview;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MyGalleryAdapter myGalleryAdapter;
    private GridLayoutManager mGridLayoutManager;

    private MenuItem hsitoryMenuItem;
    private MenuItem searchMenuItem;
    private SearchView searchView;

    private DataFilteredCallBack dataFilteredCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList=new ArrayList<>();
        dataListview=(RecyclerView)findViewById(R.id.dataListview);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        mGridLayoutManager= new GridLayoutManager(this, 3);
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


        dataFilteredCallBack=new DataFilteredCallBack() {
            @Override
            public void onDataFiltered(String quer, ArrayList<FlickrImage> filteredList) {

                MyDatabaseHelper.getInstance().getMyDatabseHandler().addQueryResults(quer,filteredList);

                if(!hsitoryMenuItem.isVisible())
                hsitoryMenuItem.setVisible(true);


            }
        };


        myGalleryAdapter=new MyGalleryAdapter(dataFilteredCallBack,this,dataList);

        dataListview.setLayoutManager(mGridLayoutManager);
        dataListview.setAdapter(myGalleryAdapter);



        getFlickrImagesDefaultFeed();



    }


    public void getFlickrImagesDefaultFeed(){

        MyProgressDialog.show(this,"Please wait..");
        AppNetworkCalls.getFlickrImages(new FlickerImagesCallback() {
            @Override
            public void onResponse(ArrayList<FlickrImage>flickrImages) {

                dataList.clear();
                dataList.addAll(flickrImages);
                myGalleryAdapter.setmDatalist(dataList);
                myGalleryAdapter.notifyDataSetChanged();
                MyProgressDialog.dismiss();
            }

            @Override
            public void onError(Exception e) {
                MyProgressDialog.dismiss();
                Utils.showError(MainActivity.this,e);

            }
        });


    }



    public void refreshDafulatFeeds(){

        AppNetworkCalls.getFlickrImages(new FlickerImagesCallback() {
            @Override
            public void onResponse(ArrayList<FlickrImage>flickrImages) {

                dataList.clear();
                dataList.addAll(flickrImages);
                myGalleryAdapter.setmDatalist(dataList);
                myGalleryAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception e) {
                swipeRefreshLayout.setRefreshing(false);
                Utils.showError(MainActivity.this,e);

            }
        });






    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.history){

            Intent i=new Intent(MainActivity.this,SearchHistoryActivity.class);
            startActivity(i);
            searchMenuItem.collapseActionView();
        }else{

            
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        hsitoryMenuItem  = menu.findItem(R.id.history);

        if(!MyDatabaseHelper.getInstance().getMyDatabseHandler().haveSerachQueries()){

            hsitoryMenuItem.setVisible(false);

        }


        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

         searchMenuItem  = menu.findItem(R.id.search);
         searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);


        MenuItemCompat.setOnActionExpandListener(searchMenuItem,this);

        return true;
    }


    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        /*Toast.makeText(MainActivity.this,"open",Toast.LENGTH_SHORT).show();*/
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {

        /*Toast.makeText(MainActivity.this,"close",Toast.LENGTH_SHORT).show();*/
        return true;
    }



    @Override
    public void onRefresh() {
        refreshDafulatFeeds();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){


        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        myGalleryAdapter.getFilter().filter(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


}
