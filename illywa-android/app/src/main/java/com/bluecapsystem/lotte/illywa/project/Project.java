package com.bluecapsystem.lotte.illywa.project;

import androidx.annotation.NonNull;
import com.bluecapsystem.lotte.illywa.common.utils.IDGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 상품 정보 영상 제작을 위한 Project 정보
 */
public class Project {

	/** 프로젝트 ID */
	@Expose
	private String projectId;

	/** 프로젝트 이름 */
	@Expose
	private String name;

	/** 선택한 TAG 목록 */
	@Expose
	private List<Tag> tags;


	/**
	 * @param name 이름
	 */
	public Project(final String name) {
		this.projectId = IDGenerator.createID("PROJ");
		this.name = name;
		this.tags = new ArrayList<>();
	}

	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	/**
	 * 프로젝트의 내용을 json 으로 변경 한다
	 *
	 * @return project json format string
	 */
	public String toJson() {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}

	/**
	 * @return 프로젝트 아이디
	 */
	public String getProjectId() {
		return projectId;
	}


	/**
	 * @return 프로젝트명
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 프로젝트 명
	 * @return this
	 */
	public Project setName(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return TAG 목록
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags TAG 목록
	 * @return this
	 */
	public Project setTags(final List<Tag> tags) {
		this.tags = tags;
		return this;
	}
}
