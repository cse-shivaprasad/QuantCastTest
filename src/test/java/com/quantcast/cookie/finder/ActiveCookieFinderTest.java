package com.quantcast.cookie.finder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.quantcast.cookie.Cookie;

public class ActiveCookieFinderTest {
	
	private ActiveCookieFinder activeCookieFinder;
	
	@Before
	public void setup() {
		activeCookieFinder = new ActiveCookieFinder();
	}
	
	@Test
	public void should_return_all_the_entries_with_same_frequency() throws Exception{
		Cookie[] cookies = new Cookie[] {
					new Cookie("AAA","2018-12-09T14:19:00+00:00"),
					new Cookie("BBB","2018-12-09T14:19:00+00:00"),
					new Cookie("CCC","2018-12-09T14:19:00+00:00"),
					new Cookie("DDD","2018-12-09T14:19:00+00:00"),
					new Cookie("EEE","2018-12-09T14:19:00+00:00")
				};
			
		List<Cookie> cookiesList = Arrays.asList(cookies);
		Date cookiesByDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-09");
		
		List<String> actualOutput = activeCookieFinder.findMostActiveCookies(cookiesList, cookiesByDate);
		
		Assert.assertEquals(actualOutput.size(), 5);
		
		Collections.sort(actualOutput);
		String[] expectedOutput = new String[] {"AAA","BBB","CCC","DDD","EEE"};
		Assert.assertArrayEquals(actualOutput.toArray(new String[5]), expectedOutput);
	}
	
	@Test
	public void should_return_only_one_entry() throws Exception{
		
		Cookie[] cookies = new Cookie[] {
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("DDD","2018-12-09T14:19:00+00:00"),
				new Cookie("EEE","2018-12-09T14:19:00+00:00")
			};
		
		List<Cookie> cookiesList = Arrays.asList(cookies);
		Date cookiesByDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-09");
		
		List<String> actualOutput = activeCookieFinder.findMostActiveCookies(cookiesList, cookiesByDate);
		
		Assert.assertEquals(actualOutput.size(), 1);
		
		Collections.sort(actualOutput);
		String[] expectedOutput = new String[] {"AAA"};
		Assert.assertArrayEquals(actualOutput.toArray(new String[1]), expectedOutput);
	}
	
	@Test
	public void should_return_only_two_equal_frequency_entries() throws Exception{
		
		Cookie[] cookies = new Cookie[] {
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("BBB","2018-12-09T14:19:00+00:00"),
				new Cookie("BBB","2018-12-09T14:19:00+00:00"),
				new Cookie("EEE","2018-12-09T14:19:00+00:00")
			};
		
		List<Cookie> cookiesList = Arrays.asList(cookies);
		Date cookiesByDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-09");
		
		List<String> actualOutput = activeCookieFinder.findMostActiveCookies(cookiesList, cookiesByDate);
		
		Assert.assertEquals(actualOutput.size(), 2);
		
		Collections.sort(actualOutput);
		String[] expectedOutput = new String[] {"AAA","BBB"};
		Assert.assertArrayEquals(actualOutput.toArray(new String[2]), expectedOutput);
		
	}
	
	@Test
	public void should_return_empty_entries_for_non_matching_date() throws Exception{
		
		Cookie[] cookies = new Cookie[] {
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("AAA","2018-12-09T14:19:00+00:00"),
				new Cookie("BBB","2018-12-09T14:19:00+00:00"),
				new Cookie("BBB","2018-12-09T14:19:00+00:00"),
				new Cookie("EEE","2018-12-09T14:19:00+00:00")
			};
		
		List<Cookie> cookiesList = Arrays.asList(cookies);
		Date cookiesByDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-10");
		
		List<String> actualOutput = activeCookieFinder.findMostActiveCookies(cookiesList, cookiesByDate);
		
		Assert.assertEquals(actualOutput.size(), 0);
	}
	
	
}
