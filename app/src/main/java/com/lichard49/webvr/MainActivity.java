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

    private WebView webView;
    private int choiceIndex = 1;
    private boolean webViewLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.content);
        webView.setOnTouchListener(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true); //Maybe you don't need this rule
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setUseWideViewPort(true);
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("http://aframe.glitch.me/");
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url){
                webViewLoaded = true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.d("lichard49", message);
                result.confirm();
                return true;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            choiceIndex = (choiceIndex + 1) % 3;
            Log.d("lichard49", "POKE " + choiceIndex);
            webView.loadUrl("javascript:selectChoice('" + choiceIndex + "')");
        }
        return false;
    }
}
