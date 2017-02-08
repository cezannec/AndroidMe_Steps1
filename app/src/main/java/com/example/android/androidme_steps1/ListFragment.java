package com.example.android.androidme_steps1;

import android.content.Intent;
import android.opengl.Visibility;
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

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

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

                // For phone and tablet layouts, pass information in the intent
                Bundle b = new Bundle();
                b.putInt("head", headPos);
                b.putInt("body", bodyPos);
                b.putInt("leg", legPos);
                intent.putExtras(b);

                // For the tablet, change the currently existing Fragment
                if(getActivity().findViewById(R.id.headFragment) != null) {
                    // This means this is a tablet layout!!
                    // tablet layout; update the fragments - don't just create a bundle
                    // extra info passed in intent
                    int headIndex = intent.getIntExtra("head", 0);
                    BodyPartFragment headFragment = new BodyPartFragment();
                    headFragment.setId(AndroidImageAssets.getHeads().get(headIndex));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.headFragment, headFragment)
                            .commit();

                    // extra info passed in intent
                    int bodyIndex = intent.getIntExtra("body", 0);
                    BodyPartFragment bodyFragment = new BodyPartFragment();
                    bodyFragment.setId(AndroidImageAssets.getBods().get(bodyIndex));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.bodyFragment, bodyFragment)
                            .commit();

                    int legIndex = intent.getIntExtra("leg", 0);
                    BodyPartFragment legFragment = new BodyPartFragment();
                    legFragment.setId(AndroidImageAssets.getLegs().get(legIndex));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.legFragment, legFragment)
                            .commit();

                }

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
