package com.demo.flickerapi.paeser;

import com.demo.flickerapi.model.FlickerApiResponse;
import com.google.gson.Gson;
import java.io.IOException;

/**
 * <h1> FlickerApiParser  </h1>
 * Gson basd JSON parser implementation
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */
public class FlickerApiParser {

    public static FlickerApiResponse parseFlickerPhotoResponse(String jsonResponseObj)throws IOException{

        // Skipping unknown starting character in the JSON response
        jsonResponseObj = jsonResponseObj.replace("jsonFlickrApi(", "");

        jsonResponseObj = jsonResponseObj.substring(0,jsonResponseObj.length()-2);

        // Initialing Gson
        Gson gson = new Gson();

        FlickerApiResponse flickerApiResponse = gson.fromJson(jsonResponseObj, FlickerApiResponse.class);

        return flickerApiResponse;
    }
}
