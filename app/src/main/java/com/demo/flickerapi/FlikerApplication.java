package com.demo.flickerapi;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;
import com.demo.flickerapi.model.RequestManager;
import com.demo.flickerapi.model.image.ImageCacheManager;

/**
 *
 *
 * <h1> Flicker Application   </h1>
 * Init Volley library for making Network call to pull the image from flicker server
 * Also initialize the cache (In-Memory Cache) to store the downloaded image, to avoid over usage of network bandwidth
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */

public class FlikerApplication extends Application {

    private static int DISK_IMAGECACHE_SIZE = 1024*1024*10;
    private static CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.PNG;
    private static int DISK_IMAGECACHE_QUALITY = 100;  //PNG is loss less so quality is ignored but must be provided

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    /**
     * Intialize the request manager and the image cache
     */
    private void init() {
        RequestManager.init(this);
        createImageCache();
    }

    /**
     * Create the image cache. Uses Memory Cache by default. Change to Disk for a Disk based LRU implementation.
     */
    private void createImageCache(){
        ImageCacheManager.getInstance().init(this,
                this.getPackageCodePath()
                , DISK_IMAGECACHE_SIZE
                , DISK_IMAGECACHE_COMPRESS_FORMAT
                , DISK_IMAGECACHE_QUALITY
                , ImageCacheManager.CacheType.MEMORY);
    }
}