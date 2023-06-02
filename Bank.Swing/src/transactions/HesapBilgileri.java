package transactions;

import java.security.KeyStore.PrivateKeyEntry;

import gui.AnaEkran;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import database.DatabaseConnection;
import database.IBilgiControl;
import database.dbCenter;
import transactions.KullaniciGiris;

public class HesapBilgileri extends dbCenter implements IBilgiControl{

	//Singleton

	private static HesapBilgileri hesapBilgileri = null;
	
	public static HesapBilgileri getInstance() {
		
		if (hesapBilgileri == null) {
			hesapBilgileri = new HesapBilgileri();
		}
		return hesapBilgileri;
	}

	public void girisYap(String kullaniciString) {
		
		this.kullaniciyiAl(kullaniciString);
		this.isleriAl(kullaniciString);
	}
	
	public ArrayList<String> yapilacak_isler_listesi = new ArrayList<>();
	public ArrayList<String> yapilan_isler_listesi = new ArrayList<>();
	public ArrayList<String> tamamlanan_isler_listesi = new ArrayList<>();
	
	public void isleriAl(String kullaniciAdi) {
	    if (this.bilgilerDogrumu()) {
	        String queryString = "SELECT yapilacak_isler, yapilan_islemler, tamamlanan_isler FROM works " +
	                "WHERE kullanici = '" + kullaniciAdi + "'";
	        
	        try (Statement statement = super.connection.createStatement();
	             ResultSet rs = statement.executeQuery(queryString)) {
	            
	            while (rs.next()) {
	            	
	            	String yapilacakIsler = rs.getString("yapilacak_isler");
	                String yapilanIslemler = rs.getString("yapilan_islemler");
	                String tamamlananIsler = rs.getString("tamamlanan_isler");

	                yapilacak_isler_listesi.add(yapilacakIsler);
	                AnaEkran.yapilacaklarListModel.addElement(yapilacakIsler);

	                yapilan_isler_listesi.add(yapilanIslemler);
	                AnaEkran.yapilmaktaListModel.addElement(yapilanIslemler);

	                tamamlanan_isler_listesi.add(tamamlananIsler);
	                AnaEkran.tamamlananListModel.addElement(tamamlananIsler);
	                
	                System.out.println(yapilacak_isler_listesi.get(0));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private void kullaniciyiAl(String kullaniciAdi) {
	    String queryString = "SELECT * FROM users WHERE username = ?";
	    
	    try (PreparedStatement statement = super.connection.prepareStatement(queryString)) {
	        statement.setString(1, kullaniciAdi);
	      
	        
	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	                super.kullaniciId = rs.getInt("id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	@Override
	public boolean bilgilerDogrumu() {
		// TODO Auto-generated method stub
		return !(super.kullaniciId == 0);
	}

	@Override
	public HesapBilgileri getHesapBilgileri() {
		// TODO Auto-generated method stub
		return null;
	}
}
