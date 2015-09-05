package com.demo.flickerapi.model;

import java.util.List;


/**
 * <h1> Photos  </h1>
 * Class to capture Photo info from API response
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */


public class Photos {

    private String page ;

    private int pages ;

    private int perPage;

    private String total;

    private List<Photo> photo;

    public Photos(String page, int pages, int perPage, String total, List<Photo> photo) {
        this.page = page;
        this.pages = pages;
        this.perPage = perPage;
        this.total = total;
        this.photo = photo;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
