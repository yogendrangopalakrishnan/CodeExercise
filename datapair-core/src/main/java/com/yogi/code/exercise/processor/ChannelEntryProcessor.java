package com.yogi.code.exercise.processor;

import java.util.concurrent.ConcurrentLinkedDeque;

import com.yogi.code.exercise.strategy.IExecutionStrategy;
import com.yogi.code.exercise.strategy.SynchronousExecutionStrategy;
import com.yogi.code.exercise.validator.EntryValidator;

/*
 * A singleton processor which composes a strategy for execution.
 * @author Yogendran Gopalakrishnan -- yogendran_g@programmer.net
 * 
 */

public class ChannelEntryProcessor {
	
	//In the real world, this would have been injected based on need
	private IExecutionStrategy strategy = new SynchronousExecutionStrategy();
	
	//List of all incoming and accepted entries
	private static final ConcurrentLinkedDeque<String> IncomingEntriesQueue = new ConcurrentLinkedDeque<String>();
	
	//List of all pairs matched so far
	private static final ConcurrentLinkedDeque<String> matchedEntriesQueue = new ConcurrentLinkedDeque<String>();
	
	private EntryValidator validator = new EntryValidator();
	
	protected ChannelEntryProcessor () {
		//Private constructor for singleton
	}
	
	private static class SingletonHolder{
		private static final ChannelEntryProcessor entriesHolder = new ChannelEntryProcessor();
	}
	
	public static ChannelEntryProcessor getInstance() {
		return SingletonHolder.entriesHolder;
	}
	
	public void validateAndAccept(String entry) throws Exception {
		if (EntryValidator.validate(entry))
		{
			IncomingEntriesQueue.add(entry);
			String pair = strategy.execute(entry);
			
			if (pair != null) {
				matchedEntriesQueue.add(pair);
			}
		}
	}

	/**
	 * @return the incomingentriesqueue
	 */
	public static ConcurrentLinkedDeque<String> getIncomingentriesqueue() {
		return IncomingEntriesQueue;
	}

	/**
	 * @return the matchedentriesqueue
	 */
	public static ConcurrentLinkedDeque<String> getMatchedentriesqueue() {
		return matchedEntriesQueue;
	}

}
