package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader
{
    public BufferedImage load(String path) throws IOException
    {
        BufferedImage image;

        image = ImageIO.read(new File(path));

        if(image == null)
        {
            throw new IOException("Failed to load image from path: " + path);
        }

        return image;
    }
}
