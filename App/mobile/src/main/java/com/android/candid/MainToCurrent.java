package com.android.candid;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.fabric.sdk.android.Fabric;

public class MainToCurrent extends AppCompatActivity {
    private static final String TWITTER_KEY = "3RIk5zEiyHyvGCKHIciNuxigb";
    private static final String TWITTER_SECRET = "y8O5VoqGpGz4bSsCDzpyYrtn3wWqQe1DAFSToCdqiGuy9PNlr8";

    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    public final static String EXTRA_MESSAGE_2 = "com.android.candid.MESSAGE2";
    private JSONArray jarray;
    private JSONArray jarray1;
    private List<Info> info_sen = new ArrayList<Info>();
    private List<Info> info_rep = new ArrayList<Info>();
    private String img_sen_url;
    private String lat;
    private String lng;
    public ArrayList<String> activeComittee = new ArrayList<String>();
    public ArrayList<String> recentBills = new ArrayList<String>();
    public ArrayList<String> tempac = new ArrayList<String>();
    public ArrayList<String> tempbc = new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public List<Long> tweetIds;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TweetViewFetchAdapter adapter =
                new TweetViewFetchAdapter<CompactTweetView>(MainToCurrent.this);
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.current_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TabHost tabhost = (TabHost) findViewById(R.id.tabHost_curr);

        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("senator");
        tabSpec.setContent(R.id.tab_senator_curr);
        tabSpec.setIndicator("Senator");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("rep");
        tabSpec.setContent(R.id.tab_rep_curr);
        tabSpec.setIndicator("Representative");
        tabhost.addTab(tabSpec);

        populateInfoList();
        populateViewList();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void populateInfoList() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Intent intent1 = getIntent();



        int count1 = intent1.getIntExtra("count1", 0);
        Log.d("count_main", String.valueOf(count1));
        for (int i = 0; i < count1; i++) {
            String fullname = intent1.getStringExtra("full_name" + i);
            String party = intent1.getStringExtra("party" + i);
            String title = intent1.getStringExtra("title" + i);
            String twitter = intent1.getStringExtra("twitter" + i);
            String termEnd = intent1.getStringExtra("termEnd" + i);
            String website = intent1.getStringExtra("website" + i);
            String email = intent1.getStringExtra("email" + i);
            ArrayList<Integer> count = intent1.getIntegerArrayListExtra("countComm" + i);
            Log.d("countComm", String.valueOf(count));
            activeComittee = new ArrayList<String>();
            for (int k = 0; k < count.get(i); k++) {
                ArrayList<Integer> count2 = intent1.getIntegerArrayListExtra("countBill" + i + k);
                activeComittee.add("- " + intent1.getStringExtra("committeeName" + i + k) + "\n");
                Log.d("count2", String.valueOf(count2));
                recentBills = new ArrayList<String>();
                for (int j = 0; j < count2.get(k); j++) {
                    String filtered3 = intent1.getStringExtra("dateIntroduced" + i + k + j);
                    recentBills.add("- " + intent1.getStringExtra("dateIntroduced" + i + k + j) + ": " + intent1.getStringExtra("officialTitle" + i + k + j) + "\n");
                }
            }
            Log.d("ac_print", String.valueOf(activeComittee));
            if (title.equals("Sen")) {
                info_sen.add(new Info(fullname, party, website, email, "More Info", twitter, termEnd, tempac, recentBills));

            } else {
                info_rep.add(new Info(fullname, party, website, email, "More Info", twitter, termEnd, tempbc, recentBills));
            }

        }
    }

    private void populateViewList() {
        ArrayAdapter<Info> adapterSen = new MyListAdapterSen();
        ArrayAdapter<Info> adapterRep = new MyListAdapterRep();
        ListView list = (ListView) findViewById(R.id.listView_sen);
        ListView list1 = (ListView) findViewById(R.id.listView_rep);
        list.setAdapter(adapterSen);
        list1.setAdapter(adapterRep);
    }

    private class MyListAdapterSen extends ArrayAdapter<Info> {
        public MyListAdapterSen() {
            super(MainToCurrent.this, R.layout.temp_sen_rep, info_sen);
        }

        @Override
        public View getView(int position, final View convertView, final ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.temp_sen_rep, parent, false);
            }

            final Info currentInfo = info_sen.get(position);

            TextView textName = (TextView) itemView.findViewById(R.id.name_rep_sen);
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

            ImageView imageWeb = (ImageView) itemView.findViewById(R.id.imageView_web_rep_sen);
            imageWeb.setImageResource(R.mipmap.com_logo);
            imageWeb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(currentInfo.getWeb()));
                    startActivity(intent);
                }
            });

            ImageView imageMail = (ImageView) itemView.findViewById(R.id.imageView_mail_rep_sen);
            imageMail.setImageResource(R.mipmap.mails_logo);
            imageMail.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + currentInfo.getEmail()));
                    startActivity(intent);
                }
            });

            TextView textMore = (TextView) itemView.findViewById(R.id.more_rep_sen);
            textMore.setText(currentInfo.getmoreInfo());

//            TextView textTweet = (TextView) itemView.findViewById(R.id.textView_tweet);
//            textTweet.setText(currentInfo.getTweet());

            textMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                String bitmap = img_sen_url;
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                intent.putExtra("fullname", currentInfo.getName());

                intent.putExtra("party", currentInfo.getParty());

                intent.putExtra("enddate", currentInfo.getEndDate());

                intent.putExtra("latCurr", lat);
                intent.putExtra("lngCurr", lng);

                intent.putExtra("activeComittee", activeComittee);
                intent.putExtra("recentBills", recentBills);

                startActivity(intent);
                }
            });


            final TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
            // Can also use Twitter directly: Twitter.getApiClient()
            final com.twitter.sdk.android.core.services.StatusesService statusesService = twitterApiClient.getStatusesService();
            System.out.println("11111111111");

            final View finalItemView = itemView;
            String screen_name = currentInfo.getTweet();
            System.out.println(screen_name);
            final View finalItemView1 = itemView;
            statusesService.userTimeline(null, screen_name, null, null, null, null, null, null, null, new Callback<List<Tweet>>() {
                @Override
                public void success(Result<List<Tweet>> result) {
                    Tweet t = result.data.get(0);
//                    Log.d("entitites", String.valueOf(t.entities.media.get(0).mediaUrl));
                    Log.d("last tweet", String.valueOf(t.text));
                    Log.d("imgURL", String.valueOf(t.user.profileImageUrl));
                    Log.d("imgURLHTTPS", String.valueOf(t.user.profileImageUrlHttps).replace("_normal", "_bigger"));

//                    Log.d("media", String.valueOf(t.entities.media));
//                    Log.d("get(0)", String.valueOf(t.entities.media.get(0)));
//                    Log.d("mediaURL", String.valueOf(t.entities.media.get(0).mediaUrl));
//                    Log.d("TmediaURL", String.valueOf(t.mediaUrl));
                    System.out.println(t.idStr);
                    System.out.println("SUCCESS");
                    tweetIds = Arrays.asList(Long.parseLong(t.idStr));
//                    tweetIds.add(Long.parseLong("503435417459249153"));
//                    final TextView myLayout
//                            = (TextView) finalItemView.findViewById(R.id.tweet_text);
                    final TextView myLayout1
                            = (TextView) finalItemView1.findViewById(R.id.tweet_id_text);
                    System.out.println("SUCESSSSSSSSSSS");
                    System.out.println(tweetIds);
                    img_sen_url = t.user.profileImageUrl;
                    ImageButton imageButton_sen = (ImageButton) finalItemView1.findViewById(R.id.imageButton_rep_sen);
                    new UrlToImage(imageButton_sen) // image id
                            .execute(img_sen_url);
                    myLayout1.setText("@" + currentInfo.getTweet() + ": " + t.text);
                }

                public void failure(TwitterException exception) {
                    //Do something on failure
                    System.out.println(exception.getCause());
                    System.out.println(exception.getLocalizedMessage());

                    System.out.println("+++++++++++++++++++++++++++++++");
                }
            });

            ImageButton imageButton_sen_button = (ImageButton) itemView.findViewById(R.id.imageButton_rep_sen);
            imageButton_sen_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String bitmap = img_sen_url;
                    Intent intent = new Intent("com.android.candid.CurrentToDetail");
                    intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                    intent.putExtra(EXTRA_MESSAGE, bitmap);

                    TextView editText = (TextView) findViewById(R.id.name_rep_sen);
                    intent.putExtra("fullname", currentInfo.getName());

                    TextView editText1 = (TextView) findViewById(R.id.party_sen_rep);
                    intent.putExtra("party", currentInfo.getParty());

                    String endDate = currentInfo.getEndDate();
                    intent.putExtra("enddate", currentInfo.getEndDate());

                    lat = getIntent().getStringExtra("latMain123");
                    lng = getIntent().getStringExtra("longMain123");

                    System.out.println("^^^^^^^^^^^^^");
                    System.out.println(lat);
                    System.out.println(lng);
                    System.out.println("^^^^^^^^^^^^^");

                    intent.putExtra("latCurr", lat);
                    intent.putExtra("lngCurr", lng);

                    intent.putExtra("activeComittee", activeComittee);
                    intent.putExtra("recentBills", recentBills);

                    startActivity(intent);
                }
            });

        return itemView;
    }
}

private class MyListAdapterRep extends ArrayAdapter<Info> {
    public MyListAdapterRep() {
        super(MainToCurrent.this, R.layout.temp_sen_rep, info_rep);
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = getLayoutInflater().inflate(R.layout.temp_sen_rep, parent, false);
        }

        final Info currentInfo = info_rep.get(position);

        TextView textName = (TextView) itemView.findViewById(R.id.name_rep_sen);
        textName.setText(currentInfo.getName());

        TextView textParty = (TextView) itemView.findViewById(R.id.party_sen_rep);
        if (currentInfo.getParty().equals("D")) {
            textParty.setTextColor(Color.parseColor("#0008ff"));
            textParty.setText("Democrat");
        } else if (currentInfo.getParty().equals("R")) {
            textParty.setTextColor(Color.parseColor("#ff0000"));
            textParty.setText("Republic");
        }

        ImageView imageWeb = (ImageView) itemView.findViewById(R.id.imageView_web_rep_sen);
        imageWeb.setImageResource(R.mipmap.com_logo);
        imageWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(currentInfo.getWeb()));
                startActivity(intent);
            }
        });

        ImageView imageMail = (ImageView) itemView.findViewById(R.id.imageView_mail_rep_sen);
        imageMail.setImageResource(R.mipmap.mails_logo);
        imageMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + currentInfo.getEmail()));
                startActivity(intent);
            }
        });

        TextView textMore = (TextView) itemView.findViewById(R.id.more_rep_sen);
        textMore.setText(currentInfo.getmoreInfo());

//            TextView textTweet = (TextView) itemView.findViewById(R.id.textView_tweet);
//            textTweet.setText(currentInfo.getTweet());

        textMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bitmap = img_sen_url;
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                intent.putExtra("fullname", currentInfo.getName());

                intent.putExtra("party", currentInfo.getParty());

                intent.putExtra("enddate", currentInfo.getEndDate());

                intent.putExtra("latCurr", lat);
                intent.putExtra("lngCurr", lng);

                intent.putExtra("activeComittee", activeComittee);
                intent.putExtra("recentBills", recentBills);

                startActivity(intent);
            }
        });

        ImageButton imageButton_rep_button = (ImageButton) itemView.findViewById(R.id.imageButton_rep_sen);
        imageButton_rep_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bitmap = img_sen_url;
                Intent intent = new Intent("com.android.candid.CurrentToDetail");
                intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                intent.putExtra(EXTRA_MESSAGE, bitmap);

                intent.putExtra("fullname", currentInfo.getName());

                intent.putExtra("party", currentInfo.getParty());

                intent.putExtra("enddate", currentInfo.getEndDate());

                intent.putExtra("latCurr", lat);
                intent.putExtra("lngCurr", lng);

                intent.putExtra("activeComittee", activeComittee);
                intent.putExtra("recentBills", recentBills);

                startActivity(intent);
            }
        });

        final TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        // Can also use Twitter directly: Twitter.getApiClient()
        final com.twitter.sdk.android.core.services.StatusesService statusesService = twitterApiClient.getStatusesService();
        System.out.println("11111111111");

        final View finalItemView = itemView;
        statusesService.userTimeline(null, currentInfo.getTweet(), null, null, null, null, null, null, null, new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                Tweet t = result.data.get(0);
//                    Log.d("entitites", String.valueOf(t.entities.media.get(0).mediaUrl));
                Log.d("last tweet", String.valueOf(t.text));
                Log.d("imgURL", String.valueOf(t.user.profileImageUrl));
                Log.d("imgURLHTTPS", String.valueOf(t.user.profileImageUrlHttps).replace("_normal", "_bigger"));
//                    Log.d("media", String.valueOf(t.entities.media));
//                    Log.d("get(0)", String.valueOf(t.entities.media.get(0)));
//                    Log.d("mediaURL", String.valueOf(t.entities.media.get(0).mediaUrl));
//                    Log.d("TmediaURL", String.valueOf(t.mediaUrl));
                System.out.println(t.idStr);
                System.out.println("SUCCESS");
                tweetIds = Arrays.asList(Long.parseLong(t.idStr));
//                    tweetIds.add(Long.parseLong("503435417459249153"));
                final TextView myLayout1
                        = (TextView) finalItemView.findViewById(R.id.tweet_id_text);
                System.out.println("SUCESSSSSSSSSSS");
                System.out.println(tweetIds);
                img_sen_url = t.user.profileImageUrl;
                ImageButton imageButton_rep = (ImageButton) finalItemView.findViewById(R.id.imageButton_rep_sen);
                new UrlToImage(imageButton_rep) // image id
                        .execute(img_sen_url);
                myLayout1.setText("@" + currentInfo.getTweet() + ": " + t.text);
            }

            @Override
            public void failure(TwitterException exception) {
                // Toast.makeText(...).show();
                System.out.println(exception);
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            }
        });

        return itemView;
    }
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
                Intent intent = new Intent(MainToCurrent.this, MainActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
