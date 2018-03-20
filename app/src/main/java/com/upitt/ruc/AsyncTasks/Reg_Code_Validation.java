package com.upitt.ruc.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

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

public class Reg_Code_Validation extends AsyncTask<String, Void, String> {


    Context context;
    public Reg_Code_Validation_AsyncResponse delegate = null;

    public Reg_Code_Validation(Context context, Reg_Code_Validation_AsyncResponse delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    public interface Reg_Code_Validation_AsyncResponse {
        void processFinish(String output);
    }

    @Override
    protected String doInBackground(String... params) {

        String loginURL = "http://atown.dreamhosters.com/ruc/android/checkcode.php";
        String email = params[0];
        String imgURL = params[1];
        String displayName = params[2];
        String code = params[3];

        try {
            URL url = new URL(loginURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(displayName, "UTF-8") + "&" +
                    URLEncoder.encode("photo", "UTF-8") + "=" + URLEncoder.encode(imgURL, "UTF-8") + "&" +
                    URLEncoder.encode("code", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8");


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

    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
