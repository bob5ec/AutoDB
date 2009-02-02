package bz.asd.autodb.data;

import java.util.LinkedList;
import java.util.List;

public abstract class Model {
	
	public static final int SERIENMODELL=0, WERBEMODELL=1, UMBAU=2;

	private String hersteller, herstellerNr, auflage, produktionsdatum,
	bilddatei, modellStandort, aenderungsdatum,
	marke, achsfolge, typ, aufbau, art, druck,
	preisEK, preisVK, preisSL;
	private int modellArt;
	private List<ExtraProperty> extraProperties;
	
	public Model() {
		init();
	}
	
	protected void init() {
		extraProperties = new LinkedList<ExtraProperty>();
	}
	
	protected abstract void setHasChanged(boolean hasChanged);
	
	public boolean equals(Object other) {
		if(other instanceof Model) {
			Model o = (Model) other;
			return equals(bilddatei, o.bilddatei) &&
			equals(druck, o.druck) &&
			equals(hersteller, o.hersteller) &&
			equals(herstellerNr, o.herstellerNr) &&
			equals(auflage, o.auflage) &&
			equals(produktionsdatum, o.produktionsdatum) &&
			equals(modellStandort, o.modellStandort) &&
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

	public String getModellStandort() {
		return modellStandort;
	}

	public void setModellStandort(String modellStandort) {
		setHasChanged(true);
		this.modellStandort = modellStandort;
	}

	public String getAenderungsdatum() {
		return aenderungsdatum;
	}

	public void setAenderungsdatum(String aenderungsdatum) {
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
