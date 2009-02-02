package bz.asd.autodb.data;

/**
 *
 * @author lars
 */
public class UserSession implements bz.asd.mvc.Model {

    private String[] lastOpenFiles;

    public String[] getLastOpenFiles() {
        return lastOpenFiles;
    }

    public void setLastOpenFiles(String[] lastOpenFiles) {
        this.lastOpenFiles = lastOpenFiles;
    }
}
