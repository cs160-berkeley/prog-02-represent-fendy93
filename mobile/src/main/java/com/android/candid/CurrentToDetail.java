package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fendyzhou on 2/28/16.
 */
public class CurrentToDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra(MainActivity.EXTRA_MESSAGE);
        ImageView viewBitmap = (ImageView) findViewById(R.id.img);
        viewBitmap.setImageBitmap(bitmap);

        Intent intent = getIntent();

        String full_name = intent.getStringExtra("fullname");
        TextView textView = (TextView) findViewById(R.id.fname);
        textView.setTextSize(18);
        textView.setText(full_name);

        String party_name = intent.getStringExtra("party");
        TextView textView1 = (TextView) findViewById(R.id.pname);
        textView1.setTextSize(18);
        textView1.setText(party_name);
    }
}
