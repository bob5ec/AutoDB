package bz.asd.autodb.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 *
 * @author lars
 */
public class ImageView implements Icon, Serializable {

    private int maxWidth, maxHeight, width, height;
    private BufferedImage image;

    public ImageView() {
        
    }

    public void setImage(File file) throws IOException {
        image = ImageIO.read(file);
        
        // skale up the image to the maximum possible size
        double hSkale = maxHeight / (double)image.getHeight();
        double wSkale = maxWidth  / (double)image.getWidth();
        double skale;
        int skaledHeight = (int)(image.getHeight() * wSkale);
        int skaledWidth  = (int)(image.getWidth()  * hSkale);
        if(skaledHeight < maxHeight && skaledWidth < maxWidth) {
            skale = Math.max(hSkale, wSkale);
        } else if(skaledHeight > maxHeight) {
            skale = hSkale;
        } else if(skaledWidth > maxWidth) {
            skale = wSkale;
        } else {
            skale = hSkale;
        }

        height = (int)(image.getHeight() * skale);
        width  = (int)(image.getWidth()  * skale);
        //System.out.println(width + " "+ height);
    }

    public void setImageFilename(String filename) {
        System.out.println("imageView:"+filename);
        if(filename == null) return;

        try {
            File file = new File(filename);

            if(file.canRead()) {
                setImage(file);
            }
        } catch (Exception ex) {
            Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
        if(image != null) arg1.drawImage(image, arg2, arg3, width, height, arg0);
        //System.out.println(arg0.getWidth() +" "+arg0.getHeight());
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

}
