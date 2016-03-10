package com.android.candid;

import android.app.ListActivity;
import android.os.Bundle;

import com.android.candid.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

import io.fabric.sdk.android.Fabric;

public class TweetListActivity extends ListActivity {
    private static final String TWITTER_KEY = "VvswvxS58tccAJw25R3ksGDvO";
    private static final String TWITTER_SECRET = "51Xw7IruD3YXdddZTzPcYpORbv5sMH8UNor1MO737KOVV4URXr";

    List<Long> tweetIds = Arrays.asList(503435417459249153L, 510908133917487104L, 473514864153870337L, 477788140900347904L);
    final TweetViewFetchAdapter adapter =
            new TweetViewFetchAdapter<CompactTweetView>(TweetListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.tweet_list);
        setListAdapter(adapter);
        adapter.setTweetIds(tweetIds, new Callback<List<Tweet>>() {
            @Override
            public void success(com.twitter.sdk.android.core.Result<List<Tweet>> result) {

            }

            @Override
            public void failure(TwitterException exception) {
                // Toast.makeText(...).show();
            }
        });
    }
}