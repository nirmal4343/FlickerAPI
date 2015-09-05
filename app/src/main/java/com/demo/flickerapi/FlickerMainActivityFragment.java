package com.demo.flickerapi;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.demo.flickerapi.com.demo.flickerapi.gps.GPSLocation;
import com.demo.flickerapi.httpconnection.RestCall;
import com.demo.flickerapi.model.Photo;
import com.demo.flickerapi.model.Status;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**

 * <h1> Fragment   </h1>
 * This is the fragment display in MainActivity to display the downloaded image from flicker.
 * Also, has the implementation of network call using AsyncTask to pull the JSON response from server
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */

public class FlickerMainActivityFragment extends Fragment {

    private WeakReference<GetFlickerPhotosTask> asyncTaskWeakRef;

    private GridView gridView;

    private GPSLocation gps;

    private Location gpsLocation;

    private CustomGridAdapter adapter;

    private List<Photo> photoList;

    private OnHeadlineSelectedListener homeActivity;

    private  ProgressDialog progressBar;

    private double latitude = 0;

    private double longitude = 0;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {

            homeActivity = (OnHeadlineSelectedListener) activity;

        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()+ " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        GPSLocation gps = new GPSLocation(getActivity());

        // check if GPS enabled
        if(gps.canGetLocation()){

             latitude = gps.getLatitude();
             longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getActivity().getApplicationContext(), "Your Location is - \nLat: " + Double.toString(latitude) + "\nLong: " + Double.toString(longitude), Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            // gps.showSettingsAlert();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_flicker_main, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getActivity().findViewById(R.id.gridView1);

        photoList = new ArrayList<Photo>();

        adapter = new CustomGridAdapter(getActivity(), photoList);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                homeActivity.onPhotoSelected(view , photoList.get(position));

            }
        });

    }

    @Override
    public void onResume() {

        super.onResume();

        getFlickerPhotoTask();
    }

    /**
     * Invokes AsyncTask to pull image list from flicker server.
     *
     */

    private void getFlickerPhotoTask() {

        GetFlickerPhotosTask asyncTask = new GetFlickerPhotosTask(this);

        this.asyncTaskWeakRef = new WeakReference<GetFlickerPhotosTask>(asyncTask );

        asyncTask.execute();
    }


    /**
     * Get Flicker Photo Task Implementation
     * AsyncTask<Void, Void ,  Status>
     */
    private class GetFlickerPhotosTask extends AsyncTask<Void, Void , Status> {

        private WeakReference<FlickerMainActivityFragment> fragmentWeakRef;

        private GetFlickerPhotosTask (FlickerMainActivityFragment fragment) {

            this.fragmentWeakRef = new WeakReference<FlickerMainActivityFragment>(fragment);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            // Clear the list before populating a fresh list
            photoList.clear();

            progressBar = new ProgressDialog(getActivity());
            progressBar.setCancelable(true);
            progressBar.setMessage("Loading Image ...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();

        }

        @Override
        protected com.demo.flickerapi.model.Status doInBackground(Void... params) {


            if(latitude != 0 && longitude != 0) {

                // Lat Long id able to be detected, proceeding with the REST API to get photo list from server.
                return RestCall.getInstance().getFlickerPhotoList(getActivity(), Double.toString(latitude) , Double.toString(longitude));

            } else{
                // can't get location
                // GPS or Network is not enabled
                // Proceeding default location
                Toast.makeText(getActivity().getApplicationContext(), getActivity().getResources().getString(R.string.gps_error), Toast.LENGTH_LONG).show();
                return RestCall.getInstance().getFlickerPhotoList(getActivity(), "33.766827", "-84.294109");
            }

        }

        @Override
        protected void onPostExecute(com.demo.flickerapi.model.Status response) {

            super.onPostExecute(response);

            if (this.fragmentWeakRef.get() != null) {

                copyIterator(response.getFlickerApiResponse().getPhotos().getPhoto().iterator(), photoList);

                adapter.notifyDataSetChanged();

                progressBar.dismiss();
            }

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeActivity = null;
    }

    private boolean isAsyncTaskPendingOrRunning() {
        return this.asyncTaskWeakRef != null &&
                this.asyncTaskWeakRef.get() != null &&
                !this.asyncTaskWeakRef.get().getStatus().equals(AsyncTask.Status.FINISHED);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */

    public interface OnHeadlineSelectedListener {

        public void onPhotoSelected(View view, Photo photo);

    }

    /**
     * Copy List items ( Type: Photo) received from server to the List<Photo> refered in the GridView Adapter
     */
    public static <T> List<T> copyIterator(Iterator<T> iter , List<T> copy) {
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

}