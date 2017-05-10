package com.example.misikirmehari.rubyjobs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misikirmehari.rubyjobs.HelperClasses.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;


public class NewsViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();

	public NewsViewAdapter(Context context,
                           ArrayList<HashMap<String, String >> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
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
		TextView author;
		TextView title;
		TextView description;
		ImageView news_image;
		TextView url;
		TextView date;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);

		// Get the position
		resultp = data.get(position);



		// Locate the TextViews in listview_item.xml
		author = (TextView) itemView.findViewById(R.id.author);
		title = (TextView) itemView.findViewById(R.id.title);
		description = (TextView) itemView.findViewById(R.id.description);
		url  = (TextView) itemView.findViewById(R.id.url);
		date = (TextView) itemView.findViewById(R.id.date);



		// Locate the ImageView in listview_item.xml
		news_image = (ImageView) itemView.findViewById(R.id.news_image);




		// Capture position and set results to the TextViews
		author.setText(resultp.get(MainActivity.AUTHOR));
		title.setText(resultp.get(MainActivity.TITLE));
		description.setText(resultp.get(MainActivity.DESCRIPTION));
		url.setText(resultp.get(MainActivity.URL));
		date.setText(resultp.get(MainActivity.DATE));



		// Capture position and set results to the ImageView
		// Passes News images URL into ImageLoader.class

		imageLoader.DisplayImage(resultp.get(MainActivity.IMAGE),news_image);


		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, SingleNewsView.class);
				intent.putExtra("url",resultp.get(MainActivity.URL));
                context.startActivity(intent);


			}
		});


		return itemView;
	}
}

