package bz.asd.autodb.data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public abstract class Model implements Groupable<Model>, bz.asd.mvc.Model {

    public static final int SERIENMODELL = 0, WERBEMODELL = 1, UMBAU = 2;
    public static final int WETTMAR = 0, DINKLAGE = 1, GARBSEN = 2;
    //todo use this array, when naming attributed in UI
    public static final String[] ATTRIBUTE_NAMES = {"Hersteller", "Hersteller Nr",
        "Auflage", "Produktionsdatum", "Bild", "Modellstandort", "Marke", "Achsfolge",
        "Typ", "Aufbau", "Art", "Druck", "Preis EK", "Preis VK", "Preis SL", "Modellart",
        "Änderungsdatum"};
    public static final int HERSTELLER = 0, HERSTELLERNR = 1, AUFLAGE = 2, PRODUKTIONSDATUM = 3,
            BILDDATEI = 4, MODELLSTANDORT = 5, MARKE = 6, ACHSFOLGE = 7, TYP = 8, AUFBAU = 9,
            ART = 10, DRUCK = 11, PREISEK = 12, PREISVK = 13, PREISSL = 14, MODELLART = 15,
            AENDERUNGSDATUM = 16;
    public static int ATTRIBUTE_COUNT = 17;
    public static final String[] MODELLART_NAMES = {"Serienmodell", "Werbemodell", "Umbau"};
    public static final String[] MODELLSTANDORT_NAMES = {"Wettmar", "Dinklage"};
    //todo a sort is an int array containig these numbers. the first element
    //in this array is the first arrtibute to sort for
    private String hersteller, herstellerNr, auflage,
            bilddatei, marke, achsfolge, typ, aufbau, art, druck,
            preisEK, preisVK, preisSL, bemerkung;
    private int modellArt, modellStandort;
    private Date produktionsdatum, aenderungsdatum;
    private List<ExtraProperty> extraProperties;

    protected PropertyChangeSupport propertySupport;

    public Model() {
        init();
    }

    protected void init() {
        extraProperties = new LinkedList<ExtraProperty>();
        propertySupport = new PropertyChangeSupport(this);
    }

    //protected abstract void setHasChanged(boolean hasChanged);
    protected abstract void notifyChangeListener();

    @Override
    public boolean equals(Object other) {
        if (other instanceof Model) {
            Model o = (Model) other;
            return equals(bilddatei, o.bilddatei) &&
                    equals(druck, o.druck) &&
                    equals(hersteller, o.hersteller) &&
                    equals(herstellerNr, o.herstellerNr) &&
                    equals(auflage, o.auflage) &&
                    equals(produktionsdatum, o.produktionsdatum) &&
                    modellStandort == o.modellStandort &&
                    equals(aenderungsdatum, o.aenderungsdatum) &&
                    equals(marke, o.marke) &&
                    equals(achsfolge, o.achsfolge) &&
                    equals(typ, o.typ) &&
                    equals(aufbau, o.aufbau) &&
                    equals(art, o.art) &&
                    equals(preisEK, o.preisEK) &&
                    equals(preisVK, o.preisVK) &&
                    equals(preisSL, o.preisSL) &&
                    modellArt == o.modellArt &&
                    extraProperties.equals(o.extraProperties);
        } else {
            return false;
        }
    }

    private boolean equals(String a, String b) {
        return (a == null && b == null) ||
                a != null && b != null && a.equals(b);
    }

    private boolean equals(Date a, Date b) {
        return (a == null && b == null) ||
                a != null && b != null && a.getTime() == b.getTime();
    }

    /* Groupable implementation */
    public int compareTo(int attribute, Model o) {
        if (o == null) {
            return -1;
        }

        int res;
        switch (attribute) {
            case MODELLART:
                res = o.modellArt - modellArt;
                if (res < 0) {
                    res = -1;
                } else if (res > 0) {
                    res = 1;
                }
                break;
            case AENDERUNGSDATUM:
                res = aenderungsdatum.compareTo(o.aenderungsdatum);
                break;
            default:
                String val = ((String) getValue(attribute));
                String val2 = (String) o.getValue(attribute);
                if (val == null && val2 == null) {
                    res = 0;
                } else {
                    if (val == null) {
                        res = -1;
                    } else if (val2 == null) {
                        res = 1;
                    } else {
                        res = val.compareToIgnoreCase(val2);
                    }
                }
        }
        return res;
    }

    @Override
    public String toString() {
        int[] order = new int[0];
        //int[] order = new int[ATTRIBUTE_COUNT];

        /*for(int i=0; i<ATTRIBUTE_COUNT; i++) {
        order[i]=i;
        }*/

        return toString(order, 0);
    }

    public String toString(int[] order, int groupLevel) {
        StringBuffer res = new StringBuffer();

        for (int i = groupLevel; i < order.length; i++) {
            String val = getStringValue(order[i]);
            if (val.length() != 0) {
                res.append(ATTRIBUTE_NAMES[order[i]] + "=" + val + " ");
            }
        }

        /*int notOrdered = ATTRIBUTE_COUNT - order.length;
        if (notOrdered > 0) {
            // Java is pesimistic, init with false
            boolean[] present = new boolean[ATTRIBUTE_COUNT];

            for (int ordered : order) {
                present[ordered] = true;
            }

            for (int i = 0; i < ATTRIBUTE_COUNT; i++) {
                if (!present[i]) {
                    String val = getStringValue(i);
                    if (val.length() != 0) {
                        res.append(" " + ATTRIBUTE_NAMES[i] + "=" + val);
                    }
                }
            }
        }*/


        return res.toString();
    }

    /** Workaround, to get the Names for an order, do not use because of bad oo-modeling.
     * @deprecated
     * @param attribute the attribute index of the desired value
     * @return
     */
    public Object getValue(int attribute) {
        Object res;
        switch (attribute) {
            case HERSTELLER:
                res = hersteller;
                break;
            case HERSTELLERNR:
                res = herstellerNr;
                break;
            case AUFLAGE:
                res = auflage;
                break;
            case PRODUKTIONSDATUM:
                res = produktionsdatum;
                break;
            case BILDDATEI:
                res = bilddatei;
                break;
            case MODELLSTANDORT:
                res = modellStandort;
                break;
            case MARKE:
                res = marke;
                break;
            case ACHSFOLGE:
                res = achsfolge;
                break;
            case TYP:
                res = typ;
                break;
            case AUFBAU:
                res = aufbau;
                break;
            case ART:
                res = art;
                break;
            case DRUCK:
                res = druck;
                break;
            case PREISEK:
                res = preisEK;
                break;
            case PREISVK:
                res = preisVK;
                break;
            case PREISSL:
                res = preisSL;
                break;
            case MODELLART:
                res = modellArt;
                break;
            case AENDERUNGSDATUM:
                res = aenderungsdatum;
                break;
            default:
                throw new IllegalArgumentException("Model attribute invalide");
        }
        return res;
    }

    /**
     * @deprecated
     * @param attribute
     * @return
     */
    public String getStringValue(int attribute) {
        String res;
        switch (attribute) {
            default:
                Object val = getValue(attribute);
                if (val != null) {
                    res = val.toString();
                } else {
                    res = "";
                }
                break;
            case MODELLART:
                res = MODELLART_NAMES[modellArt];
                break;
            case MODELLSTANDORT:
                res = MODELLSTANDORT_NAMES[modellStandort];
                break;
            case AENDERUNGSDATUM:
                if (aenderungsdatum != null) {
                    res = DateFormat.getDateInstance().format(aenderungsdatum);
                } else {
                    res = "";
                }
                break;
        }
        return res;
    }

    public int getAttributeCount() {
        return ATTRIBUTE_COUNT;
    }

    /* Getter and Setter */
    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
        notifyChangeListener();
        // TODO switch all changeListener positions (code style)
    }

    public String getHerstellerNr() {
        return herstellerNr;
    }

    public void setHerstellerNr(String herstellerNr) {
        this.herstellerNr = herstellerNr;
        notifyChangeListener();
    }

    public String getAuflage() {
        return auflage;
    }

    public void setAuflage(String auflage) {
        this.auflage = auflage;
        notifyChangeListener();
    }

    public Date getProduktionsdatum() {
        return produktionsdatum;
    }

    public void setProduktionsdatum(Date produktionsdatum) {
        this.produktionsdatum = produktionsdatum;
        notifyChangeListener();
    }

    public String getBilddatei() {
        return bilddatei;
    }

    public void setBilddatei(String bilddatei) {
        //TODO IMPROVE add property changing stuff to all attributes
        String old = this.bilddatei;
        this.bilddatei = bilddatei;
        //firePropertyChange("bilddatei", old, bilddatei);
        propertySupport.firePropertyChange("bilddatei", old, bilddatei);
        notifyChangeListener();
    }

    public int getModellStandort() {
        return modellStandort;
    }

    public void setModellStandort(int modellStandort) {
        this.modellStandort = modellStandort;
        notifyChangeListener();
    }

    public Date getAenderungsdatum() {
        return aenderungsdatum;
    }

    public void setAenderungsdatum(Date aenderungsdatum) {
        this.aenderungsdatum = aenderungsdatum;
        notifyChangeListener();
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
        notifyChangeListener();
    }

    public String getAchsfolge() {
        return achsfolge;
    }

    public void setAchsfolge(String achsfolge) {
        this.achsfolge = achsfolge;
        notifyChangeListener();
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
        notifyChangeListener();
    }

    public String getAufbau() {
        return aufbau;
    }

    public void setAufbau(String aufbau) {
        this.aufbau = aufbau;
        notifyChangeListener();
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
        notifyChangeListener();
    }

    public String getDruck() {
        return druck;
    }

    public void setDruck(String druck) {
        this.druck = druck;
        notifyChangeListener();
    }

    public String getPreisEK() {
        return preisEK;
    }

    public void setPreisEK(String preisEK) {
        this.preisEK = preisEK;
        notifyChangeListener();
    }

    public String getPreisVK() {
        return preisVK;
    }

    public void setPreisVK(String preisVK) {
        this.preisVK = preisVK;
        notifyChangeListener();
    }

    public String getPreisSL() {
        return preisSL;
    }

    public void setPreisSL(String preisSL) {
        this.preisSL = preisSL;
        notifyChangeListener();
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }
    
    public int getModellArt() {
        return modellArt;
    }

    public void setModellArt(int modellArt) {
        this.modellArt = modellArt;
        notifyChangeListener();
    }

    public boolean getModellArtSerienmodell() {
        return modellArt == SERIENMODELL;
    }

    public void setModellArtSerienmodell(boolean serienmodell) {
        if(serienmodell) {
            this.modellArt = SERIENMODELL;
            notifyChangeListener();
        }
    }

    public boolean getModellArtWerbemodell() {
        return modellArt == WERBEMODELL;
    }

    public void setModellArtWerbemodell(boolean werbemodell) {
        if(werbemodell) {
            this.modellArt = WERBEMODELL;
            notifyChangeListener();
        }
    }

    public boolean getModellArtEigenbau() {
        return modellArt == UMBAU;
    }

    public void setModellArtEigenbau(boolean eigenbau) {
        if(eigenbau) {
            this.modellArt = UMBAU;
            notifyChangeListener();
        }
    }

    public boolean getModellStandortWettmar() {
        return modellStandort == WETTMAR;
    }

    public void setModellStandortWettmar(boolean set) {
        if(set) {
            this.modellStandort = WETTMAR;
            notifyChangeListener();
        }
    }

    public boolean getModellStandortDinklage() {
        return modellStandort == DINKLAGE;
    }

    public void setModellStandortDinklage(boolean set) {
        if(set) {
            this.modellStandort = DINKLAGE;
            notifyChangeListener();
        }
    }

    public boolean getModellStandortGarbsen() {
        return modellStandort == GARBSEN;
    }

    public void setModellStandortGarbsen(boolean set) {
        if(set) {
            this.modellStandort = GARBSEN;
            notifyChangeListener();
        }
    }

    public List<ExtraProperty> getExtraProperties() {
        return extraProperties;
    }

    public void setExtraProperties(List<ExtraProperty> extraProperties) {
        this.extraProperties = extraProperties;
        notifyChangeListener();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
