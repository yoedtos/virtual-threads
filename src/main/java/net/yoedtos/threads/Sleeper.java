package net.yoedtos.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sleeper {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sleeper.class);
	private Sleeper() {}
	
	private static void doWork() {
		LOGGER.debug("sleep");
		try {
			Thread.sleep(3000);
			LOGGER.debug("wake up");
		} catch (Exception e) {
			LOGGER.error("Work failed: {}", e.getMessage());
		}
	}

	public static long runThread(int times) {
		long startTime = System.currentTimeMillis();
		try {
			for (var i = 0; i < times; i++) {
				new Thread(() -> doWork()).start();
			}

			Thread.sleep(5000);
		} catch (Exception e) {
			LOGGER.error("Failed: {}", e.getMessage());
		}

		return System.currentTimeMillis() - startTime;
	}

	public static long runVThread(int times) {
		long startTime = System.currentTimeMillis();
		try {
			for (var i = 0; i < times; i++) {
				Thread.startVirtualThread(() -> doWork());
			}

			Thread.sleep(5000);
		} catch (Exception e) {
			LOGGER.error("Failed: {}", e.getMessage());
		}

		return System.currentTimeMillis() - startTime;
	}
}
