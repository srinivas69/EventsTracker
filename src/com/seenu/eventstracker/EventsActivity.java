package com.seenu.eventstracker;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class EventsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle b = getIntent().getExtras();

		String name = b.getString("NAME");
		Toast.makeText(EventsActivity.this, "Welcome! " + name,
				Toast.LENGTH_SHORT).show();

	}

}
