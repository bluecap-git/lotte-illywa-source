package com.bluecapsystem.lotte.illywa.edl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Mashup 을 관리 하는 리스트
 */
public class MashupList extends ArrayList<Mashup> {

	private static final long serialVersionUID = 6702116021739364119L;

	/**
	 * Mashup 목록을 검색한다
	 *
	 * @param condition 검색 조건
	 * @param <T>       return type
	 * @return 조건에 맞는 Mashup 목록
	 */
	public <T extends Mashup> List<T> findList(final MashupFindCondition condition) {
		final Set<Predicate<Mashup>> predicateList = condition.getPredicates();
		Stream<Mashup> stream = this.stream();

		for (final Predicate<Mashup> p : predicateList) {
			stream = stream.filter(p);
		}
		return (List<T>) stream.collect(Collectors.toList());
	}


	/**
	 * id 로 Mashup 을 찾는다
	 *
	 * @param clipId Mashup Id
	 * @param <T>    return type
	 * @return 검색된 mashup
	 */
	public <T extends Mashup> T findOne(final String clipId) {
		return (T) this.stream().filter(mashup -> mashup.getMashupId().equals(clipId))
				.findFirst().orElse(null);
	}
}

