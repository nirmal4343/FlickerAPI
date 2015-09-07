package com.demo.flickerapi.config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.demo.flickerapi.model.Photo;

/**
 * <h1> App Config  </h1>
 * Declare and define all the application configuration parameter like server URL and more
 * Some configuration method which is used to pull key information.
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 09/03/2015
 */

public class AppConfig {


    public static final String SERVER_URL = "https://api.flickr.com/services/rest/";

    public static final String METHOD = "?method=flickr.photos.search";

    public static final String API_KEY = "&api_key=" + "ce34ea52b317fd11abf51d95f418fd7c";

    public static final String API_TAG = "&tags="+ "places";

    public static final String FORMAT_JSON = "&format=json";

    public static final String FORMAT_XML = "&format=xml";

    public static final String LATITUDE = "&latitude=";

    public static final String LONGITUDE = "&longitude=";

    public static final String REQUEST_METHOD_GET = "GET";

    public static final String REQUEST_METHOD_POST = "POST";

    public static final String RESPONSE_OK = "ok";

    public static final int READ_TIMEOUT = 10*1000;

    public static final int CONNECT_TIMEOUT = 15*1000;




    /**
     * Form server url for to pull photos around current location, return server url for JSON response type.
     *
     * A Sample URL,     //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ce34ea52b317fd11abf51d95f418fd7c&tags=food&format=json&latitude=33.766827&longitude=-84.294109
     *
     * @param latitude , longitude
     * @return String
     */

    public static String getJSONServerUrl(String latitude, String longitude){

        return SERVER_URL + METHOD +  API_KEY + API_TAG + FORMAT_JSON + LATITUDE + latitude + LONGITUDE + longitude;
    }

    /**
     * Form server url to return response as a XML response type
     * @param latitude , longitude
     * @return String
     */

    public static String getXMLServerUrl(String latitude, String longitude){

        return SERVER_URL + METHOD +  API_KEY + API_TAG + FORMAT_XML + LATITUDE + latitude + LONGITUDE + longitude;
    }

    /**
     * Check the network connectivity before making server call
     * @param context
     * @return boolean
     */

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {

            return true;

        }
        return false;
    }

    /**
     * Generate photo download URL for selected photo object
     * @param photo
     * @return
     *   Well from URL to download image using volley
     */

    public static String formImageDownloadUrl(Photo photo){

        return "https://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getSecret()+".jpg";

    }

}
