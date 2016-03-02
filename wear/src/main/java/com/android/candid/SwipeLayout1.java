package com.android.candid;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.activity.WearableActivity;

public class SwipeLayout1 extends WearableActivity {

    ViewPager viewPager;
    SwipeAdapter1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_layout1);
        viewPager = (ViewPager) findViewById(R.id.view_pager1);
        adapter = new SwipeAdapter1(this);
        viewPager.setAdapter(adapter);

    }
}
