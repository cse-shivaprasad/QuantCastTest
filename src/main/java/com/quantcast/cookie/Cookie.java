package com.quantcast.cookie;

import java.time.ZonedDateTime;
import java.util.Date;

public class Cookie {

	public String name;
	public Date date;
	
	public Cookie(String name, String date) {
		this.name = name;
		//TODO : Better way to handle the Data pattern rather than hard coding it to a specific pattern
		this.date = Date.from(ZonedDateTime.parse(date).toInstant());
	}
}
