package it.uniroma2.ispw.bean;

public class PagamentoBean {

	private int importo;
	private String metodoPag;
	private String numeroCarta;
	private String scadenzaCarta;
	private String numeroDiSicurezza;
	private String codiceIBAN;
	private String causale;
	
	public PagamentoBean(){
		this.importo = 0;
		this.metodoPag = "";
		this.numeroCarta = "";
		this.scadenzaCarta = "";
		this.numeroDiSicurezza = "";
		this.codiceIBAN = "";
		this.causale = "";
	}

	public int getImporto() {
		return importo;
	}

	public void setImporto(int importo) {
		this.importo = importo;
	}

	public String getMetodoPag() {
		return metodoPag;
	}

	public void setMetodoPag(String metodoPag) {
		this.metodoPag = metodoPag;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public String getScadenzaCarta() {
		return scadenzaCarta;
	}

	public void setScadenzaCarta(String scadenzaCarta) {
		this.scadenzaCarta = scadenzaCarta;
	}

	public String getNumeroDiSicurezza() {
		return numeroDiSicurezza;
	}

	public void setNumeroDiSicurezza(String numeroDiSicurezza) {
		this.numeroDiSicurezza = numeroDiSicurezza;
	}

	public String getCodiceIBAN() {
		return codiceIBAN;
	}

	public void setCodiceIBAN(String codiceIBAN) {
		this.codiceIBAN = codiceIBAN;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}
	
	
}