package com.yogi.code.exercise.validator;

/*
 * Used to validate the contents of the incoming 
 * @author Yogendran Gopalakrishnan -- yogendran_g@programmer.net
 * 
 */

public class EntryValidator {
	
	public boolean validate(String entry) {
		return entry.matches("[RGB]\\d_\\d") ? true : false;
	}
	
}
