package com.android.candid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fendyzhou on 3/8/16.
 */
public class SunLightHTTP extends AsyncTask<Void, Void, Void> {
    private String API_KEY = "778b67a7e69b4f779d949b64602e1d46";
    public String member_id;
    public int count;
    public String forecastJsonStr1;
    public ArrayList<String> committee_id = new ArrayList<String>();
    public ArrayList<Integer> count1 = new ArrayList<Integer>();
    public ArrayList<String> introduced_on = new ArrayList<String>();
    public ArrayList<String> name = new ArrayList<String>();
    public ArrayList<String> official_title = new ArrayList<String>();
    public String lat;
    public String lng;
    public ArrayList<Integer> count0 = new ArrayList<Integer>();
    private String forecastJsonStr0 = null;
    private String forecastJsonStr = null;
    public ArrayList<String> firstName = new ArrayList<String>();
    public ArrayList<String> temp_party = new ArrayList<String>();
    public ArrayList<String> party = new ArrayList<String>();
    public ArrayList<String> lastName = new ArrayList<String>();
    public ArrayList<String> termEnd = new ArrayList<String>();
    public ArrayList<String> title = new ArrayList<String>();
    public ArrayList<String> website = new ArrayList<String>();
    public ArrayList<String> oc_email = new ArrayList<String>();
    public ArrayList<String> tweet_id = new ArrayList<String>();
    public ArrayList<String> bioguide_id = new ArrayList<String>();

    private Context context;

    public SunLightHTTP(Context context){
        this.context=context;
    }


    @Override
    protected void onPreExecute() {
        // write show progress Dialog code here
        super.onPreExecute();
    }

    public void setData(String... params){
        lat = params[0];
        lng = params[1];
    }


    @Override
    protected Void doInBackground(Void... params) {
        final String FORECAST_BASE_URL = "http://congress.api.sunlightfoundation.com/legislators/locate?";
        final String LAT_PARAM = "latitude";
        final String LNG_PARAM = "longitude";
        final String KEY_PARAM = "apikey";
        final String ZIP_PARAM = "zip";

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(LAT_PARAM, lat)
                    .appendQueryParameter(LNG_PARAM, lng)
                    .appendQueryParameter(KEY_PARAM, API_KEY)
                    .build();
            URL url = new URL(builtUri.toString());
            Log.d("url", String.valueOf(url));
//            URL url = new URL("http://congress.api.sunlightfoundation.com/legislators/locate?zip=94709&apikey=778b67a7e69b4f779d949b64602e1d46");

            //baseUrl.concat(apiKey));
            System.out.println("1");
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println("11");
            //urlConnection.setRequestMethod("GET");
            System.out.println(urlConnection);
            urlConnection.connect();

            System.out.println("2");


            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println("3");

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            System.out.println("4");

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                System.out.println("the buffer length is 0");
                return null;
            }

            forecastJsonStr = buffer.toString();
            System.out.println(forecastJsonStr);
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        System.out.println("finished");
        System.out.println("After PARSING JSON RESULT!!!!!!!!");
        JSONObject bigObject = null;
        try {
            bigObject = new JSONObject(forecastJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray repJsonArray = null;
        try {
            repJsonArray = bigObject.getJSONArray("results");
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        count = repJsonArray.length();
        Log.d("length", String.valueOf(count));
        for(int i = 0; i < repJsonArray.length(); i++){
            JSONObject repObject = null;
            try {
                repObject = (JSONObject)repJsonArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                firstName.add((String) repObject.get("first_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                party.add((String) repObject.get("party"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                lastName.add((String) repObject.get("last_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                termEnd.add((String) repObject.get("term_end"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                title.add((String) repObject.get("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                website.add((String) repObject.get("website"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                oc_email.add((String) repObject.get("oc_email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                tweet_id.add((String) repObject.get("twitter_id"));
                Log.d("twitter", tweet_id.get(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                bioguide_id.add((String) repObject.get("bioguide_id"));
                Log.d("bioguide_id", bioguide_id.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // ************************###################################################################

            final String FORECAST_BASE_URL0 = "http://congress.api.sunlightfoundation.com/committees?";
            final String FORECAST_BASE_URL1 = "http://congress.api.sunlightfoundation.com/bills?";
            final String CID_PARAM = "committee_ids";
            final String MID_PARAM = "member_ids";

            HttpURLConnection urlConnection0 = null;
            BufferedReader reader0 = null;

            // Will contain the raw JSON response as a string.

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast

                Uri builtUri0 = Uri.parse(FORECAST_BASE_URL0).buildUpon()
                        .appendQueryParameter(MID_PARAM, bioguide_id.get(i))
                        .appendQueryParameter(KEY_PARAM, API_KEY)
                        .build();
                URL url0 = new URL(builtUri0.toString());
                Log.d("url", String.valueOf(url0));
//            URL url = new URL("http://congress.api.sunlightfoundation.com/legislators/locate?zip=94709&apikey=778b67a7e69b4f779d949b64602e1d46");

                //baseUrl.concat(apiKey));
                System.out.println("1");
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection0 = (HttpURLConnection) url0.openConnection();
                System.out.println("11");
                //urlConnection.setRequestMethod("GET");
                System.out.println(urlConnection0);
                urlConnection.connect();

                System.out.println("2");


                // Read the input stream into a String
                InputStream inputStream0 = urlConnection0.getInputStream();
                StringBuffer buffer0 = new StringBuffer();
                if (inputStream0 == null) {
                    // Nothing to do.
                    return null;
                }
                reader0 = new BufferedReader(new InputStreamReader(inputStream0));

                System.out.println("3");

                String line;
                while ((line = reader0.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer0.append(line + "\n");
                }

                System.out.println("4");

                if (buffer0.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    System.out.println("the buffer length is 0");
                    return null;
                }

                forecastJsonStr0 = buffer0.toString();
                System.out.println(forecastJsonStr0);
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection0 != null) {
                    urlConnection0.disconnect();
                }
                if (reader0 != null) {
                    try {
                        reader0.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }

            System.out.println("finished");
            System.out.println("After PARSING JSON RESULT!!!!!!!!");
            JSONObject bigObject0 = null;
            try {
                bigObject0 = new JSONObject(forecastJsonStr0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray repJsonArray0 = null;
            try {
                repJsonArray0 = bigObject0.getJSONArray("results");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            count0.add(repJsonArray0.length());
            Log.d("length_comm", String.valueOf(count0));
            for(int k = 0; k < repJsonArray0.length(); k++) {
                JSONObject repObject0 = null;
                try {
                    repObject0 = (JSONObject) repJsonArray0.get(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    committee_id.add((String) repObject0.get("committee_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    name.add((String) repObject0.get("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //*********************************************************************************************************
                HttpURLConnection urlConnection1 = null;
                BufferedReader reader1 = null;

                // Will contain the raw JSON response as a string.

                try {
                    // Construct the URL for the OpenWeatherMap query
                    // Possible parameters are avaiable at OWM's forecast API page, at
                    // http://openweathermap.org/API#forecast

                    Uri builtUri1 = Uri.parse(FORECAST_BASE_URL1).buildUpon()
                            .appendQueryParameter(CID_PARAM, committee_id.get(k))
                            .appendQueryParameter(KEY_PARAM, API_KEY)
                            .build();
                    URL url1 = new URL(builtUri1.toString());
                    Log.d("url", String.valueOf(url1));
//            URL url = new URL("http://congress.api.sunlightfoundation.com/legislators/locate?zip=94709&apikey=778b67a7e69b4f779d949b64602e1d46");

                    //baseUrl.concat(apiKey));
                    System.out.println("1");
                    // Create the request to OpenWeatherMap, and open the connection
                    urlConnection1 = (HttpURLConnection) url1.openConnection();
                    System.out.println("11");
                    //urlConnection.setRequestMethod("GET");
                    System.out.println(urlConnection1);
                    urlConnection1.connect();

                    System.out.println("2");


                    // Read the input stream into a String
                    InputStream inputStream1 = urlConnection1.getInputStream();
                    StringBuffer buffer1 = new StringBuffer();
                    if (inputStream1 == null) {
                        // Nothing to do.
                        return null;
                    }
                    reader1 = new BufferedReader(new InputStreamReader(inputStream1));

                    System.out.println("3");

                    String line;
                    while ((line = reader1.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer1.append(line + "\n");
                    }

                    System.out.println("4");

                    if (buffer1.length() == 0) {
                        // Stream was empty.  No point in parsing.
                        System.out.println("the buffer length is 0");
                        return null;
                    }

                    forecastJsonStr1 = buffer1.toString();
                    System.out.println(forecastJsonStr1);
                } catch (IOException e) {
                    Log.e("PlaceholderFragment", "Error ", e);
                    // If the code didn't successfully get the weather data, there's no point in attemping
                    // to parse it.
                    return null;
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e("PlaceholderFragment", "Error closing stream", e);
                        }
                    }
                }
                System.out.println("finished");
                System.out.println("After PARSING JSON RESULT!!!!!!!!");
                JSONObject bigObject1 = null;
                try {
                    bigObject1 = new JSONObject(forecastJsonStr1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray repJsonArray1 = null;
                try {
                    repJsonArray1 = bigObject1.getJSONArray("results");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                count1.add(repJsonArray1.length());
                Log.d("length_bill", String.valueOf(count1));
                for(int j = 0; j < repJsonArray1.length(); j++) {
                    JSONObject repObject1 = null;
                    try {
                        repObject1 = (JSONObject) repJsonArray1.get(j);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        introduced_on.add((String) repObject1.get("introduced_on"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        official_title.add((String) repObject1.get("official_title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }


        }

//        try {
//            count = (Integer) countJson.get(0);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        // *****************************************************************************************************

        return null;
    }

//    public SunLightHTTP(AsyncResponse asyncResponse){
//        delegate = asyncResponse;
//    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent intent1 = new Intent(context, MainToCurrent.class);

        Log.d("count", String.valueOf(count));
        intent1.putExtra("latMain123", lat);
        intent1.putExtra("longMain123", lng);

        Log.d("count", String.valueOf(count0));
        intent1.putExtra("count1", count);
        for(int i = 0; i < count; i++) {
            intent1.putExtra("full_name" + i, firstName.get(i) + " " + lastName.get(i));
            intent1.putExtra("party" + i, party.get(i));
            intent1.putExtra("termEnd" + i, termEnd.get(i));
            intent1.putExtra("title" + i, title.get(i));
            intent1.putExtra("website" + i, website.get(i));
            intent1.putExtra("oc_email" + i, oc_email.get(i));
            intent1.putExtra("twitter" + i, tweet_id.get(i));
            Log.d("twitter" + i, tweet_id.get(i));
            intent1.putExtra("countComm" + i, count0);
            Log.d("countCommSUN", String.valueOf(count0));
            for(int k = 0; k < count0.get(i); k++) {
                intent1.putExtra("committeeName" + i + k, name.get(k));
                intent1.putExtra("countBill" + i + k, count1);
                Log.d("countBILL", String.valueOf(count1));
                for (int j = 0; j < count1.get(k); j++) {
                    intent1.putExtra("dateIntroduced" + i + k + j, introduced_on.get(j));
                    intent1.putExtra("officialTitle" + i + k + j, official_title.get(j));
                }
            }
        }




        context.startActivity(intent1);
        ((Activity)context).finish();
    }
}
