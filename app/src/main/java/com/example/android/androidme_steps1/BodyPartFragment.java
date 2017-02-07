package com.example.android.androidme_steps1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.androidme_steps1.data.AndroidImageAssets;

/**
 * Created by cezannec on 1/24/17.
 */

// Fragment that holds an image for an AndroidMe body part (head, body, or legs)
    // The image it holds is determined by the image id, which is set by a call to setId()
public class BodyPartFragment extends Fragment {

    // Keep track of image resource with class variables
    public static final String IMG_ID = "IMG_ID";
    private int mImageId;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BodyPartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Load current image resource id if it exists
        if (savedInstanceState != null) {
            mImageId = savedInstanceState.getInt(IMG_ID);
        }

        // Inflate the Android-Me image segment, populating it with an image based on it's resource id
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image);

        // Set the currently selected image resource id
//        imageView.setImageResource(mImageId);
//
//        Log.v("BodyPartFrag", "image resource... id = " + mImageId);

//        int savedImageId = getActivity().getIntent().getIntExtra("head", 0);
////
//        Log.v("BodyPartFrag", "saved pos = " + savedImageId);
//
//        mImageId = AndroidImageAssets.getAll().get(savedImageId);

//
        imageView.setImageResource(mImageId);
        Log.v("BodyPartFrag", "image resource... id = " + mImageId);


        // Return the root view
        return rootView;
    }


    // Save current image resource id
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putInt(IMG_ID, mImageId);
    }


    // This is a "setter" method that gives us a way to update the id from the adapter class
    public void setId(int id) {
        this.mImageId = id;
    }
}
