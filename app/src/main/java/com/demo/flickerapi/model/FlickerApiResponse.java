package com.demo.flickerapi.model;


/**
 * <h1> FlickerApiResponse  </h1>
 * Manage API response while using Gson parser
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */

public class FlickerApiResponse {

    private Photos photos;

    private String stat;

    public FlickerApiResponse(Photos photos, String stat) {
        this.photos = photos;
        this.stat = stat;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
