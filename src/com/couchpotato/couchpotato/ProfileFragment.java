package com.couchpotato.couchpotato;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class ProfileFragment extends Fragment {
 
	public static User myShows;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
 
        //set user title
        TextView tv = (TextView) rootView.findViewById(R.id.textview_username);
        tv.setText(myShows.username);
        
        //show all watched shows
        if(!myShows.watched.isEmpty()) {
        	for(String show:myShows.watched) {
        		String buttonID = "watched_" + show;
        		int resID = getResources().getIdentifier(buttonID, "id", "com.couchpotato.couchpotato");
        		LinearLayout layout = ((LinearLayout)rootView.findViewById(resID));
        		layout.setVisibility(View.VISIBLE);
        	}
        }
        
      //show all watched shows
        if(!myShows.toWatch.isEmpty()) {
        	for(String show:myShows.toWatch) {
        		String buttonID = "wanted_" + show;
        		int resID = getResources().getIdentifier(buttonID, "id", "com.couchpotato.couchpotato");
        		LinearLayout layout = ((LinearLayout)rootView.findViewById(resID));
        		layout.setVisibility(View.VISIBLE);
        	}
        }
        return rootView;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	 //show all watched shows
        if(!myShows.watched.isEmpty()) {
        	for(String show:myShows.watched) {
        		String buttonID = "watched_" + show;
        		int resID = getResources().getIdentifier(buttonID, "id", "com.couchpotato.couchpotato");
        		LinearLayout layout = ((LinearLayout)this.getView().findViewById(resID));
        		layout.setVisibility(View.VISIBLE);
        	}
        }
        
      //show all watched shows
        if(!myShows.toWatch.isEmpty()) {
        	for(String show:myShows.toWatch) {
        		String buttonID = "wanted_" + show;
        		int resID = getResources().getIdentifier(buttonID, "id", "com.couchpotato.couchpotato");
        		LinearLayout layout = ((LinearLayout)this.getView().findViewById(resID));
        		layout.setVisibility(View.VISIBLE);
        	}
        }
    	
    }
    
    public static void initializeProfile(String s,String p){
    	if(null == myShows) {
    		myShows = new User(s,p,new ArrayList<String>(),new ArrayList<String>());
    	}
    }
}
