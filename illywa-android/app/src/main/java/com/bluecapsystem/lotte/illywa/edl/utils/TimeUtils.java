package com.bluecapsystem.lotte.illywa.edl.utils;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

	/**
	 * duration 을 string 형 timecode 로 변경 한다
	 *
	 * @param duration milliseconds duration
	 * @return HH:mm:ss.fff 형식의 타임코드
	 */
	public static String toTimeCode(final Long duration) {
		return DurationFormatUtils.formatDuration(duration, "HH:mm:ss.SSS");
	}


	/**
	 * timecode 를 duration 으로 변경 한다
	 *
	 * @param timecode "HH:mm:ss.SSS" 형식의 time code
	 * @return milliseconds duration
	 * @throws Throwable string format exception
	 */
	public static Long toDuration(final String timecode) throws Throwable {
		final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		final Date dt = fmt.parse(timecode);
		return dt.getTime();
	}
}
