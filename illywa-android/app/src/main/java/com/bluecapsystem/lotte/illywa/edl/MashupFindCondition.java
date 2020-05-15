package com.bluecapsystem.lotte.illywa.edl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class MashupFindCondition {

	private String mashupId;

	/**
	 * default construct
	 */
	public MashupFindCondition() {
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 검색 조건을 생성 한다
	 *
	 * @return Mashup 검색 조건
	 */
	public Set<Predicate<Mashup>> getPredicates() {

		final Set<Predicate<Mashup>> predicates = new HashSet<>();

		// ID 검색 조건
		Optional.ofNullable(mashupId)
				.filter(StringUtils::isNotBlank)
				.ifPresent(id -> {
					predicates.add(mashup -> id.equals(mashup.getMashupId()));
				});

		return predicates;
	}
}
