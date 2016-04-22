package com.yogi.code.exercise.enums;

public enum RGBEnum {

	R ("R"), G ("G"), B ("B"),
	R1 ("R1"), G1 ("G1"), B1 ("B1"),
	R2 ("R2"), G2 ("G2"), B2 ("B2");
	
	private String value;
	
	RGBEnum(String value) {
		this.value = value;
	}
	
	public static RGBEnum extractRGBFromEntry (String entry) {
		String rgbPartOfString = entry.substring(0, 2);
		return RGBEnum.valueOf(rgbPartOfString);
	}
	
	public static RGBEnum getAlternatePairToLookFor (String entry) {
		RGBEnum rgbEnum = extractRGBFromEntry(entry);
		return getAlternatePairToLookFor(rgbEnum);
	}
	
	public static RGBEnum getAlternatePairToLookFor (RGBEnum entryAsEnum) {
		
		switch (entryAsEnum) {
		
		case R1 : 
			return R2;
			
		case R2 :
			return R1;
		
		case G1 : 
			return G2;
			
		case G2 : 
			return G1;
			
		case B1 : 
			return B2;
			
		case B2 : 
			return B1; 
			
		default : 
			return null;			
		
		}	
		
	}
	
	public static RGBEnum getRootRGB(String entry) {
		return RGBEnum.valueOf(entry.substring(0, 1));
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	

}
