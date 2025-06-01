package util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Alerta {

	public static void erro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void erro(Component telaMae, String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void erro(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void erro(Component telaMae, Exception e) {
		JOptionPane.showMessageDialog(telaMae, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void aviso(Component telaMae, String msg) {
        JOptionPane.showMessageDialog(telaMae, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
	
	public static void info(Component telaMae, String msg) {
        JOptionPane.showMessageDialog(telaMae, msg, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static void sucesso(Component telaMae, String msg) {
        JOptionPane.showMessageDialog(telaMae, msg, "Sucesso", JOptionPane.PLAIN_MESSAGE);
    }

	public static Boolean confirmar(Component telaMae, String msg){
		return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(telaMae, msg, "Confirmar", JOptionPane.YES_NO_OPTION);
	}
	
}
