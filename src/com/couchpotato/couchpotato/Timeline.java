package com.couchpotato.couchpotato;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.couchpotato.couchpotato.CircularProgressBar.ProgressAnimationListener;

@SuppressLint("NewApi")
public class Timeline extends Activity {
	
	int counter;
	int timeSection;
	long start = 0;
	Random randomGenerator;
	CircularProgressBar c;
	Chronometer chronometer;
	int voteCount = 0;
	
	 //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
        	//choose random friend
        	int friend = (randomGenerator.nextInt(5));
        	friend++;
        	
        	//choose and upvote or down vote
        	boolean vote = (randomGenerator.nextBoolean());
        	
        	//determine which friend is picked and increment its votecount accordingly
        	String textID = "votetext_friend" + Integer.toString(friend);
    		int resID = getResources().getIdentifier(textID, "id", "com.couchpotato.couchpotato");
    		//TextView = ((TextView)rootView.findViewById(resID));
    		TextView tv = ((TextView)findViewById(resID));
    		
    		//sets text of the text view.  large and complicated I know...just believe
    		try {
    			if(vote) { //upvote
    				tv.setText(Integer.toString((Integer.parseInt(tv.getText().toString()))+ 1));
    				c.setTitle(Integer.toString(++voteCount));
    			}
    			else {//downvote
    				tv.setText(Integer.toString((Integer.parseInt(tv.getText().toString()))- 1));
    				c.setTitle(Integer.toString(--voteCount));
    			}
    		} catch(NumberFormatException nfe) {
    		   System.out.println("Could not parse " + nfe);
    		} 
        	
    		//choose a random time for new vote
    		friend = randomGenerator.nextInt(5);
    		friend++;
            timerHandler.postDelayed(this, friend*1000);
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		//recieve intent
		Intent intent = getIntent();
		//change action bar title
		getActionBar().setTitle(intent.getStringExtra(Browse.TVShowName));
		
		c = (CircularProgressBar) findViewById(R.id.circularprogressbar4);
		c.animateProgressTo(0, 100, new ProgressAnimationListener() {
			
			@Override
			public void onAnimationStart() {				
			}
			
			
			@Override
			public void onAnimationProgress(int progress) {
				//c2.setTitle(progress + "%");
			}
			
			@Override
			public void onAnimationFinish() {
				c.setSubTitle("done");
				chronometer.stop();
			}
		});
		
		chronometer = ((Chronometer)findViewById(R.id.chrono));
		chronometer.start();
		//set counter 
		counter = 0;
		//set Timesection of the clock
		//simulates 5 minutes
		timeSection = 1;
		
		randomGenerator = new Random();
		
		//set timer to update friends votes
        timerHandler.postDelayed(timerRunnable, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	@Override
	public void onPause() {
	   super.onPause();
	   timerHandler.removeCallbacks(timerRunnable);
	}

	public void updateP(View v) {
		c.setTitle(Integer.toString(++voteCount));	
	}
	
	public void updateN(View v) {
		c.setTitle(Integer.toString(--voteCount));	
	}
	/*
	public boolean Vote(View v) {
		if (v.getId() == R.id.button_upvote) {
			counter++;
			return true;
		}
		else if (v.getId() == R.id.button_downvote) {
			counter--;
			return true;
		}
		return false;
	}
	
	public void changeTime(View v){
		timeSection++;
		ImageView clock = (ImageView) findViewById(R.id.imageview_clock);
		
		//if the clock needs to reset
		if(timeSection == 13){
			timeSection = 1;
			clock.setImageResource(R.drawable.couchpotato_clock01);
		}
		else {
			String clockid;
			int clckID;
			
			//update the clock according to the various names Max gave
			if (timeSection < 10) {
				//update clock
				clockid = "couchpotato_clock0" + Integer.toString(timeSection);
				clckID = getResources().getIdentifier(clockid, "drawable", "com.couchpotato.couchpotato");
        		clock.setImageResource(clckID);
			}
			else {
				clockid = "couchpotato_clock" + Integer.toString(timeSection);
				clckID = getResources().getIdentifier(clockid, "drawable", "com.couchpotato.couchpotato");
        		clock.setImageResource(clckID);
			}
		}
		
		//generate a random number to calculate vote 
		String voteValue = Integer.toString((randomGenerator.nextInt(26))-13+counter);
		
		//update the corresponding textbox
		
	}

	private ImageView findViewById(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/
}
