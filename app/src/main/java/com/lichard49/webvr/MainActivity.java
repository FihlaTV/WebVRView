package com.lichard49.webvr;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity implements View.OnTouchListener {

    private WebVRView webVRView;
    private boolean webViewLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webVRView = new WebVRView(this);
        webVRView.init("file:///android_asset/index.html");
        webVRView.setOnTouchListener(this);
        webVRView.executeJS("setGazeEnabled", "true");

        setContentView(webVRView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            webVRView.executeJS("selectChoice");
        }
        return false;
    }
}
