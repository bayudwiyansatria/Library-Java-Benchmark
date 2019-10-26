package com.bayudwiyansatria.benchmark;

import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ParallelBenchmark {
	public void measureProcessing(Stream<String> input) {
		final long start = System.nanoTime();
		try (Stream<String> lines = input) {
			final double cpuTime = lines.parallel().mapToLong(ParallelBenchmark::processLine).sum(), realTime = System.nanoTime() - start;
			final int cores = Runtime.getRuntime().availableProcessors();
			System.out.println("          Cores: " + cores);
			System.out.format("       CPU time: %.2f s\n", cpuTime / SECONDS.toNanos(1));
			System.out.format("      Real time: %.2f s\n", realTime / SECONDS.toNanos(1));
			System.out.format("CPU utilization: %.2f%%\n\n", 100.0 * cpuTime / realTime / cores);
		}
	}
	
	public static long processLine(String line) {
		final long localStart = System.nanoTime();
		for (int i = 0; i < line.length(); i++) {
			for (int j = 0; j < line.length(); j++) {
			}
		}
		return System.nanoTime() - localStart;
	}
}
