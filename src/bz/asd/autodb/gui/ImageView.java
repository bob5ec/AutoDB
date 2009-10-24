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

    private int scaledWidth, scaledHeight, initComponentWidth, initComponentHight;
    private BufferedImage image;

    public ImageView() {
        
    }

    public void setImage(File file) throws IOException {
        image = ImageIO.read(file);


        //System.out.println(width + " "+ height);
    }

    public double calcScale(int maxWidth, int maxHeight, int imgWidth, int imgHeight) {
        // skale up the image to the maximum possible size
        double hSkale = maxHeight / (double)imgHeight;
        double wSkale = maxWidth  / (double)imgWidth;
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
        return skale;
    }

    public void setImageFilename(String filename) {
        //System.out.println("imageView:"+filename);
        if(filename == null) {
            image = null;
            return;
        }

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
        if(image != null) {
            double scale = calcScale(arg0.getWidth(), arg0.getHeight(), image.getWidth(), image.getHeight());
        
            scaledHeight = (int)(image.getHeight() * scale);
            scaledWidth  = (int)(image.getWidth()  * scale);
            
            arg1.drawImage(image, arg0.getX(), arg0.getY(), scaledWidth, scaledHeight, arg0);
        }
        System.out.println(arg0.getWidth() +" "+arg0.getHeight());
    }

    public int getIconWidth() {
        return 425;
    }

    public int getIconHeight() {
        return 265;
    }

}
