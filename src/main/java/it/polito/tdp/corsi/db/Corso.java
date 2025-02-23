package it.polito.tdp.corsi.db;

import java.util.Objects;

public class Corso {

	private String codins;
	private int crediti;
	private String nome;
	private Integer pd;
	public Corso(String codins, int i, String nome, Integer pd) {
		super();
		this.codins = codins;
		this.crediti = i;
		this.nome = nome;
		this.pd = pd;
	}
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public int getCrediti() {
		return crediti;
	}
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPd() {
		return pd;
	}
	public void setPd(Integer pd) {
		this.pd = pd;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codins);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		return Objects.equals(codins, other.codins);
	}
	@Override
	public String toString() {
		return "Corso: codins = " + codins + ", crediti = " + crediti + ", nome = " + nome + ", pd = " + pd;
	}
	
	
}
