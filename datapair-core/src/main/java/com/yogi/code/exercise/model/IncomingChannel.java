package com.yogi.code.exercise.model;

import com.yogi.code.exercise.enums.ChannelEnum;
import com.yogi.code.exercise.processor.ChannelEntryProcessor;

public class IncomingChannel {	
	
	
	
	private static final ChannelEntryProcessor processor = ChannelEntryProcessor.getInstance();
	
	private ChannelEnum channel;
	
	public IncomingChannel(ChannelEnum channel) {
		this.setChannelNumber(channel);
	}
	
	public void append (String entry) throws RuntimeException {
		System.out.println("INCOMING CHANNEL " + channel.name() + " :: Accepting Entry :: " + entry);
		
		try {
			processor.validateAndAccept(entry);
		} catch (Exception ex) {
			System.out.println("There was an exception ");
		}
		
	}

	/**
	 * @return the channelNumber
	 */
	public ChannelEnum getChannelNumber() {
		return channel;
	}

	/**
	 * @param channelNumber the channelNumber to set
	 */
	public void setChannelNumber(ChannelEnum channel) {
		this.channel = channel;
	}

	/**
	 * @return the channel
	 */
	public ChannelEnum getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(ChannelEnum channel) {
		this.channel = channel;
	}

}
