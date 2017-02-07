package com.example.android.androidme_steps1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.androidme_steps1.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cezannec on 1/24/17.
 */

public class ListFragment extends Fragment {

    private MasterListAdapter mAdapter;

    private int headPos;
    private int bodyPos;
    private int legPos;


    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Intent intent = new Intent(getActivity(), AndroidMeActivity.class);

        // This adapter takes in an arraylist of all the image resources (created above) to display
        mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        GridView listView = (GridView) rootView.findViewById(R.id.gridview_images);
        listView.setAdapter(mAdapter);

        // We'll call our AndroidMeActivity if "next" button is clicked
        Button nextButton = (Button) rootView.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getActivity(), AndroidMeActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Set all image resources depending on where someone has clicked last
                // Using setId
                //updateBodyPart(position);

                // Using the fact that there are 12 of each head, body, and leg images, we can identify the
                //   correct ViewPager that they are linked to based on the currentPosition/12
                // (This also rounds down to the nearest int)
                int bodyPartIndex = position /12;

                // Modify the position so that it falls in the range of items in each ViewPager
                int currentPosition = position - 12*bodyPartIndex;

                Log.v("ListFrag", "0-2 part: " + bodyPartIndex +",  pos = " + currentPosition);

                // Set the currently displayed item for the correct body part fragment
                switch(bodyPartIndex) {
                    case 0: //intent.putExtra("head", currentPosition);
                        headPos = currentPosition;
                        break;
                    case 1: //intent.putExtra("body", currentPosition);
                        bodyPos = currentPosition;
                        break;
                    case 2: //intent.putExtra("leg", currentPosition);
                        legPos = currentPosition;
                        break;
                    default: break;
                }

                Bundle b = new Bundle();
                b.putInt("head", headPos);
                b.putInt("body", bodyPos);
                b.putInt("leg", legPos);
                intent.putExtras(b);

            }
        });

        return rootView;
    }
}
