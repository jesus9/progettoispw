package it.uniroma2.ispw.controller;

import java.util.ArrayList;
import java.util.List;

import it.uniroma2.ispw.bean.PagamentoBean;
import it.uniroma2.ispw.factory.FactoryCostoSpedizione;
import it.uniroma2.ispw.factory.FactoryPagamento;
import it.uniroma2.ispw.model.Consumatore;
import it.uniroma2.ispw.model.Ente;
import it.uniroma2.ispw.model.LineaOrdine;
import it.uniroma2.ispw.model.Ordine;
import it.uniroma2.ispw.model.Pagamento;
import it.uniroma2.ispw.model.Prodotto;
import it.uniroma2.ispw.model.Spedizione;
import it.uniroma2.ispw.model.SpedizioneNormale;
import it.uniroma2.ispw.model.SpedizioneRapida;
import it.uniroma2.ispw.model.UtenteRegistrato;
import it.uniroma2.ispw.persistence.LineaOrdineDAO;
import it.uniroma2.ispw.persistence.OrdineDAO;
import it.uniroma2.ispw.persistence.PagamentoDAO;
import it.uniroma2.ispw.persistence.ProdottoDAO;
import it.uniroma2.ispw.persistence.SpedizioneDAO;
import it.uniroma2.ispw.prezzo.PrezzoFinale;
import it.uniroma2.ispw.prezzo.PrezzoFinaleConsumatore;
import it.uniroma2.ispw.prezzo.PrezzoFinaleEnte;
import it.uniroma2.ispw.spedizione.CostoSpedizione;
import it.uniroma2.ispw.spedizione.CostoSpedizioneNormale;
import it.uniroma2.ispw.spedizione.CostoSpedizioneRapida;


public class AcquistaProdotto {
	
	ProdottoDAO p = new ProdottoDAO();
	LineaOrdineDAO lo = new LineaOrdineDAO();
	
	public boolean effettuaAcquisto(List<Prodotto> lp, String tipoSpedizione, 
			UtenteRegistrato ur, PagamentoBean pbean, String recapito){
		
		/* creazione ordine */
		Ordine ord = new Ordine();
		
		/* calcolo importo senza sconto*/
		int importoNonScontato = 0;
		for (Prodotto p : lp){
			importoNonScontato += p.getPrezzo();
		}
		
		/* calcolo importo con sconto */
		int importoScontato = 0;
		int sconto = 0;
		PrezzoFinale pf;
		if (ur instanceof Ente){
			pf = new PrezzoFinaleEnte();
		}
		else{
			pf = new PrezzoFinaleConsumatore();
		}
		
		for (Prodotto p : lp){
			importoScontato += pf.calcolaPrezzoFinale(p);
		}
		sconto = importoNonScontato - importoScontato;
		ord.setPrezzo(importoScontato);
		
		/* calcolo costo Spedizione */
		FactoryCostoSpedizione fcs = new FactoryCostoSpedizione();
		CostoSpedizione cs = fcs.creaSpedizione(tipoSpedizione);
		int costoSped = cs.calcolaCostoSpedizione(lp.size());
		ord.setPrezzoSped(costoSped);
		
		/* creazione lista linee ordine */
		List<LineaOrdine> lineeOrdine = new ArrayList<LineaOrdine>();
		for (Prodotto p : lp){
			LineaOrdine lo = new LineaOrdine(pf.calcolaPrezzoFinale(p),ord,p);
			lineeOrdine.add(lo);
			System.out.println(lo.getProdotto().getNome());
		}
		ord.setLineeOrdine(lineeOrdine);
		
		/* creazione pagamento */
		FactoryPagamento fp = new FactoryPagamento();
		Pagamento pag = fp.creaPagamento(pbean);
		pag.setImporto(importoScontato + costoSped);
		pag.setOrdine(ord);
		pag.setUtenteRegistrato(ur);
		ord.setPagamento(pag);
		
		/* creazione Spedizione*/
		Spedizione sped;
		if (tipoSpedizione.equals("Normale")) sped = new SpedizioneNormale("In corso", recapito, ord);
		else sped = new SpedizioneRapida("In corso", recapito, ord);
		ord.setSped(sped);
		
		OrdineDAO odao = new OrdineDAO();
		System.out.println("prova");
		
		
		ord.setUtenteReg(ur);
		odao.addOrdine(ord);
	
		return true;
		
		
		
	}

	public List<Pagamento> acquistiUtente(String email) {
		// TODO Auto-generated method stub
		List<Pagamento> listaPag = null;
		PagamentoDAO pd = new PagamentoDAO();
		listaPag = pd.listaAcquistiUtente(email);
		return listaPag;
	}

	public List<Prodotto> prodottiUtente(String email){
		
		List<Prodotto> prodotti = null;
		prodotti = p.listaProdottiUtente(email);
		return prodotti;
	}
	
	public List<LineaOrdine> venditeUtente(String email){
		
		List<Prodotto> listaP = prodottiUtente(email);
		List<LineaOrdine> listaLO = null;
		List<LineaOrdine> listaLO2 = new ArrayList<LineaOrdine>();
		Prodotto prod = null;
		for(int i=0; i<listaP.size(); i++){
			prod = listaP.get(i);
			listaLO = lo.listaVenditeUtente(prod);
			for(int j=0; j<listaLO.size(); j++){
				listaLO2.add(listaLO.get(j));
			}
		}
		return listaLO2;
	}
}
