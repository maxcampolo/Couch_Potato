package com.couchpotato.couchpotato;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.couchpotato.adapter.TabsPagerAdapter;

@SuppressLint("NewApi")
public class Browse extends FragmentActivity implements ActionBar.TabListener{

	public final static String TVShowName = "com.couchpotato.couchpotato.TVShowName";
	public static String MyUser = "Name";
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
    private String[] tabs = {"Profile","Browse"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		
		//grab action bar
		viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
        
        Intent intent = getIntent();
        
        //Set Profile Name
        MyUser = intent.getStringExtra(MainActivity.USERCLASS);
        
	}
	
	@Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//onClick method to bring up TVShow Description page
    public void openTVShow(View v) {
    	ImageView img = (ImageView) findViewById(v.getId());
    	String out = (String) img.getTag();
    	Intent nextScreen = new Intent(this, TVShow.class);
    	nextScreen.putExtra(TVShowName, out);
    	startActivity(nextScreen);	
    }
	

}
