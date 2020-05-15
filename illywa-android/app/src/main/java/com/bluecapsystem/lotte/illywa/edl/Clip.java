package com.bluecapsystem.lotte.illywa.edl;

import com.google.gson.annotations.Expose;

import java.util.Map;

public abstract class Clip {

	/** clip 아이디 앞에 붙는 prefix 문자 */
	protected final static String PREFIX_CLIP_ID = "CLP";

	/** clip id */
	@Expose
	private String clipId;
	
	/** clip file path */
	@Expose
	private String filePath;

	/** clip type */
	@Expose
	private ClipTypes type;


	/** default construct */
	public Clip(final String clipId) {
		this.clipId = clipId;
	}

	/**
	 * @return clip id
	 */
	public String getClipId() {
		return clipId;
	}

	/**
	 * @return local file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath local file path
	 * @return {@link Clip}
	 */
	protected <T extends Clip> T setFilePath(final String filePath) {
		this.filePath = filePath;
		return (T) this;
	}

	/**
	 * @return the type of clip
	 */
	public ClipTypes getType() {
		return type;
	}

	/**
	 * @param type set the type of clip
	 * @param <T>
	 * @return this
	 */
	protected <T extends Clip> T setType(final ClipTypes type) {
		this.type = type;
		return (T) this;
	}


	/**
	 * @return Get details of a clip file
	 */
	public abstract Map<String, Object> getDetail();

}
