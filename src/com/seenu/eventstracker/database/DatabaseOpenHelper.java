package com.seenu.eventstracker.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "EventsDB";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "Events";

	// columns of the database
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_ENTRY_TYPE = "entry_type";
	public static final String COLUMN_IMAGE_URL = "image_url";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TIME = "time";

	private final Context context;
	private SQLiteDatabase database;

	// database path
	private static String DATABASE_PATH;

	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub

		this.context = context;
		DATABASE_PATH = context.getFilesDir().getParentFile().getPath()
				+ "/databases/";
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void create() throws IOException {
		boolean check = checkDataBase();

		SQLiteDatabase db_Read = null;

		// Creates empty database default system path
		db_Read = this.getWritableDatabase();
		db_Read.close();
		try {
			if (!check) {
				copyDataBase();
			}
		} catch (IOException e) {
			throw new Error("Error copying database");
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DATABASE_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (SQLiteException e) {
			// database does't exist yet.
			e.printStackTrace();
		}

		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * 
	 */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = DATABASE_PATH + DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	/** open the database */
	public void open() throws SQLException {
		String myPath = DATABASE_PATH + DATABASE_NAME;
		database = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	/** close the database */
	@Override
	public synchronized void close() {
		if (database != null)
			database.close();
		super.close();
	}

	// retrieves a particular user
	public Cursor getUser(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, TABLE_NAME, new String[] {
				COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION, COLUMN_ENTRY_TYPE,
				COLUMN_IMAGE_URL, COLUMN_DATE, COLUMN_TIME }, COLUMN_ID + " = "
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	// retrieves all users
	public Cursor getAllUsers() {
		return database.query(TABLE_NAME, new String[] { COLUMN_ID,
				COLUMN_NAME, COLUMN_LOCATION, COLUMN_ENTRY_TYPE,
				COLUMN_IMAGE_URL, COLUMN_DATE, COLUMN_TIME }, null, null, null,
				null, null);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
