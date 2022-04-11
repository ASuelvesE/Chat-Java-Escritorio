
package principal;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import layout.Chat;
import sockets.Socket;

public class Uso {

	private static String eventoChat = "evento chat";
	private static Chat michat;
	private static Cliente cliente;
	private static Socket socket;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String nombre = JOptionPane.showInputDialog("Escribe tu nick");
				if (nombre == null || nombre.equals(""))
					cliente = new Cliente("Anonimo");
				else
					cliente = new Cliente(nombre);

				iniciaChat("General");
			}
		});
	}

	public static void iniciaChat(String sala) {
		michat = new Chat(sala);
		socket = new Socket();
	}
	///////////////////////////////// GETTERS AND SETTERS
	///////////////////////////////// ////////////////////////////////

	public static String getEventoChat() {
		return eventoChat;
	}

	public static Chat getMichat() {
		return michat;
	}

	public static void setCliente(Cliente cliente) {
		Uso.cliente = cliente;
	}

	public static Cliente getCliente() {
		return cliente;
	}

}
