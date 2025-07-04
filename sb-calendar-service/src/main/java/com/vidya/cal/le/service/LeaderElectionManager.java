package com.vidya.cal.le.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LeaderElectionManager {

	@Autowired
	@Qualifier(value = "leaderElectionService")
	private LeaderElectionService leaderElectionService;

	@PostConstruct
	void start() {
		leaderElectionService.connect();
	}

	@PreDestroy
	public void disconnect() {
		leaderElectionService.disconnect();
	}
	
	@Async
	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void leaderElectionStatus() {
		leaderElectionService.checkLeaderStatus();
	}

	public boolean amILeader() {
		return leaderElectionService.amILeader();
	}
}