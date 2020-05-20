package com.bluecapsystem.lotte.illywa.common.utils;

public class ExceptionUtil {

	/**
	 * throw runtime exception
	 *
	 * @param message exception message
	 * @param <T>     return type
	 * @return not return
	 */
	public static <T extends Object> T throw_(final String message) {
		throw new RuntimeException(message);
	}
}
