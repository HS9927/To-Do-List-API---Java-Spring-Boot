package com.project.To_Do_List_API___Java_Spring_Boot.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	
	private static DateTimeFormatter formatterYYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


	public String funLocalDateTimeToDateTime (String localDateTime) {
		return "";
	}
	
	public static String TimestampNow () {
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		return timestamp.toLocalDateTime().format(formatterYYYYMMDDHHMMSS);
	}
	
}
