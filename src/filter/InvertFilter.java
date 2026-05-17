package filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InvertFilter implements ImageFilter
{
    @Override
    public int apply(BufferedImage image, int x, int y)
    {
        Color color = new Color(image.getRGB(x, y));

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int newRed = 255 - red;
        int newGreen = 255 - green;
        int newBlue = 255 - blue;

        return new Color(newRed, newGreen, newBlue).getRGB();
    }
}
