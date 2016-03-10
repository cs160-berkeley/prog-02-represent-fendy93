package com.android.candid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import javax.xml.transform.Result;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "3RIk5zEiyHyvGCKHIciNuxigb";
    private static final String TWITTER_SECRET = "y8O5VoqGpGz4bSsCDzpyYrtn3wWqQe1DAFSToCdqiGuy9PNlr8";


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "VvswvxS58tccAJw25R3ksGDvO";
//    private static final String TWITTER_SECRET = "51Xw7IruD3YXdddZTzPcYpORbv5sMH8UNor1MO737KOVV4URXr";


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "VvswvxS58tccAJw25R3ksGDvO";
//    private static final String TWITTER_SECRET = "51Xw7IruD3YXdddZTzPcYpORbv5sMH8UNor1MO737KOVV4URXr";


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "3RIk5zEiyHyvGCKHIciNuxigb";
//    private static final String TWITTER_SECRET = "y8O5VoqGpGz4bSsCDzpyYrtn3wWqQe1DAFSToCdqiGuy9PNlr8";


    Button zip_button;
    Button current_button;
    public final static String EXTRA_MESSAGE = "com.android.candid.MESSAGE";
    private GoogleApiClient mGoogleApiClient;
    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

//        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
//        loginButton.setCallback(new Callback<TwitterSession>() {
//            @Override
//            public void success(com.twitter.sdk.android.core.Result<TwitterSession> result) {
//
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                // Do something on failure
//            }
//        });

        zip_button = (Button) findViewById(R.id.zipcode_button);
        current_button = (Button) findViewById(R.id.current_location_button);

        zip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.zipcode);
                if (editText.getText().length() == 0 || Integer.valueOf(editText.getText().toString()) != 76768){
                    editText.setError("Please enter ZIP Code: 76768");
                } else {
                    Intent intent = new Intent(MainActivity.this, MainToZip.class);
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    String message = editText.getText().toString();
                    intent.putExtra(EXTRA_MESSAGE, message);
                    sendIntent.putExtra("Location", "ZIP");
                    startService(sendIntent);
                    startActivity(intent);
                }
            }
        });

        current_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                Intent intent = new Intent(MainActivity.this, MainToCurrent.class);
                sendIntent.putExtra("Location", "Current");
//                startService(sendIntent);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result to the login button.
//        loginButton.onActivityResult(requestCode, resultCode, data);
//    }



    private void ZipButton(){
        startActivity(new Intent("com.android.candid.MainToZip"));
    }

    private void CurrentButton(){
        startActivity(new Intent("com.android.candid.MainToCurrent"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zipcode_button:
                ZipButton();
                break;
            case R.id.current_location_button:
                CurrentButton();
                break;
        }
    }

//    public void sendMessage(View view) {
//
//    }

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
