package com.couchpotato.couchpotato;

import java.util.List;

public class User {

	public static String username;
	public static String password;
	public static List<String> watched;
	public static List<String> toWatch;
	
	public static void addWatched(String tvshow) {
		watched.add(tvshow);
	}
	public static void addToWatch(String tvshow) {
		toWatch.add(tvshow);
	}
	public User(String s, String p, List<String> a, List<String> b) {
		username = s;
		password = p;
		watched = a;
		toWatch = b;
	}
}
