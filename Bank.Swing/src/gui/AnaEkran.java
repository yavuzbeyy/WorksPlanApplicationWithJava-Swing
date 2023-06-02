package gui;

import java.awt.EventQueue;

import transactions.HesapBilgileri;
import transactions.KullaniciAdiAl;
import transactions.KullaniciGiris;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseConnection;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AnaEkran extends JFrame {

	private JPanel contentPane;
	
	DatabaseConnection dbConnection;
	
	public static DefaultListModel<String> yapilacaklarListModel = new DefaultListModel<>();
	public static DefaultListModel<String> yapilmaktaListModel = new DefaultListModel<>();
	public static DefaultListModel<String> tamamlananListModel = new DefaultListModel<>();
	
	private JTextField yapilacakIslerEkleTexti;
	private JTextField islemTexti;
	public static String userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkran frame = new AnaEkran();
					frame.setVisible(true);
					frame.setFocusable(true);
					frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public AnaEkran() {
		setTitle("İşlerim");
		
		final HesapBilgileri hesapBilgileri = new HesapBilgileri();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JList<String> yapilacak_listesi = new JList<>(yapilacaklarListModel);
		yapilacak_listesi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		yapilacak_listesi.setBackground(new Color(184, 134, 11));
		yapilacak_listesi.setBounds(10, 151, 318, 536);
		contentPane.add(yapilacak_listesi);
		
		final JList<String> yapilmakta_listesi = new JList<>(yapilmaktaListModel);
		yapilmakta_listesi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		yapilmakta_listesi.setBackground(new Color(70, 130, 180));
		yapilmakta_listesi.setBounds(406, 151, 318, 536);
		contentPane.add(yapilmakta_listesi);
		
		final JList<String> tamamlanan_listesi = new JList<>(tamamlananListModel);
		tamamlanan_listesi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		tamamlanan_listesi.setBackground(new Color(85, 107, 47));
		tamamlanan_listesi.setBounds(787, 151, 318, 536);
		contentPane.add(tamamlanan_listesi);
		
		JLabel lblNewLabel = new JLabel("Yapılacak İşler");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel.setBounds(10, 29, 318, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblYapmaktaOlduklarm = new JLabel("Yapmakta Olduğum İşler");
		lblYapmaktaOlduklarm.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		lblYapmaktaOlduklarm.setBounds(406, 29, 318, 41);
		lblYapmaktaOlduklarm.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblYapmaktaOlduklarm);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tamamlanan İşler");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		lblNewLabel_1_1.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel_1_1.setBounds(787, 29, 318, 41);
		contentPane.add(lblNewLabel_1_1);
		
		JButton yapilacaklaraEkle = new JButton("Ekle");
		yapilacaklaraEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				yapilacaklaraEklemeMetodu();
			}
			
			
			private void yapilacaklaraEklemeMetodu() {
				
				dbConnection = new DatabaseConnection();
				
			    String eklenecekIs = islemTexti.getText().trim();

			    String queryString = "INSERT INTO works(yapilacak_isler, kullanici) VALUES (?, ?)";

			    try (PreparedStatement statement = dbConnection.connection.prepareStatement(queryString)) {
			        statement.setString(1, eklenecekIs);
			        statement.setString(2, userName);
			        statement.executeUpdate();
			        
			        yapilacaklarListModel.addElement(eklenecekIs);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			  
			    		
				yapilacak_listesi.setModel(yapilacaklarListModel);		
				
			}
		});
		

		
		yapilacaklaraEkle.setBackground(new Color(0, 100, 0));
		yapilacaklaraEkle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yapilacaklaraEkle.setBounds(10, 80, 129, 61);
		contentPane.add(yapilacaklaraEkle);
			
		
		JButton yapilacaklardanSil = new JButton("Sil");
		yapilacaklardanSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yapilicaklardanSilmeMetodu();
			}

			private void yapilicaklardanSilmeMetodu()  {
				
				dbConnection = new DatabaseConnection();
				
			    String silincekIs = islemTexti.getText().trim();

			    String queryString = "DELETE FROM works WHERE yapilacak_isler = ? AND kullanici = ?";
	

			    try (PreparedStatement statement = dbConnection.connection.prepareStatement(queryString)) {
			        statement.setString(1, silincekIs);
			        statement.setString(2, userName);
			        statement.executeUpdate();
			        
			        yapilacaklarListModel.removeElement(silincekIs);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			  
			    		
				yapilacak_listesi.setModel(yapilacaklarListModel);		
				
			}
		});
		yapilacaklardanSil.setBackground(new Color(139, 0, 0));
		yapilacaklardanSil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yapilacaklardanSil.setBounds(199, 80, 129, 61);
		contentPane.add(yapilacaklardanSil);
		
		JButton yapilmaktaOlanlaraEkle = new JButton("Ekle");
		yapilmaktaOlanlaraEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yapilmaktaOlanlaraEkleMetodu();
			}

			private void yapilmaktaOlanlaraEkleMetodu() {
				
				{
					
					dbConnection = new DatabaseConnection();
					
				    String eklenecekIs = islemTexti.getText().trim();

				    String queryString = "INSERT INTO works(yapilan_islemler, kullanici) VALUES (?, ?)";

				    try (PreparedStatement statement = dbConnection.connection.prepareStatement(queryString)) {
				        statement.setString(1, eklenecekIs);
				        statement.setString(2, userName);
				        statement.executeUpdate();
				        
				        yapilmaktaListModel.addElement(eklenecekIs);
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }
				  
				    		
					yapilmakta_listesi.setModel(yapilmaktaListModel);		
					
				}
				
			}
		});
		yapilmaktaOlanlaraEkle.setBackground(new Color(0, 100, 0));
		yapilmaktaOlanlaraEkle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yapilmaktaOlanlaraEkle.setBounds(406, 80, 129, 61);
		contentPane.add(yapilmaktaOlanlaraEkle);
		
		JButton yapilmaktaOlanlardanSil = new JButton("Sil");
		yapilmaktaOlanlardanSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yapilmaktaOlanlardanSilmeMetodu();
			}

			private void yapilmaktaOlanlardanSilmeMetodu() {
				
				dbConnection = new DatabaseConnection();
				
			    String silincekIs = islemTexti.getText().trim();

			    String queryString = "DELETE FROM works WHERE yapilan_islemler = ? AND kullanici = ?";
	

			    try (PreparedStatement statement = dbConnection.connection.prepareStatement(queryString)) {
			        statement.setString(1, silincekIs);
			        statement.setString(2, userName);
			        statement.executeUpdate();
			        
			        yapilmaktaListModel.removeElement(silincekIs);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			  
			    		
				yapilmakta_listesi.setModel(yapilmaktaListModel);		
				
			}
		});
		yapilmaktaOlanlardanSil.setBackground(new Color(139, 0, 0));
		yapilmaktaOlanlardanSil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yapilmaktaOlanlardanSil.setBounds(595, 80, 129, 61);
		contentPane.add(yapilmaktaOlanlardanSil);
		
		JButton tamamlananlaraEkle = new JButton("Ekle");
		tamamlananlaraEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamamlananlaraEkleMetodu();
			}

			private void tamamlananlaraEkleMetodu() {
				
				{
					
					{
						
						dbConnection = new DatabaseConnection();
						
					    String eklenecekIs = islemTexti.getText().trim();

					    String queryString = "INSERT INTO works(tamamlanan_isler, kullanici) VALUES (?, ?)";

					    try (PreparedStatement statement = dbConnection.connection.prepareStatement(queryString)) {
					        statement.setString(1, eklenecekIs);
					        statement.setString(2, userName);
					        statement.executeUpdate();
					        
					        tamamlananListModel.addElement(eklenecekIs);
					    } catch (SQLException e) {
					        e.printStackTrace();
					    }
					  
					
						tamamlanan_listesi.setModel(tamamlananListModel);		
						
					}
					
				}
				
			}
		});
		tamamlananlaraEkle.setBackground(new Color(0, 100, 0));
		tamamlananlaraEkle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tamamlananlaraEkle.setBounds(787, 80, 129, 61);
		contentPane.add(tamamlananlaraEkle);
		
		JButton tamamlananlardanSil = new JButton("Sil");
		tamamlananlardanSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamamlananlardanSilMetodu();
			}

			private void tamamlananlardanSilMetodu() {
				
				{
					
					dbConnection = new DatabaseConnection();
					
				    String silincekIs = islemTexti.getText().trim();

				    String queryString = "DELETE FROM works WHERE tamamlanan_isler = ? AND kullanici = ?";
		

				    try (PreparedStatement statement = dbConnection.connection.prepareStatement(queryString)) {
				        statement.setString(1, silincekIs);
				        statement.setString(2, userName);
				        statement.executeUpdate();
				        
				        tamamlananListModel.removeElement(silincekIs);
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }
				  
				    		
					tamamlanan_listesi.setModel(tamamlananListModel);		
					
				}
				
			}
		});
		tamamlananlardanSil.setBackground(new Color(139, 0, 0));
		tamamlananlardanSil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tamamlananlardanSil.setBounds(976, 80, 129, 61);
		contentPane.add(tamamlananlardanSil);
		
		islemTexti = new JTextField();
		islemTexti.setBounds(249, 10, 190, 53);
		contentPane.add(islemTexti);
		islemTexti.setColumns(10);
		


	}
	
	public static void kullaniciDondur(String kullaniciAdi) {
		userName = kullaniciAdi;
	}


}
