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
 * Clip 을 관리 하는 리스트
 */
public class ClipList extends ArrayList<Clip> {

	private static final long serialVersionUID = 6702116021739364119L;

	/** 관리 하는 edl 정보 */
	private EDL edl = null;

	@FunctionalInterface
	public interface ClipPostEvent {
		void post(EDL edl, Clip clip);
	}

	/** clip added event callback list */
	private final Collection<ClipPostEvent> postAdded;

	/** clip removed event callback list */
	private final Collection<ClipPostEvent> postRemoved;





	public ClipList() {
		super();
		this.postAdded = new ArrayList<>();
		this.postRemoved = new ArrayList<>();
	}

	/**
	 * 추가 이벤트 callbak 함추를 설정 한다
	 *
	 * @param event 이벤트 처리 함수
	 * @return this
	 */
	public ClipList addEventPostAdded(final ClipPostEvent event) {
		this.postAdded.add(event);
		return this;
	}

	/**
	 * 삭제 이벤트 callback 함수를 설정 한다
	 *
	 * @param event 이벤트 처리 함수
	 * @return this
	 */
	public ClipList addEventPostRemoved(final ClipPostEvent event) {
		this.postAdded.add(event);
		return this;
	}


	private synchronized void sendClipEvent(
			final EDL edl,
			final Clip clip,
			final Collection<ClipPostEvent> callbacks) {
		final List<Runnable> runner = new ArrayList<>();
		callbacks.forEach(c -> {
			runner.add(() -> {
				c.post(edl, clip);
			});
		});
		EventExecutor.execPost(runner);
	}

	@Override
	public boolean add(final Clip clip) {
		this.add(this.size(), clip);
		return true;
	}

	@Override
	public void add(final int index, final Clip clip) {
		super.add(index, clip);
		this.sendClipEvent(edl, clip, postAdded);
	}

	@Override
	public Clip remove(final int index) {
		final Clip clip = super.remove(index);
		this.sendClipEvent(edl, clip, postRemoved);
		return clip;
	}

	/**
	 * clip 을 삭제 한다
	 * @param clipId
	 * @return
	 */
	public boolean remove(String clipId){
		final Clip clip = findOne(clipId);

		if(clip == null ){
			return false;
		}

		boolean ret = this.remove(clip);
		if(ret ) {
			this.sendClipEvent(edl, clip, postRemoved);
		}
		return ret;
	}

	/**
	 * 클립 목록을 검색한다
	 *
	 * @param condition 검색 조건
	 * @param <T>       return type
	 * @return 조건에 맞는 클립 목록
	 */
	public <T extends Clip> List<T> findList(final ClipFindCondition condition) {
		final Set<Predicate<Clip>> predicateList = condition.getPredicates();
		Stream<Clip> stream = this.stream();

		for (final Predicate<Clip> p : predicateList) {
			stream = stream.filter(p);
		}
		return (List<T>) stream.collect(Collectors.toList());
	}


	/**
	 * id 로 클립을 찾는다
	 *
	 * @param clipId clip id
	 * @param <T>    return type
	 * @return 검색된 clip
	 */
	public <T extends Clip> T findOne(final String clipId) {
		return (T) this.stream().filter(clip -> clip.getClipId().equals(clipId))
				.findFirst().orElse(null);
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
	public ClipList setEdl(final EDL edl) {
		this.edl = this.edl == null ? edl : ExceptionUtil.throw_("edl is already set");
		return this;
	}


}

