package database;

import transactions.HesapBilgileri;

public interface IBilgiControl {

	
	public boolean bilgilerDogrumu();
	
	public HesapBilgileri getHesapBilgileri();
}
