package com.example.misikirmehari.rubyjobs;

/**
 * Final Project
 * Misikir A. Mehari
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.misikirmehari.rubyjobs.HelperClasses.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView listview;
    NewsViewAdapter adapter;
    JSONArray jsonarray;
    static String AUTHOR = "author";
    static String TITLE = "title";
    static String DESCRIPTION = "description";
    static String URL = "url";
    static String IMAGE = "news_image";
    static String DATE = "date";
    ArrayList<HashMap<String, String>> newslist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Used to make the window fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        new GetNews().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Set which animation to perform on next Button click
        int id = item.getItemId();

        switch (id) {
            case R.id.jobsearch:
                Intent jobintent = new Intent(this, JobActivity.class);
                startActivity(jobintent);
                return true;
            case R.id.savedjobs:
                Intent savedjobsintent = new Intent(this, SavedJobsActivity.class);
                startActivity(savedjobsintent);
                return true;
            case R.id.ruby_tool_box:
                Intent rubytoolboxIntent = new Intent(this, RubyRefsActivity.class);
                rubytoolboxIntent.putExtra("uri", "https://www.ruby-toolbox.com");
                startActivity(rubytoolboxIntent);
                return true;
            case R.id.ruby_gems:
                Intent rubygemIntent = new Intent(this, RubyRefsActivity.class);
                rubygemIntent.putExtra("uri", "https://rubygems.org");
                startActivity(rubygemIntent);
                return true;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            newslist = new ArrayList<HashMap<String, String>>();

            HttpHandler sh = new HttpHandler();

            // Making a request to NewsApi and get response
            String jsonStr = sh.makeServiceCall("https://newsapi.org/v1/articles?source=techradar&sortBy=latest&apiKey=b88d1a7d390e45e4bb74cfecc1c1246d");

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    jsonarray = jsonObj.getJSONArray("articles");

                    for (int i = 0; i < jsonarray.length(); i++) {

                        HashMap<String, String> newsmap = new HashMap<String, String>();

                        jsonObj = jsonarray.getJSONObject(i);

                        // Retrieve JSON Objects
                        newsmap.put("author", jsonObj.getString("author"));
                        newsmap.put("title", jsonObj.getString("title"));
                        newsmap.put("description", jsonObj.getString("description"));
                        newsmap.put("url", jsonObj.getString("url"));
                        newsmap.put("news_image", jsonObj.getString("urlToImage"));
                        newsmap.put("date", jsonObj.getString("publishedAt"));

                        // Set the JSON Objects into the array
                        newslist.add(newsmap);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            listview = (ListView) findViewById(R.id.list);

            // Pass the results into NewsViewAdapter.java
            adapter = new NewsViewAdapter(MainActivity.this, newslist);

            // Set the adapter to the ListView
            listview.setAdapter(adapter);

        }

    }


}

