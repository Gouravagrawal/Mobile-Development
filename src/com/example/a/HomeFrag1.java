package com.example.a;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFrag1 extends Fragment implements OnClickListener {
	// A placeholder fragment containing a simple view.

	private final String TAG = getClass().getSimpleName();
	private Button btn;
	private Context context;

	public HomeFrag1() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, "onAttach(-)");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		Log.i(TAG, "onCreate(-)");
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView(-,-)");
		View rootView = inflater.inflate(R.layout.home_frag1, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "onActivityCreated(-)");
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setHomeButtonEnabled(true);
		
		// Initialize components
		btn = (Button) getActivity().findViewById(R.id.add_btn);
		btn.setOnClickListener(this);

	}
	
	private ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, "onStart()");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "onResume()");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(TAG, "onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, "onStop()");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG, "onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, "onDetach()");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_btn:
			NavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);
			android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity()
					.getSupportFragmentManager().beginTransaction();
			// remove fragment from fragment manager
			fragmentTransaction.remove(getActivity()
					.getSupportFragmentManager().findFragmentByTag("home1"));
			// add fragment in fragment manager
			fragmentTransaction.add(R.id.container, new HomeFrag2(), "home2");
			fragmentTransaction.addToBackStack("home1");
			fragmentTransaction.commit();

			break;

		default:
			break;
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		Log.d(TAG, "Fragment.onCreateOptionsMenu");
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
//			NavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(false);
//			android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity()
//					.getSupportFragmentManager().beginTransaction();
//			// remove fragment from fragment manager
//			fragmentTransaction.remove(getActivity()
//					.getSupportFragmentManager().findFragmentByTag("home1"));
//			// add fragment in fragment manager
//			fragmentTransaction.add(R.id.container, new HomeFrag2(), "home2");
//			fragmentTransaction.addToBackStack("home1");
//			fragmentTransaction.commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
