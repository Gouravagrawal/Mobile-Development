package com.example.a;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;

// http://stackoverflow.com/questions/24186781/android-navigation-drawer-fragment-state

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private  final String TAG = getClass().getSimpleName();
	
	public static final int HOME = 0;
	public static final int PROFILE = 1;
	public static final int SETTINGS = 2;
	public static final int ABOUT = 3;
	public static final int CONTACT_US = 4;
	public static final int FEEDBACK = 5;
	public static final int HELP = 6;
	public static final int FAQ = 7;
	public static final int FRAGMENT_COUNT = FAQ + 1;

	public static ActionBar actionBar;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_main);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
	}
	
	

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments

		showFragment(position);
	}

	private void showFragment(int position) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fTransaction = fragmentManager.beginTransaction();
				
		switch (position) {
		case HOME:
			fTransaction.replace(R.id.container, new HomeFrag(), "home").commit();
			break;
		case PROFILE:
			fTransaction.replace(R.id.container, new ProfileFrag(), "profile").commit();
			break;
		case SETTINGS:
			fTransaction.replace(R.id.container, new SettingsFrag(), "settings").commit();
			break;
		case ABOUT:
			fTransaction.replace(R.id.container, new AboutFrag(), "about").commit();
			break;
		case HELP:
			fTransaction.replace(R.id.container, new HelpFrag(), "help").commit();
			break;
		case FAQ:
			fTransaction.replace(R.id.container, new FaqFrag(), "faq").commit();
			break;
		}
		onSectionAttached(position);
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 0:
			mTitle = getString(R.string.home);
			break;
		case 1:
			mTitle = getString(R.string.profile);
			break;
		case 2:
			mTitle = getString(R.string.settings);
			break;
		case 3:
			mTitle = getString(R.string.about);
			break;
		case 4:
			mTitle = getString(R.string.help);
			break;
		case 5:
			mTitle = getString(R.string.faq);
			break;
		}
	}

	
	public void restoreActionBar() {
	    actionBar = getSupportActionBar();
	   // actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
	   // actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// To show action icons on child fragment same as parent fragment
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d(TAG, "Fragment.onCreateOptionsMenu");
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// To show action icons only on parent fragments
			//getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:
			Toast.makeText(this, "Settings", 1).show();
			break;

        case R.id.action_example:
        	Toast.makeText(this, "Example", 1).show();
			break;
			
        case android.R.id.home:
			Log.i(TAG, "clickedddd");
			FragmentManager fragmentManager = getSupportFragmentManager();
			if (fragmentManager.getBackStackEntryCount() > 0) {
				fragmentManager.popBackStack();
				Log.i(TAG,
						"stack count: " + fragmentManager.getBackStackEntryCount());
			}	
		}
		
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);

	}

	

}
