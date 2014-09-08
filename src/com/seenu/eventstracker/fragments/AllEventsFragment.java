package com.seenu.eventstracker.fragments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.seenu.eventstracker.EventDetailsActivity;
import com.seenu.eventstracker.R;
import com.seenu.eventstracker.R.layout;
import com.seenu.eventstracker.adapters.AllEventsAdapter;
import com.seenu.eventstracker.adapters.EventsListAdapter;
import com.seenu.eventstracker.database.DatabaseOpenHelper;
import com.seenu.eventstracker.pojo.EventsPOJOClass;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AllEventsFragment extends Fragment {

	private View view;

	// listview widget & adapter
	private ListView lv;
	private AllEventsAdapter adapter;

	// instance of DatabaseOpenHelper class
	private DatabaseOpenHelper myDbHelper;

	// list of event ids of the user
	private ArrayList<String> usrEvtIds;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// inflating the layout to All Events tab
		view = inflater.inflate(R.layout.events_lv_fragment, null);

		lv = (ListView) view.findViewById(R.id.listView1);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		usrEvtIds = new ArrayList<String>();
		myDbHelper = new DatabaseOpenHelper(getActivity());

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

		// fetch all events
		final Cursor cursor = myDbHelper.getAllEvents();
		cursor.moveToFirst();

		// fetch all events of a user
		final Cursor cursorUserEvents = myDbHelper.getAllUserEvents();

		// adding the event ids to usrEvtIds ArrayList
		if (cursorUserEvents.getCount() != 0) {

			cursorUserEvents.moveToFirst();

			usrEvtIds.add(cursorUserEvents.getString(cursorUserEvents
					.getColumnIndex(DatabaseOpenHelper.COLUMN_USER_EVENT_ID)));

			while (cursorUserEvents.moveToNext()) {

				usrEvtIds
						.add(cursorUserEvents.getString(cursorUserEvents
								.getColumnIndex(DatabaseOpenHelper.COLUMN_USER_EVENT_ID)));

			}
			/*
			 * System.out.println(usrEvtIds.size() + usrEvtIds.get(0) +
			 * usrEvtIds.get(1) + usrEvtIds.get(2));
			 */
		}

		// initializing the listview adapterDatabaseOpenHelper
		adapter = new AllEventsAdapter(getActivity(), cursor, 0);
		lv.setAdapter(adapter);

		// listview item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub

				String favValue = "0";

				cursor.moveToPosition(pos);
				String id = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_ID));
				String event = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_NAME));
				String location = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_LOCATION));
				String date = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_DATE));
				String time = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_TIME));
				String eventType = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_ENTRY_TYPE));
				String url = cursor.getString(cursor
						.getColumnIndex(DatabaseOpenHelper.COLUMN_IMAGE_URL));

				// creating an object with the Event values
				EventsPOJOClass obj = new EventsPOJOClass(id, event, location,
						eventType, date, time, url);

				Iterator eventIdItr = usrEvtIds.iterator();

				while (eventIdItr.hasNext()) {
					String eventId = (String) eventIdItr.next();
					if (String.valueOf(pos).equals(eventId))
						// System.out.println("Matched");
						favValue = "1";
				}

				// starting Event Details Activity with Intent
				Intent i = new Intent(getActivity(), EventDetailsActivity.class);
				i.putExtra("EVENTS_OBJ", obj);
				i.putExtra("FAVOURITE", favValue);
				System.out.println("FAVOURITE " + favValue);
				startActivity(i);
			}
		});
	}
}
