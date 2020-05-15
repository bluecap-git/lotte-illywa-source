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

	/** 기본 기준 layer 여부 */
	private Boolean isBasis;

	/**
	 * @param type    Layer 유형
	 * @param isBasis 기본 Layer 여부
	 */
	public Layer(final LayerTypes type, final Boolean isBasis) {
		this.layerId = IDGenerator.createID(Layer.PREFIX_ID);
		this.type = type;
	}

	/**
	 * @param type Layer 유형
	 */
	public Layer(final LayerTypes type) {
		this(type, false);
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
	 * 기준 레이어 여부 설정
	 * 기준 레이어는 EDL 전체의 길이를 지정 하고, 삭제 할 수 없다
	 *
	 * @return 기본 Layer 여부
	 */
	public Boolean getBasis() {
		return isBasis;
	}

}
