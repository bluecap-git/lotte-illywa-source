package com.bluecapsystem.lotte.illywa;

import com.bluecapsystem.lotte.illywa.common.event.EventExecutor;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MatinTest {


	public class SampleCallable implements Callable<Integer> {

		private Integer number;

		private int index = 0;

		public SampleCallable(final int index, final Integer number) {

			this.index = index;
			this.number = number;
		}

		@Override
		public Integer call() throws Exception {
			System.out.println("Call " + this.index + " callable object");

			int result = 1;
			if (number == 0 || number == 1) {
				result = 1;
			} else {
				for (int i = 2; i <= number; i++) {
					result *= i;
					TimeUnit.SECONDS.sleep(5);
				}
			}

			System.out.println("[index - " + index + "] Result for number - " + number + " -> " + result);

			return result;
		}
	}

	@Test
	@Ignore
	public void ThreadQueueTest_Callable() {

		// thread 2 개인 서비스를 생성 한다
		final ExecutorService executorService = Executors.newFixedThreadPool(2);

		final List<Future<Integer>> resultList = new ArrayList<>();

		final Random random = new Random();

		for (int i = 0; i < 4; i++) {
			final Integer number = random.nextInt(10);
			final SampleCallable calculator = new SampleCallable(i, number);
			final Future<Integer> result = executorService.submit(calculator);
			resultList.add(result);
		}

		for (final Future<Integer> future : resultList) {
			try {
				System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
			} catch (final InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();

	}

	@Test
	@Ignore
	public void ThreadQueueTest_Runnable() {

		// thread 2 개인 서비스를 생성 한다
		final ExecutorService executorService = Executors.newFixedThreadPool(2);

		final Runnable runner = () -> {
			// 스레드 총 개수 및 작업 스레드 이름 출력
			final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
			final int poolSize = threadPoolExecutor.getPoolSize();
			final String threadName = Thread.currentThread().getName();
			System.out.println("[총 스레드 개수 : " + poolSize + "] 작업 스레드 이름 : " + threadName);

			// 예외 발생시킴
			final int value = Integer.parseInt("삼");
		};


		IntStream.range(0, 10)
				.forEach(idx -> {
					executorService.submit(runner);
					try {
						Thread.sleep(10);
					} catch (final Exception ex) {
						ex.printStackTrace();
					}
				});

		executorService.shutdown();

	}

	@FunctionalInterface
	interface MashupEvent {
		void post(int var1);
	}

	@FunctionalInterface
	interface MashupEvent2 {
		void post(int var1);
	}


	@Test
	public void Sample() {

		final EventExecutor event = new EventExecutor();

		final List<Runnable> events = new ArrayList<>();
		IntStream.range(0, 10)
				.forEach(index -> {
					final Runnable runner;

					final MashupEvent e;
					switch (index % 2) {
						case 1:
							e = new Exec1();
							break;
						default:
							e = new Exec2();
							break;
					}
					events.add(() -> {
						e.post(index);
					});
				});

		EventExecutor.execPost(events);
		System.out.println("submit");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (final Throwable th) {
			th.printStackTrace();
		}

		// 프로그램 종료시 반듯이 해줘야 한다
		EventExecutor.shutdown();
	}


	class Exec1 implements MashupEvent, MashupEvent2 {
		@Override
		public void post(final int var1) {
			System.out.println("---------- " + var1);
		}
	}


	class Exec2 implements MashupEvent {

		@Override
		public void post(final int var1) {

			System.out.println("============== " + var1);

		}
	}

}
