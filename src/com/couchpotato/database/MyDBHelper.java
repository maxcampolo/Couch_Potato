package com.couchpotato.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.couchpotato.database.FeedReaderContractor.FeedEntry;

public class MyDBHelper extends SQLiteOpenHelper {
	
	private static MyDBHelper sInstance;
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_SHOW_ENTRIES =
	    "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
	   /* FeedEntry._ID + " INTEGER PRIMARY KEY," + */
	    FeedEntry.COLUMN_NAME_TV_SHOW_NAME + TEXT_TYPE + COMMA_SEP +
	    FeedEntry.COLUMN_NAME_TV_IMAGE_SRC + TEXT_TYPE + COMMA_SEP +
	    FeedEntry.COLUMN_NAME_TV_AIR_CHANNEL + TEXT_TYPE + COMMA_SEP +
	    FeedEntry.COLUMN_NAME_TV_AIR_TIME + TEXT_TYPE + COMMA_SEP +
	    FeedEntry.COLUMN_NAME_TV_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
	    FeedEntry.COLUMN_NAME_YOUR_RATING + TEXT_TYPE + 
	    " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
	
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CouchPotatoReader.db";
	
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public static MyDBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you 
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
          sInstance = new MyDBHelper(context.getApplicationContext());
        }
        return sInstance;
      }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SHOW_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
    

}
