package com.seenu.eventstracker.fragments;

import java.io.IOException;

import com.seenu.eventstracker.EventDetailsActivity;
import com.seenu.eventstracker.R;
import com.seenu.eventstracker.R.layout;
import com.seenu.eventstracker.adapters.AllEventsAdapter;
import com.seenu.eventstracker.adapters.EventsListAdapter;
import com.seenu.eventstracker.database.DatabaseOpenHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MyEventsFragment extends Fragment {

	private View view;

	// listview widget & adapter
	private ListView lv;
	private AllEventsAdapter adapter;

	// instance of DatabaseOpenHelper class
	private DatabaseOpenHelper myDbHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// inflating the layout to this tab
		view = inflater.inflate(R.layout.events_lv_fragment, null);

		lv = (ListView) view.findViewById(R.id.listView1);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

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

		// fetch all user events
		final Cursor cursor = myDbHelper.getAllUserEvents();

		if (cursor.getCount() != 0) {
			// initializing the listview adapter
			cursor.moveToFirst();
			adapter = new AllEventsAdapter(getActivity(), cursor, 0);
			lv.setAdapter(adapter);
		}

		// listview item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				// starting Event Details Activity with Intent
				Intent i = new Intent(getActivity(), EventDetailsActivity.class);
				startActivity(i);
			}
		});
	}
}
