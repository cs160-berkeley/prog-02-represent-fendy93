package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class ZipDetails extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_senate);

        ImageButton img_jc = (ImageButton) findViewById(R.id.jc_imgb);
        img_jc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.bm_imgb);
                sendIntent.putExtra("image", bitmap);

                TextView editText = (TextView) findViewById(R.id.jc_textb);
                String message = editText.getText().toString();
                sendIntent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.jc_party);
                String message1 = editText1.getText().toString();
                sendIntent.putExtra("party", message1);

                startService(sendIntent);
            }
        });

        ImageButton img_tc = (ImageButton) findViewById(R.id.tc_imgb);
        img_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.bb_imgb);
                sendIntent.putExtra("image", bitmap);

                TextView editText = (TextView) findViewById(R.id.tc_textb);
                String message = editText.getText().toString();
                sendIntent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.tc_party);
                String message1 = editText1.getText().toString();
                sendIntent.putExtra("party", message1);

                startService(sendIntent);
            }
        });

        ImageButton img_lm = (ImageButton) findViewById(R.id.lm_imgb);
        img_lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.bb_imgb);
                sendIntent.putExtra("image", bitmap);

                TextView editText = (TextView) findViewById(R.id.lm_textb);
                String message = editText.getText().toString();
                sendIntent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.lm_party);
                String message1 = editText1.getText().toString();
                sendIntent.putExtra("party", message1);

                startService(sendIntent);
            }
        });

        ImageButton img_mk = (ImageButton) findViewById(R.id.mk_imgb);
        img_mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.bb_imgb);
                sendIntent.putExtra("image", bitmap);

                TextView editText = (TextView) findViewById(R.id.mk_textb);
                String message = editText.getText().toString();
                sendIntent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.mk_party);
                String message1 = editText1.getText().toString();
                sendIntent.putExtra("party", message1);

                startService(sendIntent);
            }
        });


    }
}