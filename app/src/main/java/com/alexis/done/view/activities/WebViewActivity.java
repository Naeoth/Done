/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class WebViewActivity.java
 */

package com.alexis.done.view.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alexis.done.R;

/**
 * This class handles the web view.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class WebViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Displays the return button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Settings the web view.
        WebView webview = (WebView) findViewById(R.id.webview);
        WebSettings params = webview.getSettings();
        params.setJavaScriptEnabled(true);
        params.setBuiltInZoomControls(true);
        // Sets the web view client.
        webview.setWebViewClient( new MyWebViewClient() );

        // Loads the URL specified in the web view.
        webview.loadUrl( getIntent().getStringExtra("url") );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();

        return super.onOptionsItemSelected(item);
    }

    /**
    * This inner class handle the web view client of the web view.
    */
    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

    }

}
