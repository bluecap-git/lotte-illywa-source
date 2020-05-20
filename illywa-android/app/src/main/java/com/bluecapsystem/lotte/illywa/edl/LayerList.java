package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.Nullable;
import com.bluecapsystem.lotte.illywa.common.event.EventExecutor;
import com.bluecapsystem.lotte.illywa.common.utils.ExceptionUtil;

import java.util.ArrayList;
import java.util.Collection;
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

	/** 관리 하는 edl 정보 */
	private EDL edl;

	@FunctionalInterface
	public interface LayerPostEvent {
		void post(EDL edl, Layer obj);
	}

	/** clip added event callback list */
	private final Collection<LayerPostEvent> postAdded;

	/** clip removed event callback list */
	private final Collection<LayerPostEvent> postRemoved;

	public LayerList() {
		super();
		postAdded = new ArrayList<>();
		postRemoved = new ArrayList<>();
	}

	/**
	 * 추가 이벤트 callbak 함추를 설정 한다
	 *
	 * @param event 이벤트 처리 함수
	 * @return this
	 */
	public LayerList addEventPostAdded(final LayerPostEvent event) {
		this.postAdded.add(event);
		return this;
	}

	/**
	 * 삭제 이벤트 callback 함수를 설정 한다
	 *
	 * @param event 이벤트 처리 함수
	 * @return this
	 */
	public LayerList addEventPostRemoved(final LayerPostEvent event) {
		this.postAdded.add(event);
		return this;
	}


	private synchronized void sendLayerEvent(
			final EDL edl,
			final Layer clip,
			final Collection<LayerList.LayerPostEvent> callbacks) {
		final List<Runnable> runner = new ArrayList<>();
		callbacks.forEach(c -> {
			runner.add(() -> {
				c.post(edl, clip);
			});
		});
		EventExecutor.execPost(runner);
	}


	@Override
	public boolean add(final Layer obj) {
		this.add(this.size(), obj);
		return true;
	}

	@Override
	public void add(final int index, final Layer obj) {
		super.add(index, obj);
		this.sendLayerEvent(edl, obj, postAdded);
	}

	@Override
	public Layer remove(final int index) {
		final Layer clip = super.remove(index);
		this.sendLayerEvent(edl, clip, postRemoved);
		return clip;
	}

	public boolean remove(@Nullable final Clip clip) {
		return this.remove(this.indexOf(clip)) != null;
	}


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

	/**
	 * @return 클립을 관리하는 edl
	 */
	public EDL getEdl() {
		return edl;
	}


	/**
	 * @param edl
	 * @return
	 */
	public LayerList setEdl(final EDL edl) {
		this.edl = this.edl == null ? edl : ExceptionUtil.throw_("edl is already set");
		return this;
	}
}

