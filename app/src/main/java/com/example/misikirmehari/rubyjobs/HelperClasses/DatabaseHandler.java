package com.example.misikirmehari.rubyjobs.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.view.ViewPropertyAnimatorListener;

import com.example.misikirmehari.rubyjobs.SavedJobs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Helper Class to save Jobs in Database
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "savedJobsManager.db";

    // Table name
    private static final String TABLE_SAVED_JOBS = "savedjobs";


    // Saved Jobs Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_JOBTITLE = "jobtitle";
    public static final String KEY_COMPANY = "company";
    public static final String KEY_URL = "url";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SAVEDJOBS_TABLE = "CREATE TABLE " + TABLE_SAVED_JOBS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_JOBTITLE + " TEXT,"
                + KEY_COMPANY + " TEXT," + KEY_URL + " TEXT " + ")";

        db.execSQL(CREATE_SAVEDJOBS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_JOBS);

        // Create tables again
        onCreate(db);

    }

    // Adding new JOB
    public void addSavedJob(SavedJobs savedJobs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_JOBTITLE, savedJobs.getJobTitle());
        values.put(KEY_COMPANY,savedJobs.getCompany());
        values.put(KEY_URL,savedJobs.getUrl());

        db.insert(TABLE_SAVED_JOBS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single saved job
    public SavedJobs getSavedJob(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SAVED_JOBS, new String[] { KEY_ID,
                        KEY_JOBTITLE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SavedJobs savedJobs = new SavedJobs(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));

        return savedJobs;
    }

    public void remove(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        String string =String.valueOf(id);
        db.execSQL("DELETE FROM favorite WHERE _id = '" + string + "'");
    }


    // Getting All Savedjobs
    public List<SavedJobs> getAllSavedJobs() {
        List<SavedJobs> savedJobsList = new ArrayList<SavedJobs>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVED_JOBS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SavedJobs savedJobs = new SavedJobs();
                savedJobs.setID(Integer.parseInt(cursor.getString(0)));
                savedJobs.setJobTitle(cursor.getString(1));
                savedJobs.setCompany(cursor.getString(2));
                savedJobs.setUrl(cursor.getString(3));


                savedJobsList.add(savedJobs);
            } while (cursor.moveToNext());
        }

        return savedJobsList;
    }


    public int updateSavedJob(SavedJobs savedJobs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JOBTITLE, savedJobs.getJobTitle());
        values.put(KEY_COMPANY,savedJobs.getCompany());
        values.put(KEY_URL,savedJobs.getUrl());

        // updating row
        return db.update(TABLE_SAVED_JOBS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(savedJobs.getID()) });
    }

    // Deleting single Job
    public void deleteSavedJob(SavedJobs savedJobs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SAVED_JOBS, KEY_ID + " = ?",
                new String[] { String.valueOf(savedJobs.getID()) });
        db.close();
    }

    // Deleting the whole Table
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SAVED_JOBS,null,null);
        db.close();
    }

    // Getting Jobs Count
    public int getSavedJobsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SAVED_JOBS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}


