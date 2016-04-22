package com.yogi.code.exercise.enums;

public enum RGBEnum {

	R (1),
	G (2),
	B (3);	
	
	private int colorCode;
	
	RGBEnum(int colorHashCode) {
		this.colorCode = colorHashCode;
	}
}
