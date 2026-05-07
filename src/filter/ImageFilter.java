package filter;

import java.awt.image.BufferedImage;

public interface ImageFilter
{
    int apply(BufferedImage image, int x, int y);
}
