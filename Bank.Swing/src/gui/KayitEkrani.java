package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.IBilgiControl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import gui.Controller;
import transactions.HesapBilgileri;
import transactions.KullaniciBasvuru;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KayitEkrani extends JFrame implements IBilgiControl {

	private KullaniciBasvuru kullaniciBasvuruObjesi = null;
	
	private JPanel contentPane;
	private JTextField Adsoyad;
	private JTextField kullaniciAdi;
	private JTextField sifre;
	public final  int limit = 15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KayitEkrani frame = new KayitEkrani();
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
	public KayitEkrani() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1011, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kayıt Olma Ekranı");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(291, 65, 401, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad - Soyad         =");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1.setBounds(167, 263, 176, 59);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kullanıcı Adı / T.C  =");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(167, 342, 200, 59);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ŞİFRE                    =");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_1_1.setBounds(167, 415, 200, 59);
		contentPane.add(lblNewLabel_1_1_1);
		
		Adsoyad = new JTextField();
		Adsoyad.setBounds(417, 263, 257, 59);
		contentPane.add(Adsoyad);
		Adsoyad.setColumns(10);
		Controller.sadeceHarfKontrol(Adsoyad);
		Controller.setMaxLimit(Adsoyad, 18);
		
		kullaniciAdi = new JTextField();
		kullaniciAdi.setColumns(10);
		kullaniciAdi.setBounds(417, 342, 257, 59);
		contentPane.add(kullaniciAdi);
		Controller.setMaxLimit(kullaniciAdi, 12);
		
		sifre = new JTextField();
		sifre.setColumns(10);
		sifre.setBounds(417, 415, 257, 59);
		contentPane.add(sifre);
		Controller.setMaxLimit(sifre, 14);
		
		JButton kayitOl = new JButton("Kayıt Ol");
		kayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				if(Controller.textAlanlariDolumu(contentPane) == true)
				{
				JOptionPane.showMessageDialog(contentPane, "Kayıt Başarılı.");
				basvuruYap();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Kayıt Başarısız.");
				}
			}


		});
		kayitOl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		kayitOl.setBounds(370, 541, 236, 83);
		contentPane.add(kayitOl);
	}
	
	
	public KullaniciBasvuru getKullaniciBasvuruObjesi() {
		if(this.kullaniciBasvuruObjesi == null) {
			kullaniciBasvuruObjesi = new KullaniciBasvuru();
		}
		return kullaniciBasvuruObjesi;
	}

	
	public boolean bilgilerDogrumu() {
		return Controller.textAlanlariDolumu(contentPane);
	}

	public HesapBilgileri getHesapBilgileri() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void basvuruYap() {
		
		getKullaniciBasvuruObjesi().setAdSoyad(Adsoyad.getText().trim());
		getKullaniciBasvuruObjesi().setKullaniciAdi(kullaniciAdi.getText().trim());
		getKullaniciBasvuruObjesi().setSifre(sifre.getText().trim());
		getKullaniciBasvuruObjesi().basvuruOnayla(); 
		
		contentPane.setVisible(false);
		dispose(); // Ekranı kapat
		new Screen().setVisible(true);
	}
	
}
