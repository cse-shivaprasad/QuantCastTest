package com.quantcast.cookie.finder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.quantcast.cookie.Cookie;

public class ActiveCookieFinder {
	
	
	public List<String> findMostActiveCookies(List<Cookie> cookies, Date byDate){
		
		// 1. Filter cookies by matching date
		List<Cookie> filteredCookies = findCookiesByDate(cookies, byDate, true);
		List<String> activeCookies = new LinkedList<String>();
		if(filteredCookies == null || filteredCookies.size() == 0) 
			return activeCookies;
		
		//2. Build occurrence map based on cookie name 
		Map<String,Integer> occuranceMap = new HashMap<String,Integer>();
		for(Cookie cookie : filteredCookies) {
			occuranceMap.put(cookie.name, occuranceMap.getOrDefault(cookie.name,0)+1);
		}
		
		//3. Compare the Map entries using Priority Queue to achieve sub linear time complexity
		PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((x,y)-> x.getValue().compareTo(y.getValue()));
		for(Map.Entry<String, Integer> entry : occuranceMap.entrySet()) {
			if(queue.isEmpty() || queue.peek().getValue() == entry.getValue()) {
				queue.offer(entry);
			}else if(queue.peek().getValue() < entry.getValue()) {
				queue.remove();
				queue.offer(entry);
			}
		}
		
		//4. Build the output from Priority Queue
		for(Map.Entry<String, Integer> entry : queue) {
			activeCookies.add(entry.getKey());
		}
		
		return activeCookies;
	}
	
	public List<Cookie> findCookiesByDate(List<Cookie> cookies, Date byDate, boolean onlyDate){
		
		List<Cookie> filteredCookies = new ArrayList<>();
		for(Cookie cookie : cookies) {
			if(isSameDate(cookie.date,byDate)) {
				filteredCookies.add(cookie);
			}
		}
		return filteredCookies;
	}
	
	public boolean isSameDate(Date date1, Date date2) {
		
		return (date1.getDay() == date2.getDay() && 
				date1.getMonth() == date2.getMonth() && 
				date1.getYear() == date2.getYear());
	}
	
}
