package com.kbulab.exam.http01;

import android.content.Context;

public class DownloadWebPage extends Thread{
    Context context;
    String page;

    public DownloadWebPage(Context context, String page) {
        this.context = context;
        this.page = page;
    }
}
