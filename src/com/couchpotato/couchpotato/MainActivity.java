package com.couchpotato.couchpotato;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.couchpotato.database.FeedReaderContractor.FeedEntry;
import com.couchpotato.database.MyDBHelper;

public class MainActivity extends Activity {
	
	public final static String USERCLASS = "com.couchpotato.couchpotato.userclass";
	public MyDBHelper myDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myDbHelper = MyDBHelper.getInstance(this);
		myDbHelper = hardcodeDatabase(myDbHelper);
		
		Button addButton = (Button) findViewById(R.id.button_signup);  
		addButton.setOnClickListener(new OnClickListener() {

		            @Override
		            public void onClick(View v) {
		                // TODO Auto-generated method stub
		                openDialogue(v);
		            }
		        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void login(View v) {
		Intent intent = new Intent(this, Browse.class);
		String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
		intent.putExtra(USERCLASS,username);
		ProfileFragment.initializeProfile(username,null);
		startActivity(intent);
	}

	private void openDialogue(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		
        builder.setTitle("Sign-up");
		// Get the layout inflater
	    LayoutInflater inflater = this.getLayoutInflater();
	    final View layout = inflater.inflate(R.layout.sign_up, null);
	    
	  
	    // Add action buttons
	     builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   Intent i = new Intent(getApplicationContext(),Browse.class);
	            	   String username = ((EditText)layout.findViewById(R.id.edittext_newusername)).getText().toString();
	            	   i.putExtra(USERCLASS,username);
	            	   
	            	   ProfileFragment.initializeProfile(username,null);
	            	   startActivity(i);
	               }	
	           })
	           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   dialog.cancel();
	               }
	           });      
	    
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(layout);
	    builder.show();
	}
	private MyDBHelper hardcodeDatabase(MyDBHelper db) {
		db = insertDatabase(db,"Game of Thrones","gameofthrones","Fox","6-7pm Fridays","People fight and kill each other.  Not a potato show.","3");
		db = insertDatabase(db,"The Chappelle Show","chappelle","CC","3-3:30pm Weekdays","Im rich!!!!!!!!!!!","4");
		db = insertDatabase(db,"Sportscenter","sportscenter","ESPN","8am-8pm Everyday","Catch All the news in the sports realm all day on repeat forever.","2.5");
		db = insertDatabase(db,"Cosmos:A Spacetime Odyssey","cosmos","Discovery","9-10pm Sunday","Learn about the galaxy and our place in the cosmos.  Host NDT becomes your high school science teacher for an hour.","4");
		db = insertDatabase(db,"Breaking Bad","breakingbad","HBO/AMC","10-11pm Saturday","A high school teacher becomes a drug dealer to provide for his family after his death","3.5");
		db = insertDatabase(db,"South Park","southpark","CC","11pm Fri,Sat","4th graders living in a abnormal world do terrible things","3.4");
		db = insertDatabase(db,"Invader Zim","invzim","NICK","2pm,4pm Wed","An alien is in charge of taking over Earth and proving he is capable of conquering worlds. His sidekick, Gir, is cute and adorable.","2.5");
		return db;
	}
	
	private MyDBHelper insertDatabase(MyDBHelper db,String name,String src,String channel,String air,String des,String rating) {
		// Gets the data repository in write mode
		SQLiteDatabase sqlDB = db.getWritableDatabase();
		
		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_TV_SHOW_NAME, name);
		values.put(FeedEntry.COLUMN_NAME_TV_IMAGE_SRC, src);
		values.put(FeedEntry.COLUMN_NAME_TV_AIR_CHANNEL, channel);
		values.put(FeedEntry.COLUMN_NAME_TV_AIR_TIME, air);
		values.put(FeedEntry.COLUMN_NAME_TV_DESCRIPTION, des);
		values.put(FeedEntry.COLUMN_NAME_YOUR_RATING, rating);
		
		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = sqlDB.insert(
		         FeedEntry.TABLE_NAME,
		         null,
		         values);
		
		return db;
	}
}
