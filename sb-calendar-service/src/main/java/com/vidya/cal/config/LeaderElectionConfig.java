package com.vidya.cal.config;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class LeaderElectionConfig {

	private static final Logger logger = LoggerFactory.getLogger(LeaderElectionConfig.class);
	
	@Bean(name = "jGroupChannel")
	public JChannel jGroupChannel() throws Exception {
		// Using default config, udp.xml will be used from the jar
		JChannel channel = new JChannel();
		channel.setReceiver(new Receiver() {
			@Override
			public void receive(Message msg) {
				logger.info("Received message from " + msg.getSrc() + ": " + msg.getObject());
			}
			@Override
			public void viewAccepted(View newView) {
				logger.info("One more node joined/left: " + newView);
				logger.info("Current node size are: " + newView.getMembers().size());
			}
		});
		return channel;
	}

}