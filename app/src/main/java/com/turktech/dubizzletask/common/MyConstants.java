package com.turktech.dubizzletask.common;

/**
 * Created by Adeel Turk on 13/04/2017.
 */

public class MyConstants {

    //String url = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=" + API_KEY + "&format="json"";
/*
String uri = String.format("http://somesite.com/some_endpoint.php?param1=%1$s&param2=%2$s",
    num1,
    num2
*/
    public static final String FLICKR_API_KEY = "b12fa7250a55807d0e00eb10a727bf96";
    public static final String FLICKR_API_URL = "https://api.flickr.com/services/rest/?method=%1$s&api_key=%2$s&nojsoncallback=1&format=json&extras=url_s,url_m,url_l";
    public static final String FLICKR_SEARCH_KEY = "&text=%2$s";
    public static final String FLICKR_RECENT_IMAGES = "flickr.photos.getRecent";
    public static final String FLICKR_SEARCH_IMAGES = "flickr.photos.search";
    public static final String FLICKR_POPULAR_IMAGES = "flickr.photos.getPopular";


}