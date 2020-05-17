package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;
import com.bluecapsystem.lotte.illywa.edl.utils.TimeUtils;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

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
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	/**
	 * mashup 을 가장 뒤에 추가 한다
	 *
	 * @param mashup 추가 할 mashup
	 * @return this
	 */
	public Layer addMashup(final Mashup mashup) {
		final String startTC = Optional.of(this.mashups) //
				.filter(m -> m.size() > 0) //
				.flatMap(m -> {
					return Optional.of(m.get(m.size() - 1).getEndTC());
				}).orElse(TimeUtils.DEFAULT_TC);
		mashup.offset(startTC);
		this.mashups.add(mashup);

		return this;
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


}
