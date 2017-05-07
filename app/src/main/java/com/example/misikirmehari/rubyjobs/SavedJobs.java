package com.example.misikirmehari.rubyjobs;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.misikirmehari.rubyjobs.JobCard.MyPREFERENCES;
import static java.lang.System.in;

public class SavedJobs extends AppCompatActivity {

    ArrayList<HashMap<String, String>> SavedJobList;
    private ListView lv;

//    SharedPreferences prefs = PreferenceManager
//            .getDefaultSharedPreferences(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_jobs);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);


        SavedJobList = new ArrayList<>();

        lv = (ListView)findViewById(R.id.saved_job_list);


        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.i("Sharedprefs",entry.getKey() + ": " +
                    entry.getValue().toString());
        }


//        for (int i = 0; i < phraseCount ; i++) {
//
//            HashMap <String, String> savedJobs = new HashMap<>();
//
//            String name = prefs.getString("NameAgeText", " ");
//
//            savedJobs.put("jobtitle", name);
//
////                        String gender = c.getString("gender");
////
////                        // Phone node is JSON Object
////                        JSONObject phone = c.getJSONObject("phone");
////                        String mobile = phone.getString("mobile");
////                        String home = phone.getString("home");
////                        String office = phone.getString("office");
//
//            // tmp hash map for single contact
//
//
//            // adding each child node to HashMap key => value
//
//
////            contact.put("email", email);
////            contact.put("mobile", address);
//
//            // adding contact to contact list
//            SavedJobList.add(savedJobs);
//
//
////
////        int phraseCount = prefs.getInt("phrase_count", 0);
////
////        ArrayList<String> listItems = new ArrayList <String>();
////
////
////        for(int i = 1; i <= phraseCount; i++) {
////
////            listItems.add(prefs.getString("NameAgeText", ""));
////        }
////
////        listview = (ListView) findViewById(R.id.saved_job_list);
////
////        adapter = new ArrayAdapter<String>(this, R.layout.activity_saved_jobs, listItems);
////
////        listview.setAdapter(adapter);
//
//            ListAdapter adapter = new SimpleAdapter(
//                    SavedJobs.this, SavedJobList,
//                    R.layout.savedjoblistitem, new String[]{"jobtitle"}
//                    , new int[]{R.id.jobtitle});
//
//
//            lv.setAdapter(adapter);
//
//        }

        }
}