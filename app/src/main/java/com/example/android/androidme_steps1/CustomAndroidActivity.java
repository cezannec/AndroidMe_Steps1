package com.example.android.androidme_steps1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.androidme_steps1.data.AndroidImageAssets;

public class CustomAndroidActivity extends AppCompatActivity {

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
    }
}
