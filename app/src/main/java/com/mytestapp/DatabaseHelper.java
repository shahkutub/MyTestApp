package com.mytestapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "profileinfo";

	// Table Names
	private static final String TABLE_INFO = "profiletable";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PHONE = "phone";


	// Table Create Statements
	// Document table create statement


	//Barcode Table Create sql

	private static final String CREATE_TABLE_INFO = "CREATE TABLE "
			+ TABLE_INFO + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " TEXT,"
			+ KEY_PHONE + " TEXT" + ")";



	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	//Brands Table Create sql



	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables

		db.execSQL(CREATE_TABLE_INFO);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);


		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new DocumentTableInfo


	public void addContact(Generic info) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, info.getName());
		values.put(KEY_PHONE, info.getPhone());
		db.insert(TABLE_INFO, null, values);
		db.close(); // Closing database connection
	}




	// Getting All BarCodeTableInfo
	public List<Generic> getContact() {
		List<Generic> infoList = new ArrayList<Generic>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_INFO;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Generic document = new Generic();
				document.setId(cursor.getString(0));
				document.setName(cursor.getString(1));
				document.setPhone(cursor.getString(2));
				infoList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return infoList;
	}

	public void deleteContact() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_INFO);
		db.close();
	}
}
