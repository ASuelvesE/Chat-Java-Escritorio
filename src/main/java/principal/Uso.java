package principal;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import io.socket.client.IO;
import io.socket.emitter.Emitter;


public class Uso {

	private static io.socket.client.Socket mSocket;

	private static String conectado = "usuario nuevo";
	private static String actualizaUsuarios = "actualiza usuarios";
	private static String eventoChat = "evento chat";

	private static Chat michat;
	private static Cliente cliente;
	private static String chat = "";
	
	public static void main(String[] args) {	
		michat = new Chat();
		cliente = new Cliente(JOptionPane.showInputDialog("Escribe tu nick"));

		try {
			mSocket = IO.socket("https://chatescritoriojava.herokuapp.com/");
//			mSocket = IO.socket("http://127.0.0.1:3000/");
			mSocket.connect(); // Conectamos y permanecemos a la escucha
			mSocket.emit(conectado, cliente.getNombre());
			System.out.println("Usuario conectado al servidor");
		} catch (URISyntaxException e) {
			System.out.println("No se ha podido conectar con el servidor");
		}


		///////////////////////////////////////////////////// RECIBIMOS DEL SERVIDOR
		///////////////////////////////////////////////////// ////////////////////////////////////////////////////
		
		mSocket.on(actualizaUsuarios, new Emitter.Listener() { // Esperamos el nombre del nuevo usuario conectado
			public void call(Object... args) {
				michat.getUsuarios().setText("\n");
				for (int i = 0; i < args.length; i++) {
					michat.getUsuarios().setText("\t" + (String)args[i]);
				}
			}
		});
		
		mSocket.on(eventoChat, new Emitter.Listener() { // Esperamos el mensaje de chat desde el servidor
			public void call(Object... args) {
				chat += "   -"+args[1] + " : " + (String) args[0] + "\n";
				michat.getArea().setText(chat);
			}
		});
	}
	
/////////////////////////////////////////////// PETICIONES AL SERVIDOR
/////////////////////////////////////////////// ////////////////////////////////////////////

	public static void enviar() {
		mSocket.emit(eventoChat, michat.areaEscribir.getText(), cliente.getNombre());
		michat.areaEscribir.setText(null);
	}
}
