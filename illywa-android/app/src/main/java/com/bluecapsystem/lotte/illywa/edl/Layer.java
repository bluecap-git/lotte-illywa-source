package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.common.utils.IDGenerator;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Layer {

	/** 아이디 앞에 붙는 prefix 문자 */
	protected final static String PREFIX_ID = "LAY";

	/** Layer 고유 아이디 */
	@Expose
	private String layerId;

	/** 레이어 유형 */
	@Expose
	private LayerTypes type;

	@Expose
	private MashupList mashups;

	/**
	 * @param type Layer 유형
	 */
	public <T extends Mashup> Layer(final LayerTypes type, final Class<T> mashupType) {
		this.layerId = IDGenerator.createID(Layer.PREFIX_ID);
		this.type = type;

		switch (type) {
			case Video:
				this.mashups = new VideoMashupList();
				break;
			default:
				this.mashups = new MashupList<T>();
				break;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	/**
	 * @return Layer 관리 아이디
	 */
	public String getLayerId() {
		return layerId;
	}

	/**
	 * @return Layer 유형
	 */
	public LayerTypes getType() {
		return type;
	}


	/**
	 * @return mashup 목록
	 */
	public <R extends MashupList> R getMashups() {
		return (R) mashups;
	}
}
