package util;

import filter.ImageFilter;
import processor.ImageProcessor;
import processor.MultiThreadedImageProcessor;

import java.awt.image.BufferedImage;

public class PerformanceTester {

    private final int[] threadCounts;

    public PerformanceTester(int[] threadCounts) {
        this.threadCounts = threadCounts;
    }

    public void test(BufferedImage image, ImageFilter filter) {
        System.out.println("Performance test results:");
        System.out.println("-------------------------");

        for (int threadCount : threadCounts) {
            ImageProcessor processor = new MultiThreadedImageProcessor(threadCount);

            long startTime = System.nanoTime();

            processor.process(image, filter);

            long endTime = System.nanoTime();

            long durationMs = (endTime - startTime) / 1_000_000;

            System.out.println("Threads: " + threadCount + " | Time: " + durationMs + " ms");
        }
    }
}