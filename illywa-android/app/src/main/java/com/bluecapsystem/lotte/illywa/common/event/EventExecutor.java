package com.bluecapsystem.lotte.illywa.common.event;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * event 실행을 위한 클래스
 */
public class EventExecutor {

	/** Thread pool */
	private final static ExecutorService executorService;

	static {
		// thread pool 을 생성 한다
		executorService = Executors.newFixedThreadPool(20);
	}

	/**
	 * 시스템 종료시 반듯이 해줘야 한다
	 */
	public static void shutdown() {
		executorService.shutdown();
	}

	/**
	 * @param events post event 실행 목록
	 */
	public static void execPost(final List<Runnable> events) {
		events.forEach(e -> {
			executorService.submit(e);
		});
	}

}
