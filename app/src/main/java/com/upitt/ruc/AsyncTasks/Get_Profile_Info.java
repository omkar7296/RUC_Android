package com.upitt.ruc.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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

    private RelativeLayout fragment_profile_page_parentLayout;
    private ProgressBar fragment_profile_page_progressbar;

    public Get_Profile_Info(Context context, Get_Profile_Info_AsyncResponse delegate, RelativeLayout fragment_profile_page_parentLayout, ProgressBar fragment_profile_page_progressbar) {
        this.context = context;
        this.delegate = delegate;
        this.fragment_profile_page_parentLayout = fragment_profile_page_parentLayout;
        this.fragment_profile_page_progressbar = fragment_profile_page_progressbar;
    }

    public interface Get_Profile_Info_AsyncResponse {
        void processFinish(String about_me, String sports_activities, String hobbies_interests, String places, String location, String lived_since, String name, String photo, String points);

        void processFailed(String result);
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
        fragment_profile_page_parentLayout.setVisibility(View.GONE);
        fragment_profile_page_progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String result) {
        //Log.i("TEst", result);

        try {

            if (result.equals("failed")) {

                delegate.processFailed(result);

            } else {
                JSONObject jsonObject = new JSONObject(result);

                String about_me = jsonObject.getString("about_me");
                String sports_activities = jsonObject.getString("sports_activities");
                String hobbies_interests = jsonObject.getString("hobbies");
                String places = jsonObject.getString("favourite_places");
                String location = jsonObject.getString("neighbourhood");
                String lived_since = jsonObject.getString("lived_since");
                String name = jsonObject.getString("name");
                String photo = jsonObject.getString("photo");
                String points = Integer.toString(jsonObject.getInt("points"));

//            if(lived_since != "null")
//                Log.i("TEst",lived_since);

                fragment_profile_page_progressbar.setVisibility(View.GONE);
                fragment_profile_page_parentLayout.setVisibility(View.VISIBLE);

                delegate.processFinish(about_me, sports_activities, hobbies_interests, places, location, lived_since, name, photo, points);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
