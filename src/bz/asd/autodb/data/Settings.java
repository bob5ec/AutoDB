package bz.asd.autodb.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class represents the (user)settings of the application 
 * @author lars
 *
 */
public class Settings {
	
	private static final String SETTINGS_FILENAME = "settings.xml";
    private static final int LAST_OPEN_FILES_COUNT = 5;

    private static Settings instance;

    private UserSession userSession;
    private TreeViewSettings treeViewSettings;
	
	private Settings() {
        
		try {
            try {
                load();
            } catch (FileNotFoundException fnfe) {
                setDefaults();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
	}

    public static Settings getInstance() {
        if(instance == null) instance = new Settings();
        return instance;
    }

    private void load() throws IOException {
        load(SETTINGS_FILENAME);
    }

    private void load(String filename) throws IOException {
        FileInputStream is = new FileInputStream(filename);
        XMLDecoder decoder = new XMLDecoder(is);
        userSession = (UserSession)decoder.readObject();
        treeViewSettings = (TreeViewSettings)decoder.readObject();
        decoder.close();
    }
    
    public void save(String filename) throws IOException {
        FileOutputStream os = new FileOutputStream(filename);
        XMLEncoder encoder = new XMLEncoder(os);
        encoder.writeObject(userSession);
        encoder.writeObject(treeViewSettings);
        encoder.close();
    }

    public void save() throws IOException {
        save(SETTINGS_FILENAME);
    }

    private void setDefaults() {
        String[] lastOpenFiles = new String[LAST_OPEN_FILES_COUNT];
        userSession = new UserSession();
        userSession.setLastOpenFiles(lastOpenFiles);
        treeViewSettings = new TreeViewSettings();
        treeViewSettings.setGroupLevel(0);
        treeViewSettings.setOrder(new int[0]);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    // TODO add change listener here
    
    public TreeViewSettings getTreeViewSettings() {
        return treeViewSettings;
    }

    public void setTreeViewSettings(TreeViewSettings treeViewSettings) {
        this.treeViewSettings = treeViewSettings;
    }
}
