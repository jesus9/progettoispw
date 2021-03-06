package it.uniroma2.ispw.factory;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import it.uniroma2.ispw.style.BeautifulJButton;
import it.uniroma2.ispw.style.BeautifulJPanel;
import it.uniroma2.ispw.style.BeautifulJTable;

public class BeautifulWidgetFactory extends WidgetFactory {
	
	private BeautifulWidgetFactory() {}
	
	private static class LazyHolderBWF{
		private static final BeautifulWidgetFactory INSTANCE = new BeautifulWidgetFactory();
	}
	
	public static BeautifulWidgetFactory getInstance(){
		return LazyHolderBWF.INSTANCE;
	}

	@Override
	public JButton createJButton() {
		return new BeautifulJButton();
	}

	@Override
	public JPanel createJPanel() {
		return new BeautifulJPanel();
	}

	@Override
	public JTable createJTable() {
		return new BeautifulJTable();
	}

}
