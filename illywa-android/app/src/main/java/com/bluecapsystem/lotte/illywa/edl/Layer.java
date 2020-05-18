package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;
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
	public Layer(final LayerTypes type) {
		this.layerId = IDGenerator.createID(Layer.PREFIX_ID);
		this.type = type;

		this.mashups = new MashupList();
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
	public MashupList getMashups() {
		return mashups;
	}
}
