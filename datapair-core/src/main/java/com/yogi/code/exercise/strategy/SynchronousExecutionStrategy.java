package com.yogi.code.exercise.strategy;

/*
 * Handles the incoming entries from the processor in a synchronous manner.
 * All the entries for R1 or R2 will be in the same queue. 
 * 
 * Pseudo code:
 * 1. Peek first entry in the root RGB queue
 * 2. If current incoming entry is R1 then check if head is R2
 * 3. If R2 then add generate pair, pop the entry
 * 4. If not R2 then add the current R1 into the queue
 * 
 * @author Yogendran Gopalakrishnan -- yogendran_g@programmer.net
 * 
 */

import java.util.concurrent.ConcurrentLinkedDeque;

import com.yogi.code.exercise.enums.RGBEnum;
import com.yogi.code.exercise.model.PairEntriesHolderSingleton;

public class SynchronousExecutionStrategy implements IExecutionStrategy {

	@Override
	public String execute(String entry) throws Exception {		
		
		return executeSynchStrategy(entry);
	}
	
	private String executeSynchStrategy(String entry) throws Exception {
		
		String pairStringToReturn = null;
		
        RGBEnum rootRGBAtHand = RGBEnum.getRootRGB(entry);		
		RGBEnum enumAtHand = RGBEnum.extractRGBFromEntry(entry);
		
		ConcurrentLinkedDeque<String> queueForRootRGB = 
				PairEntriesHolderSingleton.getInstance().getQueueForRootRGB(rootRGBAtHand);
		
		//Check the first entry to decide if incoming entry is paired or enqueued
		String dequeHeadAsString = queueForRootRGB.peek();
		
		if (dequeHeadAsString != null) {
			RGBEnum deaqueHeadAsEnum = RGBEnum.extractRGBFromEntry(dequeHeadAsString);
			
			if (enumAtHand.equals(deaqueHeadAsEnum)) {
				System.out.println("SynchronousExecutionStrategy :: Adding entry " + entry + " to queue ");
				queueForRootRGB.add(entry);
			} else {
				//Remove the entry now that it has been paired.
				queueForRootRGB.poll();
				
				pairStringToReturn = enumAtHand.getValue().endsWith("1") ? 
						"{" + entry + "," + dequeHeadAsString + "}" : "{" + dequeHeadAsString + "," + entry + "}";
				
				System.out.println("SynchronousExecutionStrategy :: Found Pair " + pairStringToReturn);
			}	
		} else {
			System.out.println("SynchronousExecutionStrategy :: Adding entry " + entry + " to queue ");
			queueForRootRGB.add(entry);
		}
		
		return pairStringToReturn;
	}

}
