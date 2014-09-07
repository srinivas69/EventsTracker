package com.seenu.eventstracker;

import com.seenu.eventstracker.fragments.AllEventsFragment;
import com.seenu.eventstracker.fragments.MyEventsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class EventsActivity extends ActionBarActivity {

	// Fragments for the tabs
	private Fragment allEventsFrag;
	private Fragment myEventsFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_activity);

		allEventsFrag = new AllEventsFragment();
		myEventsFrag = new MyEventsFragment();

		// instance of the Actionbar
		ActionBar aBar = getSupportActionBar();
		aBar.setTitle("Events");

		// setting tab navigation for the Actionbar
		aBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab allEventsTab = aBar.newTab().setText("All");
		ActionBar.Tab myEventsTab = aBar.newTab().setText("My Events");

		allEventsTab.setTabListener(new TabListener(allEventsFrag));
		myEventsTab.setTabListener(new TabListener(myEventsFrag));

		aBar.addTab(allEventsTab);
		aBar.addTab(myEventsTab);

		// Bundle for receiving the data from the previous activity
		Bundle b = getIntent().getExtras();
		String name = b.getString("NAME");

		// Welcome Toast message after the launch of this activity
		Toast.makeText(EventsActivity.this, "Welcome! " + name,
				Toast.LENGTH_SHORT).show();

	}

	// class for the tab listener
	private static class TabListener implements ActionBar.TabListener {

		Fragment fragment;

		public TabListener(Fragment fragment) {
			// TODO Auto-generated constructor stub
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(android.support.v7.app.ActionBar.Tab tab,
				FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.fragment_container, fragment);

		}

		@Override
		public void onTabSelected(android.support.v7.app.ActionBar.Tab tab,
				FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.remove(fragment);

		}

		@Override
		public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab,
				FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

	}

}
