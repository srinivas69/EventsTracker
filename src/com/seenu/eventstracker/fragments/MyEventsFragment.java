package com.seenu.eventstracker.fragments;

import com.seenu.eventstracker.R;
import com.seenu.eventstracker.R.layout;
import com.seenu.eventstracker.adapters.EventsListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MyEventsFragment extends Fragment {

	private View view;

	// listview widget & adapter
	private ListView lv;
	private EventsListAdapter adapter;

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

		// initializing the listview adapter
		adapter = new EventsListAdapter(getActivity());
		lv.setAdapter(adapter);
	}

}
