package com.bluecapsystem.lotte.illywa.edl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 클립을 찾기 위한 검색 조건
 */
public class ClipFindCondition {

	/** select clip id */
	private String clipId;

	/** select types */
	private Set<ClipTypes> types;

	/**
	 * default constructor
	 */
	public ClipFindCondition() {
		types = Collections.emptySet();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 검색 조건 목록을 생성 환다
	 */
	public Set<Predicate<Clip>> getPredicates() {

		final Set<Predicate<Clip>> predicates = new HashSet<>();

		// ID 검색 조건

		// ID 검색 조건
		Optional.ofNullable(clipId)
				.filter(StringUtils::isNotBlank)
				.ifPresent(id -> {
					predicates.add(clip -> id.equals(clip.getClipId()));
				});

		// 유형 검색
		Optional.ofNullable(this.types) //
				.filter(types -> types != null && types.size() > 0) //
				.ifPresent(types -> { //
					predicates.add(layer -> this.types.stream().filter(type -> type == layer.getType()).count() > 0);
				});

		return predicates;
	}


	/**
	 * @return clip id
	 */
	public String getClipId() {
		return clipId;
	}

	/**
	 * clip id 로 검색 한다
	 *
	 * @param clipId 검색할 clip id
	 * @return this
	 */
	public ClipFindCondition setClipId(final String clipId) {
		this.clipId = clipId;
		return this;
	}


	/**
	 * @return clip types
	 * s
	 */
	public Set<ClipTypes> getTypes() {
		return types;
	}

	/**
	 * @param types 검색할 clip types
	 * @return this
	 */
	public ClipFindCondition setTypes(final Set<ClipTypes> types) {
		this.types = types;
		return this;
	}


}
