
package presentation;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

import bll.Manager;

public class InterfataComanda extends Frame {
	private JFrame frame;
	private JPanel panel= new JPanel();
	private JPanel produs = new JPanel();
	private JPanel adaugaP = new JPanel();
	private JPanel modifP = new JPanel();
	private JPanel stergeP = new JPanel();
	//private JPanel pnlCustomersGenerateRecipt = new JPanel();
			
	private JTextField  adaugaDenumireP=new JTextField("DenumireP");
	private JTextField  adaugaCantit=new JTextField("Cantitate");
	private JTextField  adaugaPretU=new JTextField("PretUnitar");
	private JTextField  modifID=new JTextField("ID");
	private JTextField  modifDenumireP=new JTextField("DenumireNoua");
	private JTextField  modifCantitate=new JTextField("CantitateNoua");
	private JTextField  modifPret=new JTextField("PretNou");
	private JTextField  sterge=new JTextField("IDp");
	
	private JButton butonAdauga= new JButton("Adauga produs");
	private JButton butonModif= new JButton("Modifica produs");
	private JButton butonSterge= new JButton("Sterge produs");

	private JTable tabelProdus;
	private Tabel tm= new Tabel();
	
	
	
	
	public InterfataComanda() {
		Manager manager = new Manager();
		
		frame = new JFrame("OperatiiProduse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		produs.setMaximumSize(new Dimension(700, 500));
		
		produs.setLayout(new BoxLayout(produs, BoxLayout.Y_AXIS));
		adaugaP.setLayout(new BoxLayout(adaugaP, BoxLayout.X_AXIS));
		modifP.setLayout(new BoxLayout(modifP, BoxLayout.X_AXIS));
		stergeP.setLayout(new BoxLayout(stergeP, BoxLayout.X_AXIS));

		
		adaugaDenumireP.setMaximumSize(new Dimension(200, 20));
		adaugaCantit.setMaximumSize(new Dimension(200, 20));
		adaugaPretU.setMaximumSize(new Dimension(200, 20));
		modifID.setMaximumSize(new Dimension(200, 20));
		modifDenumireP.setMaximumSize(new Dimension(200, 20));
		modifCantitate.setMaximumSize(new Dimension(200, 20));
		modifPret.setMaximumSize(new Dimension(200, 20));
		sterge.setMaximumSize(new Dimension(200, 20));
		produs.setMaximumSize(new Dimension(800, 800));
		
		butonAdauga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.addProduct(adaugaDenumireP.getText(), Integer.parseInt(adaugaCantit.getText()),	Double.parseDouble(adaugaPretU.getText()));
					tabelProdus.setModel(tm.CreateModel(manager.getProducts()));
				} catch (SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException | InstantiationException | SQLException  e1) {
					e1.printStackTrace();
				}
			}
		});

			
		butonModif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.editProduct(Integer.parseInt(modifID.getText()), modifDenumireP.getText(),
							Integer.parseInt(modifCantitate.getText()),
							Double.parseDouble(modifPret.getText()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e1) {
					
					e1.printStackTrace();
				}
				tabelProdus.setModel(tm.CreateModel(manager.getProducts()));
			}
		});

		
			
		butonSterge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.deleteProduct(Integer.parseInt(sterge.getText()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e1) {
					e1.printStackTrace();
				}
				tabelProdus.setModel(tm.CreateModel(manager.getProducts()));
			}
		});
		
		tabelProdus = new JTable(tm.CreateModel(manager.getProducts()));
		//tabelProdus.setVisible(true);
		
		adaugaP.add(adaugaDenumireP);
		adaugaP.add(adaugaCantit);
		adaugaP.add(adaugaPretU);
		adaugaP.add(butonAdauga);
		modifP.add(modifID);
		modifP.add(modifDenumireP);
		modifP.add(modifCantitate);
		modifP.add(modifPret);
		modifP.add(butonModif);
		stergeP.add(sterge);
		stergeP.add(butonSterge);
		produs.add(adaugaP);
		produs.add(modifP);
		produs.add(stergeP);
		produs.add(tabelProdus);
		panel.add(produs);
		frame.add(panel);
		frame.setVisible(true);
		
		
		
	}
}