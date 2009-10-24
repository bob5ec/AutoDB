package bz.asd.tools.db4o;

import bz.asd.autodb.data.Model;
import bz.asd.autodb.data.db4o.Db4oDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Odbc2Db4o {

    Connection connection;
    SimpleDateFormat format;

    public Odbc2Db4o() throws Exception {

        format = new SimpleDateFormat("yyyy-MM-dd");

        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        connection = DriverManager.getConnection("jdbc:odbc:KFZInternet","","");

        Db4oDatabase db4o = new Db4oDatabase("odbcExport"+(new Date()).getTime()+".db4o");
        db4o.open();

        Statement sqlStmt = connection.createStatement();
        ResultSet rSet = sqlStmt.executeQuery("SELECT * FROM KFZ;");

        while(rSet.next()) {
            Model model = db4o.createModel();
            model.setHersteller(filter(rSet.getString(3)));
            model.setHerstellerNr(filter(rSet.getString(4)));
            model.setAuflage(filter(rSet.getString(5)));
            model.setMarke(filter(rSet.getString(6)));
            model.setAchsfolge(filter(rSet.getString(7)));
            model.setTyp(filter(rSet.getString(8)));
            model.setArt(filter(rSet.getString(9)));
            model.setDruck(filter(rSet.getString(10)));
            int art = rSet.getInt(11);
            switch (art) {
                case 1:
                    art = Model.SERIENMODELL;
                    break;
                case 2:
                    art = Model.WERBEMODELL;
                    break;
            }
            model.setModellArt(art);
            model.setBemerkung(filter(rSet.getString(12)));
            model.setBilddatei(filter(rSet.getString(13)));
            int ort = rSet.getInt(14);
            switch(ort) {
                case 1:
                    ort = Model.GARBSEN;
                    break;
                case 2:
                    ort = Model.DINKLAGE;
                    break;
                case 3:
                    ort = Model.WETTMAR;
                    break;
            }


            model.setModellStandort(ort);
            model.setPreisEK(Double.parseDouble(filter(rSet.getString(15))));
            model.setPreisVK(Double.parseDouble(filter(rSet.getString(16))));
            model.setPreisSL(Double.parseDouble(filter(rSet.getString(17))));

            model.setAenderungsdatum(dateString2Date(filter(rSet.getString(18))));
            model.setProduktionsdatum(filter(rSet.getString(19)));
            model.setAufbau(filter(rSet.getString(20)));
        }

        db4o.save();
        db4o.close();

    }

    private Date dateString2Date(String str) {
        Date res = null;
        try {
            res = format.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(Odbc2Db4o.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("date String was:"+str);
        }
        return res;
    }

    private String filter(String str) {
        if(str.compareTo("-") == 0) return null;
        else return str;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Odbc2Db4o main = new Odbc2Db4o();
        } catch (Exception ex) {
            Logger.getLogger(Odbc2Db4o.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
