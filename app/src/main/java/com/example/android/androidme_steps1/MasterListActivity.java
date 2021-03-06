package com.example.android.androidme_steps1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.androidme_steps1.data.AndroidImageAssets;

/**
 * Created by cezannec on 1/25/17.
 */

public class MasterListActivity extends AppCompatActivity {

    private final String LOG_TAG = MasterListActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_master_list);

//        GridView gridview = (GridView) findViewById(R.id.gridview_images);
//        gridview.setAdapter(new MasterListAdapter(this, AndroidImageAssets.getHeads()));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(MasterListActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        if (findViewById(R.id.headFragment) != null) {
            // The headFragment view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.nextButton);
            nextButton.setVisibility(View.GONE);
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

            if (savedInstanceState == null) {
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
        } else {
            mTwoPane = false;
        }
    }
}
