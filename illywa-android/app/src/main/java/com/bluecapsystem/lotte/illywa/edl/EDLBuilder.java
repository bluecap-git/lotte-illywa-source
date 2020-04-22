package com.bluecapsystem.lotte.illywa.edl;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * EDL 을 생성 로드 하고
 * Layer 와 Clip 을 생성 한다
 */
public class EDLBuilder {

	private static EDL GLOVAL_EDL;

	/**
	 * @param pre ID 생성 접두사
	 * @return 생성된 UUID
	 */
	public String createID(final String pre) {
		final UUID uuid = UUID.randomUUID();

		final String prefix = Optional.ofNullable(pre)
				.filter(StringUtils::isNotBlank)
				.map(str -> String.format("%s-", pre))
				.orElseGet(() -> StringUtils.EMPTY);

		return String.format("%s%s", prefix, uuid.toString());
	}

	public EDL createEDL() {
		return EDLBuilder.GLOVAL_EDL;
	}


}
