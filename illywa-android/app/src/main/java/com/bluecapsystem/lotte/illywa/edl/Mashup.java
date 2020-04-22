package com.bluecapsystem.lotte.illywa.edl;

/**
 * Layer 에 올라가는 clip 의 편집 정보를 가진다
 */
public class Mashup {

	/** 시작 시간 */
	private String startTC;

	/** 끝 시간 */
	private String endTC;

	/** 원본의 clip */
	private Clip clip;

	/** 실제 사용되는 local file 경로 */
	private String localFilePath;

	/** mashup 길이 */
	private String duration;

	/** mashup 유형 */
	private MashupTypes type;

	/**
	 * @return layer 상의 {@link Mashup} 시작 시간
	 */
	public String getStartTC() {
		return startTC;
	}

	/**
	 * @param startTC layer 상의 {@link Mashup} 시작 시간
	 * @return this
	 */
	public Mashup setStartTC(final String startTC) {
		this.startTC = startTC;
		return this;
	}

	/**
	 * @return layer 상의 {@link Mashup} 끝 시간
	 */
	public String getEndTC() {
		return endTC;
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
	public Clip getClip() {
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
	 * @return {@link Mashup} 의 사용 길이
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @return {@link Mashup} 의 유형을 지정 한다
	 */
	public MashupTypes getType() {
		return type;
	}

}
