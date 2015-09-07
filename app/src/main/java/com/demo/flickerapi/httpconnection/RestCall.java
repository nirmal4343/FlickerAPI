package com.demo.flickerapi.httpconnection;

import android.content.Context;
import android.util.Log;

import com.demo.flickerapi.R;
import com.demo.flickerapi.config.AppConfig;
import com.demo.flickerapi.model.FlickerApiResponse;
import com.demo.flickerapi.model.Status;
import com.demo.flickerapi.paeser.FlickerApiParser;

/**
 * <h1> RestFull API implementations  </h1>
 * Provide RestFull API implementation to pull photo info from Flicker server
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */

public class RestCall implements GetRESTData{

	private static final String TAG = "RestCall";

	private static RestCall instance = null;

	private RestCall() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Singleton implementation of this class
	 */

	public static synchronized RestCall getInstance() {

		if(instance == null) {

			instance = new RestCall();

		}

		return instance;
	}


	/**
	 * Method interact with Rest Client to pull Photo info from Flicker server.
	 *
	 * @param strUrl server url
	 * @return String JSON string response
	 */

	private String getHttpResponse(String strUrl) {

        String response = null;

        RestClient client = new RestClient(strUrl);

		try {

			client.Execute(RestClient.RequestMethod.GET);

		} catch (Exception e) {

			e.printStackTrace();
		}

		response = client.getResponse();

		return response;
	}

    @Override
    public Status getFlickerPhotoList(Context context, String latitude, String longitude) {

        Status status = new Status();
        // Check Network Availability
        if(AppConfig.isNetworkAvailable(context)) {

			Log.v("SERVER-URL" , AppConfig.getJSONServerUrl(latitude,longitude));

			String resp = getHttpResponse(AppConfig.getJSONServerUrl(latitude,longitude));

			parseResponse(context, resp, status);

			Log.v("Response Flicker" , resp);

        } else {

            status.setStatus(Status.STATUS_FAIL);

            status.setStatus(context.getResources().getString(R.string.network_error));
        }
        return status;
    }

	/**
	 * This method interact with parser to parse JSON response
	 *
	 * @param strResp JSON based string
	 * @return Status  contains Response object
	 */

	private void parseResponse(Context context , String strResp, Status status ){

		try {

			FlickerApiResponse objResp = FlickerApiParser.parseFlickerPhotoResponse(strResp);

			if(objResp != null){


				if(objResp.getStat() != null &&
						objResp.getStat().equals(AppConfig.RESPONSE_OK)) {

                    status.setStatus(Status.STATUS_SUCCESS);

                    status.setFlickerApiResponse(objResp);

				} else {

                    status.setStatus(Status.STATUS_FAIL);

                    status.setMessage(context.getResources().getString(R.string.parse_error));
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

			status.setStatus(Status.STATUS_FAIL);
		}
	}
}
