package it.polito.tdp.corsi.model;

public class Divisione {

	private String CDS;
	private  Integer n;
	public Divisione(String cDS, Integer n) {
		super();
		CDS = cDS;
		this.n = n;
	}
	public String getCDS() {
		return CDS;
	}
	public void setCDS(String cDS) {
		CDS = cDS;
	}
	public Integer getN() {
		return n;
	}
	public void setN(Integer n) {
		this.n = n;
	}
	
	//non defiisco hashcode e equals perché so che questi oggetti non verranno mai inseriti in mappe e non ci farò mai un equals, perché
	//la classe serve solo di appoggio. In caso contrario, devo farlo sempre
}
