package com.seenu.eventstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.eve_dets_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.favs_menu:

			Intent i = new Intent(EventDetailsActivity.this,
					EventsActivity.class);
			i.putExtra("TAB_NUMBER", 1);
			startActivity(i);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
