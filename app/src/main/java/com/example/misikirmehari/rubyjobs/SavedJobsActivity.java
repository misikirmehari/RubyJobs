package com.example.misikirmehari.rubyjobs;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.misikirmehari.rubyjobs.HelperClasses.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SavedJobsActivity extends AppCompatActivity {
    static String JOBTITLE = "jobtitle";
    static String COMPANY = "company";
    static String URL = "url";

    ListView mListView;
    JobsViewAdapter adapter;
    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<HashMap<String, String>> jobsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_saved_jobs);


        mListView = (ListView) findViewById(R.id.saved_job_list);

       //db.deleteAll();




        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String job = bundle.getString("JOBT");
            String company = bundle.getString("JOBC");
            String url = bundle.getString("JOBU");

            db.addSavedJob(new SavedJobs(job,company,url));

        }


        List<SavedJobs> savedjobs = db.getAllSavedJobs();


        for (SavedJobs saved_jobs : savedjobs) {

//            Log.i(saved_jobs.getJobTitle(), saved_jobs.getCompany());



            HashMap<String, String> job = new HashMap<>();

            job.put("jobtitle", saved_jobs.getJobTitle());
            job.put("company", saved_jobs.getCompany());
            job.put("url", saved_jobs.getUrl());

            jobsList.add(job);
        }

        adapter = new JobsViewAdapter(SavedJobsActivity.this,jobsList);

        mListView.setAdapter(adapter);


    }


}
