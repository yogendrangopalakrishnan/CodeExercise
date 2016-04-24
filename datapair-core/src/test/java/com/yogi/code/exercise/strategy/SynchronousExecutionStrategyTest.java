package com.yogi.code.exercise.strategy;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yogi.code.exercise.enums.RGBEnum;
import com.yogi.code.exercise.model.PairEntriesHolderSingleton;

public class SynchronousExecutionStrategyTest {
	
	private static SynchronousExecutionStrategy strategy = new SynchronousExecutionStrategy();

	@BeforeClass
	public static void setUp() throws Exception {
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		//Populate R queue with channel entries 2
		
		ConcurrentLinkedDeque<String> testQueueR = 
				PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(RGBEnum.R);
		
		testQueueR.add("R2_3");
		testQueueR.add("R2_4");
		testQueueR.add("R2_1");
		
		//Populate G queue with channel entries 1
		
		ConcurrentLinkedDeque<String> testQueueG = 
				PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(RGBEnum.G);
		
		testQueueG.add("G1_9");
		testQueueG.add("G1_4");
		testQueueG.add("G1_3");
		
		//Keeping B queue empty to test scenario of empty channel entries and strategy copes with it
	}

	@Test
	public void testInflowHappyPath() throws Exception {
		
		ConcurrentLinkedDeque<String> testQueueR = 
				PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(RGBEnum.R);
		
		String returnEntry = strategy.execute("R1_9");
		assertEquals(returnEntry, "{R1_9,R2_3}");		
		
		assertEquals(testQueueR.size(), 2);
		
		returnEntry = strategy.execute("R1_1");
		assertEquals(returnEntry, "{R1_1,R2_4}");	
		
		returnEntry = strategy.execute("R1_8");
		assertEquals(returnEntry, "{R1_8,R2_1}");	
		
		assertEquals(testQueueR.size(), 0);
		
		returnEntry = strategy.execute("R1_7");
		
		assertEquals(testQueueR.size(), 1);
		
		String headOfRQueue = testQueueR.poll();
		assertEquals(RGBEnum.R1, RGBEnum.extractRGBFromEntry(headOfRQueue));
		
	}
	
	@Test
	public void testFlowUnderLoad() throws Exception {
		
		ConcurrentLinkedDeque<String> testQueueG = 
				PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(RGBEnum.G);
		
		for (int i = 0 ; i < 50 ; i++) {
			strategy.execute("G2"+"_" + i); 
		}
		
		assertEquals(testQueueG.size(), 47);		
		
		
		//Make sure there are no G1 entries 
		
		assertTrue(validateCorrectRGBEntriesInQueue(testQueueG, RGBEnum.G2));
		
		//Populate with sizeable G1 entries and make sure the requisite pairing count happens
		
		for (int i = 0 ; i < 30 ; i++) {
			strategy.execute("G1"+"_" + i); 
		}
		
		assertEquals(testQueueG.size(), 17);
		
		//Make sure there are no G1 entries 
		
		assertTrue(validateCorrectRGBEntriesInQueue(testQueueG, RGBEnum.G2));		
		
	}
	
	@Test
	public void testWhenChannelContentsEmptyForSpecificRGB() throws Exception {
		
		ConcurrentLinkedDeque<String> testQueueB = 
				PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(RGBEnum.B);
		
		String returnEntry = strategy.execute("B1_9");
		
		//Make sure the incoming entry is null
		assertNull(returnEntry);
		
		returnEntry = strategy.execute("B2_9");
		
		assertEquals(returnEntry, "{B1_9,B2_9}");	
		
		//Make sure the queue is now zero in size
		assertEquals(testQueueB.size(), 0);
		
		//Make sure the queues of the other two RGB entries remain the same in size.
		
		Object[] queueAsArray = PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(RGBEnum.G).toArray();
		
		for(int i =0; i < queueAsArray.length ; i++) {
			String entryAtHand = (String) queueAsArray[i];
			System.out.println("CHECK :: " + entryAtHand);
		}

	}
	
	private static boolean validateCorrectRGBEntriesInQueue(ConcurrentLinkedDeque<String> testQueue, RGBEnum checkForEnum) {
		
		Object[] queueAsArray = testQueue.toArray();
		
		for(int i =0; i < queueAsArray.length ; i++) {
			String entryAtHand = (String) queueAsArray[i];
			if (!RGBEnum.extractRGBFromEntry(entryAtHand).equals(checkForEnum)) {
				return false;
			}
		}
		
		return true;
	}

}
