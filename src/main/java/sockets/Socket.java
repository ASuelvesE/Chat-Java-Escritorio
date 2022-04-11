
package sockets;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

import principal.Uso;

public class Socket {

	private static io.socket.client.Socket mSocket;

	private static String conectado = "usuario nuevo";
	private static String actualizaUsuarios = "actualiza usuarios";
	private static String eventoChat = "evento chat";

	public Socket() {
		try {
			mSocket = IO.socket("https://chatescritoriojava.herokuapp.com/");
			// mSocket = IO.socket("http://127.0.0.1:3000/");
			mSocket.connect(); // Conectamos y permanecemos a la escucha
			mSocket.emit(conectado, Uso.getCliente().getNombre(), Uso.getMichat().getSala());

			System.out.println("Usuario conectado al servidor");
		} catch (URISyntaxException e) {
			System.out.println("No se ha podido conectar con el servidor");
		}

		///////////////////////////////////////////////////// RECIBIMOS DEL SERVIDOR
		///////////////////////////////////////////////////// ////////////////////////////////////////////////////

		mSocket.on(actualizaUsuarios, new Emitter.Listener() { // Esperamos el nombre del nuevo usuario
																// conectado
			public void call(Object... args) {
				Uso.getMichat().getUsuarios().setText("");
				for (int i = 0; i < args.length; i++) {
					Uso.getMichat().getUsuarios().setText((String) args[i]);
				}
			}
		});

		mSocket.on(eventoChat, new Emitter.Listener() { // Esperamos el mensaje de chat desde el servidor
			public void call(Object... args) {
				String sala = (String) args[2];
				if (sala.equals(Uso.getMichat().getSala()))
					Uso.getMichat().getArea().append("   -" + args[1] + " : " + (String) args[0] + "\n");
			}
		});

	}
/////////////////////////////////////////////// PETICIONES AL SERVIDOR
/////////////////////////////////////////////// ////////////////////////////////////////////

	public static void enviar() { // evento,chat,usuario,sala
		mSocket.emit(eventoChat, Uso.getMichat().getAreaEscribir().getText(), Uso.getCliente().getNombre(),
				Uso.getMichat().getSala());
		Uso.getMichat().getAreaEscribir().setText(null);
	}

	public static void cierraSocket() {
		mSocket.close();
	}

}
