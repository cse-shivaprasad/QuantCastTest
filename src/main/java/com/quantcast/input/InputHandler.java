package com.quantcast.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.quantcast.cookie.Cookie;
import com.quantcast.parser.FileParser;

public class InputHandler {
	
	
	public InputArgs getInputArgs(String[] runTimeArgs) {
		InputArgs args = new InputArgs();
		JCommander commander = JCommander.newBuilder().addObject(args).build();
		commander.parse(runTimeArgs);
		return args;
	}
	

	public List<Cookie> processInput(InputArgs args) throws IllegalArgumentException, IOException{
		
		if(!validInputArgs(args.filePath, args.cookiesByDate))
			throw new IllegalArgumentException();
		
		return FileParser.parseInputFile(new File(args.filePath), true);
	}
	
	public boolean validInputArgs(String filePath, String date) throws IllegalArgumentException {
		
		//TODO : Validate it with Annotations and to drive the hard coded date format value from config
		return isValidFilePath(filePath) && isValidDate(date, "YYYY-MM-DD");
	}
	
	
	public boolean isValidFilePath(String filePath) throws IllegalArgumentException{
		
		if(filePath == null || filePath.trim().isEmpty())
			throw new IllegalArgumentException();
		
		return Files.exists(Paths.get(filePath));
	}
	
	public boolean isValidDate(String date,String format) throws IllegalArgumentException{
		
		if(date == null || date.trim().isEmpty())
			throw new IllegalArgumentException();
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
		
		try {
			dateFormatter.parse(date);
		}catch(DateTimeParseException e) {
			return false;
		}
		return true;
	}
}
