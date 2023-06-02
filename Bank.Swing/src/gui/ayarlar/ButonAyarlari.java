package gui.ayarlar;

import java.awt.Color;

import javax.swing.JButton;

public class ButonAyarlari {

	private static Color originalBgColor;
	
	public static void setBackGround(JButton button, Color backGroundColor) {
		originalBgColor = button.getBackground();
		button.setBackground(backGroundColor);
	}
	
	public static void setOrginalBackground(JButton button) {
		button.setBackground(originalBgColor);
	}
}
