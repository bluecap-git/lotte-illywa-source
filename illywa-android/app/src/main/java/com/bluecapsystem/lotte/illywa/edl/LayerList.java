package com.bluecapsystem.lotte.illywa.edl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Layer 를 관리 하는 리스트
 */
public class LayerList extends ArrayList<Layer> {

	private static final long serialVersionUID = 6142444131763076261L;


	/**
	 * 기본이 되는 Layer
	 */
	private Layer basisLayer;


	/**
	 * Layer 목록을 검색 한다
	 *
	 * @param condition 검색 조건
	 * @param <T>       return type
	 * @return 조건에 맞는 layer 목록
	 */
	public <T extends Layer> List<T> findList(final LayerFindCondition condition) {
		final Set<Predicate<Layer>> predicateList = condition.getPredicates();
		Stream<Layer> stream = this.stream();

		for (final Predicate<Layer> p : predicateList) {
			stream = stream.filter(p);
		}
		return (List<T>) stream.collect(Collectors.toList());
	}


	/**
	 * LayerID 로 Layer 를 찾는다
	 *
	 * @param layerId layer id
	 * @param <T>     return type
	 * @return 검색된 Layer
	 */
	public <T extends Layer> T findOne(final String layerId) {
		return (T) this.stream().filter(layer -> layer.getLayerId().equals(layerId))
				.findFirst().orElse(null);
	}

	/**
	 * @return 기초가 되는 Layer 설정
	 */
	public Layer getBasisLayer() {
		return basisLayer;
	}

	/**
	 * 기본이 되는 Layer 설정
	 * 기본 Layer 는 EDL 의 총 길이를 관리 한다
	 *
	 * @param basisLayer 기본 설정이 될 Layer 정보
	 * @return this
	 */
	public LayerList setBasisLayer(final Layer basisLayer) {
		this.basisLayer = basisLayer;
		return this;
	}
}

