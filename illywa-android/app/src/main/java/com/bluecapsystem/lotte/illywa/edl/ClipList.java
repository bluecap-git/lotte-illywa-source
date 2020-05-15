package com.bluecapsystem.lotte.illywa.edl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Clip 을 관리 하는 리스트
 */
public class ClipList extends ArrayList<Clip> {

	private static final long serialVersionUID = 6702116021739364119L;

	/**
	 * 클립 목록을 검색한다
	 *
	 * @param condition 검색 조건
	 * @param <T>       return type
	 * @return 조건에 맞는 클립 목록
	 */
	public <T extends Clip> List<T> findList(final ClipFindCondition condition) {
		final Set<Predicate<Clip>> predicateList = condition.getPredicates();
		Stream<Clip> stream = this.stream();

		for (final Predicate<Clip> p : predicateList) {
			stream = stream.filter(p);
		}
		return (List<T>) stream.collect(Collectors.toList());
	}


	/**
	 * id 로 클립을 찾는다
	 *
	 * @param clipId clip id
	 * @param <T>    return type
	 * @return 검색된 clip
	 */
	public <T extends Clip> T findOne(final String clipId) {
		return (T) this.stream().filter(clip -> clip.getClipId().equals(clipId))
				.findFirst().orElse(null);
	}
}

