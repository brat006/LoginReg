package com.example.anubhav.loginreg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
 * Created by anubhav on 21-05-2016.
 */
public class Registration extends AsyncTask<String, Void, String>{
    Context context;
    String send_url = "http://192.168.88.246/androidF/reg.php";
    ProgressDialog progressDialog;
    public Registration(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Registration in Progress");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String name = params[0];
        String address = params[1];
        String username = params[2];
        String password = params[3];
        String response = "";
        try {
            URL url = new URL(send_url);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address, "UTF-8")+"&"+
                        URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStreamReader = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader, "iso-8859-1"));
                response = bufferedReader.readLine();
                bufferedReader.close();
                inputStreamReader.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        if(s.equals(new String("Successfully registered"))){
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        }
    }
}
