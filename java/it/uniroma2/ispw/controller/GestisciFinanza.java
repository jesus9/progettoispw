package it.uniroma2.ispw.controller;

import java.util.List;

import it.uniroma2.ispw.model.PrezzoSpedizione;
import it.uniroma2.ispw.model.TipoProdotto;
import it.uniroma2.ispw.persistence.PrezzoSpedizioneDAO;
import it.uniroma2.ispw.persistence.TipoProdottoDAO;

public class GestisciFinanza {
	
	public boolean aggiungiTipoProdotto(TipoProdotto tp){
		TipoProdottoDAO tpdao = new TipoProdottoDAO();
		
		if (tpdao.addTipoProdotto(tp)!=null){
			return true;
		}
		return false;
	}
	
	public boolean eliminaTipoProdotto(TipoProdotto tp){
		TipoProdottoDAO tpdao = new TipoProdottoDAO();
		
		return tpdao.deleteTipoProdotto(tp);
	}
	
	public List<TipoProdotto> visualizzaListaTipiProdotto(){
		TipoProdottoDAO tpdao = new TipoProdottoDAO();
		
		return tpdao.listaTipiProdotto();
	}
	
	public boolean aggiungiOModificaPrezzoSpedizione(PrezzoSpedizione ps){
		PrezzoSpedizioneDAO psdao = new PrezzoSpedizioneDAO();
		
		if (psdao.addOrUpdatePrezzoSpedizione(ps)!=null) return true;
		return false;
	}
	

}