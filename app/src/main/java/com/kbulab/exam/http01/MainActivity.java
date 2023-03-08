package com.kbulab.exam.http01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String page = "https://developer.android.com";
    String html = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        TextView textView = (TextView) findViewById(R.id.webpage);
        WebView webView = (WebView) findViewById(R.id.webpage1);
        Button button = findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownLoad thread = new DownLoad(MainActivity.this, page);
                thread.start();
                try {
                    thread.join();
                    html = thread.getResult();
                    textView.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.INVISIBLE);
                    textView.setText(html);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button button1 = (Button) findViewById(R.id.button02);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(html.equals("")) {
                    Toast.makeText(MainActivity.this, "페이지 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    textView.setVisibility(View.INVISIBLE);
                    webView.setVisibility(View.VISIBLE);
                    webView.loadDataWithBaseURL(null, html,  "Text/html", "UTF-8", null);
                }
            }
        });
    }

    private String DownloadHtml(String page){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = "";
        try {
            URL url =  new URL(page);
            connection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                result += (line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            connection.disconnect();
        }
        return result;
    }
}