package filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BlurFilter implements ImageFilter {

    private final int radius;

    public BlurFilter() {
        this.radius = 5;
    }

    public BlurFilter(int radius) {
        if (radius < 1) {
            throw new IllegalArgumentException("Blur radius must be greater than 0");
        }

        this.radius = radius;
    }

    @Override
    public int apply(BufferedImage image, int x, int y) {
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int pixelCount = 0;

        for (int dy = -radius; dy <= radius; dy++) {
            for (int dx = -radius; dx <= radius; dx++) {
                int neighborX = x + dx;
                int neighborY = y + dy;

                if (neighborX >= 0 && neighborX < image.getWidth()
                        && neighborY >= 0 && neighborY < image.getHeight()) {

                    Color color = new Color(image.getRGB(neighborX, neighborY));

                    redSum += color.getRed();
                    greenSum += color.getGreen();
                    blueSum += color.getBlue();

                    pixelCount++;
                }
            }
        }

        int averageRed = redSum / pixelCount;
        int averageGreen = greenSum / pixelCount;
        int averageBlue = blueSum / pixelCount;

        return new Color(averageRed, averageGreen, averageBlue).getRGB();
    }
}