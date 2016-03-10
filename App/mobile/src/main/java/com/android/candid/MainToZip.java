package com.android.candid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainToZip extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    private List<Info> infos = new ArrayList<Info>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zcode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String zip_num = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String zip_watch = intent.getStringExtra("zip");
        TextView textView = (TextView) findViewById(R.id.zipNum);
        textView.setTextSize(19);
        if (zip_num != null) {
            textView.setText(zip_num);
        } else {
            if (zip_watch.equals("0")) {
                textView.setText("76768");
            } else if (zip_watch.equals("1")) {
                textView.setText("94703");
            } else if (zip_watch.equals("2")) {
                textView.setText("55233");
            } else if (zip_watch.equals("3")) {
                textView.setText("99999");
            }
        }

        TabHost tabhost = (TabHost) findViewById(R.id.tabHost_curr);

        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("senator");
        tabSpec.setContent(R.id.tab_senator);
        tabSpec.setIndicator("Senator");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("rep");
        tabSpec.setContent(R.id.tab_rep);
        tabSpec.setIndicator("Representative");
        tabhost.addTab(tabSpec);

        populateInfoList();
        populateViewList();
    }

    private void populateInfoList() {
//        for(int i = 0; i < 5; i++){
//            infos.add(new Info("Barbara Mikulski", "Democrat", R.mipmap.bmikulski, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info"));
//        }
        ArrayList<String> activeComittee = new ArrayList<String>();
        activeComittee.add("h");
        activeComittee.add("i");
        activeComittee.add("j");

        ArrayList<String> recentBills = new ArrayList<String>();
        activeComittee.add("k");
        activeComittee.add("l");
        activeComittee.add("m");

        infos.add(new Info("Barbara Boxer", "Democrat", R.mipmap.blee, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info", "@blee: Happy Birthday!", "January 10, 2026", activeComittee, recentBills));
    }

    private void populateViewList() {
        ArrayAdapter<Info> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView_zip_rep);
        ListView list1 = (ListView) findViewById(R.id.listView_zip_sen);
        list.setAdapter(adapter);
        list1.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Info> {
        public MyListAdapter() {
            super(MainToZip.this, R.layout.temp_sen_rep, infos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.temp_sen_rep, parent, false);
            }

            final Info currentInfo = infos.get(position);

            ImageButton imageButton = (ImageButton)itemView.findViewById(R.id.imageButton_rep_sen);
            imageButton.setImageResource(currentInfo.getImg());

            TextView textName = (TextView)itemView.findViewById(R.id.name_rep_sen);
            textName.setText(currentInfo.getName());

            TextView textParty = (TextView)itemView.findViewById(R.id.party_sen_rep);
            if (currentInfo.getParty() == "Democrat") {
                textParty.setTextColor(Color.parseColor("#0008ff"));
            } else if(currentInfo.getParty() == "Republic") {
                textParty.setTextColor(Color.parseColor("#ff0000"));
            }

            textParty.setText(currentInfo.getParty());

            ImageView imageWeb = (ImageView)itemView.findViewById(R.id.imageView_web_rep_sen);
            imageWeb.setImageResource(currentInfo.getWeb());
            imageWeb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.mikulski.senate.gov/"));
                    startActivity(intent);
                }
            });

            ImageView imageMail = (ImageView)itemView.findViewById(R.id.imageView_mail_rep_sen);
            imageMail.setImageResource(currentInfo.getEmail());
            imageMail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.mikulski.senate.gov/"));
                    startActivity(intent);
                }
            });

            TextView textMore = (TextView)itemView.findViewById(R.id.more_rep_sen);
            textMore.setText(currentInfo.getmoreInfo());

//            TextView textTweet = (TextView)itemView.findViewById(R.id.textView_tweet);
//            textTweet.setText(currentInfo.getTweet());

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), currentInfo.getImg());
                    Intent intent = new Intent("com.android.candid.CurrentToDetail");
                    intent.setClass(MainToZip.this, CurrentToDetail.class);
                    intent.putExtra(EXTRA_MESSAGE, bitmap);

                    TextView editText = (TextView) findViewById(R.id.name_rep_sen);
                    String message = editText.getText().toString();
                    intent.putExtra("fullname", currentInfo.getName());

                    TextView editText1 = (TextView) findViewById(R.id.party_sen_rep);
                    String message1 = editText1.getText().toString();
                    intent.putExtra("party", currentInfo.getParty());

                    String endDate = currentInfo.getEndDate();
                    intent.putExtra("enddate", currentInfo.getEndDate());

                    startActivity(intent);
                }
            });

            textMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), currentInfo.getImg());
                    Intent intent = new Intent("com.android.candid.CurrentToDetail");
                    intent.setClass(MainToZip.this, CurrentToDetail.class);
                    intent.putExtra(EXTRA_MESSAGE, bitmap);

                    TextView editText = (TextView) findViewById(R.id.name_rep_sen);
                    String message = editText.getText().toString();
                    intent.putExtra("fullname", currentInfo.getName());

                    TextView editText1 = (TextView) findViewById(R.id.party_sen_rep);
                    String message1 = editText1.getText().toString();
                    intent.putExtra("party", currentInfo.getParty());

                    String endDate = currentInfo.getEndDate();
                    intent.putExtra("enddate", currentInfo.getEndDate());

                    startActivity(intent);
                }
            });


            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
