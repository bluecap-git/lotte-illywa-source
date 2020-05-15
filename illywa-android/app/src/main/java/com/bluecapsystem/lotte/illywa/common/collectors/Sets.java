package com.bluecapsystem.lotte.illywa.common.collectors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Set Util
 */
public class Sets {

	/**
	 * create new {@link Set}
	 *
	 * @param <T>   the class of the objects in the array
	 * @param param the array by which the set will be backed
	 * @return a set view of the specified array
	 */
	@SafeVarargs
	public static <T> Set<T> newSet(final T... param) {
		return new HashSet<T>(Arrays.asList(param));
	}
}
