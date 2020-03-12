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

public class InterfataProdus extends Frame {
	private JFrame frame;
	private JPanel panel= new JPanel();
	private JPanel comenzi = new JPanel();
	private JPanel adaugaC = new JPanel();
	private JButton butonAdauga= new JButton("Adauga produs in cos");
	//private JPanel pnlCustomersGenerateRecipt = new JPanel();
			
	private JTextField  adaugaIDclient=new JTextField("IDclient");
	private JTextField  adaugaIDprodus=new JTextField("IDprodus");
	
	private JTable tabelComenzi,tabelProdus;
	private Tabel tm= new Tabel();
	
	
	public InterfataProdus() {
		Manager manager = new Manager();
		frame = new JFrame("OperatiiComenzi");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		
		comenzi.setMaximumSize(new Dimension(800, 800));

		comenzi.setLayout(new BoxLayout(comenzi, BoxLayout.Y_AXIS));
		adaugaC.setLayout(new BoxLayout(adaugaC, BoxLayout.X_AXIS));
		
		adaugaIDclient.setMaximumSize(new Dimension(50, 20));
		adaugaIDprodus.setMaximumSize(new Dimension(50, 20));
		//InterfataComanda ic=new InterfataComanda();
		
		butonAdauga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.addOrder(Integer.parseInt(adaugaIDclient.getText()), Integer.parseInt(adaugaIDprodus.getText()));
					tabelComenzi.setModel(tm.CreateModel(manager.getOrders()));
					tabelProdus.setModel(tm.CreateModel(manager.getProducts()));
				} catch (SecurityException |IllegalArgumentException e1) {
					
					e1.printStackTrace();
				}
			}
		});

		
		tabelComenzi = new JTable(tm.CreateModel(manager.getOrders()));

		adaugaC.add(adaugaIDclient);
		adaugaC.add(adaugaIDprodus);
		adaugaC.add(butonAdauga);
		comenzi.add(adaugaC);
		comenzi.add(tabelComenzi);
		
		panel.add(comenzi);
		frame.add(panel);
		frame.setVisible(true);
	}
}