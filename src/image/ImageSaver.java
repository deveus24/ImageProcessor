package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver
{
    public void save(BufferedImage image, String path) throws IOException
    {
        if(ImageIO.write(image, "png", new File(path)) == false)
        {
            throw new IOException("Could not save image to " + path);
        }

    }
}
