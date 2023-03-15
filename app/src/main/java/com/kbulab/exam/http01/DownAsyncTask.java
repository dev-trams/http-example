package com.kbulab.exam.http01;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownAsyncTask extends AsyncTask<String, String, String> {
    Context context;

    public DownAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        InputStreamReader streamReader=null;
        BufferedReader reader = null;
        String result = "";

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            streamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                result += (line + '\n');
            }

        } catch (IOException e) {
            publishProgress(e.getMessage());
        } finally {
            try {
                reader.close();
                streamReader.close();
                inputStream.close();
                connection.disconnect();
            } catch (IOException e) {
                publishProgress(e.getMessage());
            }
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

}
