package com.yogi.code.exercise.gateway;

import java.util.concurrent.ConcurrentLinkedDeque;

import com.yogi.code.exercise.concurrent.ChannelPopulatorThread;
import com.yogi.code.exercise.enums.ChannelEnum;
import com.yogi.code.exercise.model.IncomingChannel;
import com.yogi.code.exercise.processor.ChannelEntryProcessor;

public class ExerciseGateway {
	
	public static final String[] inputToChannelOne = new String[]{"R1_1","R1_2","R1_3","B1_4","B1_8","G1_5"}; 
	public static final String[] inputToChannelTwo = new String[]{"B2_6","B2_8","R2_9","G2_10","B2_7","R2_20"}; 

	public static void main(String[] args) {
		
		IncomingChannel channelOne = new IncomingChannel(ChannelEnum.CHANNEL_ONE);		
		ChannelPopulatorThread threadForChannelOne = new ChannelPopulatorThread(channelOne, inputToChannelOne);
		
		IncomingChannel channelTwo = new IncomingChannel(ChannelEnum.CHANNEL_TWO);		
		ChannelPopulatorThread threadForChannelTwo = new ChannelPopulatorThread(channelTwo, inputToChannelTwo);
		
		threadForChannelOne.start();
		threadForChannelTwo.start();
		
		Runtime.getRuntime().addShutdownHook(new ExerciseGateway.ShutdownHookThread());
	}
	
	private static class ShutdownHookThread extends Thread {

		@Override
		public void run() {
			
			ChannelEntryProcessor processor = ChannelEntryProcessor.getInstance();
			
			//Print List of all incoming and accepted entries
			ConcurrentLinkedDeque<String> IncomingEntriesQueue = processor.getIncomingentriesqueue();
			
			String inputEntriesInSequence = "";
			for (String entry : IncomingEntriesQueue) {
				inputEntriesInSequence += " - " + entry;
			}
			
			System.out.println("Incoming and accepted entries in sequence " + inputEntriesInSequence);
			
			//List of all pairs matched so far
			ConcurrentLinkedDeque<String> matchedEntriesQueue = processor.getMatchedentriesqueue();
			
			String pairEntriesInSequence = "";
			for (String entry : matchedEntriesQueue) {
				pairEntriesInSequence += " - " + entry;
			}
			
			System.out.println("Generated pair entries in sequence " + pairEntriesInSequence);
			
		}
		
	}

}
