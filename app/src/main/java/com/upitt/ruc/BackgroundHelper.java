package com.upitt.ruc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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
 * Created by omkar on 3/3/2018.
 */

public class BackgroundHelper extends AsyncTask<String,Void,String>{

    Context context;
    public AsyncResponse delegate = null;
    private LinearLayout landing_activity_parent_layout;
    private ProgressBar landing_activity_progressbar;


    public BackgroundHelper(Context context, AsyncResponse delegate, LinearLayout landing_activity_parent_layout, ProgressBar landing_activity_progressbar) {
        this.context = context;
        this.delegate = delegate;
        this.landing_activity_parent_layout = landing_activity_parent_layout;
        this.landing_activity_progressbar = landing_activity_progressbar;
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];



        if(type.equals("check_user_account"))
        {
            try {

                String loginURL = "http://atown.dreamhosters.com/ruc/android/checkforuser.php";

                String email = params[1];

                URL url = new URL(loginURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();

                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();}
        }

        return null;
    }

    @Override
    protected void onPreExecute() {

        landing_activity_parent_layout.setVisibility(View.GONE);
        landing_activity_progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String result)
    {
        landing_activity_parent_layout.setVisibility(View.VISIBLE);
        landing_activity_progressbar.setVisibility(View.GONE);
        delegate.processFinish(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


