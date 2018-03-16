package com.upitt.ruc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.upitt.ruc.activities.LoaderActivity;

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
    Context context_loader;

    public BackgroundHelper(Context context, AsyncResponse delegate)
    {
        this.context = context;
        this.delegate = delegate;
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

                String loginURL = "http://testomkar.000webhostapp.com/verify.php";

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
        else if(type.equals("insert_user_account"))
        {
            String loginURL = "http://testomkar.000webhostapp.com/insert.php";
            String email = params[1];

            try {
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
        else if(type.equals("insert_reg_code_request"))
        {
            String loginURL = "http://testomkar.000webhostapp.com/insert_reg_code_request.php";
            String email = params[1];
            String name = params[2];
            String phone = params[3];

            try {
                URL url = new URL(loginURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");


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

        Intent intent = new Intent(context.getApplicationContext(), LoaderActivity.class);
        context_loader = LoaderActivity.loader_Activity;
        context.startActivity(intent);

    }

    @Override
    protected void onPostExecute(String result)
    {
        ((AppCompatActivity) context_loader).finish();
        delegate.processFinish(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


