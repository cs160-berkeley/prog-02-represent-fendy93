package com.android.candid;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kieykouch on 3/8/16.
 */
public class SunLightHTTP extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast

            String baseUrl = "http://congress.api.sunlightfoundation.com/legislators/locate?zip=94709&";
            //"http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
            String zip = "94709";
            String apiKey = "apikey=" + "778b67a7e69b4f779d949b64602e1d46`";
            URL url = new URL("http://congress.api.sunlightfoundation.com/legislators/locate?zip=94709&apikey=778b67a7e69b4f779d949b64602e1d46");

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

//        System.out.println("6");
//        String[] k = new String[1];
//        k[0] = forecastJsonStr;
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
        for(int i = 0; i < repJsonArray.length(); i++){
            JSONObject repObject = null;
            try {
                repObject = (JSONObject)repJsonArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String firstName = null;
            try {
                firstName = (String) repObject.get("first_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String lastName = null;
            try {
                lastName = (String) repObject.get("last_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
//                    String middleName = repObject.get("middle_name");
            String termEnd = null;
            try {
                termEnd = (String) repObject.get("term_end");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String title = null;
            try {
                title = (String) repObject.get("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String postal = null;
            try {
                postal = (String) repObject.get("postal_code");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("Full Name : " + firstName + lastName);
            System.out.println("Term End : " + termEnd);
            System.out.println("Title : " + title);
            System.out.println("ZIP Code : " + postal);
        }
        return forecastJsonStr;
    }


//    @Override
//    protected String doInBackground(String... urls) {
//        String result = "";
//        URL url;
//        HttpURLConnection urlConnection = null;
//
//        try {
//            url = new URL(urls[0]);
//
//            System.out.println("1");
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//            System.out.println("2");
//            urlConnection.connect();
//
//            System.out.println("2+1");
//
//            InputStream in = urlConnection.getInputStream();
//
//            System.out.println("3");
//
//
//            InputStreamReader reader = new InputStreamReader(in);
//
//            int data = reader.read();
//
//            while (data != -1) {
//                char current = (char) data;
//                result += current;
//                data = reader.read();
//            }
//
//            //String k[] = new String[3];
//            //k[0] = result;
//            return result;
//
//        } catch (Exception e) {
//            Log.i("ERROR", "Please try again"+e);
//        }
//        return null;
//    }
}
