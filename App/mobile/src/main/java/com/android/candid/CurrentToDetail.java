package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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
    private SunLightHTTP data = new SunLightHTTP(this);
    private String lat;
    private String lng;
    private ArrayList<String> ac = new ArrayList<String>();
    private ArrayList<String> bc = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String bitmap = this.getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        ImageView viewBitmap = (ImageView) findViewById(R.id.img);
        new UrltoImageView(viewBitmap).execute(bitmap);

        Intent intent = getIntent();
        Intent intent1 = new Intent(CurrentToDetail.this, MainToCurrent.class);
        lat = intent.getStringExtra("latCurr");
        lng = intent.getStringExtra("lngCurr");

        ac = intent.getStringArrayListExtra("activeComittee");
        bc = intent.getStringArrayListExtra("recentBills");

        int lac = ac.size();
        int lbc = bc.size();


        String full_name = intent.getStringExtra("fullname");
        TextView textView = (TextView) findViewById(R.id.fname);
        textView.setTextSize(18);
        textView.setText(full_name);

        String party_name = intent.getStringExtra("party");
        if (party_name.equals("D")) {
            party_name = "Democrat";
        } else {
            party_name = "Republic";
        }
        TextView textView1 = (TextView) findViewById(R.id.pname);
        textView1.setTextSize(18);
        textView1.setText(party_name);

        String end_date = intent.getStringExtra("enddate");
        TextView textView2 = (TextView) findViewById(R.id.text_end_date);
        textView2.setTextSize(18);
        textView2.setText(end_date);

        String temp_ac = "";
        for (int i = 0; i < lac; i++) {
            temp_ac += ac.get(i);
        }
        String temp_bc = "";
        for (int j = 0; j < lbc; j++) {
            temp_bc += bc.get(j);
        }

        TextView textView3 = (TextView) findViewById(R.id.acommittee);
        textView3.setTextSize(18);
        textView3.setText(temp_ac);

        TextView textView4 = (TextView) findViewById(R.id.rbills);
        textView4.setTextSize(18);
        textView4.setText(temp_bc);



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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.home) {
//            return true;
//        }
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);

                Intent intent = new Intent(CurrentToDetail.this, MainActivity.class);
                startActivity(intent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

