package com.seenu.eventstracker.fragments;

import com.seenu.eventstracker.R;
import com.seenu.eventstracker.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllEventsFragment extends Fragment {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// inflating the layout to this tab
		view = inflater.inflate(R.layout.events_lv_fragment, null);
		return view;
	}
}
