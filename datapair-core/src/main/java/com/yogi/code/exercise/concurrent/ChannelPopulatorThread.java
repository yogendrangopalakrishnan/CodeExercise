package com.yogi.code.exercise.concurrent;

import java.util.Date;
import java.util.Random;

import com.yogi.code.exercise.model.IncomingChannel;

/*
 * Thread used to randomly populate the channel with random wait times.
 * @author Yogendran Gopalakrishnan -- yogendran_g@programmer.net
 * 
 */

public class ChannelPopulatorThread extends Thread {
	
	private final IncomingChannel channel;
	private final String[] input; 
	private final Random randomInstance = new Random();
	private final int waitTimeBound = 2;
	
	public ChannelPopulatorThread(IncomingChannel channel, String[] input) {
		this.channel = channel;
		this.input = input;
	}

	@Override
	public void run() {
		try {
			
			for (int i = 0; i < input.length; i++) {
				channel.append(input[i]);
				int waitTime = getRandomNumber();
				sleep(waitTime * 1000);				
			}		
			
		} catch (Exception ex) {
			System.out.println("Exception adding entry to the channel :: " + ex.getLocalizedMessage());
		}		
	}

	/**
	 * @return the channel
	 */
	public IncomingChannel getChannel() {
		return channel;
	}

	/**
	 * @return the input
	 */
	public String[] getInput() {
		return input;
	}

	
	/**
	 * Generates a random number for the thread to wait
	 */
	public int getRandomNumber() {
		return randomInstance.nextInt(waitTimeBound);
	}




}
