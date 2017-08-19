package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public enum ImgResources {
    SWORD("sword.jpg"),
    SHIELD("shield.png");

    public final Image img;

    ImgResources(String resourceName) {
        try{ img = ImageIO.read(ImgResources.class.getResource(resourceName)); }
        catch (IOException e){ throw new Error(e); }
    }

}