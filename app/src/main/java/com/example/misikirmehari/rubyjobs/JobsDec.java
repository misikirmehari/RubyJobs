package com.example.misikirmehari.rubyjobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;

/**
 * Created by misikirmehari on 5/5/17.
 */

public class JobsDec {

    @SerializedName("jobtitle")
    @Expose
    private String jobtitle;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("url")
    @Expose
    private String url;


    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

   public String getCompany(){
       return company;
   }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}