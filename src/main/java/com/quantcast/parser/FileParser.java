package com.quantcast.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.quantcast.cookie.Cookie;

public class FileParser {
	
	public static List<Cookie> parseInputFile(File file, boolean isHeaderIncluded) throws IOException{
		
		List<String> rows = Files.readAllLines(Paths.get(file.getAbsolutePath()));
		List<Cookie> inputCookies = new ArrayList<>();
		
		for(int i=0; i<rows.size(); i++) {
			if(isHeaderIncluded && i== 0)
				continue;
			String[] columns = rows.get(i).split(",");
			inputCookies.add(new Cookie(columns[0], columns[1]));
		}
		return inputCookies;
	}
}
