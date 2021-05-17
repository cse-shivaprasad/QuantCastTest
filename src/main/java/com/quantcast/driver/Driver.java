package com.quantcast.driver;

import com.quantcast.orchestrator.FlowOrchestrator;

public class Driver {

	public static void main(String[] args) {
		
		try {
			new FlowOrchestrator().orchestrateFlow(args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
  