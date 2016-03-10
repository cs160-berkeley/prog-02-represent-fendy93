package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fendyzhou on 2/28/16.
 */
public class CurrentToDetail extends AppCompatActivity {
    private List<Info> infos = new ArrayList<Info>();
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

        String end_date = intent.getStringExtra("enddate");
        TextView textView2 = (TextView) findViewById(R.id.text_end_date);
        textView2.setTextSize(18);
        textView2.setText(end_date);

//        populateInfoList();
//        populateViewList();
    }

//    private void populateInfoList() {
////        for(int i = 0; i < 5; i++){
////            infos.add(new Info("Barbara Mikulski", "Democrat", R.mipmap.bmikulski, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info"));
////        }
//
//        ArrayList<String> activeComittee = new ArrayList<String>();
//        activeComittee.add("a");
//        activeComittee.add("b");
//        activeComittee.add("c");
//
//        ArrayList<String> activeComittee1 = new ArrayList<String>();
//        activeComittee1.add("L");
//        activeComittee1.add("M");
//        activeComittee1.add("K");
//
//        ArrayList<String> recentBills = new ArrayList<String>();
//        recentBills.add("d");
//        recentBills.add("e");
//        recentBills.add("f");
//
//        infos.add(new Info("Barbara Boxer", "Democrat", R.mipmap.blee, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info", "@blee: Happy Birthday!", "January 1, 2026", activeComittee, recentBills));
//        infos.add(new Info("Mikulski", "Democrat", R.mipmap.bmikulski, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info", "@blee: Happy Birthday!", "January 1, 2010", activeComittee1, recentBills));
//    }
//
//    private void populateViewList() {
//        final int infoSize = infos.size();
//        System.out.println(infoSize);
//        for (int i = 0; i < infoSize; i++) {
//            Info currentInfo = infos.get(i);
//            if (currentInfo.getName().equals(getIntent().getStringExtra("fullname"))) {
//                System.out.println(getIntent().getStringExtra("fullname"));
//                System.out.println(currentInfo.getName());
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, currentInfo.getActiveComittee());
//                ListView list = (ListView) findViewById(R.id.listView_ac);
//                ListView list1 = (ListView) findViewById(R.id.listView_rb);
//                list.setAdapter(adapter);
//                list1.setAdapter(adapter);
//                break;
//            }
//            break;
//        }
//    }
}

