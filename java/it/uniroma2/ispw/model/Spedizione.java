package it.uniroma2.ispw.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "spedizione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Spedizione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idSpedizione")
	private int idSpedizione;
	

	@Column(name = "statoSpedizione")
	private String statoSpedizione;
	
	@Column(name = "recapito")
	private String recapito;
	
	@OneToOne
	@JoinColumn(name = "idOrdine", referencedColumnName = "idOrdine")
	protected Ordine ordine;
	
	public Spedizione(){}
	
	public Spedizione(String statoSpedizione, String recapito, Ordine ordine){
		this.statoSpedizione = statoSpedizione;
		this.recapito = recapito;
		this.ordine = ordine;
	}
	
	

	public int getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getStatoSpedizione() {
		return statoSpedizione;
	}

	public void setStatoSpedizione(String statoSpedizione) {
		this.statoSpedizione = statoSpedizione;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	
	public abstract int calcolaCostoSpedizione(int numeroDiElementi);
	
}
