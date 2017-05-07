package com.example.misikirmehari.rubyjobs;

import android.content.Context;
import android.content.Intent;
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
 * Created by misikirmehari on 5/5/17.
 */
@Layout(R.layout.job_card_view)
public class JobCard {


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String NameAgeText = "namekey";



    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private JobsDec mjobs;
    private Context mContext;
    public SharedPreferences msharedpreferences;

    private SwipePlaceHolderView mSwipeView;

    public JobCard(Context context, JobsDec jobsDec, SwipePlaceHolderView swipeView,SharedPreferences sharedpreferences) {
        mContext = context;
        mjobs = jobsDec;
        mSwipeView = swipeView;
        msharedpreferences = sharedpreferences;
        msharedpreferences = mContext.getSharedPreferences(MyPREFERENCES,mContext.MODE_PRIVATE);



    }



    @Resolve
    private void onResolved(){
        //Glide.with(mContext).load(mProfile.getImageUrl()).into(profileImageView);
        nameAgeTxt.setText(mjobs.getJobtitle() + ", " + mjobs.getCity() + "," +mjobs.getCompany());
        //locationNameTxt.setText(m.getLocation());
    }




    @SwipeOut
    private void onSwipedOut(){





        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){

        String n = mjobs.getJobtitle();
        Log.i("Sharedprefs", n);

        SharedPreferences.Editor editor = msharedpreferences.edit();
        editor.putString(NameAgeText,n);
        editor.commit();

        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}

