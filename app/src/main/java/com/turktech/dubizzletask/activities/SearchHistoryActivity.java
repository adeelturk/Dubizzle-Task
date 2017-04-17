package com.turktech.dubizzletask.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.turktech.dubizzletask.R;
import com.turktech.dubizzletask.dao.MyDatabaseHelper;
import com.turktech.dubizzletask.fragment.SearchResultsFragment;
import com.turktech.dubizzletask.model.SearchQuery;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 17/04/2017.
 */

public class SearchHistoryActivity extends AppCompatActivity {


    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;

    ArrayList<SearchQuery> searchQueryDatalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serach_history);

        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        searchQueryDatalist=new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.myFragmentpager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        getAllSerachQueries();
    }

    public void getAllSerachQueries(){

         searchQueryDatalist=MyDatabaseHelper.getInstance().getMyDatabseHandler().getAllSearchQueries();
        mCustomPagerAdapter.notifyDataSetChanged();

    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        Context mContext;

        public CustomPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = new SearchResultsFragment();
            Bundle args = new Bundle();
            args.putSerializable("queryobj",searchQueryDatalist.get(position));
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return searchQueryDatalist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return searchQueryDatalist.get(position).query;
        }
    }








}
