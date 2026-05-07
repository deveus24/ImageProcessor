package processor;

import filter.ImageFilter;

import java.awt.image.BufferedImage;

public interface ImageProcessor
{
    BufferedImage process(BufferedImage image, ImageFilter filter);
}
