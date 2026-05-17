package processor;

import filter.ImageFilter;
import task.ImageProcessingTask;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadedImageProcessor implements ImageProcessor {

    private final int threadCount;

    public MultiThreadedImageProcessor(int threadCount) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException("Thread count must be greater than 0");
        }

        this.threadCount = threadCount;
    }

    @Override
    public BufferedImage process(BufferedImage image, ImageFilter filter) {
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        int height = image.getHeight();
        int rowsPerThread = height / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int startRow = i * rowsPerThread;
            int endRow;

            if (i == threadCount - 1) {
                endRow = height;
            } else {
                endRow = startRow + rowsPerThread;
            }

            ImageProcessingTask task = new ImageProcessingTask(
                    image,
                    result,
                    filter,
                    startRow,
                    endRow
            );

            executor.execute(task);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Image processing was interrupted", e);
        }

        return result;
    }
}