package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SRDetails extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sen_rep_details);

        Intent newActivity1=new Intent();
        setResult(RESULT_OK, newActivity1);

//        Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra("picture_watch");
//        ImageView viewBitmap = (ImageView) findViewById(R.id.pic_watch);
//        viewBitmap.setImageBitmap(bitmap);
//
//        Intent intent = getIntent();
//
//        String full_name = intent.getStringExtra("fullname_watch");
//        TextView textView = (TextView) findViewById(R.id.watch_name);
//        textView.setTextSize(18);
//        textView.setText(full_name);
//
//        String party_name = intent.getStringExtra("party_watch");
//        TextView textView1 = (TextView) findViewById(R.id.party_watch);
//        textView1.setTextSize(18);
//        textView1.setText(party_name);

    }
}
