package com.bluecapsystem.lotte.illywa.edl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class LayerFindCondition {

	private String layerId;

	private Set<LayerTypes> types;


	/**
	 * default construct
	 */
	public LayerFindCondition() {
		types = Collections.emptySet();
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Set<Predicate<Layer>> getPredicates() {

		final Set<Predicate<Layer>> predicates = new HashSet<>();

		// ID 검색 조건
		Optional.ofNullable(layerId)
				.filter(StringUtils::isNotBlank)
				.ifPresent(id -> {
					predicates.add(layer -> id.equals(layer.getLayerId()));
				});

		// 유형 검색
		Optional.ofNullable(this.types) //
				.filter(types -> types != null && types.size() > 0) //
				.ifPresent(types -> { //
					predicates.add(layer -> this.types.stream().filter(type -> type == layer.getType()).count() > 0);
				});

		return predicates;
	}
}
