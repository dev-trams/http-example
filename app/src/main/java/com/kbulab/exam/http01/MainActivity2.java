package com.kbulab.exam.http01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    String html = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textView);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        Button button = (Button) findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.setText("");
                String page = editText.getText().toString();
                if(page.equals("")) {
                    Toast.makeText(MainActivity2.this, "URL입력", Toast.LENGTH_SHORT).show();
                } else {
                    DownloadWebPage thread = new DownloadWebPage(MainActivity2.this, page);
                    thread.start();
                    try {
                        thread.join();
                        html = thread.getResult();
                        textView.setText(html);
                    } catch (InterruptedException e) {
                        Toast.makeText(MainActivity2.this, "오류", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }


}