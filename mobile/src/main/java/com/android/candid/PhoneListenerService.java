package com.android.candid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
//    private static final String TOAST = "/send_toast";
    private static final String IMG = "/image";
    private static final String FN = "/fullname";
    private static final String P = "/party";
    private static final String DONE = "done";
    public String image;
    public String party;
    public String fullname;


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
//        if( messageEvent.getPath().equalsIgnoreCase( IMG ) ) {
//            image = (Bitmap) StringToBitMap(new String(messageEvent.getData(), StandardCharsets.UTF_8));
        if (messageEvent.getPath().equalsIgnoreCase( P )) {
            party = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//        } else if (messageEvent.getPath().equalsIgnoreCase( IMG )) {
//            image = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        } else if (messageEvent.getPath().equalsIgnoreCase( FN )) {
            fullname = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        } else if (messageEvent.getPath().equalsIgnoreCase( DONE )) {
            Intent intent = new Intent(this, CurrentToDetail.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
//            intent.putExtra(MainActivity.EXTRA_MESSAGE, image);
//            intent.putExtra(MainActivity.EXTRA_MESSAGE, StringToBitMap(image));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bmikulski);
            intent.putExtra(MainActivity.EXTRA_MESSAGE, bitmap);
            intent.putExtra("fullname", fullname);
            intent.putExtra("party", party);
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

//        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
//        if( messageEvent.getPath().equalsIgnoreCase(TOAST) ) {
//
//            // Value contains the String we sent over in WatchToPhoneService, "good job"
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            Intent intent = new Intent(PhoneListenerService.this, CurrentToDetail.class );
//            System.out.println("******************************");
//            System.out.println("success1");
//            System.out.println("******************************");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            //you need to add this flag since you're starting a new activity from a service
//            intent.putExtra("Location", "ZIP");
//            Log.d("T", "about to start watch MainActivity with CAT_NAME: Fred");
//            startActivity(intent);

//            // Make a toast with the String
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, value, duration);
//            toast.show();

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions

//        } else {
//            super.onMessageReceived( messageEvent );
//        }

    }
//    public Bitmap StringToBitMap(String encodedString){
//        try {
//            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
//            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            return bitmap;
//        } catch(Exception e) {
//            e.getMessage();
//            return null;
//        }
//    }
}
