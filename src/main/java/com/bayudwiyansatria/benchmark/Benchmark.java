/*
 * MIT License
 *
 * Copyright (c) 2019 Bayu Dwiyan Satria
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bayudwiyansatria.benchmark;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Benchmark {
	public static void main (String[]args){
		long StartTime = System.nanoTime ();
		String DataDirectory = "src/main/resources/";
		String FileName = "bigdata.csv";
		String DataSet = DataDirectory + FileName;
		try {
			final double cpuTime = Files.lines ( Paths.get ( DataSet ) ).parallel ( ).mapToLong (Benchmark::computeProcess).sum ();
			final double realTime = System.nanoTime() - StartTime;
			int cores = Runtime.getRuntime().availableProcessors();
			System.out.println("          Cores: " + cores);
			System.out.format("       CPU time: %.2f s\n", cpuTime / SECONDS.toNanos(1));
			System.out.format("      Real time: %.2f s\n", realTime / SECONDS.toNanos(1));
			System.out.format("CPU utilization: %.2f%%\n\n", 100.0 * cpuTime / realTime / cores);
		
		} catch ( Exception e ){
			System.out.println ( e );
		}
	}
	
	public static long computeProcess(String line){
		final long localStart = System.nanoTime();
		String[] value = line.split ( "," );
		return System.nanoTime() - localStart;
	}
	
	public static String calc(String line){
		final long localStart = System.nanoTime();
		return "a";
	}
}