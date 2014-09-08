package com.seenu.eventstracker;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class EventDetailsActivity extends ActionBarActivity {

	private ActionBar aBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details_activity);

		aBar = getSupportActionBar();
		aBar.setTitle("Event Details");
	}

}
