package com.example.misikirmehari.rubyjobs;

/**
 * Created by misikirmehari on 5/7/17.
 */

public class SavedJobs {
    //private variables
    private int _id;
    private String _jobtitle;


    public SavedJobs(){

    }

    public SavedJobs(String jobtitle){
       this._jobtitle = jobtitle;
    }


    public SavedJobs(int id, String jobtitle){
        this._id = id;
        this._jobtitle = jobtitle;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getJobTitle(){
        return this._jobtitle;
    }

    // setting name
    public void setJobTitle(String jobtitle){
        this._jobtitle = jobtitle;
    }


}


