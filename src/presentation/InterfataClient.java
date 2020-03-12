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

public class InterfataClient extends Frame {
	
	private JFrame frame;
	private JPanel panel= new JPanel();
	private JPanel client = new JPanel();;
	private JPanel adaugaCl = new JPanel();;
	private JPanel modifCl = new JPanel();;
	private JPanel stergeCl = new JPanel();;
	//private JPanel pnlCustomersGenerateRecipt = new JPanel();
			
	private JTextField  adaugaNumeCl=new JTextField("NameClient");
	private JTextField  modifID=new JTextField("ID");
	private JTextField  modifNumeCl=new JTextField("NameClient");
	private JTextField  sterge=new JTextField("IDcl");
	
	private JButton butonAdauga= new JButton("Adauga client");
	private JButton butonModif= new JButton("Modifica client");
	private JButton butonSterge= new JButton("Sterge client");

	private JTable tabelClienti;
	private Tabel tm= new Tabel();
	
	

	public InterfataClient() {
		Manager manager = new Manager();
		frame = new JFrame("OperatiiClienti");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
			
		client.setMaximumSize(new Dimension(900, 900));
	
		client.setLayout(new BoxLayout(client, BoxLayout.Y_AXIS));
		adaugaCl.setLayout(new BoxLayout(adaugaCl, BoxLayout.X_AXIS));
		stergeCl.setLayout(new BoxLayout(stergeCl, BoxLayout.X_AXIS));
		modifCl.setLayout(new BoxLayout(modifCl, BoxLayout.X_AXIS));
		//pnlCustomersGenerateRecipt.setLayout(new BoxLayout(pnlCustomersGenerateRecipt, BoxLayout.X_AXIS));
		
		adaugaNumeCl.setMaximumSize(new Dimension(200, 20));
		modifNumeCl.setMaximumSize(new Dimension(200, 20));
		modifID.setMaximumSize(new Dimension(200, 20));
	
		
		
		butonAdauga.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						manager.addCustomer(adaugaNumeCl.getText());
					} catch (SecurityException | InstantiationException | SQLException e1) {
						
						e1.printStackTrace();
					}
					tabelClienti.setModel(tm.CreateModel(manager.getCustomers()));

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException| IntrospectionException e1) {
					e1.printStackTrace();
				} 
			}
		});

		

		butonModif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.editCustomer(Integer.parseInt(modifID.getText()), modifNumeCl.getText());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e1) {
					
					e1.printStackTrace();
				}
				tabelClienti.setModel(tm.CreateModel(manager.getCustomers()));
			}
		});

		
		butonSterge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.deleteCustomer(Integer.parseInt(sterge.getText()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException| IntrospectionException e1) {
					e1.printStackTrace();
				}
				tabelClienti.setModel(tm.CreateModel(manager.getCustomers()));
			}
		});

		
		tabelClienti = new JTable(tm.CreateModel(manager.getCustomers()));

		adaugaCl.add(adaugaNumeCl);
		adaugaCl.add(butonAdauga);
		modifCl.add(modifID);
		modifCl.add(modifNumeCl);
		modifCl.add(butonModif);
		stergeCl.add(sterge);
		stergeCl.add(butonSterge);
				
		client.add(adaugaCl);
		client.add(modifCl);
		client.add(stergeCl);
		client.add(tabelClienti);
		panel.add(client);
		frame.add(panel);
		frame.setVisible(true);
		
	}
}