package com.android.candid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class PressResult1 extends WearableActivity {
    ViewPager viewPager;
    SwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presidential_result1);
        Random rn = new Random();
        int max = 2;
        int min = 0;
        int num = rn.nextInt(max - min + 1) + min;

        if (num == 0) {
            TextView textView = (TextView) findViewById(R.id.ob_v);
            textView.setText("30%");

            TextView textView1 = (TextView) findViewById(R.id.r_v);
            textView1.setText("70%");
        } else if (num == 1) {
            TextView textView = (TextView) findViewById(R.id.ob_v);
            textView.setText("80%");

            TextView textView1 = (TextView) findViewById(R.id.r_v);
            textView1.setText("20%");
        } else if (num == 2) {
            TextView textView = (TextView) findViewById(R.id.ob_v);
            textView.setText("40%");

            TextView textView1 = (TextView) findViewById(R.id.r_v);
            textView1.setText("60%");
        }
    }
}
