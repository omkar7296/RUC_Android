package com.upitt.ruc.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.upitt.ruc.Entities.Profile;

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
 * Created by omkar on 3/20/2018.
 */

public class Update_Profile_Info extends AsyncTask<Profile, Void, String> {

    Context context;
    Update_Profile_Info_AsyncResponse delegate = null;
    private RelativeLayout fragment_profile_page_parentLayout;
    private ProgressBar fragment_profile_page_progressbar;

    public Update_Profile_Info(Context context, Update_Profile_Info_AsyncResponse delegate, RelativeLayout fragment_profile_page_parentLayout, ProgressBar fragment_profile_page_progressbar) {
        this.context = context;
        this.delegate = delegate;
        this.fragment_profile_page_parentLayout = fragment_profile_page_parentLayout;
        this.fragment_profile_page_progressbar = fragment_profile_page_progressbar;
    }

    public interface Update_Profile_Info_AsyncResponse {
        void updateFinish(String output);
    }

    @Override
    protected String doInBackground(Profile... profiles) {

        String loginURL = "http://atown.dreamhosters.com/ruc/android/updateprofile.php";

        Profile profile = profiles[0];

        String email = profile.getEmail();
        String about_me = profile.getAbout_me();
        String sports_activities = profile.getSports_activities();
        String hobbies_interests = profile.getHobbies_interests();
        String places = profile.getPlaces();
        String neighbourhood = profile.getLocation();
        String lived_since = profile.getLived();


        try {
            URL url = new URL(loginURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                    URLEncoder.encode("about_me", "UTF-8") + "=" + URLEncoder.encode(about_me, "UTF-8") + "&" +
                    URLEncoder.encode("sports_activities", "UTF-8") + "=" + URLEncoder.encode(sports_activities, "UTF-8") + "&" +
                    URLEncoder.encode("hobbies", "UTF-8") + "=" + URLEncoder.encode(hobbies_interests, "UTF-8") + "&" +
                    URLEncoder.encode("favourite_places", "UTF-8") + "=" + URLEncoder.encode(places, "UTF-8") + "&" +
                    URLEncoder.encode("neighbourhood", "UTF-8") + "=" + URLEncoder.encode(neighbourhood, "UTF-8") + "&" +
                    URLEncoder.encode("lived_since", "UTF-8") + "=" + URLEncoder.encode(lived_since, "UTF-8");

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
        fragment_profile_page_progressbar.setVisibility(View.GONE);
        fragment_profile_page_parentLayout.setVisibility(View.VISIBLE);
        delegate.updateFinish(result);
    }
}
