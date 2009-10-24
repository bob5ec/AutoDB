package bz.asd.autodb.data;

import java.awt.Dimension;

/**
 *
 * @author lars
 */
public class UserSession implements bz.asd.mvc.Model {

    private RingBuffer lastOpenFiles;
    private Dimension windowSize;
    private String lastImagePath;

    public void setDefaults() {
        lastOpenFiles = new RingBuffer();
        lastOpenFiles.setSize(5);
        windowSize = new Dimension(800,600);
        lastImagePath = "";
    }

    public RingBuffer getLastOpenFiles() {
        return lastOpenFiles;
    }

    public void setLastOpenFiles(RingBuffer lastOpenFiles) {
        this.lastOpenFiles = lastOpenFiles;
    }

    public Dimension getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }

    public String getLastImagePath() {
        return lastImagePath;
    }

    public void setLastImagePath(String lastImagePath) {
        this.lastImagePath = lastImagePath;
    }
}
