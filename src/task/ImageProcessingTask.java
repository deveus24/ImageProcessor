package task;

import filter.ImageFilter;

import java.awt.image.BufferedImage;

public class ImageProcessingTask implements Runnable {

    private final BufferedImage inputImage;
    private final BufferedImage outputImage;
    private final ImageFilter filter;
    private final int startRow;
    private final int endRow;

    public ImageProcessingTask(
            BufferedImage inputImage,
            BufferedImage outputImage,
            ImageFilter filter,
            int startRow,
            int endRow
    ) {
        this.inputImage = inputImage;
        this.outputImage = outputImage;
        this.filter = filter;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        for (int y = startRow; y < endRow; y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                int newColor = filter.apply(inputImage, x, y);
                outputImage.setRGB(x, y, newColor);
            }
        }
    }
}