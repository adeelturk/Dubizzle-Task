package com.turktech.dubizzletask.network;

import com.turktech.dubizzletask.common.MyConstants;
import com.turktech.dubizzletask.common.Utils;
import com.turktech.dubizzletask.interfaces.FlickerImagesCallback;
import com.turktech.dubizzletask.model.FlickrImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Adeel Turk on 13/04/2017.
 */

public class AppNetworkCalls {



    public static void getFlickrImages(final FlickerImagesCallback callback){


        String url=String.format(MyConstants.FLICKR_API_URL,MyConstants.FLICKR_RECENT_IMAGES,MyConstants.FLICKR_API_KEY);


        /*NetworkHandler.getInstance().get(url, new NetworkStringCallback() {
            @Override
            public void onServiceCallback(String response, Exception exception) {

                System.out.print("");
            }
        });*/


        NetworkHandler.getInstance().get(url, new NetworkCallBack() {
            @Override
            public void onServiceCallback(JSONObject response, Exception error) {
                if(error==null){

                    try{


                        if(response.getString("stat").equalsIgnoreCase("ok")){


                            JSONObject jsonObj=response.getJSONObject("photos");
                            JSONArray jsonDataList=jsonObj.getJSONArray("photo");

                            if(jsonDataList.length()>0){

                                ArrayList<FlickrImage> mdataList=new ArrayList<FlickrImage>();

                                for (int i=0;i<jsonDataList.length();i++){


                                    JSONObject jsonData=jsonDataList.getJSONObject(i);
                                    FlickrImage data= Utils.getGson().fromJson(jsonData.toString(),FlickrImage.class);
                                    mdataList.add(data);

                                }
                                callback.onResponse(mdataList);

                            }else{
                                callback.onError(new Exception("Something went wrong please try again"));
                            }



                        }else{

                            callback.onError(new Exception("Something went wrong please try again"));
                        }


                    }catch (Exception e){

                        //callback.onError(new Exception("Something went wrong please try again"));
                        callback.onError(e);
                        e.printStackTrace();
                    }


                }else{


                    callback.onError(new Exception("Couldn't find data please try again"));
                }


            }
        });
    }



    public static void searchFlickrImages(String searchWord,final FlickerImagesCallback callback){


        String url=String.format(MyConstants.FLICKR_API_URL+MyConstants.FLICKR_SEARCH_KEY,
                MyConstants.FLICKR_RECENT_IMAGES,MyConstants.FLICKR_API_KEY,searchWord);



        NetworkHandler.getInstance().get(url, new NetworkCallBack() {
            @Override
            public void onServiceCallback(JSONObject response, Exception error) {


                if(error==null){

                    try{


                        if(response.getString("stat").equalsIgnoreCase("ok")){


                            JSONObject jsonObj=response.getJSONObject("photos");
                            JSONArray jsonDataList=jsonObj.getJSONArray("photo");

                            if(jsonDataList.length()>0){

                                ArrayList<FlickrImage> mdataList=new ArrayList<FlickrImage>();

                                for (int i=0;i<jsonDataList.length();i++){


                                    JSONObject jsonData=jsonDataList.getJSONObject(i);
                                    FlickrImage data= Utils.getGson().fromJson(jsonData.toString(),FlickrImage.class);
                                    mdataList.add(data);

                                }
                                callback.onResponse(mdataList);

                            }else{
                                callback.onError(new Exception("Something went wrong please try again"));
                            }



                        }else{

                            callback.onError(new Exception("Something went wrong please try again"));
                        }


                    }catch (Exception e){

                        callback.onError(new Exception("Something went wrong please try again"));
                        e.printStackTrace();
                    }


                }else{


                    callback.onError(new Exception("Couldn't find data please try again"));
                }

            }
        });
    }





}
