package processor;
import filter.ImageFilter;

import java.awt.image.BufferedImage;


public class SingleThreadedImageProcessor implements ImageProcessor
{

    @Override
    public BufferedImage process(BufferedImage image, ImageFilter filter)
    {
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                int newColor = filter.apply(image, x, y);
                result.setRGB(x, y, newColor);
            }
        }
        return result;
    }

}
