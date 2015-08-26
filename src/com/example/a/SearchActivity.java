package com.example.a;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.android_jsp_mysql.R;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends ActionBarActivity implements OnClickListener {

	// Declare Variables
	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	static String SONG_NAME = "songname";
	static String TUNE_FIND_URL = "tunefind_url";
	EditText SongName;
	Button searchButtton;
	String songName;

	private final String TAG = getClass().getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.home_frag);
		// Execute DownloadJSON AsyncTask
		SongName = (EditText) findViewById(R.id.searchsongtextfield);
		searchButtton = (Button) findViewById(R.id.searchbutton);
		searchButtton.setOnClickListener(this);
	}

	// DownloadJSON AsyncTask
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(SearchActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Loading songs");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
			// Retrieve JSON Objects from the given URL address
			// jsonobject =
			// JSONfunctions.getJSONfromURL("http://www.tunefind.com/api/v1/show");

			String response;

			response = new TunesFindAPI().getAlias(songName);

			try {
				// Locate the array name in JSON88
				JSONObject mJSONObject = new JSONObject(response);
				JSONArray jsonarray = mJSONObject.getJSONArray("songs");

				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
					map.put("songname", jsonobject.getString("name"));
					 map.put("tunefind_url", jsonobject.getString("tunefind_url"));

					arraylist.add(map);
					
				//	Log.i("ARRRRRRRRRRRRRRRRAYYYYYy","SONGURL"+ arraylist.get(0).get("tunefindurl"));
					
				//	Log.i("ARRRRRRRRRRRRRRRRAYYYYYy","SONGURL"+ arraylist.get(1));
					
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void args) {

			mProgressDialog.dismiss();

			// Write json parsing code here

			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java

			adapter = new ListViewAdapter(SearchActivity.this, arraylist);
			// Set the adapter to the ListView
			listview.setAdapter(adapter);

			 mProgressDialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchbutton:
			 songName = SongName.getText().toString();
			 
			String lowercaseName= songName.toLowerCase();
			// Line to replace space with the hyphen
			songName = lowercaseName.replaceAll(" ", "-");
			new DownloadJSON().execute();
			break;

		default:
			break;
		}
		
	}

}
