package com.example.misikirmehari.rubyjobs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class RubyRefsActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ruby_gems);

        wv = (WebView) findViewById(R.id.ruby_gems_web_view);
        wv.getSettings().setJavaScriptEnabled(true); // enable javascript

        Intent intent = getIntent();
        String Flag = intent.getStringExtra("uri");

        wv.loadUrl(Flag);
//        setContentView(wv);
    }
}

