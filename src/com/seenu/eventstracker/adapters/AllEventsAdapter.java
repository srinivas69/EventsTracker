package com.seenu.eventstracker.adapters;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.seenu.eventstracker.R;
import com.seenu.eventstracker.database.DatabaseOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AllEventsAdapter extends CursorAdapter {

	public AllEventsAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		// TODO Auto-generated method stub

		ViewHolder holder = (ViewHolder) view.getTag();

		String event = cursor.getString(cursor
				.getColumnIndex(DatabaseOpenHelper.COLUMN_NAME));
		String location = cursor.getString(cursor
				.getColumnIndex(DatabaseOpenHelper.COLUMN_LOCATION));
		String date = cursor.getString(cursor
				.getColumnIndex(DatabaseOpenHelper.COLUMN_DATE));
		String eventType = cursor.getString(cursor
				.getColumnIndex(DatabaseOpenHelper.COLUMN_ENTRY_TYPE));
		String url = cursor.getString(cursor
				.getColumnIndex(DatabaseOpenHelper.COLUMN_IMAGE_URL));

		// setting text for the item textviews
		holder.nameText.setText(event);
		holder.locText.setText(location);
		holder.dateText.setText(date);
		holder.eveTypText.setText(eventType);

		UrlImageViewHelper.setUrlDrawable(holder.imageThumbnail, url,
				R.drawable.ic_launcher);

	}

	@Override
	public View newView(Context arg0, Cursor cur, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.events_lv_items, null);
		ViewHolder holder = new ViewHolder();

		// initialization
		holder.imageThumbnail = (ImageView) view.findViewById(R.id.imageView1);
		holder.nameText = (TextView) view.findViewById(R.id.textView1);
		holder.locText = (TextView) view.findViewById(R.id.textView2);
		holder.dateText = (TextView) view.findViewById(R.id.textView3);
		holder.eveTypText = (TextView) view.findViewById(R.id.textView4);

		// holder.deleteImg.setVisibility(View.INVISIBLE);

		view.setTag(holder);

		return view;
	}

	private static class ViewHolder {

		private ImageView imageThumbnail;
		private TextView nameText;
		private TextView locText;
		private TextView dateText;
		private TextView eveTypText;
	}

}
