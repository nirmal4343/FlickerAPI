package com.demo.flickerapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.demo.flickerapi.config.AppConfig;
import com.demo.flickerapi.model.Photo;
import com.demo.flickerapi.model.com.demo.flickerapi.model.image.ImageCacheManager;

import java.util.List;


/**
 * <h1> CustomAdapter   </h1>
 * Adapter been used to project the image item in GridView
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/3/2015
 */

public class CustomGridAdapter extends BaseAdapter {

    private Context context;
    private final List<Photo> gridValues;

    //Constructor to initialize values
    public CustomGridAdapter(Context context, List<Photo> gridValues) {

        this.context        = context;
        this.gridValues     = gridValues;
    }

    @Override
    public int getCount() {

        // Number of times getView method call depends upon gridValues.length
        return gridValues.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }


    // Number of times getView method call depends upon gridValues.length

    public View getView(int position, View convertView, ViewGroup parent) {

        // LayoutInflator to call external grid_item.xml file

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = inflater.inflate( R.layout.items , null);

            viewHolder = new ViewHolder();

            viewHolder.flickerImage = (NetworkImageView)convertView.findViewById(R.id.flikerImage);

            viewHolder.imageTitle = (TextView)convertView.findViewById(R.id.grid_item_label);

            viewHolder.progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar2);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(gridValues.get(position) != null ) {

            String imgUrl = AppConfig.formImageDownloadUrl(gridValues.get(position));

            Log.d("*** URL **", imgUrl);

            viewHolder.flickerImage.setDefaultImageResId(R.drawable.placeholder);

            viewHolder.flickerImage.setImageUrl(imgUrl, ImageCacheManager.getInstance().getImageLoader());

            viewHolder.imageTitle.setText(gridValues.get(position).getTitle());


        }

        return convertView;
    }


    /**
     * ViewHolder for the GridView row, for faster rendering of image in GridView
     *
     * @author Nirmal Thakur
     *
     */
    static class ViewHolder{
        NetworkImageView flickerImage;
        TextView imageTitle;
        ProgressBar progressBar;
    }
}