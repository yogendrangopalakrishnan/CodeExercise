package com.yogi.code.exercise.gateway;

import java.util.Collection;

import com.yogi.code.exercise.concurrent.ChannelPopulatorThread;
import com.yogi.code.exercise.enums.ChannelEnum;
import com.yogi.code.exercise.model.IncomingChannel;
import com.yogi.code.exercise.processor.ChannelEntryProcessor;

/*
 * Entry point to the exercise question.
 * @author Yogendran Gopalakrishnan -- yogendran_g@programmer.net
 * 
 * Data is arriving "simultaneously" into our system as two streams via two channels: Channel 1 and Channel 2.    
 * The data could be of three types: R, G and B.  
 * Each data element should have two properties: channelNumber and uniqueID.
 * These three types of Data are arriving in a random sequence on the two channels.
 * Write a program which creates pairs of "same types" arriving on two channels in their "order of arrival".
 * 
 * Channel 1: R1_1 R1_2 R1_3 B1_4 B1_8 G1_5
 * Channel 2: B2_6 B2_8 R2_9 G2_10 B2_7 R2_20
 * 
 * Expected output : (R1_1, R2_9) (B1_4, B2_6) (B1_8, B2_8) (G1_5, G2_10) (R1_2, R2_20)
 * 
 * Assumptions: 
 * 1. No flow control needed for channel.
 */

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
			
			//Print List of all incoming and accepted entries
			Collection<String> IncomingEntriesQueue = ChannelEntryProcessor.getIncomingentriesqueue();
			
			String inputEntriesInSequence = "";
			for (String entry : IncomingEntriesQueue) {
				inputEntriesInSequence += " - " + entry;
			}
			
			System.out.println("Incoming and accepted entries in sequence " + inputEntriesInSequence);
			
			//Print list of all pairs matched so far
			Collection<String> matchedEntriesQueue = ChannelEntryProcessor.getMatchedentriesqueue();
			
			String pairEntriesInSequence = "";
			for (String entry : matchedEntriesQueue) {
				pairEntriesInSequence += " - " + entry;
			}
			
			System.out.println("Generated pair entries in sequence " + pairEntriesInSequence);
			
		}
		
	}

}
