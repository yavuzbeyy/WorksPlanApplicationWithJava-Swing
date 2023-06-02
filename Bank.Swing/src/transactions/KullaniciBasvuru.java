package transactions;

import database.DatabaseConnection;
import database.IBilgiControl;

import java.sql.SQLException;
import java.sql.Statement;

public class KullaniciBasvuru extends DatabaseConnection implements IBilgiControl {

	protected int kullaniciId = 0;
	protected String adSoyad;
	protected String kullaniciAdi;
	protected String sifre;
	
	
	public int getKullaniciId() {
		return kullaniciId;
	}
	
	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}
	
	public String getAdSoyad() {
		return adSoyad;
	}
	
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	
	public String getKullaniciAdi() {
		return kullaniciAdi;
	}
	
	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}
	
	public String getSifre() {
		return sifre;
	}
	
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	public boolean bilgilerDogrumu() {
		
		return !(this.adSoyad == null || this.kullaniciAdi == null || this.sifre == null);
		
	}
	public HesapBilgileri getHesapBilgileri() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void basvuruOnayla() {
		
		DatabaseConnection connection;
		
	    String queryString = "INSERT INTO users (name, username, password) VALUES ("
	            + "'" + this.adSoyad + "',"
	            + "'" + this.kullaniciAdi + "',"
	            + "'" + this.sifre + "'"
	            + ")";
	    try {
	    	super.statement = super.connection.createStatement();
	        statement.executeUpdate(queryString);
	        System.out.println("Gönderim başarılı");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
