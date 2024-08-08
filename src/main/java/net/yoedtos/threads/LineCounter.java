package net.yoedtos.threads;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LineCounter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LineCounter.class);
	
	private LineCounter() {}
	
	private static void doWork(int index) {
		var resource = LineCounter.class.getClassLoader().getResource("8Kints.txt");
		LOGGER.debug("{} - entering {}", index, Thread.currentThread());
		try (var stream = Files.lines(Path.of(resource.toURI()))){
			LOGGER.debug("{} - file's line: {}",index, stream.count());
			LOGGER.debug("{} - exiting {}", index, Thread.currentThread());
		} catch (Exception e) {
			LOGGER.error("Work failed: {}", e.getMessage());
		}
	}

	public static long runThread(int times) {
		long startTime = System.currentTimeMillis();
		
		try(var executors = Executors.newFixedThreadPool(10)){
			for (var i = 0; i < times; i++) {
				var index = i;
				executors.submit(() -> doWork(index));
			}
			
			executors.shutdown();
			executors.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error("Failed: {}", e.getMessage());
		}
		return System.currentTimeMillis() - startTime;
	}
	
	public static long runVThread(int times) {
		long startTime = System.currentTimeMillis();
		
		try(var executors = Executors.newVirtualThreadPerTaskExecutor()) { 
			for (var i = 0; i < times; i++) {
				var index = i;
				executors.submit(() -> doWork(index));
			}
			executors.shutdown();
			executors.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error("Failed: {}", e.getMessage());
		}
		return System.currentTimeMillis() - startTime;
	}
}
