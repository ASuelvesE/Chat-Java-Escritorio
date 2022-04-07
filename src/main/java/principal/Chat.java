package principal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Chat {

	private Cliente cliente;
	private String chat = "";
	JFrame miMarco;
	JPanel mainPanel,asidePanel,footerPanel;
	JLabel titulo,titulo_usuarios;
	JTextArea usuarios;
	JTextArea area;
	JTextArea areaEscribir;
	JButton boton;



	public Chat() {
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		
		miMarco = new JFrame();
		miMarco.getContentPane().setBackground(new Color(141,214,255));
		miMarco.setBounds(400,300, tamanoPantalla.width/2, tamanoPantalla.height/2);
		miMarco.setLayout(new BorderLayout(10,10));
		
		area = new JTextArea(20,75);
		area.setFont(new Font("Courier",Font.BOLD,16));
		area.setText("");

		
		areaEscribir = new JTextArea("",5,95);
		areaEscribir.setFont(new Font("Courier",Font.BOLD,14));
		
		boton = new JButton("Enviar mensaje");
		boton.setPreferredSize(new Dimension(130,70));
		boton.addActionListener(new Escuchador());
		
		titulo_usuarios = new JLabel("-Usuarios conectados: \n\n");

		usuarios = new JTextArea();
		usuarios.setFont(new Font("Courier",Font.BOLD,14));
		usuarios.setBounds(650, 75, 300, 500);
		
		asidePanel = new JPanel(new BorderLayout(10,10));
		asidePanel.add(titulo_usuarios,BorderLayout.NORTH);
		asidePanel.add(usuarios,BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(area);
		
		mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
		mainPanel.add(scroll);
		mainPanel.add(asidePanel);
		
		footerPanel = new JPanel();
		footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		footerPanel.add(areaEscribir);
		footerPanel.add(boton);

		miMarco.add(new JLabel("BIENVENIDOS AL CHAT:"),BorderLayout.NORTH);
		miMarco.add(mainPanel,BorderLayout.WEST);
		miMarco.add(asidePanel,BorderLayout.EAST); 
		miMarco.add(footerPanel,BorderLayout.SOUTH);
		
		miMarco.setDefaultCloseOperation(miMarco.EXIT_ON_CLOSE);
		miMarco.setVisible(true);
	}

	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public String getChat() {
		return chat;
	}



	public void setChat(String chat) {
		this.chat = chat;
	}



	public JLabel getTitulo() {
		return titulo;
	}



	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}



	public JTextArea getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(JTextArea usuarios) {
		this.usuarios = usuarios;
	}



	public JTextArea getArea() {
		return area;
	}



	public void setArea(JTextArea area) {
		this.area = area;
	}



	public JTextArea getAreaEscribir() {
		return areaEscribir;
	}



	public void setAreaEscribir(JTextArea areaEscribir) {
		this.areaEscribir = areaEscribir;
	}



	public JButton getBoton() {
		return boton;
	}



	public void setBoton(JButton boton) {
		this.boton = boton;
	}



	public JFrame getVentana() {
		return miMarco;
	}



	public void setVentana(JFrame ventana) {
		this.miMarco = ventana;
	}



	class Escuchador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Uso.enviar();
		}
	}

}

