package com.android.candid;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainToCurrent extends AppCompatActivity {
    private static final String TWITTER_KEY = "3RIk5zEiyHyvGCKHIciNuxigb";
    private static final String TWITTER_SECRET = "y8O5VoqGpGz4bSsCDzpyYrtn3wWqQe1DAFSToCdqiGuy9PNlr8";
//    private static final String TWITTER_KEY = "VvswvxS58tccAJw25R3ksGDvO";
//    private static final String TWITTER_SECRET = "51Xw7IruD3YXdddZTzPcYpORbv5sMH8UNor1MO737KOVV4URXr";

    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    public final static String EXTRA_MESSAGE_2 = "com.android.candid.MESSAGE2";
    private JSONArray jarray;
    private JSONArray jarray1;
    private List<Info> infos = new ArrayList<Info>();
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
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.current_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        System.out.println("2222222222222");

//        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
//        // Can also use Twitter directly: Twitter.getApiClient()
//        com.twitter.sdk.android.core.services.StatusesService statusesService = twitterApiClient.getStatusesService();
//        System.out.println("11111111111");

//        statusesService.userTimeline(null, "SenatorBoxer", null, null, null, null, null, null, null, new Callback<List<Tweet>>() {
//        @Override
//        public void success(Result<List<Tweet>> result) {
//            Tweet t = result.data.get(0);
//            List<Tweet> tw = result.data;
//            TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(tw));
//            System.out.println(t.idStr);
//            System.out.println("SUCCESS");
//            tweetIds = Arrays.asList(Long.parseLong(t.idStr));
////            final LinearLayout myLayout
////                    = (LinearLayout) findViewById(R.id.twitter);
//            System.out.println("SUCESSSSSSSSSSS");
//            System.out.println(tweetIds);
////            final ListView listView = (ListView) findViewById(R.id.list);
////            listView.setAdapter(adapter);
////            adapter.setTweetIds(tweetIds, new Callback<List<Tweet>>() {
////                @Override
////                public void success(com.twitter.sdk.android.core.Result<List<Tweet>> result) {
////                    listView.addView(new);
////                }
////
////                @Override
////                public void failure(TwitterException exception) {
////                    // Toast.makeText(...).show();
////                }
////            });
////
//        }
//
//        public void failure(TwitterException exception) {
//            //Do something on failure
//            System.out.println(exception);
//        }
//        });







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
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void populateInfoList() {
//        for(int i = 0; i < 5; i++){
//            infos.add(new Info("Barbara Mikulski", "Democrat", R.mipmap.bmikulski, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info"));
//        }
        ArrayList<String> activeComittee = new ArrayList<String>();
        activeComittee.add("a");
        activeComittee.add("b");
        activeComittee.add("c");

        ArrayList<String> recentBills = new ArrayList<String>();
        activeComittee.add("d");
        activeComittee.add("e");
        activeComittee.add("f");

        ArrayList<String> activeComittee1 = new ArrayList<String>();
        activeComittee1.add("L");
        activeComittee1.add("M");
        activeComittee1.add("K");

        infos.add(new Info("Barbara Boxe", "Democrat", R.mipmap.blee, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info", "", "January 1, 2026", activeComittee, recentBills));
        infos.add(new Info("Mikulski", "Democrat", R.mipmap.bmikulski, R.mipmap.com_logo, R.mipmap.mails_logo, "More Info", "@blee: Happy Birthday!", "January 1, 2010", activeComittee1, recentBills));
    }

    private void populateViewList() {
        ArrayAdapter<Info> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView_sen);
        ListView list1 = (ListView) findViewById(R.id.listView_rep);
        list.setAdapter(adapter);
        list1.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainToCurrent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.android.candid/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainToCurrent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.android.candid/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "MainToCurrent Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.android.candid/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "MainToCurrent Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.android.candid/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

    private class MyListAdapter extends ArrayAdapter<Info> {
        public MyListAdapter() {
            super(MainToCurrent.this, R.layout.temp_sen_rep, infos);
        }

        @Override
        public View getView(int position, final View convertView, final ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.temp_sen_rep, parent, false);
            }

            final Info currentInfo = infos.get(position);

            ImageButton imageButton = (ImageButton) itemView.findViewById(R.id.imageButton_rep_sen);
            imageButton.setImageResource(currentInfo.getImg());

            TextView textName = (TextView) itemView.findViewById(R.id.name_rep_sen);
            textName.setText(currentInfo.getName());

            TextView textParty = (TextView) itemView.findViewById(R.id.party_sen_rep);
            if (currentInfo.getParty() == "Democrat") {
                textParty.setTextColor(Color.parseColor("#0008ff"));
            } else if (currentInfo.getParty() == "Republic") {
                textParty.setTextColor(Color.parseColor("#ff0000"));
            }

            textParty.setText(currentInfo.getParty());

            ImageView imageWeb = (ImageView) itemView.findViewById(R.id.imageView_web_rep_sen);
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

            ImageView imageMail = (ImageView) itemView.findViewById(R.id.imageView_mail_rep_sen);
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

            TextView textMore = (TextView) itemView.findViewById(R.id.more_rep_sen);
            textMore.setText(currentInfo.getmoreInfo());

//            TextView textTweet = (TextView) itemView.findViewById(R.id.textView_tweet);
//            textTweet.setText(currentInfo.getTweet());

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), currentInfo.getImg());
                    Intent intent = new Intent("com.android.candid.CurrentToDetail");
                    intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                    intent.putExtra(EXTRA_MESSAGE, bitmap);

                    TextView editText = (TextView) findViewById(R.id.name_rep_sen);
                    String message = editText.getText().toString();
                    intent.putExtra("fullname", message);

                    TextView editText1 = (TextView) findViewById(R.id.party_sen_rep);
                    String message1 = editText1.getText().toString();
                    intent.putExtra("party", message1);

                    String endDate = currentInfo.getEndDate();
                    intent.putExtra("enddate", endDate);

                    startActivity(intent);
                }
            });

            textMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), currentInfo.getImg());
                    Intent intent = new Intent("com.android.candid.CurrentToDetail");
                    intent.setClass(MainToCurrent.this, CurrentToDetail.class);
                    intent.putExtra(EXTRA_MESSAGE, bitmap);

                    TextView editText = (TextView) findViewById(R.id.name_rep_sen);
                    String message = editText.getText().toString();
                    intent.putExtra("fullname", message);

                    TextView editText1 = (TextView) findViewById(R.id.party_sen_rep);
                    String message1 = editText1.getText().toString();
                    intent.putExtra("party", message1);

                    String endDate = currentInfo.getEndDate();
                    intent.putExtra("enddate", endDate);

                    startActivity(intent);
                }
            });

            final TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
            // Can also use Twitter directly: Twitter.getApiClient()
            final com.twitter.sdk.android.core.services.StatusesService statusesService = twitterApiClient.getStatusesService();
            System.out.println("11111111111");

            final View finalItemView = itemView;
            statusesService.userTimeline(null, "SenatorBoxer", null, null, null, null, null, null, null, new Callback<List<Tweet>>() {
                @Override
                public void success(Result<List<Tweet>> result) {
                    Tweet t = result.data.get(0);
//                    Log.d("entitites", String.valueOf(t.entities.media.get(0).mediaUrl));
                    Log.d("last tweet", String.valueOf(t.text));
                    Log.d("imgURL", String.valueOf(t.user.profileImageUrl));
                    Log.d("imgURLHTTPS", String.valueOf(t.user.profileImageUrlHttps).replace());
//                    Log.d("media", String.valueOf(t.entities.media));
//                    Log.d("get(0)", String.valueOf(t.entities.media.get(0)));
//                    Log.d("mediaURL", String.valueOf(t.entities.media.get(0).mediaUrl));
//                    Log.d("TmediaURL", String.valueOf(t.mediaUrl));
                    System.out.println(t.idStr);
                    System.out.println("SUCCESS");
                    tweetIds = Arrays.asList(Long.parseLong(t.idStr));
//                    tweetIds.add(Long.parseLong("503435417459249153"));
                    final LinearLayout myLayout
                            = (LinearLayout) finalItemView.findViewById(R.id.twitter);
                    System.out.println("SUCESSSSSSSSSSS");
                    System.out.println(tweetIds);
                    TweetUtils.loadTweets(tweetIds, new Callback<List<Tweet>>() {
                        @Override
                        public void success(Result<List<Tweet>> result) {
                            for (Tweet tweet : result.data) {
                                System.out.println("SUCESSSSSSSSSSS");
                                myLayout.addView(new TweetView(MainToCurrent.this, tweet));
                            }
                            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        }

                        @Override
                        public void failure(TwitterException exception) {
                            // Toast.makeText(...).show();
                            System.out.println(exception);
                            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                        }
                    });
                    final com.twitter.sdk.android.core.services.StatusesService statusesService = twitterApiClient.getStatusesService();


                }

                public void failure(TwitterException exception) {
                    //Do something on failure
                    System.out.println(exception.getCause());
                    System.out.println(exception.getLocalizedMessage());

                    System.out.println("+++++++++++++++++++++++++++++++");
                }
            });

//            statusesService.show(Long.parseLong(tweetIds), "SenatorBoxer", null, new Callback<List<Tweet>>() {
//                @Override
//                public void success(Result<List<Tweet>> result) {
//
//                }
//
//                @Override
//                public void failure(TwitterException e) {
//
//                }
//            });
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
