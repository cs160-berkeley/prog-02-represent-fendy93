package com.android.candid;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double curLongitude;
    private double curLatitude;
    private JSONArray jarray;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
            // TODO Auto-generated method stub
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc));
            if (mMap != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            }
            System.out.println("*************");
            System.out.println("latitude is " + location.getLatitude() + " longtitude is " + location.getLongitude());
            curLongitude = location.getLongitude();
            curLatitude = location.getLatitude();
            // System.out.println("Get county " + getCounty());
            System.out.println("insite the county");

//            Geocoder gcd = new Geocoder(getApplicationContext());
//            try {
//                List<Address> addresses = gcd.getFromLocation(curLatitude, curLongitude, 1);
//                getVote(addresses.get(0).getAdminArea());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////              String helloworld = "http://congress.api.sunlightfoundation.com/legislators/locate?latitude=37.8715916&longitude=-122.2727466&apikey=e8bcea73983e4f2196d97f668a85dbde";
//            String helloworld1 = "http://congress.api.sunlightfoundation.com/legislators/locate?zip=94703&apikey=e8bcea73983e4f2196d97f668a85dbde";
////            SunLightHTTP k3 = new SunLightHTTP();
//            System.out.println("Hellol");
////            result = k3.execute("abc").toString();
////                String result2 = k3.execute(helloworld1).toString();
//            System.out.println(result);
//            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%");
////                System.out.println(result2);
//            System.out.println("After PARSING JSON RESULT!!!!!!!!");
            }
        });

    }

    public void getVote(String county) {
        String json = null;


        try {
            Context context = getApplicationContext();
            InputStream stream = context.getAssets().open("election-county-2012.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
            System.out.println("read in file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jarray = new JSONArray(json);
            System.out.println("created json array successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject j = (JSONObject) jarray.get(i);
                if (j.get("county-name").equals(county)) {
                    System.out.println("found it! obama-percentage" + j.get("obama-percentage") + " romney-percentage " + j.get("romney-percentage"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,String> parse(JSONObject json , Map<String,String> out) throws JSONException{
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            String val = null;
            try{
                JSONObject value = json.getJSONObject(key);
                parse(value,out);
            }catch(Exception e){
                val = json.getString(key);
            }

            if(val != null){
                out.put(key,val);
            }
        }
        return out;
    }

}
