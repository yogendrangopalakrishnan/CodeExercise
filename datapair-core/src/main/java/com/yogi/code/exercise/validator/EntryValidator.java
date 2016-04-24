package com.yogi.code.exercise.validator;

/*
 * Used to validate the contents of the incoming string entry
 * 
 * @author Yogendran Gopalakrishnan -- yogendran_g@programmer.net
 * 
 */

public class EntryValidator {
	
	public static boolean validate(String entry) {
		
		if (entry == null)
				return false;
		
		return entry.matches("[RGB]\\d_\\d+$") ? true : false;
	}
	
}
