package com.yogi.code.exercise.model;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.yogi.code.exercise.enums.RGBEnum;

public class PairEntriesHolderSingleton {
	
	private static final ConcurrentLinkedDeque<String> REntryHolder = new ConcurrentLinkedDeque<String> ();
	private static final ConcurrentLinkedDeque<String> GEntryHolder = new ConcurrentLinkedDeque<String> ();
	private static final ConcurrentLinkedDeque<String> BEntryHolder = new ConcurrentLinkedDeque<String> ();
	
	private static final HashMap<RGBEnum, ConcurrentLinkedDeque<String>> dequeHolder = 
			new HashMap<RGBEnum, ConcurrentLinkedDeque<String>> ();
	
	private PairEntriesHolderSingleton() {
		//Prevent object instantiation
	}
	
	private static class SingletonHolder{
		private static final PairEntriesHolderSingleton entriesHolder = new PairEntriesHolderSingleton();
	}
	
	public static PairEntriesHolderSingleton getInstance() {
		dequeHolder.put(RGBEnum.R, REntryHolder);
		dequeHolder.put(RGBEnum.G, GEntryHolder);
		dequeHolder.put(RGBEnum.B, BEntryHolder);
		return SingletonHolder.entriesHolder;
	}	
	
	/**
	 * @return the rEntryHolder
	 */
	public ConcurrentLinkedDeque<String> getREntryHolder() {
		return dequeHolder.get(RGBEnum.R);
	}

	/**
	 * @return the gEntryHolder
	 */
	public ConcurrentLinkedDeque<String> getGEntryHolder() {
		return dequeHolder.get(RGBEnum.G);
	}

	/**
	 * @return the bEntryHolder
	 */
	public ConcurrentLinkedDeque<String> getBEntryHolder() {
		return dequeHolder.get(RGBEnum.B);
	}
	
	public ConcurrentLinkedDeque<String> getQueueForRootRGB (RGBEnum rootRGB) {
		return dequeHolder.get(rootRGB);
	}

}
