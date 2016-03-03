package com.android.candid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class ZipLocation extends WearableActivity {
    ViewPager viewPager;
    SwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zip_location);

        Intent intent = getIntent();

        if (intent.getStringExtra("new zipcode") != null) {
            String zip_new = intent.getStringExtra("new zipcode");
            TextView textView = (TextView) findViewById(R.id.zip_text);
            textView.setTextSize(18);
            textView.setText(zip_new);
        }


    }
}
