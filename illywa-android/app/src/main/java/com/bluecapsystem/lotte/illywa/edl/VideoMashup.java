package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.NonNull;
import com.bluecapsystem.lotte.illywa.common.utils.TimeUtils;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VideoMashup extends Mashup<VideoClip> {

	/** clip 의 시작점 */
	@Expose
	private String clipStartTC;

	/** clip 의 끝점 */
	@Expose
	private String clipEndTC;


	/**
	 * @param clip video clip
	 */
	public VideoMashup(final VideoClip clip) {
		super(clip, MashupTypes.Video);
		this.clipStartTC = TimeUtils.DEFAULT_TC;
		this.clipEndTC = TimeUtils.DEFAULT_TC;
	}


	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return clip 의 trim 된 시작점
	 */
	public String getClipStartTC() {
		return clipStartTC;
	}

	/**
	 * clip 의 trim 된 시작점
	 *
	 * @param clipStartTC clip 의 trim 된 시작점
	 * @return this
	 */
	public VideoMashup setClipStartTC(final String clipStartTC) {
		this.clipStartTC = clipStartTC;
		updateEndTc();
		return this;
	}

	/**
	 * @return clip 의 trim 된 끝점
	 */
	public String getClipEndTC() {
		return clipEndTC;
	}

	/**
	 * @param clipEndTC clip 의 trim 된 끝점
	 * @return this
	 */
	public VideoMashup setClipEndTC(final String clipEndTC) {
		this.clipEndTC = clipEndTC;
		updateEndTc();
		return this;
	}


	/**
	 * 시작 / 끝 시간이 변경 되었을때 endTC 를 변경 해 준다
	 */
	private void updateEndTc() {
		// clip 이 변경 되었을 경우 end 시간을 설정해 준다
		final Long duration = TimeUtils.toLong(this.clipEndTC) - TimeUtils.toLong(this.clipStartTC);
		final Long end = TimeUtils.toLong(this.getStartTC()) + duration;
		this.setEndTC(TimeUtils.toTimeCode(end));
	}
}
