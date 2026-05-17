import filter.BlurFilter;
import filter.GrayscaleFilter;
import filter.ImageFilter;
import filter.InvertFilter;
import image.ImageLoader;
import image.ImageSaver;
import processor.ImageProcessor;
import processor.MultiThreadedImageProcessor;
import util.PerformanceTester;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose filter:");
        System.out.println("1. Grayscale");
        System.out.println("2. Invert");
        System.out.println("3. Blur");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ImageFilter filter;

        if (choice == 1) {
            filter = new GrayscaleFilter();
        } else if (choice == 2) {
            filter = new InvertFilter();
        } else if (choice == 3) {
            filter = new BlurFilter(10);
        } else {
            System.out.println("Invalid filter choice.");
            return;
        }

        System.out.println("Please enter file path:");
        String filePath = scanner.nextLine();

        System.out.println("Please enter path where to save the file:");
        String savePath = scanner.nextLine();

        try {

            ImageLoader imageLoader = new ImageLoader();
            BufferedImage image = imageLoader.load(filePath);

            int[] threadCounts = {1, 2, 4, 8, 16};

            PerformanceTester tester = new PerformanceTester(threadCounts);
            tester.test(image, filter);

            System.out.println("Enter thread count for final processing:");
            int threadCount = scanner.nextInt();

            ImageProcessor processor = new MultiThreadedImageProcessor(threadCount);

            long startTime = System.nanoTime();

            BufferedImage processedImage = processor.process(image, filter);

            long endTime = System.nanoTime();

            long durationMs = (endTime - startTime) / 1_000_000;

            System.out.println("Final processing time: " + durationMs + " ms");

            ImageSaver imageSaver = new ImageSaver();
            imageSaver.save(processedImage, savePath);

            System.out.println("Image saved successfully.");

        } catch (IOException e) {
            System.out.println("Failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        scanner.close();
    }
}