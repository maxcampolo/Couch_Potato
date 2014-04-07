package com.couchpotato.couchpotato;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.couchpotato.database.FeedReaderContractor.FeedEntry;
import com.couchpotato.database.MyDBHelper;


@SuppressLint("NewApi")
public class TVShow extends Activity {

	// Define a projection that specifies which columns from the database
	// you will actually use after this query.
	public static String[] projection = {
	    //FeedEntry._ID,
	    FeedEntry.COLUMN_NAME_TV_SHOW_NAME,
	    FeedEntry.COLUMN_NAME_TV_IMAGE_SRC,
	    FeedEntry.COLUMN_NAME_TV_AIR_CHANNEL,
	    FeedEntry.COLUMN_NAME_TV_AIR_TIME,
	    FeedEntry.COLUMN_NAME_TV_DESCRIPTION,
	    FeedEntry.COLUMN_NAME_YOUR_RATING 
	    };

	public static String tag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tvshow);
		getActionBar().setDisplayShowTitleEnabled(false);
		
		Button addButton = (Button) findViewById(R.id.button_addto);  
		addButton.setOnClickListener(new OnClickListener() {

		            @Override
		            public void onClick(View v) {
		                // TODO Auto-generated method stub
		                openDialogue(v);
		            }
		        });

		
		//recieve intent
		Intent intent = getIntent();
		tag = intent.getStringExtra(Browse.TVShowName);
		
		//get the database
		MyDBHelper mDbHelper = MyDBHelper.getInstance(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		/* How you want the results sorted in the resulting Cursor
		String sortOrder =
		    FeedEntry.COLUMN_NAME_TV_SHOW_NAME + " DESC"; */

		Cursor c = db.query(
		    FeedEntry.TABLE_NAME,  // The table to query
		    projection,                               // The columns to return
		    FeedEntry.COLUMN_NAME_TV_IMAGE_SRC + " =? ",                                // The columns for the WHERE clause
		    new String[] {intent.getStringExtra(Browse.TVShowName)},                            // The values for the WHERE clause
		    null,                                     // don't group the rows
		    null,                                     // don't filter by row groups
		    null,
		    null										// The sort order
		    );

		if (c != null) {
	        c.moveToFirst();
		}
		/*Set Tv Show Name
		String TVShowName = intent.getStringExtra(Browse.TVShowName); */
		TextView TVSName = (TextView)findViewById(R.id.textview_tvshowname);
		TVSName.setText(c.getString(0));
		
		//set tv show image
		ImageView TVimage = (ImageView)findViewById(R.id.imageview_tvshowimage);
		TVimage.setImageResource(getImageId(this, c.getString(1)));
		
		//Set TV show air timetextview_airtime
		TextView TVAT = (TextView)findViewById(R.id.textview_airtime);
		TVAT.setText(c.getString(2) + ": " + c.getString(3));
		
		//Set TV description
		TextView des = (TextView)findViewById(R.id.textview_tvdescription);
		des.setText(c.getString(4));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tvshow, menu);
		return true;
	}
	
	public static int getImageId(Context context, String imageName) {
	    return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
	}	
	
	
	private void openDialogue(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TVShow.this);
		
		         alertDialogBuilder.setTitle(((TextView)findViewById(R.id.textview_tvshowname)).getText());
		         alertDialogBuilder.setPositiveButton("Watched",new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog,int id) {
		                	// close the alert box and put a Toast to the user
		                    dialog.cancel();
		                    ProfileFragment.myShows.addWatched(tag);
		                    Toast.makeText(getApplicationContext(), "Added to your Watched list",
		                            Toast.LENGTH_LONG).show();
		                }
		            });
		         alertDialogBuilder.setNegativeButton("Want To Watch",new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog,int id) {

		                    // close the alert box and put a Toast to the user
		                    dialog.cancel();
		                    ProfileFragment.myShows.addToWatch(tag);
		                    Toast.makeText(getApplicationContext(), "Added to your Want to Watch list",
		                            Toast.LENGTH_LONG).show();
		                }
		            });
		         AlertDialog alertDialog = alertDialogBuilder.create();
		
		         // show alert
		
		         alertDialog.show();
	
		    }
}
