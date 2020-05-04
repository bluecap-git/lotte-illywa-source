package com.bluecapsystem.lotte.illywa.edl;

import java.util.List;

/**
 * Contains video editing information
 */
public class EDL {

	/** edl id */
	private String edlId;

	/** tag information of media */
	private String tag;

	/** list of clips be used */
	private List<Clip> clips;

	/**
	 * create a edl
	 *
	 * @param edlId set the edl id
	 */
	public EDL(final String edlId) {
	}

	/**
	 * @return get edl id
	 */
	public String getEdlId() {
		return edlId;
	}

	/**
	 * @return edl 의 tag 정보를 반환
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag edl 의 tag 정보
	 * @return thsi
	 */
	public EDL setTag(final String tag) {
		this.tag = tag;
		return this;
	}

	/**
	 * @return edl 의 클립 목록
	 */
	public List<Clip> getClips() {
		return clips;
	}


	/**
	 * clip 을 추가 한다
	 *
	 * @param clip
	 */
	public void AddClip(final Clip clip) {
		this.clips.add(clip);
	}
}
