package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;
import com.bluecapsystem.lotte.illywa.edl.utils.TimeUtils;
import com.google.gson.annotations.Expose;

import java.util.Map;

public class VideoClip extends Clip {


	@Expose
	private String startTC = "00:00:00";

	@Expose
	private String endTC = "00:00:00";


	public VideoClip(final String filePath) {
		super(IDGenerator.createID(Clip.PREFIX_CLIP_ID));
		this.setType(ClipTypes.Video)
				.setFilePath(filePath);
				
		this.startTC = TimeUtils.DEFAULT_TC;
		this.startTC = TimeUtils.DEFAULT_TC;
	}

	@Override
	public Map<String, Object> getDetail() {

		return null;
	}

	/**
	 * @return 영상의 총 길이를 알려 준다
	 */
	public String getDuration() {
		return TimeUtils.getDuration(this.startTC, this.endTC);

	}

	/**
	 * @return 비디오 시작 시간
	 */
	public String getStartTC() {
		return startTC;
	}

	/**
	 * @param startTC 비디오 시작 시간
	 * @return this
	 */
	public VideoClip setStartTC(final String startTC) {
		this.startTC = startTC;
		return this;
	}

	/**
	 * @return 비디오 끝 시간
	 */
	public String getEndTC() {
		return endTC;
	}

	/**
	 * @param endTC 비디오 끝 시간
	 * @return this
	 */
	public VideoClip setEndTC(final String endTC) {
		this.endTC = endTC;
		return this;
	}
}
