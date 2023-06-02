package transactions;

public class KullaniciAdiAl {

	public String kullaniciAdi;
	
	public void kullaniciAdiAyarla(String username) {

		kullaniciAdi = username;
	}
	
	public String kullaniciAdiDondur() {
		return kullaniciAdi;
	}
	
}
