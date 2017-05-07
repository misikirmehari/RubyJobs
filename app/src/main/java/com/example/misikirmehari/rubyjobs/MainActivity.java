package com.example.misikirmehari.rubyjobs;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;



    ListView listview;
    ListViewAdapter adapter;
    JSONArray jsonarray;
    static String AUTHOR = "author";
    static String TITLE = "title";
    static String DESCRIPTION = "description";
    static String URL = "url";
    static String IMAGE = "news_image";
    static String DATE ="date";
    ArrayList<HashMap<String, String>> arraylist;


    ArrayList<HashMap<String, String>> contactList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        contactList = new ArrayList<>();

        new GetContacts().execute();

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
            case R.id.sign_in:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.jobsearch:
                Intent jobintent = new Intent(this, JobActivity.class);
                startActivity(jobintent);
                return true;
            case R.id.savedjobs:
                Intent savedjobsintent = new Intent(this, SavedJobsActivity.class);
                startActivity(savedjobsintent);
                return true;



            default:
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

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

            arraylist = new ArrayList<HashMap<String, String>>();

            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall( "https://newsapi.org/v1/articles?source=techradar&sortBy=latest&apiKey=b88d1a7d390e45e4bb74cfecc1c1246d");

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    jsonarray = jsonObj.getJSONArray("articles");

                    for (int i = 0; i < jsonarray.length(); i++) {

                        HashMap<String, String> map = new HashMap<String, String>();

                        jsonObj = jsonarray.getJSONObject(i);

                        // Retrive JSON Objects
                        map.put("author", jsonObj.getString("author"));
                        map.put("title", jsonObj.getString("title"));
                        map.put("description", jsonObj.getString("description"));
                        map.put("url", jsonObj.getString("url"));
                        map.put("news_image", jsonObj.getString("urlToImage"));
                        map.put("date", jsonObj.getString("publishedAt"));


                        // Set the JSON Objects into the array
                        arraylist.add(map);
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
            }

            else {
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
            /**
             * Updating parsed JSON data into ListView
             * */

            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(MainActivity.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);

        }

    }




}

