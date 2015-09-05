package com.demo.flickerapi.httpconnection;


import com.demo.flickerapi.config.AppConfig;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * <h1> Http Connection Manager  </h1>
 * Provides basic http connection implementation to proceed with HTTP rest call
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 09/04/2015
 */
public abstract class HttpConnectionManager {

    private HttpURLConnection httpConnection;

    private URL url;

    public static int HTTP_OK = 200;


    /**
     * Initialize and return HttpConnection object for a URL
     *
     * @param strUrl server url
     * @return HttpURLConnection
     */

    public HttpURLConnection getHttpConnection(String strUrl){

        try {

            url = new URL(strUrl);

            httpConnection =  (HttpURLConnection) url.openConnection();

            return httpConnection;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * Add Http Connection param
     *
     * @param httpConnection connection object, this object will be initialized with all connection parameter
     * @param requestMethod connection request type (GET/POST)
     * @return null
     */

    public void addHttpGetParams(HttpURLConnection httpConnection , String requestMethod) throws ProtocolException {

        /* HTTP Request method type (GET/POST) */
        httpConnection.setRequestMethod(requestMethod);
        /* optional request header */
        httpConnection.setRequestProperty("Content-Type", "application/json");
        /* optional request header */
        httpConnection.setRequestProperty("Accept", "application/json");
        /* give it 10 seconds to respond */
        httpConnection.setReadTimeout(AppConfig.READ_TIMEOUT);
        /* give it 15 seconds to connect */
        httpConnection.setConnectTimeout(AppConfig.CONNECT_TIMEOUT);
    }

    public void addHttpPostParams(HttpsURLConnection httpConnection , String requestMethod) throws ProtocolException {

        /* Defer POST params implementations as this is out of scope at this point */

    }
}
