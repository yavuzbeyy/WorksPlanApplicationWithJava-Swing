package database;

import java.util.ArrayList;

public class dbCenter extends DatabaseConnection{


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
}
