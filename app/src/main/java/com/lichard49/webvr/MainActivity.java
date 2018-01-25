package com.lichard49.webvr;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.content);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true); //Maybe you don't need this rule
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setUseWideViewPort(true);
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("http://aframe.glitch.me/");

    }
}
