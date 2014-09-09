package com.seenu.eventstracker;

import com.seenu.eventstracker.fragments.AllEventsFragment;
import com.seenu.eventstracker.fragments.MyEventsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class EventsActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	// Tab titles
	private String[] tabs = { "All", "Favourites" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_activity);

		// Bundle for receiving the data from the previous activity.
		Bundle b = getIntent().getExtras();

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);

		actionBar = getSupportActionBar();
		actionBar.setTitle("Events");
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);

		// perform check if bundle contains key called TAB_NUMBER

		if (b != null) {
			if (b.containsKey("TAB_NUMBER")) {
				System.out.println(b.containsKey("TAB_NUMBER"));
				viewPager.setCurrentItem(1);
			} else if (b.containsKey("NAME")) {
				String name = b.getString("NAME");
				viewPager.setCurrentItem(0);

				Toast.makeText(EventsActivity.this, "Welcome!" + name,
						Toast.LENGTH_SHORT).show();
			}
		}

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		// on swiping the viewpager make respective tab selected
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	private static class TabsPagerAdapter extends FragmentPagerAdapter {

		public TabsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {

			switch (index) {
			case 0:
				// All Events Fragment
				return new AllEventsFragment();
			case 1:
				// My Events Fragment
				return new MyEventsFragment();

			}

			return null;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public int getCount() {
			// get item count - equal to number of tabs
			return 2;
		}

	}

}
