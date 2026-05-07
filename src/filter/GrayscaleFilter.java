package filter;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayscaleFilter implements ImageFilter
{

    @Override
    public int apply(BufferedImage image, int x, int y)
    {
        Color color = new Color(image.getRGB(x, y));

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int gray = (red + green + blue) / 3;

        return new Color(gray, gray, gray).getRGB();
    }
}

