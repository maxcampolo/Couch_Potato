package com.couchpotato.database;

import android.provider.BaseColumns;

public final class FeedReaderContractor {

	    // To prevent someone from accidentally instantiating the contract class,
	    // give it an empty constructor.
	    public FeedReaderContractor() {}

	    /* Inner class that defines the table contents */
	    public static abstract class FeedEntry implements BaseColumns {
	    	public static final String _ID = "id";
	        public static final String TABLE_NAME = "showinformation";
	        public static final String COLUMN_NAME_TV_SHOW_NAME = "tvshowname";
	        public static final String COLUMN_NAME_TV_IMAGE_SRC = "tvimagesrc";
	        public static final String COLUMN_NAME_TV_AIR_CHANNEL = "tvairchannel";
	        public static final String COLUMN_NAME_TV_AIR_TIME = "tvairtime";
	        public static final String COLUMN_NAME_TV_DESCRIPTION = "description";
	        public static final String COLUMN_NAME_YOUR_RATING = "yourrating";
	    }
	
}
