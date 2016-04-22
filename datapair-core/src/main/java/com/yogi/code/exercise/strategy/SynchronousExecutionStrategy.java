package com.yogi.code.exercise.strategy;

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
		
		String dequeHeadAsString = queueForRootRGB.peek();
		
		if (dequeHeadAsString != null) {
			RGBEnum deaqueHeadAsEnum = RGBEnum.extractRGBFromEntry(dequeHeadAsString);
			
			if (enumAtHand.equals(deaqueHeadAsEnum)) {
				System.out.println("SynchronousExecutionStrategy :: Adding entry " + entry + " to queue ");
				queueForRootRGB.add(entry);
			} else {
				queueForRootRGB.poll();
				System.out.println("SynchronousExecutionStrategy :: Pairing " + entry + " with " + dequeHeadAsString);
				pairStringToReturn = "{" + entry + "," + dequeHeadAsString + "}";
			}	
		} else {
			System.out.println("SynchronousExecutionStrategy :: Adding entry " + entry + " to queue ");
			queueForRootRGB.add(entry);
		}
		
		return pairStringToReturn;
	}

}
