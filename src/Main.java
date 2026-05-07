import java.awt.image.BufferedImage;
import filter.ImageFilter;
import java.io.IOException;
import java.util.Scanner;

import filter.GrayscaleFilter;
import image.*;
import processor.ImageProcessor;
import processor.SingleThreadedImageProcessor;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter file filePath:");
        String filePath = scanner.nextLine();
        System.out.println("Please enter path where to save the file:");
        String savePath = scanner.nextLine();

        try
        {
            ImageLoader imageLoader = new ImageLoader();
            BufferedImage image = imageLoader.load(filePath);

            ImageFilter filter = new GrayscaleFilter();

            ImageProcessor processor = new SingleThreadedImageProcessor();
            BufferedImage processedImage = processor.process(image, filter);

            ImageSaver imageSaver = new ImageSaver();
            imageSaver.save(processedImage, savePath);
        } catch (IOException e)
        {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}