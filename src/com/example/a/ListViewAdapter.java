package com.example.a;

import java.util.ArrayList;
import java.util.HashMap;

import com.facebook.share.model.ShareLinkContent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data=null;
	//ImageLoader imageLoader;
	HashMap<String, String> resultp ;
	

	public ListViewAdapter(Context context,
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
		TextView song;
		ImageView browserbutton;
		ImageView shareButton;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		resultp = data.get(position);
			// Locate the TextViews in listview_item.xml
		song = (TextView) itemView.findViewById(R.id.song);
		browserbutton=(ImageView)itemView.findViewById(R.id.songplayimage);
		shareButton=(ImageView)itemView.findViewById(R.id.fbsharebutton);
		
		song.setText(resultp.get(SearchActivity.SONG_NAME));
		
		
		
		browserbutton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
			String songurl=resultp.get(SearchActivity.TUNE_FIND_URL);
			
			Log.i("CHEEEEEEEEEEEEEEEEK","SONGURL"+ songurl);
			
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(songurl));
				context.startActivity(browserIntent);
 
			}
		});
		
		
		shareButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
			String songurl=resultp.get(SearchActivity.TUNE_FIND_URL);
			
			
			
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, songurl);
			sendIntent.setType("text/plain");
			context.startActivity(sendIntent);
 
			}
		});
		
		
		
		
		return itemView;
	}
}
