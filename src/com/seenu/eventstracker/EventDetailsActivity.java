package com.seenu.eventstracker;

import java.io.IOException;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.seenu.eventstracker.database.DatabaseOpenHelper;
import com.seenu.eventstracker.pojo.EventsPOJOClass;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetailsActivity extends ActionBarActivity {

	private ActionBar aBar;

	// EventsPOJOClass object
	private EventsPOJOClass obj;

	// Textview widgets
	private TextView eveNamTv;
	private TextView eveDatTv;
	private TextView eveTimTv;
	private TextView eveLocTv;
	private TextView eveTypeTv;

	// Imageview Widgetevent
	private ImageView eveThumbIv;

	// Button Widget to add event to Favourites
	private Button favsBt;

	private DatabaseOpenHelper myDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details_activity);

		aBar = getSupportActionBar();
		aBar.setTitle("Event Details");

		Bundle b = getIntent().getExtras();

		myDbHelper = new DatabaseOpenHelper(EventDetailsActivity.this);

		eveThumbIv = (ImageView) findViewById(R.id.eveDateImg);
		eveNamTv = (TextView) findViewById(R.id.textView1);
		eveDatTv = (TextView) findViewById(R.id.eveDateTv);
		eveTimTv = (TextView) findViewById(R.id.eveTimTv);
		eveLocTv = (TextView) findViewById(R.id.textView6);
		eveTypeTv = (TextView) findViewById(R.id.eveTypTv);
		favsBt = (Button) findViewById(R.id.button1);

		if (b != null)
			obj = (EventsPOJOClass) b.get("EVENTS_OBJ");

		try {
			// check if database exists in app path, if not copy it from assets
			myDbHelper.create();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			// open the database
			myDbHelper.open();
			myDbHelper.getWritableDatabase();
		} catch (SQLException sqle) {
			throw sqle;
		}

		final String id = obj.id;
		final String event = obj.event;
		final String location = obj.location;
		final String date = obj.date;
		final String time = obj.time;
		final String eventType = obj.eventType;
		final String url = obj.url;

		eveNamTv.setText(event);
		eveDatTv.setText(date);
		eveTimTv.setText(time);
		eveLocTv.setText(location);
		eveTypeTv.setText(eventType);

		UrlImageViewHelper.setUrlDrawable(eveThumbIv, url,
				R.drawable.ic_launcher);

		favsBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				myDbHelper.insertUserEvent(id, event, location, eventType, url,
						date, time);
				Toast.makeText(EventDetailsActivity.this,
						"Marked event as favourite", Toast.LENGTH_SHORT).show();
			}
		});

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

		case R.id.logout_menu:
			myDbHelper.deleteAllUserEvents();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
