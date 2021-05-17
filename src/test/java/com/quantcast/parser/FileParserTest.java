package com.quantcast.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.quantcast.cookie.Cookie;

public class FileParserTest {
	
	@Test
	public void should_parse_input_file_with_header_and_return_cookie_list() throws Exception{
		List<Cookie> inputCookies = FileParser.parseInputFile(new File("src/main/resources/test-inputs/input1.csv"), true);
		Assert.assertEquals(8, inputCookies.size());
	}
	
	@Test
	public void should_parse_input_file_without_header_and_return_cookie_list() throws Exception{
		List<Cookie> inputCookies = FileParser.parseInputFile(new File("src/main/resources/test-inputs/input2.csv"), false);
		Assert.assertEquals(9, inputCookies.size());
	} 
	
	@Test
	public void should_throw_IOException() {
		IOException exception = Assert.assertThrows(IOException.class,
									() -> FileParser.parseInputFile(new File("main/resources/test-inputs/input3.csv"), true));
		Assert.assertTrue(exception != null);
	} 
}
