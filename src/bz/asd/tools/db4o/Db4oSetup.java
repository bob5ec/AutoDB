
package bz.asd.tools.db4o;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.Model;
import bz.asd.autodb.data.db4o.Db4oDatabase;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lars
 */
public class Db4oSetup {

    Database db;

    public Db4oSetup() {
        try {
            db = new Db4oDatabase("test.db4o");
            db.open();

            //add();
            show();
            db.close();
        } catch (Exception ex) {
            Logger.getLogger(Db4oSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void show() throws Exception {
        List<Model> models = db.getModels();
        int i=0;
        for(Model m : models) {
            System.out.println("druck: " + m.getDruck());
            System.out.println(i++ + ": " + m.toString());
        }
    }

    private void add() throws Exception {
        db.createModel().setDruck("1");
        db.save();
    }

    public static void main(String[] args) {
        new Db4oSetup();
    }

}
