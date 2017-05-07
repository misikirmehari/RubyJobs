package com.example.misikirmehari.rubyjobs;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SavedJobsActivity extends AppCompatActivity {


    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_jobs);



        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<HashMap<String, String>> jobsList = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.saved_job_list);


        List<SavedJobs> savedjobs = db.getAllSavedJobs();

        Bundle b = getIntent().getExtras();

        if (b != null) {


            String job = b.getString("job");
            db.addSavedJob(new SavedJobs(job));

        }


        for (SavedJobs saved_jobs : savedjobs) {

            HashMap<String, String> job = new HashMap<>();

            job.put("joblist", saved_jobs.getJobTitle());

            jobsList.add(job);
        }


        ListAdapter adapter = new SimpleAdapter(
                SavedJobsActivity.this, jobsList,
                R.layout.savedjoblistitem, new String[]{"joblist"}, new int[]{R.id.savedjobtitle});


        mListView.setAdapter(adapter);
    }

}
