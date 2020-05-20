package com.bluecapsystem.lotte.illywa.project;

import androidx.annotation.NonNull;
import com.bluecapsystem.lotte.illywa.edl.EDL;
import com.bluecapsystem.lotte.illywa.edl.EDLBuilder;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Tag 정보를 관리 한다
 */
public class Tag {

	/** TAG 관리 ID */
	private String tagId;

	/** Tag Name */
	@Expose
	private String name;

	/** 사용할 영상 파일 목록 */
	@Expose
	private List<String> videoFiles;

	/** edl 정보를 저장 하는 json */
	@Expose
	private String edlJson;

	/** 편집 정보 EDL */
	@Expose(serialize = false, deserialize = false)
	private EDL edl;


	/**
	 * @param tagId Tag ID
	 * @param name  Tag 이름
	 */
	public Tag(final String tagId, final String name) {
		this.tagId = tagId;
		this.name = name;
		this.videoFiles = new ArrayList<>();

		this.edl = new EDLBuilder().newEDL();
	}

	@NonNull
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "edlJson");
	}

	/**
	 * {@link EDL} 정보를 json String 형식으로 tag 에 저장 한다
	 *
	 * @return this
	 */
	public Tag saveEDL() {
		this.edlJson = Optional.ofNullable(this.edl) //
				.filter(e -> e != null) //
				.flatMap(e -> Optional.of(e.toJson()))
				.orElse(null);
		return this;
	}

	/**
	 * @return Tag ID
	 */
	public String getTagId() {
		return tagId;
	}


	/**
	 * @return Tag 이름
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Tag 이름
	 * @return this
	 */
	public Tag setName(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return 편집 EDL 정보
	 */
	public EDL getEdl() {
		return edl;
	}


	/**
	 * @param edl 편집 EDL 정보를 가진다
	 * @return this
	 */
	public Tag setEdl(final EDL edl) {
		this.edl = edl;
		return this;
	}

	/**
	 * @return 사용할 비디오 파일 목록
	 */
	public List<String> getVideoFiles() {
		return videoFiles;
	}


	/**
	 * @param videoFiles 사용할 비디오 파일 목록
	 * @return this
	 */
	public Tag setVideoFiles(final List<String> videoFiles) {
		this.videoFiles = videoFiles;
		return this;
	}


	/**
	 * @return edl json
	 */
	public String getEdlJson() {
		return edlJson;
	}
}
