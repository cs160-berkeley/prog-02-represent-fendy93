package com.android.candid;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String CURR = "/Current";
    private static final String ZP = "/ZIP";
    private static final String CURR_COUNT = "/count";
    public String count;
    public String fullname;
    public String party;
    public ArrayList<String> temp_full = new ArrayList<String>();
    public ArrayList<String> temp_party = new ArrayList<String>();

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)
        System.out.println(messageEvent.getPath());
        if (messageEvent.getPath().equalsIgnoreCase(CURR_COUNT)) {
            count = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Log.d("count to watch", count);

        }

        for (int i = 0; i < Integer.parseInt(count); i++) {
            if (messageEvent.getPath().equalsIgnoreCase("fullName" + i)) {
                fullname = new String(messageEvent.getData(), StandardCharsets.UTF_8);
                Log.d("fullname to watch", fullname);
                temp_full.add(fullname);
                Log.d("added", fullname);
            } else if (messageEvent.getPath().equalsIgnoreCase("party" + i)) {
                party = new String(messageEvent.getData(), StandardCharsets.UTF_8);
                Log.d("party to watch", party);
                temp_party.add(party);
                Log.d("added", party);
            }
        }

        if (messageEvent.getPath().equalsIgnoreCase(ZP)) {
            Intent intent = new Intent(WatchListenerService.this, ZipDetails.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            if (messageEvent.getPath().equalsIgnoreCase( CURR_COUNT )) {
//                String count = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//                Log.d("count to watch", count);
//                intent.putExtra("count_to_watch", count);
//                for (int i =0; i < Integer.parseInt(count); i++) {
//                    if (messageEvent.getPath().equalsIgnoreCase( "fullName"+i )) {
//                        String fullname = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//                        Log.d("fullname to watch", fullname);
//                        intent.putExtra("full_name_to_watch" + i, fullname);
//                    }
//                    if (messageEvent.getPath().equalsIgnoreCase( "party"+i )) {
//                        String party = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//                        Log.d("party to watch", party);
//                        intent.putExtra("party_to_watch" + i, party);
//                    }
//                }
            //you need to add this flag since you're starting a new activity from a service
//                intent.putExtra("Location", "Current");
            intent.putExtra("count_to_watch", Integer.parseInt(count));
            Log.d("count to watch final", count);
            for (int i = 0; i < Integer.parseInt(count); i++) {
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("fullname to watch final", String.valueOf(temp_full));
                intent.putExtra("full_name_to_watch", temp_full);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("party to watch final", String.valueOf(temp_party));
                intent.putExtra("party_to_watch", temp_party);
            }
            Log.d("T", "about to start watch MainActivity with CAT_NAME: Lexy");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase(CURR)) {
            Intent intent = new Intent(WatchListenerService.this, CurrDetails.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            if (messageEvent.getPath().equalsIgnoreCase( CURR_COUNT )) {
//                String count = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//                Log.d("count to watch", count);
//                intent.putExtra("count_to_watch", count);
//                for (int i =0; i < Integer.parseInt(count); i++) {
//                    if (messageEvent.getPath().equalsIgnoreCase( "fullName"+i )) {
//                        String fullname = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//                        Log.d("fullname to watch", fullname);
//                        intent.putExtra("full_name_to_watch" + i, fullname);
//                    }
//                    if (messageEvent.getPath().equalsIgnoreCase( "party"+i )) {
//                        String party = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//                        Log.d("party to watch", party);
//                        intent.putExtra("party_to_watch" + i, party);
//                    }
//                }
            //you need to add this flag since you're starting a new activity from a service
//                intent.putExtra("Location", "Current");
            intent.putExtra("count_to_watch", Integer.parseInt(count));
            Log.d("count to watch final", count);
            for (int i = 0; i < Integer.parseInt(count); i++) {
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("fullname to watch final", String.valueOf(temp_full));
                intent.putExtra("full_name_to_watch", temp_full);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("party to watch final", String.valueOf(temp_party));
                intent.putExtra("party_to_watch", temp_party);
            }
            Log.d("T", "about to start watch MainActivity with CAT_NAME: Lexy");
            startActivity(intent);
        } else {
            super.onMessageReceived(messageEvent);
        }

    }
}