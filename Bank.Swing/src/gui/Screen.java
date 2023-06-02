package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TextUI;

import com.mysql.cj.xdevapi.DbDocFactory;

import database.DatabaseConnection;
import database.IBilgiControl;

import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Checkbox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.PublicKey;
import java.util.Arrays;

import gui.ayarlar.ButonAyarlari;
import transactions.HesapBilgileri;
import transactions.KullaniciGiris;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;


public class Screen extends JFrame implements IBilgiControl{

	private JPanel contentPane;
	private JPasswordField passwordField;
	public final Color originalBackGroundColor;
	
	private final String kullaniciAdiString = "T.C. NO / Kullanıcı Adı";
	private final String sifreString = "ŞifreŞifre";
	
	private KullaniciGiris kullaniciGirisObjesi = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Screen frame = new Screen();
				try {	
					frame.setVisible(true);
					frame.setFocusable(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Screen() {
		
		DatabaseConnection dbConnection = new DatabaseConnection();
		
		setTitle("İş Programı");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel girisPanel = new JPanel();
		girisPanel.setBackground(new Color(245, 245, 220));
		girisPanel.setBounds(0, 0, 710, 526);
		contentPane.add(girisPanel);
		girisPanel.setLayout(null);
		girisPanel.setFocusable(true);
		
		Label ilkYazi = new Label("YAPILACAK İŞLERE HOŞ GELDİNİZ");
		ilkYazi.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		ilkYazi.setAlignment(Label.CENTER);
		ilkYazi.setBounds(10, 51, 626, 84);
		girisPanel.add(ilkYazi);
		
		//Kullanıcı Adı Text Bölgesi
		final TextField textField = new TextField();
		textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		textField.setBounds(225, 168, 317, 22);
		textField.setText(kullaniciAdiString);
		girisPanel.add(textField);
		
		//Kullanıcı adı tıklandığında içini boşalt
		textField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(textField.getText().trim().equals(kullaniciAdiString))
					textField.setText("");
			}
			//Tıklamayı kaldırdığında yazıyı geri yükleme
			public void focusLost(FocusEvent e) {
				if(textField.getText().trim().equals(""))
					textField.setText(kullaniciAdiString);
			}
		});

		//Password text bölgesi
	    passwordField = new JPasswordField();
		passwordField.setBounds(225, 232, 317, 43);
		passwordField.setToolTipText(sifreString);
		passwordField.setText(sifreString);
		girisPanel.add(passwordField);
		passwordField.addFocusListener(new FocusAdapter() {
			
			//Şifre  tıklandığında içini boşalt
		    public void focusGained(FocusEvent e) {
		        char[] password = passwordField.getPassword();
		        if (Arrays.equals(password, sifreString.toCharArray())) {
		            passwordField.setText("");
		        }
		    }
		    //Password  tıklandığında içini boşalt
		    public void focusLost(FocusEvent e) {
		        char[] password = passwordField.getPassword();
		        if (password.length == 0) {
		            passwordField.setText(sifreString);
		        }
		    }
		});

		
		// Giriş Butonu
		final JButton girisButton = new JButton("Giriş");
		girisButton.setBackground(new Color(0, 100, 0));
		girisButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				ButonAyarlari.setBackGround(girisButton, Color.cyan);
			}
		    public void mouseExited(MouseEvent e) {
		        ButonAyarlari.setOrginalBackground(girisButton);
		    }
		});
		
		// Giriş yapıldığında
		girisButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(textField.getText().trim().equals(kullaniciAdiString))
		    		JOptionPane.showMessageDialog(contentPane, "Lütfen bilgilerinizi giriniz.");
		    	else if (textField.getText().trim().equals("")) {
		    		JOptionPane.showMessageDialog(contentPane, "Lütfen boş bırakmayınız.");
				}
		    	else {
			     girisYap(textField.getText().trim(),String.valueOf(passwordField.getPassword()));
		    	}
		    }
		});
		girisButton.setBounds(295, 296, 183, 59);
		girisPanel.add(girisButton);
		originalBackGroundColor = girisButton.getBackground();

		// Kayıt Ol butonu
		final JButton kayitOlButton = new JButton("Kayıt Ol");
		kayitOlButton.setBackground(new Color(139, 0, 0));
		kayitOlButton.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        ButonAyarlari.setBackGround(kayitOlButton, Color.cyan);
		    }

		    public void mouseExited(MouseEvent e) {
		        ButonAyarlari.setOrginalBackground(kayitOlButton);
		    }
		});
		// Kayıt ol'a basıldığında
		kayitOlButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        setVisible(false); // Ekranı gizle
		        dispose(); // Ekranı kapat
		        new KayitEkrani().setVisible(true); // Yeni ekranı aç
		    }
		});
		kayitOlButton.setBounds(295, 379, 182, 59);
		girisPanel.add(kayitOlButton);
		
		//Yazi
		Label kayitOlYazisi = new Label("Hala kaydınız yok mu ?");
		kayitOlYazisi.setAlignment(Label.CENTER);
		kayitOlYazisi.setBounds(-67, 392, 356, 43);
		girisPanel.add(kayitOlYazisi);
	}

	public boolean bilgilerDogrumu() {
		return false;
	}

	public HesapBilgileri getHesapBilgileri() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void girisYap(String kullanici,String sifre) {
		
		   String username = kullanici;
		   String password = sifre;		   
		   
		   AnaEkran.kullaniciDondur(kullanici);
		   
		   this.getKullaniciGirisObjesi().setKullaniciAdi(username);
		   this.getKullaniciGirisObjesi().setSifre(password);
		   
		   if(this.getKullaniciGirisObjesi().girisBilgileriControl()) {
			   setVisible(false); // Ekranı gizle
		       dispose(); // Ekranı kapat
		       new AnaEkran().setVisible(true); // Yeni ekranı aç
		   }else {
			   JOptionPane.showMessageDialog(contentPane, "Giriş Bilgilerl Hatalı. Kayıt Ol ya da tekrar dene.");
		   }
	}
	
	public KullaniciGiris getKullaniciGirisObjesi() {
		if(this.kullaniciGirisObjesi == null)
			kullaniciGirisObjesi = new KullaniciGiris();
		return kullaniciGirisObjesi;
	}

}
