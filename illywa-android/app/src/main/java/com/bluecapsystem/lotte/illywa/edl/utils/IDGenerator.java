package com.bluecapsystem.lotte.illywa.edl.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.UUID;


/**
 * UUID 를 생성 한다
 */
public class IDGenerator {

	/**
	 * @param pre ID 생성 접두사
	 * @return 생성된 UUID
	 */
	public static String createID(final String pre) {
		final UUID uuid = UUID.randomUUID();

		final String prefix = Optional.ofNullable(pre)
				.filter(StringUtils::isNotBlank)
				.map(str -> String.format("%s-", pre))
				.orElseGet(() -> StringUtils.EMPTY);

		return String.format("%s%s", prefix, uuid.toString());
	}

	/**
	 * @return 생성된 UUID
	 */
	public static String createID() {
		return IDGenerator.createID(null);
	}
}
