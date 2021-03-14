package com.vidya.cal.le.service;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LeaderElectionService {

	private static final Logger logger = LoggerFactory.getLogger(LeaderElectionService.class);

	@Autowired
	@Qualifier(value = "jGroupChannel")
	JChannel jGroupChannel;

	public Boolean amILeader() {
		Address address = jGroupChannel.getView().getMembers().get(0);
		if (address.equals(jGroupChannel.getAddress())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public void connect() {
		try {
			// If not exists creates LeaderElectionCluster. If exists, it joins the cluster.
			jGroupChannel.connect("LeaderElectionCluster");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		jGroupChannel.close();
		logger.info("Disconnected from the jGroup cluster.");
	}

	/**
	 * Always first joined node is master/leader
	 */
	public void checkLeaderStatus() {
		Address address = jGroupChannel.getView().getMembers().get(0);
		if (address.equals(jGroupChannel.getAddress())) {
			logger.info("I'm (" + jGroupChannel.getAddress() + ") the leader");
		} else {
			logger.info("I'm (" + jGroupChannel.getAddress() + ") not the leader");
		}
	}
}