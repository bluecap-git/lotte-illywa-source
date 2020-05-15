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
	 * timecode 를 long duration 으로 변경 한다
	 *
	 * @param timecode "HH:mm:ss.SSS" 형식의 time code
	 * @return milliseconds duration
	 */
	public static Long toLong(final String timecode) {
		final Date dt;
		try {
			final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");
			fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
			dt = fmt.parse(timecode);
		} catch (final Throwable th) {
			throw new RuntimeException("time format error", th);
		}
		return dt.getTime();
	}


	/**
	 * 시간 사이 길이를 구한다
	 *
	 * @param start 시작 시간
	 * @param end   종료 시간
	 * @return 시간 길이
	 */
	public static String getDuration(final String start, final String end) {
		final Long duration = TimeUtils.toLong(end) - TimeUtils.toLong(start);
		return toTimeCode(duration);
	}


}
