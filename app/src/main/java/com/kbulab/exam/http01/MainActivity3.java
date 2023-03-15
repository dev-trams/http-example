package com.kbulab.exam.http01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {
    String page = "http://news.kbu.ac.kr/enewspaper/upimages/%EA%B5%AD%EC%A0%9C%EA%B5%90%EC%9C%A1%EC%B2%98%20%EC%9C%A0%ED%95%99%EC%83%9D%202.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}