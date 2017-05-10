package com.example.misikirmehari.rubyjobs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.misikirmehari.rubyjobs.HelperClasses.DatabaseHandler;
import com.example.misikirmehari.rubyjobs.HelperClasses.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class extends BaseAdapter
 */

public class JobsViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();



    public JobsViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        final TextView jobtitle;
        final TextView company;
        final TextView url;
        Button delete;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.savedjoblistitem, parent, false);

        // Get the position
        resultp = data.get(position);


        // Locate the TextViews in listview_item.xml
        jobtitle = (TextView) itemView.findViewById(R.id.saved_job_title);
        company = (TextView) itemView.findViewById(R.id.saved_company_name);
        url = (TextView) itemView.findViewById(R.id.saved_job_url);


        // Capture position and set results to the TextViews
        jobtitle.setText(resultp.get(SavedJobsActivity.JOBTITLE));
        company.setText(resultp.get(SavedJobsActivity.COMPANY));
        url.setText(resultp.get(SavedJobsActivity.URL));


        delete = (Button) itemView.findViewById(R.id.del_btn);

        delete.setTag(position);

        delete.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                });

        return itemView;
    }
}

