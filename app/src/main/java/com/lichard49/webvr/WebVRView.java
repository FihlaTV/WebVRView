package com.lichard49.webvr;

import android.content.Context;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by richard on 1/27/18.
 */

public class WebVRView extends WebView {

    private boolean webViewLoaded = false;
    private Queue<String> executeJSCalls;

    public WebVRView(Context context) {
        super(context);

        executeJSCalls = new LinkedList<>();
    }

    public void init(String webVRUrl) {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true); //Maybe you don't need this rule
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setUseWideViewPort(true);
        loadUrl(webVRUrl);
        setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url){
                webViewLoaded = true;
                while (!executeJSCalls.isEmpty()) {
                    String call = executeJSCalls.remove();
                    executeJS(call);
                }
            }
        });
        setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("lichard49", message);
            result.confirm();
            return true;
            }
        });
    }

    public void executeJS(String jsFunctionName, Object... params) {
        // format the parameters for the function call
        StringBuilder paramList = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            paramList.append(params[i].toString());
            if (i != params.length-1) {
                paramList.append(',');
            }
        }
        String jsCall = "javascript:" + jsFunctionName + "(" + paramList.toString() + ")";
        if (webViewLoaded) {
            // if the webview is already loaded, go ahead and execute the call
            loadUrl(jsCall);
        } else {
            // otherwise, queue the call until the webview is ready
            executeJSCalls.add(jsCall);
        }
    }
}
