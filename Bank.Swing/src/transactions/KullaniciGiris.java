package transactions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import database.DatabaseConnection;
import database.IBilgiControl;
import transactions.HesapBilgileri;

public class KullaniciGiris extends DatabaseConnection implements IBilgiControl {

	
	
	private String kullaniciString = null;
	private String sifreString 	   = null;
	
	public boolean bilgilerDogrumu() {

		return !(this.kullaniciString == null || this.sifreString ==null);
	}


	public String getKullaniciAdi() {
		return kullaniciString;
	}
	
	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciString = kullaniciAdi;
	}
	
	public String getSifre() {
		return sifreString;
	}
	
	public void setSifre(String sifre) {
		this.sifreString = sifre;
	}

	public boolean girisBilgileriControl() {
		
		if(bilgilerDogrumu())
			if(girisBasariliMi()) {
				this.getHesapBilgileri().girisYap(kullaniciString);
				return true;
			}else {
				return false;
			}
		return false;
	}

	private boolean girisBasariliMi() {
	    String queryString = "SELECT username, password FROM users " +
	            "WHERE username = ? AND password = ?";
	    
	    try (PreparedStatement statement = super.connection.prepareStatement(queryString)) {
	        statement.setString(1, this.kullaniciString);
	        statement.setString(2, this.sifreString);

	        try (ResultSet rs = statement.executeQuery()) {
	            return rs.next();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public HesapBilgileri getHesapBilgileri() {
		
		return HesapBilgileri.getInstance();
	}
	

}
