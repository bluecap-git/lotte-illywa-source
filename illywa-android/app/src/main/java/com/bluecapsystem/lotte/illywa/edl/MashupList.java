package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.common.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Mashup 을 관리 하는 리스트
 */
public class MashupList<T extends Mashup> extends ArrayList<T> {

	private static final long serialVersionUID = 6702116021739364119L;

	/**
	 * Mashup 목록을 검색한다
	 *
	 * @param condition 검색 조건
	 * @return 조건에 맞는 Mashup 목록
	 */
	public List<T> findList(final MashupFindCondition condition) {
		final Set<Predicate<T>> predicateList = condition.getPredicates();
		Stream<T> stream = this.stream();

		for (final Predicate<T> p : predicateList) {
			stream = stream.filter(p);
		}
		return stream.collect(Collectors.toList());
	}


	/**
	 * id 로 Mashup 을 찾는다
	 *
	 * @param clipId Mashup Id
	 * @return 검색된 mashup
	 */
	public T findOne(final String clipId) {
		return this.stream().filter(mashup -> mashup.getMashupId().equals(clipId))
				.findFirst().orElse(null);
	}


	@Override
	public boolean add(final T mashup) {
		final String startTC = Optional.of(this) //
				.filter(m -> m.size() > 0) //
				.flatMap(m -> {
					return Optional.of(m.get(m.size() - 1).getEndTC());
				}).orElse(TimeUtils.DEFAULT_TC);
		mashup.offset(startTC);
		return super.add(mashup);
	}


	@Override
	public void add(final int index, final T mashup) {
		final String startTC = Optional.of(this) //
				.filter(m -> m.size() > 0) //
				.filter(m -> index - 1 >= 0) //
				.flatMap(m -> {
					return Optional.of(m.get(index - 1).getEndTC());
				}).orElse(TimeUtils.DEFAULT_TC);
		mashup.offset(startTC);

		super.add(index, mashup);
	}
	
}

