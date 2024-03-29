package com.seenu.eventstracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	// Edittext Widget for User Name
	private EditText nameEt;

	/*
	 * Button Widget to skip entering the name.Currently kept this button as
	 * invisible.
	 */
	private Button skipBt;

	// user name
	private String name = "";

	// shared preference
	private SharedPreferences shrPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// call shared preferences
		shrPrefs = getSharedPreferences("EventsShrdPrfs", MODE_PRIVATE);
		Boolean b = shrPrefs.getBoolean("LOGIN_VALUE", false);

		if (b) {
			name = shrPrefs.getString("NAME", "");
			gotoNextActivity();
		}

		nameEt = (EditText) findViewById(R.id.editText1);
		skipBt = (Button) findViewById(R.id.button1);

		skipBt.setEnabled(false);

		// perform action after clicking the skip button
		skipBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				gotoNextActivity();
			}
		});

		// perform action after clicking Go key
		nameEt.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub

				if (actionId == EditorInfo.IME_ACTION_GO) {

					name = nameEt.getText().toString();

					if (name.equals("")) {
						Toast.makeText(MainActivity.this,
								"Please enter a name", Toast.LENGTH_SHORT)
								.show();
					} else {
						Editor e = shrPrefs.edit();
						e.putBoolean("LOGIN_VALUE", true);
						e.putString("NAME", name);
						e.commit();
						gotoNextActivity();
					}

				}
				return false;
			}
		});
	}

	// method for stating new activity
	private void gotoNextActivity() {
		// TODO Auto-generated method stub

		// Intent for starting Events Activity
		Intent i = new Intent(MainActivity.this, EventsActivity.class);
		i.putExtra("NAME", name);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
