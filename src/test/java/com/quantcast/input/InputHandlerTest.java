package com.quantcast.input;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.quantcast.cookie.Cookie;


public class InputHandlerTest {
	
	private InputHandler inputHandler;
	
	@Before
	public void setup() {
		inputHandler = new InputHandler();
	}
	
	@Test
	public void should_populate_InputArgs_object() {
		String[] args = new String[] {"-f","src/main/resources/test-inputs/input1.csv","-d", "2018-12-08"};
		InputArgs inputArgs = inputHandler.getInputArgs(args);
		
		String expectedFilePath = "src/main/resources/test-inputs/input1.csv";
		String expectedDate = "2018-12-08";
		
		Assert.assertEquals(expectedFilePath, inputArgs.filePath);
		Assert.assertEquals(expectedDate, inputArgs.cookiesByDate);
	}
	
	@Test
	public void should_parse_input_file_with_header_and_return_cookie_list() throws Exception{
		String[] args = new String[] {"-f","src/main/resources/test-inputs/input1.csv","-d", "2018-12-08"};
		InputArgs inputArgs = inputHandler.getInputArgs(args);
		List<Cookie> inputCookies = inputHandler.processInput(inputArgs); 
		Assert.assertEquals(8, inputCookies.size());
	}
	
	@Test
	public void validate_valid_date() {
		String[] args = new String[] {"-f","src/main/resources/test-inputs/input1.csv","-d", "2018-12-08"};
		InputArgs inputArgs = inputHandler.getInputArgs(args);
		boolean actual = inputHandler.isValidDate(inputArgs.cookiesByDate, "yyyy-MM-dd");
		Assert.assertTrue(actual);
	}
	
	@Test
	public void validate_invalid_date() {
		String[] args = new String[] {"-f","src/main/resources/test-inputs/input1.csv","-d", "2018-13-08"};
		InputArgs inputArgs = inputHandler.getInputArgs(args);
		boolean actual = inputHandler.isValidDate(inputArgs.cookiesByDate, "yyyy-MM-dd");
		Assert.assertFalse(actual);
		
	}
	
	@Test
	public void validate_valid_filePath() {
		String[] args = new String[] {"-f","src/main/resources/test-inputs/input1.csv","-d", "2018-13-08"};
		InputArgs inputArgs = inputHandler.getInputArgs(args);
		boolean actual = inputHandler.isValidFilePath(inputArgs.filePath);
		Assert.assertTrue(actual);
	}
	
	@Test
	public void validate_invalid_filePath() {
		String[] args = new String[] {"-f","main/resources/test-inputs/input1.csv","-d", "2018-13-08"};
		InputArgs inputArgs = inputHandler.getInputArgs(args);
		boolean actual = inputHandler.isValidFilePath(inputArgs.filePath);
		Assert.assertFalse(actual);
	}	

	
}
