package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.Nullable;
import com.bluecapsystem.lotte.illywa.common.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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


	@Override
	public boolean add(final Mashup mashup) {

		final String startTC = Optional.of(this) //
				.filter(m -> m.size() > 0) //
				.flatMap(m -> {
					return Optional.of(m.get(m.size() - 1).getEndTC());
				}).orElse(TimeUtils.DEFAULT_TC);
		mashup.offset(startTC);

		return super.add(mashup);
	}


	@Override
	public void add(final int index, final Mashup mashup) {
		final String startTC = Optional.of(this) //
				.filter(m -> m.size() > 0) //
				.filter(m -> index - 1 >= 0) //
				.flatMap(m -> {
					return Optional.of(m.get(index - 1).getEndTC());
				}).orElse(TimeUtils.DEFAULT_TC);
		mashup.offset(startTC);
		final Long duration = mashup.getDuration();

		// index 뒤의 건을 duration 만큼 미루어 준다
		IntStream.range(index, this.size())
				.forEach(idx -> {
					final Mashup m = this.get(idx);
					m.offset(m.getStart() + duration);
				});

		super.add(index, mashup);
	}


	@Override
	public Mashup remove(final int index) {
		final Mashup mashup = this.get(index);
		final Long duration = mashup.getDuration();
		IntStream.range(index + 1, this.size())
				.forEach(idx -> {
					final Mashup m = this.get(idx);
					m.offset(m.getStart() - duration);
				});
		return super.remove(index);
	}

	@Override
	public boolean remove(@Nullable final Object o) {
		final int idx = this.indexOf(o);
		return this.remove(idx) != null;
	}
}

