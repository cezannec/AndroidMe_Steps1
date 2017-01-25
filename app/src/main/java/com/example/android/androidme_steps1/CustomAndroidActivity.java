package com.example.android.androidme_steps1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.android.androidme_steps1.data.AndroidImageAssets;

public class CustomAndroidActivity extends AppCompatActivity {

    private int clickCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_android);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html


        if (savedInstanceState == null) {
            // Create the head, body, and leg fragments and add it to the activity
            // using a fragment transaction.

            // Default is first in list

            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setId(AndroidImageAssets.getHeads().get(0));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.headFragment, headFragment)
                    .commit();

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setId(AndroidImageAssets.getBods().get(0));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setId(AndroidImageAssets.getLegs().get(0));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.legFragment, legFragment)
                    .commit();
        }


        // set up the temporary clickListener on the head View
        setupHeadClickListener();
    }


    // Set a temporary onClickListener to the headView
    // This wil let us play around with fragment transactions
    void setupHeadClickListener() {
        FrameLayout headView = (FrameLayout) findViewById(R.id.headFragment);

        headView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // update number of clicks - which determines the head image id
                clickCount++;
                // if clickCount is a valid index, *replace* the current fragment with the next image
                // Using a call to replace!
                // Remember valid indices include 0-11
                if(clickCount < 12) {
                    BodyPartFragment headFragment = new BodyPartFragment();
                    headFragment.setId(AndroidImageAssets.getHeads().get(clickCount));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.headFragment, headFragment)
                            .commit();
                }
            }
        });
    }
}
