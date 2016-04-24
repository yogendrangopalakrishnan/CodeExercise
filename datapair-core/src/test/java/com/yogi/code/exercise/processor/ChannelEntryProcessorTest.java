package com.yogi.code.exercise.processor;

import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.yogi.code.exercise.strategy.IExecutionStrategy;
import com.yogi.code.exercise.strategy.SynchronousExecutionStrategy;

public class ChannelEntryProcessorTest {
	
	private static ChannelEntryProcessor processor;
	
	
	@Before
	public void setUp() throws Exception {
		processor = ChannelEntryProcessor.getInstance();
	}

	@Test
	public void testInvalidEntryToProcessor() throws Exception {
		ChannelEntryProcessor.getInstance().validateAndAccept("aaaa");
		assertEquals(0, processor.getIncomingentriesqueue().size());
	}
	
	@Test
	public void testValidEntryToProcessor() throws Exception {
		String entry = "R1_1";
		IExecutionStrategy strategy = Mockito.mock(IExecutionStrategy.class);
		when(strategy.execute(entry)).thenReturn(entry);
		processor.validateAndAccept(entry);
		assertEquals(1, processor.getIncomingentriesqueue().size());
		processor.getIncomingentriesqueue().pop();
	}

}
