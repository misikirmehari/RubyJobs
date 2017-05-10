package com.example.misikirmehari.rubyjobs;



public class SavedJobs {
    //private variables
    private int _id;
    private String _jobtitle;
    private String _company;
    private String _url;

    public SavedJobs() {

    }

    public SavedJobs(String jobtitle , String company, String url) {
        this._jobtitle = jobtitle;
        this._company = company;
        this._url = url;
    }


    public SavedJobs(int id, String jobtitle, String company, String url) {
        this._id = id;
        this._jobtitle = jobtitle;
        this._company = company;
        this._url = url;
    }


    public SavedJobs(int id) {
        this._id = id;
    }

    public SavedJobs(String job) {
        this._jobtitle = job;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getJobTitle() {
        return this._jobtitle;
    }

    // setting name
    public void setJobTitle(String jobtitle) {
        this._jobtitle = jobtitle;
    }

    public String getCompany() {
        return this._company;
    }

    public void setCompany(String company) {
        this._company = company;
    }

    public String getUrl() {
        return this._url;
    }

    public void setUrl(String url) {
        this._url = url;
    }
}


