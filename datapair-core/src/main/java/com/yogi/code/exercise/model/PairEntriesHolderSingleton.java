package com.yogi.code.exercise.model;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PairEntriesHolderSingleton {
	
	private final ConcurrentLinkedDeque<String> REntryHolder = new ConcurrentLinkedDeque<String> ();
	private final ConcurrentLinkedDeque<String> GEntryHolder = new ConcurrentLinkedDeque<String> ();
	private final ConcurrentLinkedDeque<String> BEntryHolder = new ConcurrentLinkedDeque<String> ();
	
	private PairEntriesHolderSingleton() {
		//Prevent object instantiation
	}
	
	private static class SingletonHolder{
		private static final PairEntriesHolderSingleton entriesHolder = new PairEntriesHolderSingleton();
	}
	
	public static PairEntriesHolderSingleton getInstance() {
		return SingletonHolder.entriesHolder;
	}	
	
	/**
	 * @return the rEntryHolder
	 */
	public ConcurrentLinkedDeque<String> getREntryHolder() {
		return REntryHolder;
	}

	/**
	 * @return the gEntryHolder
	 */
	public ConcurrentLinkedDeque<String> getGEntryHolder() {
		return GEntryHolder;
	}

	/**
	 * @return the bEntryHolder
	 */
	public ConcurrentLinkedDeque<String> getBEntryHolder() {
		return BEntryHolder;
	}

}
