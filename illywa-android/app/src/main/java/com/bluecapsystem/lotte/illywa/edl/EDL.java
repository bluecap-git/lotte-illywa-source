package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Contains video editing information
 */
public class EDL {

	/** edl id */
	@Expose
	private String edlId;

	/** tag information of media */
	@Expose
	private String tag;

	/** list of clips be used */
	@Expose
	private final ClipList clips;

	/** list fo layers */
	@Expose
	private final LayerList layers;

	/**
	 * create a edl
	 *
	 * @param edlId set the edl id
	 */
	public EDL(final String edlId) {
		clips = new ClipList();
		layers = new LayerList();
	}

	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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
	 * @return this
	 */
	public EDL setTag(final String tag) {
		this.tag = tag;
		return this;
	}

	/**
	 * @return edl 의 클립 목록
	 */
	public ClipList getClips() {
		return clips;
	}


	/**
	 * clip 을 추가 한다
	 *
	 * @param clip
	 * @return this
	 */
	public EDL addClip(final Clip clip) {
		this.clips.add(clip);
		return this;
	}


	/**
	 * layer 목록을 가져온다
	 *
	 * @return layer 목록
	 */
	public LayerList getLayers() {
		return layers;
	}
}
