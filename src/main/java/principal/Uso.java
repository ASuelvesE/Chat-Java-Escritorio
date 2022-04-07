package principal;

import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import io.socket.client.IO;
import io.socket.emitter.Emitter;


public class Uso {

	private static io.socket.client.Socket mSocket;

	private static String conectado = "usuario nuevo";
	private static String eventoChat = "evento chat";
	private static String mensaje;
	private String evento = "chat mensaje";
	private String desconectado = "usuario desconectado";
	private String usuariosConectados = "usuarios conectados";
	
	private static Chat michat;
	private static Cliente cliente;
	static String chat = "";
	
	public static void main(String[] args) {
		michat = new Chat();
		cliente = new Cliente(JOptionPane.showInputDialog("Escribe tu nick"));


		
		
		try {
			mSocket = IO.socket("https://servidorclaseperro.herokuapp.com/");
//			mSocket = IO.socket("http://127.0.0.1:3000/");
			mSocket.connect(); // Conectamos y permanecemos a la escucha
			mSocket.emit(conectado, cliente.getNombre());
			System.out.println("Usuario conectado al servidor");
		} catch (URISyntaxException e) {
			System.out.println("No se ha podido conectar con el servidor");
		}


		///////////////////////////////////////////////////// RECIBIMOS DEL SERVIDOR
		///////////////////////////////////////////////////// ////////////////////////////////////////////////////

		mSocket.on(conectado, new Emitter.Listener() { // Esperamos el nombre del nuevo usuario conectado
			public void call(Object... args) {
				String nombres = "";
				for (int i = 0; i < args.length; i++) {
					nombres += "\n-" + (String) args[i] + "\n";
					michat.getUsuarios().setText(nombres);
				}
				System.out.println(nombres);
			}
		});

		mSocket.on(eventoChat, new Emitter.Listener() { // Esperamos el mensaje de chat desde el servidor
			public void call(Object... args) {
				chat += args[1] + " : " + (String) args[0] + "\n";
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
