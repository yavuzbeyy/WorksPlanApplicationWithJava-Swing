package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.KeyStore.Entry.Attribute;
import java.security.PublicKey;

import javax.print.attribute.AttributeSet;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Controller {

	//Sadece Harf Girdisi Kontrolü
	public static void sadeceHarfKontrol(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isAlphabetic(c))
					e.consume();
			}
			
		});
	}
	
	public static void sadeceRakamKontrol(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c))
					e.consume();
			}
			
		});
	}
	
	//Karakter Sınırı Kontrolü
	public static int limit;
	public static void setMaxLimit(JTextField textField, int lim) {
        limit = lim;
        textField.setDocument(new PlainDocument() {
            public void insertString(int offs, String string, javax.swing.text.AttributeSet a) throws BadLocationException {
                if (string == null)
                    return;
                if ((getLength() + string.length()) <= limit)
                    super.insertString(offs, string, a);
            }
        });
	}
	
	//Text Alanları Kontrolü
	
	public static boolean textAlanlariDolumu(Container container) {
	    Component[] components = container.getComponents();
	    
	    for (Component component : components) {
	        if (component instanceof JTextField) {
	            JTextField textField = (JTextField) component;
	            if (textField.getText().trim().isEmpty() && textField.isEnabled()) {
	                return false;
	            }
	        } else if (component instanceof Container) {
	            if (!textAlanlariDolumu((Container) component)) {
	                return false;
	            }
	        }
	    }
	    
	    return true;
	}
	
	}
	

