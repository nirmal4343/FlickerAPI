package com.demo.flickerapi.httpconnection;

import android.util.Log;

import com.demo.flickerapi.config.AppConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * <h1> RestFull CLIENT implementations  </h1>
 * RestFull Client implementation to invoke server request
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 5/17/2015
 */

public class RestClient extends HttpConnectionManager {

	public enum RequestMethod {
		GET,
		POST
	}


	private String strURL = null;

	private int responseCode;

	private String message;

	private String response;

	public String getResponse() {

		return response;
	}

	public String getErrorMessage() {
		return message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public RestClient(String strURL)
	{
        this.strURL = strURL;

        Log.d(" Server URL::: ->  " , strURL.toString());
	}


	/**
	 * Execute Server call based on provided request method type
	 *
	 * @param method request method type GET/POST
	 * @return WeatherInfo.
	 */

	public void Execute(RequestMethod method) throws Exception
	{
		switch(method) {

            case GET:
            {
                HttpURLConnection connection = getHttpConnection(strURL);

                addHttpGetParams(connection, AppConfig.REQUEST_METHOD_GET);

                executeRequest(connection);

                break;
            }
            case POST:
            {
                // Defer POST implementation as it is out od scope at this point
                break;
            }
		}
	}



	/**
	 * Executes, connects to the server
	 *
	 * @param connection HttpURLConnection
	 * @return
	 */

	private void executeRequest(HttpURLConnection connection)
	{

		try {

            connection.connect();

            responseCode = connection.getResponseCode();

            message = connection.getResponseMessage();

            if(responseCode == HTTP_OK)
                response = convertStreamToNewString(connection.getInputStream());
            else
                response = null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}



	/**
	 * Converts the InputStream object to string
	 *
	 * @param is InputStream, received from server response
	 * @return String
	 */

	private static String convertStreamToNewString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();

		String line = null;

		try {
			while ((line = reader.readLine()) != null) {

				sb.append(line + "\n");
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Log.e("sValue - ", sb.toString());
		return sb.toString();
	}
	
}