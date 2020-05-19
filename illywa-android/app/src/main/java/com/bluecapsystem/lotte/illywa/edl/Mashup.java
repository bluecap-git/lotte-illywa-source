package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.NonNull;
import com.bluecapsystem.lotte.illywa.common.utils.IDGenerator;
import com.bluecapsystem.lotte.illywa.common.utils.TimeUtils;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Layer 에 올라가는 clip 의 편집 정보를 가진다
 */
public class Mashup<T extends Clip> {


	/** 아이디 앞에 붙는 prefix 문자 */
	protected final static String PREFIX_ID = "LAY";

	/** mashup 고유 아이디 */
	@Expose
	private String mashupId;


	/** 시작 시간 */
	@Expose
	private String startTC;

	/** 끝 시간 */
	@Expose
	private String endTC;

	/** 원본의 clip */
	@Expose(serialize = false, deserialize = false)
	private T clip;

	/** 원본 clip id */
	@Expose
	private String clipId;

	/** 실제 사용되는 local file 경로 */
	@Expose
	private String localFilePath;

	/** mashup 유형 */
	@Expose
	private MashupTypes type;


	/**
	 * @param clip 원본 클립 정보
	 */
	public Mashup(final T clip, final MashupTypes type) {
		this.clipId = IDGenerator.createID(Mashup.PREFIX_ID);
		this.clip = clip;
		this.clipId = clip.getClipId();
		this.type = type;

		this.startTC = TimeUtils.DEFAULT_TC;
		this.endTC = TimeUtils.DEFAULT_TC;
	}


	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return mashup 의 길이에 대한 Long 값을 가져온다
	 */
	public Long getDuration() {
		return getEnd() - getStart();
	}


	/**
	 * 영상의 시작 시간을 time 만큼 증감 시킨다
	 *
	 * @param time offset 을 할 시간
	 */
	public void offset(final String time) {
		this.offset(TimeUtils.toLong(time));
	}

	/**
	 * 영상의 시작 시간을 time 만큼 증감 시킨다
	 *
	 * @param time offset 을 할 시간
	 */
	public void offset(final Long time) {
		final long start = this.getStart() + time;
		final long end = this.getDuration() + start;
		this.setStartTC(TimeUtils.toTimeCode(start));
		this.setEndTC(TimeUtils.toTimeCode(end));
	}


	/**
	 * @return mashup 관리 ID
	 */
	public String getMashupId() {
		return mashupId;
	}

	/**
	 * @return layer 상의 {@link Mashup} 시작 시간
	 */
	public String getStartTC() {
		return startTC;
	}

	/**
	 * @return 시작 시간을 가져온다
	 */
	public Long getStart() {
		return TimeUtils.toLong(getStartTC());
	}


	/**
	 * 시작 시간을 변경 한다. 길이에 따른 종료 시간도 변경 된다
	 *
	 * @param startTC layer 상의 {@link Mashup} 시작 시간
	 * @return this
	 */
	public Mashup setStartTC(final String startTC) {
		final long start = TimeUtils.toLong(startTC);
		final long end = this.getDuration() + start;
		this.startTC = startTC;
		this.setEndTC(TimeUtils.toTimeCode(end));
		return this;
	}

	/**
	 * @return layer 상의 {@link Mashup} 끝 시간
	 */
	public String getEndTC() {
		return endTC;
	}

	/**
	 * @return 종료 시간을 가져온다
	 */
	public Long getEnd() {
		return TimeUtils.toLong(getEndTC());
	}


	/**
	 * @param endTC layer 상의 {@link Mashup} 끝 시간
	 * @return this
	 */
	public Mashup setEndTC(final String endTC) {
		this.endTC = endTC;
		return this;
	}

	/**
	 * @return 원본 clip 정보
	 */
	public T getClip() {
		return clip;
	}

	/**
	 * @return 실제 사용되는 파일의 경로
	 */
	public String getLocalFilePath() {
		return localFilePath;
	}

	/**
	 * @param localFilePath 실제 사용되는 파일의 경로
	 * @return this
	 */
	public Mashup setLocalFilePath(final String localFilePath) {
		this.localFilePath = localFilePath;
		return this;
	}

	/**
	 * @return mashup 유형
	 */
	public MashupTypes getType() {
		return type;
	}


}
