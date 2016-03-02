package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class CurrDetails extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_curr);

        ImageButton img_bm = (ImageButton) findViewById(R.id.bm_imgb);
        img_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.bm_imgb);
                sendIntent.putExtra("image", bitmap);

                TextView editText = (TextView) findViewById(R.id.bm_textb);
                String message = editText.getText().toString();
                sendIntent.putExtra("fullname", message);

                TextView editText1 = (TextView) findViewById(R.id.bm_party);
                String message1 = editText1.getText().toString();
                sendIntent.putExtra("party", message1);

                startService(sendIntent);
            }
        });

//        TextView text_bm = (TextView) findViewById(R.id.bm_textb);
//        text_bm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("Initial", "BM_TXT");
//                startService(sendIntent);
//            }
//        });

    }
}