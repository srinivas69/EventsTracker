package com.seenu.eventstracker.fragments;

import com.seenu.eventstracker.EventDetailsActivity;
import com.seenu.eventstracker.R;
import com.seenu.eventstracker.R.layout;
import com.seenu.eventstracker.adapters.EventsListAdapter;

import android.content.Intent;
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
	private EventsListAdapter adapter;

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

		// initializing the listview adapter
		adapter = new EventsListAdapter(getActivity());
		lv.setAdapter(adapter);

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
