package com.upitt.ruc.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by omkar on 3/18/2018.
 */

public class Get_Profile_Info extends AsyncTask<String, Void, String> {

    Context context;
    public Get_Profile_Info_AsyncResponse delegate = null;

    public Get_Profile_Info(Context context, Get_Profile_Info_AsyncResponse delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    public interface Get_Profile_Info_AsyncResponse {
        void processFinish(String about_me, String sports_activities, String hobbies_interests, String places, String location, String lived_since);
    }

    @Override
    protected String doInBackground(String... params) {
        String loginURL = "http://atown.dreamhosters.com/ruc/android/sendprofileinfo.php";
        String email = params[0];


        //JSONObject jsonObject;
        try {
            URL url = new URL(loginURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();

            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
//            try {
//                jsonObject = new JSONObject(result);
//                return jsonObject;
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("TEst", result);

        try {
            JSONObject jsonObject = new JSONObject(result);

            String about_me = jsonObject.getString("about_me");
            String sports_activities = jsonObject.getString("sports_activities");
            String hobbies_interests = jsonObject.getString("hobbies");
            String places = jsonObject.getString("favourite_places");
            String location = jsonObject.getString("neighbourhood");
            String lived_since = jsonObject.getString("lived_since");

//            if(lived_since != "null")
//                Log.i("TEst",lived_since);

            delegate.processFinish(about_me, sports_activities, hobbies_interests, places, location, lived_since);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
