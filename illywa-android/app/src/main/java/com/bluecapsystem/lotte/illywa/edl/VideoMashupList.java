package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.Nullable;

import java.util.stream.IntStream;


/**
 * Mashup 을 관리 하는 리스트
 */
public class VideoMashupList extends MashupList<VideoMashup> {


	private static final long serialVersionUID = 3135079534827169126L;


	@Override
	public void add(final int index, final VideoMashup mashup) {
		super.add(index, mashup);

		// index 이후의 건의 모든 clip의 시간을 뒤로 미루어 준다
		final Long duration = mashup.getDuration();
		// index 뒤의 건을 duration 만큼 미루어 준다
		IntStream.range(index + 1, this.size())
				.forEach(idx -> {
					final Mashup m = this.get(idx);
					m.offset(m.getStart() + duration);
				});
	}


	@Override
	public VideoMashup remove(final int index) {
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

