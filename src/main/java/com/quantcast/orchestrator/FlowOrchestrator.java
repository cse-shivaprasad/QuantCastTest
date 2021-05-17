package com.quantcast.orchestrator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.quantcast.cookie.Cookie;
import com.quantcast.cookie.finder.ActiveCookieFinder;
import com.quantcast.input.InputArgs;
import com.quantcast.input.InputHandler;
import com.quantcast.meta.OutputWriterMeta;
import com.quantcast.output.OutputWriter;

public class FlowOrchestrator {

	public void orchestrateFlow(String[] runTimeArgs) throws Exception{
		
		//1. Get inputArgs
		InputHandler inputHandler = new InputHandler();
		InputArgs inputArgs = inputHandler.getInputArgs(runTimeArgs);
		
		//2. Get all the input cookies parsed from external file
		List<Cookie> cookieList = inputHandler.processInput(inputArgs);
		
		//3. Find the list of active cookies 
		//TODO : To standardize the String date handling in InputArgs
		Date cookiesByDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputArgs.cookiesByDate);
		List<String> mostActiveCookies = new ActiveCookieFinder().findMostActiveCookies(cookieList, cookiesByDate);
		
		//4. Write output
		OutputWriter<List<String>> writer = OutputWriterMeta.valueOf(OutputWriterMeta.CONSOLE.name()).getOutputWriter();
		writer.write(mostActiveCookies);
	}
	
}
