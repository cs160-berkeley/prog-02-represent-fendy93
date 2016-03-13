package com.android.candid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fendyzhou on 3/1/16.
 */
public class ZipDetails extends WearableActivity {
    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    public final static String EXTRA_MESSAGE_2 = "com.android.candid.MESSAGE2";
    private List<InfoWear> infoWear = new ArrayList<InfoWear>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_curr);

        populateViewList();
        populateInfoList();

    }

    private void populateInfoList() {
        Intent intent1 = getIntent();

        int count1 = intent1.getIntExtra("count_to_watch", 0);
        System.out.println("#############");
        System.out.println(count1);
        ArrayList<String> fullname = intent1.getStringArrayListExtra("full_name_to_watch");
        ArrayList<String> party = intent1.getStringArrayListExtra("party_to_watch");
        for (int i = 0; i < count1; i++) {
            System.out.println("fullname");
            System.out.println(fullname);
            System.out.println("party");
            System.out.println(party);
            infoWear.add(new InfoWear(fullname.get(i), party.get(i)));
        }
    }

    private void populateViewList() {
        ArrayAdapter<InfoWear> adapterSen = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView_wear1);
        list.setAdapter(adapterSen);
    }

    private class MyListAdapter extends ArrayAdapter<InfoWear> {
        public MyListAdapter() {
            super(ZipDetails.this, R.layout.temp_watch_list, infoWear);
        }

        @Override
        public View getView(int position, final View convertView, final ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.temp_watch_list, parent, false);
            }

            final InfoWear currentInfo = infoWear.get(position);

            TextView textName = (TextView) itemView.findViewById(R.id.fullname_sen_rep);
            System.out.println("#############");
            System.out.println(currentInfo.getName());
            textName.setText(currentInfo.getName());

            TextView textParty = (TextView) itemView.findViewById(R.id.party_sen_rep);
            if (currentInfo.getParty().equals("D")) {
                Log.d("text_d", currentInfo.getParty());
                textParty.setTextColor(Color.parseColor("#0008ff"));
                textParty.setText("Democrat");
            } else if (currentInfo.getParty().equals("R")) {
                Log.d("text_r", currentInfo.getParty());
                textParty.setTextColor(Color.parseColor("#ff0000"));
                textParty.setText("Republic");
            }

            return itemView;
        }
    }
}