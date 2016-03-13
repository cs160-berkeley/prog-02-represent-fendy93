package com.android.candid;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by joleary on 2/19/16.
 */
public class PhoneToWatchService extends Service {

    private GoogleApiClient mApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                    }
                })
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mApiClient.disconnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Which cat do we want to feed? Grab this info from INTENT
        // which was passed over when we called startService
        final Bundle extras = intent.getExtras();
        final int count = extras.getInt("countS");
        final String location = extras.getString("Location");

        // Send the message with the cat name
        new Thread(new Runnable() {
            @Override
            public void run() {
                //first, connect to the apiclient
                mApiClient.connect();
                //now that you're connected, send a massage with the cat name
                System.out.println("count_service");
                System.out.println(count);

                sendMessage("/count", String.valueOf(count));
                try{
                    Thread.sleep(500);
                }
                catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                for (int i = 0; i < count; i++) {
                    final String fullNameWatch = extras.getString("full_name_watch" + i);
                    final String partyWatch = extras.getString("party_watch" + i);
                    System.out.println("******************************");
                    System.out.println(fullNameWatch);
                    System.out.println(partyWatch);
                    System.out.println("******************************");
                    sendMessage("fullName" + i, fullNameWatch);
                    try{
                        Thread.sleep(500);
                    }
                    catch ( java.lang.InterruptedException ie) {
                        System.out.println(ie);
                    }
                    sendMessage("party" + i, partyWatch);
                    try{
                        Thread.sleep(500);
                    }
                    catch ( java.lang.InterruptedException ie) {
                        System.out.println(ie);
                    }
                }

                sendMessage("/Current", location);

            }
        }).start();

        return START_STICKY;
    }

    @Override //remember, all services need to implement an IBiner
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendMessage( final String path, final String text ) {
        //one way to send message: start a new thread and call .await()
        //see watchtophoneservice for another way to send a message
        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    //we find 'nodes', which are nearby bluetooth devices (aka emulators)
                    //send a message for each of these nodes (just one, for an emulator)
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mApiClient, node.getId(), path, text.getBytes() ).await();
                    //4 arguments: api client, the node ID, the path (for the listener to parse),
                    //and the message itself (you need to convert it to bytes.)
                }
            }
        }).start();
    }

}
