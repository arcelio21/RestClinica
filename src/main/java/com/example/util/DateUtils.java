package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateUtils {

	@Value("${jwt.timezone}")
	private String TIMEZONE;

	private SimpleDateFormat simpleDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone(this.TIMEZONE));

		return sdf;
	}

	public String getDateString(){
		Date now = new Date();
		return simpleDateFormat().format(now);
	}

	public Long getDateMillis() {
		Date now = new Date();
		String srtDate = simpleDateFormat().format(now);
		Date strNow = new Date();

		try {

			strNow = simpleDateFormat().parse(srtDate);
		} catch (ParseException e) {
			DateUtils.log.error("Error de conversion ");
		}

		return strNow.getTime();
	}
}
