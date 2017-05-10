package com.example.misikirmehari.rubyjobs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;


/**
 * This class was taken from placeholderview and changed to get the
 * job activity to work.
 */
@Layout(R.layout.job_card_view)
public class JobCard {

    @View(R.id.job_title)
    private TextView job_title;


    @View(R.id.company_name)
    private TextView company_name;

    @View(R.id.city)
    private TextView city;

    @View(R.id.job_description_card)
    private TextView job_description;


    @View(R.id.job_date_posted)
    private TextView job_date_posted;


    @View(R.id.job_url)
    private TextView job_url;


    private JobsDec mjobs;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;


    public JobCard(Context context, JobsDec jobsDec, SwipePlaceHolderView swipeView) {
        mContext = context;
        mjobs = jobsDec;
        mSwipeView = swipeView;


    }


    @Resolve
    private void onResolved() {
        job_title.setText(mjobs.getJobtitle());
        company_name.setText(mjobs.getCompany());
        city.setText(mjobs.getCity());
        job_description.setText(mjobs.getDescription());
        job_date_posted.setText(mjobs.getDate());
        job_url.setText(mjobs.getUrl());

    }


    @SwipeOut
    private void onSwipedOut() {
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {

        String jobtitle = mjobs.getJobtitle();
        String company = mjobs.getCompany();
        String url = mjobs.getUrl();

        Log.i("TITLE", jobtitle);
        Log.i("COMPANY", company);
        Log.i("URL", url);

        Bundle savedJobsBundle = new Bundle();
        Intent saveintent = new Intent(mContext, SavedJobsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        savedJobsBundle.putString("JOBT", jobtitle);
        savedJobsBundle.putString("JOBC", company);
        savedJobsBundle.putString("JOBU", url);

        saveintent.putExtras(savedJobsBundle);
        mContext.startActivity(saveintent);
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState");
    }
}

