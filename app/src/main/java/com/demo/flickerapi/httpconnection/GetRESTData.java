package com.demo.flickerapi.httpconnection;

import android.content.Context;

import com.demo.flickerapi.model.Status;

/**
 * <h1> RestFull Interface  </h1>
 * Declare an API used to proceed with RestFull implementation
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 09/04/2015
 */

interface GetRESTData {

    /**
     * RestFull API declaration to pull JSON response from Flicker server
     *
     * @param context
     * @param latitude
     * @param longitude
     * @return Status.
     */
    public Status getFlickerPhotoList(Context context, String latitude, String longitude);

}