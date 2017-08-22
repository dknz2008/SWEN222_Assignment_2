package View;//package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public enum ImgResources {

    YELLOWFACE("yellowface.PNG"),
    GREENFACE("greenface.PNG");

    public final Image img;

    ImgResources(String resourceName) {
        try{ img = ImageIO.read(new File(resourceName));
                //ImgResources.class.getResource(resourceName));
        }
        catch (IOException e){ throw new Error(e); }
    }

}