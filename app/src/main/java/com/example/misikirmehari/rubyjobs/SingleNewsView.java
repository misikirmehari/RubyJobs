package com.example.misikirmehari.rubyjobs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleNewsView extends Activity {

	private WebView newsWebview ;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String url = intent.getStringExtra("url");


		newsWebview = new WebView(this);

		newsWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

		final Activity activity = this;

		newsWebview.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
			}
		});

		newsWebview.loadUrl(url);
		setContentView(newsWebview);

	}









}
