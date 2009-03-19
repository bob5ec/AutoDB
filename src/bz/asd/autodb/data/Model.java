package bz.asd.autodb.data;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public abstract class Model implements Groupable<Model>, bz.asd.mvc.Model {
	
	public static final int SERIENMODELL=0, WERBEMODELL=1, UMBAU=2;

    //todo use this array, when naming attributed in UI
    public static final String[] ATTRIBUTE_NAMES = {"Hersteller", "Hersteller Nr",
    "Auflage", "Produktionsdatum", "Bild", "Modellstandort", "Marke", "Achsfolge",
    "Typ", "Aufbau", "Art", "Druck", "Preis EK", "Preis VK", "Preis SL", "Modellart",
    "Änderungsdatum"};
    public static final int HERSTELLER=0, HERSTELLERNR=1, AUFLAGE=2, PRODUKTIONSDATUM=3,
            BILDDATEI=4, MODELLSTANDORT=5, MARKE=6, ACHSFOLGE=7, TYP=8, AUFBAU=9,
            ART=10, DRUCK=11, PREISEK=12, PREISVK=13, PREISSL=14, MODELLART=15,
            AENDERUNGSDATUM=16;
    public static int ATTRIBUTE_COUNT=17;
    public static final String[] MODELLART_NAMES = { "Serienmodell", "Werbemodell", "Umbau" };
    public static final String[] MODELLSTANDORT_NAMES = { "Wettmar", "Dinklage" };
    //todo a sort is an int array containig these numbers. the first element
    //in this array is the first arrtibute to sort for

	private String hersteller, herstellerNr, auflage, produktionsdatum,
	bilddatei, marke, achsfolge, typ, aufbau, art, druck,
	preisEK, preisVK, preisSL;
	private int modellArt, modellStandort;
    private Date aenderungsdatum;
	private List<ExtraProperty> extraProperties;
	
	public Model() {
		init();
	}
	
	protected void init() {
		extraProperties = new LinkedList<ExtraProperty>();
	}
	
	protected abstract void setHasChanged(boolean hasChanged);

    @Override
    public boolean equals(Object other) {
		if(other instanceof Model) {
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
		} else
			return false;
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
        int res;
        switch(attribute) {
            case MODELLART:
                res = o.modellArt - modellArt;
                if(res < 0) {
                    res = -1;
                } else if(res > 0) {
                    res = 1;
                }
                break;
            case AENDERUNGSDATUM:
                res = aenderungsdatum.compareTo(o.aenderungsdatum);
                break;
            default:
                res = ((String)getValue(attribute)).compareTo((String)o.getValue(attribute));
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
        
        for(int i=groupLevel; i<order.length; i++) {
            String val = getStringValue(order[i]);
            if(val.length() != 0) res.append(order[i]+"="+val+" ");
        }

        int notOrdered = ATTRIBUTE_COUNT - order.length;
        if(notOrdered > 0) {
            // Java is pesimistic, init with false
            boolean[] present = new boolean[ATTRIBUTE_COUNT];

            for(int ordered : order) {
                present[ordered] = true;
            }

            for(int i=0; i<ATTRIBUTE_COUNT; i++) {
                if(!present[i]) {
                    String val = getStringValue(i);
                    if(val.length() != 0) res.append(" "+i+"="+val);
                }
            }
        }


        return res.toString();
    }

    /** Workaround, to get the Names for an order, do not use because of bad oo-modeling.
     * @deprecated
     * @param attribute the attribute index of the desired value
     * @return
     */
    public Object getValue(int attribute) {
        Object res;
        switch(attribute) {
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
        switch(attribute) {
            default:
                Object val = getValue(attribute);
                if(val != null) res = val.toString();
                else res = "";
                break;
            case MODELLART:
                res = MODELLART_NAMES[modellArt];
                break;
            case MODELLSTANDORT:
                res = MODELLSTANDORT_NAMES[modellStandort];
                break;
            case AENDERUNGSDATUM:
                if(aenderungsdatum != null) {
                    res = DateFormat.getDateInstance().format(aenderungsdatum);
                } else res = "";
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
		setHasChanged(true);
		this.hersteller = hersteller;
	}

	public String getHerstellerNr() {
		return herstellerNr;
	}

	public void setHerstellerNr(String herstellerNr) {
		setHasChanged(true);
		this.herstellerNr = herstellerNr;
	}

	public String getAuflage() {
		return auflage;
	}

	public void setAuflage(String auflage) {
		setHasChanged(true);
		this.auflage = auflage;
	}

	public String getProduktionsdatum() {
		return produktionsdatum;
	}

	public void setProduktionsdatum(String produktionsdatum) {
		setHasChanged(true);
		this.produktionsdatum = produktionsdatum;
	}

	public String getBilddatei() {
		return bilddatei;
	}

	public void setBilddatei(String bilddatei) {
		setHasChanged(true);
		this.bilddatei = bilddatei;
	}

	public int getModellStandort() {
		return modellStandort;
	}

	public void setModellStandort(int modellStandort) {
		setHasChanged(true);
		this.modellStandort = modellStandort;
	}

	public Date getAenderungsdatum() {
		return aenderungsdatum;
	}

	public void setAenderungsdatum(Date aenderungsdatum) {
		setHasChanged(true);
		this.aenderungsdatum = aenderungsdatum;
	}

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		setHasChanged(true);
		this.marke = marke;
	}

	public String getAchsfolge() {
		return achsfolge;
	}

	public void setAchsfolge(String achsfolge) {
		setHasChanged(true);
		this.achsfolge = achsfolge;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		setHasChanged(true);
		this.typ = typ;
	}

	public String getAufbau() {
		return aufbau;
	}

	public void setAufbau(String aufbau) {
		setHasChanged(true);
		this.aufbau = aufbau;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		setHasChanged(true);
		this.art = art;
	}

	public String getDruck() {
		return druck;
	}

	public void setDruck(String druck) {
		setHasChanged(true);
		this.druck = druck;
	}

	public String getPreisEK() {
		return preisEK;
	}

	public void setPreisEK(String preisEK) {
		setHasChanged(true);
		this.preisEK = preisEK;
	}

	public String getPreisVK() {
		return preisVK;
	}

	public void setPreisVK(String preisVK) {
		setHasChanged(true);
		this.preisVK = preisVK;
	}

	public String getPreisSL() {
		return preisSL;
	}

	public void setPreisSL(String preisSL) {
		setHasChanged(true);
		this.preisSL = preisSL;
	}

	public int getModellArt() {
		return modellArt;
	}

	public void setModellArt(int modellArt) {
		setHasChanged(true);
		this.modellArt = modellArt;
	}

	public List<ExtraProperty> getExtraProperties() {
		return extraProperties;
	}

	public void setExtraProperties(List<ExtraProperty> extraProperties) {
		setHasChanged(true);
		this.extraProperties = extraProperties;
	}
}
