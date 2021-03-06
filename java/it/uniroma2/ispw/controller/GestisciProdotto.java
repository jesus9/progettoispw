package it.uniroma2.ispw.controller;

import java.util.ArrayList;
import java.util.List;

import it.uniroma2.ispw.bean.InsProdottoBean;
import it.uniroma2.ispw.bean.ProdottoBean;
import it.uniroma2.ispw.eccezioni.ErroreInserimentoProdotto;
import it.uniroma2.ispw.model.Prodotto;
import it.uniroma2.ispw.model.UtenteRegistrato;
import it.uniroma2.ispw.persistence.ProdottoDAO;
import it.uniroma2.ispw.persistence.UtenteDAO;

public class GestisciProdotto {

	ProdottoDAO p = new ProdottoDAO();
	UtenteDAO u = new UtenteDAO();
	
	protected GestisciProdotto(){}
	
	private static GestisciProdotto instance;
	 
    public synchronized static GestisciProdotto getInstance() {
        if (instance == null)
            instance = new GestisciProdotto();
        return instance;
    }
	
	public synchronized boolean inserisciProdotto(InsProdottoBean iPBean) throws ErroreInserimentoProdotto{
		
		UtenteRegistrato ur = u.getUtente(iPBean.getUtente().getEmail());
		
		Prodotto newProdotto = new Prodotto(iPBean.getNameProduct(), iPBean.getCategory(), iPBean.getTypology(), iPBean.getPrice(), 
				 iPBean.getSale(), ur, 1, iPBean.getComment());
		
		//potrebbe causare un eccezione
		p.checkProdottoPerUtente(iPBean.getNameProduct(), iPBean.getUtente().getEmail());
		
		p.addProdotto(newProdotto);
		
		if (newProdotto == null) return false;
		return true;
	}
	
	/*carica tipo prodotti in liste*/
	public synchronized List<ProdottoBean> prodottiUtente(String email){
		
		List<Prodotto> listaP = null;
		listaP = p.listaProdottiUtente(email);
		if (listaP==null) return null;
		
		List<ProdottoBean> listaPBean = new ArrayList<ProdottoBean>();
		for (Prodotto pr : listaP){
			ProdottoBean prB = new ProdottoBean();
			prB.setIdProd(pr.getId());
			prB.setCategory(pr.getCategoria());
			prB.setDisponibilita(pr.getDisponibilita());
			prB.setEmailUser(pr.getUtenteRegistrato().getEmail());
			prB.setNameProduct(pr.getNome());
			prB.setPrice(pr.getPrezzo());
			prB.setSale(pr.getSconto());
			prB.setTypology(pr.getTipologia());
			prB.setComment(pr.getCommento());
			listaPBean.add(prB);
		}
		return listaPBean;
	}
	
	public synchronized boolean deleteProduct(int id){
		
		return p.deleteProduct(id);
		
	}
	
	public synchronized boolean selezionaProdottoPerId(InsProdottoBean insProdottoBean){
		
		Prodotto pr = null;
		if((pr = p.prendiProdottoPerID(insProdottoBean.getIdProd())) != null){
			insProdottoBean.setNameProduct(pr.getNome());
			insProdottoBean.setCategory(pr.getCategoria());
			insProdottoBean.setTypology(pr.getTipologia());
			insProdottoBean.setPrice(pr.getPrezzo());
			insProdottoBean.setSale(pr.getSconto());
			
			return true;
		}
		return false;
		
	}
	
	public synchronized boolean selezionaProdottoPerId(ProdottoBean prodottoBean){
		Prodotto pr = p.prendiProdottoPerID(prodottoBean.getIdProd());
		
		if (pr==null) return false;
		
		prodottoBean.setCategory(pr.getCategoria());
		prodottoBean.setComment(pr.getCommento());
		prodottoBean.setDisponibilita(pr.getDisponibilita());
		prodottoBean.setIdProd(pr.getId());
		prodottoBean.setNameProduct(pr.getNome());
		prodottoBean.setPrice(pr.getPrezzo());
		prodottoBean.setSale(pr.getSconto());
		prodottoBean.setTypology(pr.getTipologia());
		prodottoBean.setEmailUser(pr.getUtenteRegistrato().getEmail());
		
		
		return false;
		
	}
	
	public synchronized boolean aggiornaProdotto(ProdottoBean updateProductBean){
		
		UtenteRegistrato ur = u.getUtente(updateProductBean.getEmailUser());
		
		if (ur==null) return false;
		
		Prodotto updateProduct = new Prodotto(updateProductBean.getNameProduct(),updateProductBean.getCategory(),
				updateProductBean.getTypology(),updateProductBean.getPrice(),
				updateProductBean.getSale(), ur,1,updateProductBean.getComment());
		
		updateProduct.setId(updateProductBean.getIdProd());
		
		if (p.updateProdotto(updateProduct)){
			return true;
		}
		
		return false;
		
	}

}