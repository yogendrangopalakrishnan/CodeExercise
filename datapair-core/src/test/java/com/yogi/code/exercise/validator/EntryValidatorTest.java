package com.yogi.code.exercise.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntryValidatorTest {

	@Test
	public void testHappyPath() {
		assertTrue(EntryValidator.validate("R1_1"));
	}
	
	@Test
	public void testRainyDayScenario() {
		assertFalse(EntryValidator.validate("R1_1a"));
	}
	
	@Test
	public void testLargeUniqueId() {
		assertTrue(EntryValidator.validate("R1_11111111"));
	}
	
	@Test
	public void testNullString() {
		assertFalse(EntryValidator.validate(null));
	}

}
