package com.turktech.dubizzletask.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.turktech.dubizzletask.fragment.SearchResultsFragment;
import com.turktech.dubizzletask.model.FlickrImage;
import com.turktech.dubizzletask.model.SearchQuery;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 16/04/2017.
 */

public class MyDatabseHandler extends SQLiteOpenHelper {


    private Context mContext;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "dubizle";

    /// Building  data set
    private static final String TABLE_QUERY_DATA = "search_query";
    private static final String TABLE_QUERY_RESUTS_DATA = "search_query_results";

    private static final String KEY_ID = "id";
    private static final String KEY_QUERY = "query";

    private static final int KEY_ID_INDEX= 0;
    private static final int KEY_QUERY_INDEX = 1;


    private static final String KEY_FK_ID = "fk_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String KEY_URL_MEDIUM = "urlMedium";
    private static final String KEY_URL_LARGE = "urlLarge";

    private static final int KEY_FK_ID_INDEX = 1;
    private static final int KEY_title_INDEX = 2;
    private static final int KEY_URL_INDEX = 3;
    private static final int KEY_URL_MEDIUM_INDEX =4;
    private static final int KEY_URL_LARGE_INDEX = 5;


    public MyDatabseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY_DATA = "CREATE TABLE " + TABLE_QUERY_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  KEY_QUERY+" TEXT " + ")";

        String CREATE_TABLE_QUERY_RESUTS_DATA = "CREATE TABLE " + TABLE_QUERY_RESUTS_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FK_ID + " INTEGER,"
                + KEY_TITLE + " TEXT," + KEY_URL + " TEXT," + KEY_URL_MEDIUM + " TEXT,"
                + KEY_URL_LARGE + " TEXT " + ")";



        db.execSQL(CREATE_TABLE_QUERY_DATA);
        db.execSQL(CREATE_TABLE_QUERY_RESUTS_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addNewQuery(String query){

        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(KEY_QUERY,query);

            //int id = db.update(TABLE_BUILDING_DATA, values, "bookid='5' and booktype='comic'", null);
        long l = db.insert(TABLE_QUERY_DATA, null, values);



        db.close();
    }

    public int getQueyId(String query){

        SQLiteDatabase db = this.getWritableDatabase();

        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_QUERY_DATA + " WHERE "
                + KEY_QUERY + "=?  ";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{query});



        int queyId=-1;
        if(cursor.moveToNext()){

            queyId=cursor.getInt(KEY_ID_INDEX);

        }

        return queyId;
    }

    public void addQueryResults(String query, ArrayList<FlickrImage>mDataList){



        if(getQueyId(query)!=-1){

            SQLiteDatabase db = this.getWritableDatabase();

            for(FlickrImage mData:mDataList) {
                ContentValues values = new ContentValues();
                values.put(KEY_FK_ID, getQueyId(query));
                values.put(KEY_TITLE, mData.title);
                values.put(KEY_URL, mData.url);
                values.put(KEY_URL_MEDIUM, mData.urlMedium);
                values.put(KEY_URL_LARGE, mData.urlLarge);

                long l = db.insert(TABLE_QUERY_RESUTS_DATA, null, values);

            }


        }else{

            addNewQuery(query);
            addQueryResults(query,mDataList);
        }


    }


    public boolean haveSerachQueries(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_QUERY_DATA;
        Cursor cursor=db.rawQuery(selectQuery,null);
        return cursor.moveToNext();
    }

    public  ArrayList<SearchQuery>  getAllSearchQueries(){

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<SearchQuery> dataList=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUERY_DATA;

        Cursor cursor=db.rawQuery(selectQuery,null);

        while (cursor.moveToNext()){

            SearchQuery data=new SearchQuery();

            data.id=cursor.getInt(KEY_ID_INDEX);
            data.query=cursor.getString(KEY_QUERY_INDEX);

            dataList.add(data);

        }

        return dataList;

    }



    public  ArrayList<FlickrImage>  getAllSearchResults(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<FlickrImage> dataList=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUERY_RESUTS_DATA+ " WHERE "
                + KEY_FK_ID + "=?";


        Cursor cursor=db.rawQuery(selectQuery,new String[] {String.valueOf(id)});

        while (cursor.moveToNext()){

            FlickrImage data=new FlickrImage();

            data.id=cursor.getInt(KEY_ID_INDEX);
            data.fk_id=cursor.getInt(KEY_FK_ID_INDEX);
            data.title=cursor.getString(KEY_title_INDEX);
            data.title=cursor.getString(KEY_title_INDEX);
            data.url=cursor.getString(KEY_URL_INDEX);
            data.urlMedium=cursor.getString(KEY_URL_MEDIUM_INDEX);
            data.urlLarge=cursor.getString(KEY_URL_LARGE_INDEX);

            dataList.add(data);

        }

        return dataList;

    }







}
