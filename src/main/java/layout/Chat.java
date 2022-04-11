
package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import principal.Cliente;
import principal.Uso;
import sockets.Socket;

public class Chat {

	private Cliente cliente;
	private String chat = "";
	private String sala;
	private static JFrame miMarco;

	JPanel principal, nav, mainPanel, asidePanel, footerPanel, asideIz;
	JLabel titulo, titulo_usuarios, titulo_salas;
	JTextArea usuarios, salas;
	JTextArea area;
	private JTextArea areaEscribir;
	private JButton boton, boton2, boton3;
	private Color micolor, micolor2, micolor3, micolor4, micolor5;

	public Chat(String sala) {
		this.sala = sala;
		this.micolor = new Color(20, 20, 20);
		this.micolor2 = new Color(40, 40, 40);
		this.micolor3 = new Color(50, 50, 50);
		this.micolor4 = new Color(230, 230, 230);
		this.micolor5 = new Color(80, 80, 80);
		Font mifuente = new Font("Arial", Font.BOLD, 16);
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();

		miMarco = new JFrame();
		miMarco.setBounds(tamanoPantalla.width / 7, tamanoPantalla.height / 6, tamanoPantalla.width / 2,
				tamanoPantalla.height / 2);
		miMarco.setLayout(new BorderLayout(30, 30));
		miMarco.setMinimumSize(new Dimension(900, 600));
		miMarco.setTitle(sala);

		principal = new JPanel(new BorderLayout(10, 10));
		principal.setBackground(micolor);

		titulo_salas = new JLabel("-SALAS-", JLabel.CENTER);
		titulo_salas.setFont(mifuente);
		titulo_salas.setForeground(Color.white);
		titulo_salas.setBackground(micolor3);
		titulo_salas.setOpaque(true);
		titulo_salas.setPreferredSize(new Dimension(250,40));

		titulo = new JLabel("--BIENVENIDOS AL CHAT--            SALA: " + sala, JLabel.CENTER);
		titulo.setBackground(micolor3);
		titulo.setFont(mifuente);
		titulo.setForeground(Color.white);
		titulo.setOpaque(true);
		titulo.setPreferredSize(new Dimension(600,40));

		titulo_usuarios = new JLabel("-USUARIOS-", JLabel.CENTER);
		titulo_usuarios.setFont(mifuente);
		titulo_usuarios.setForeground(Color.white);
		titulo_usuarios.setBackground(micolor3);
		titulo_usuarios.setOpaque(true);
		titulo_usuarios.setPreferredSize(new Dimension(250,40));

		nav = new JPanel(new BorderLayout());
		nav.add(titulo_salas, BorderLayout.WEST);
		nav.add(titulo, BorderLayout.CENTER);
		nav.add(titulo_usuarios, BorderLayout.EAST);
		nav.setBorder(new MatteBorder(7, 3, 5, 3,micolor5));


		area = new JTextArea();
		area.setForeground(Color.white);
		area.setBackground(micolor3);
		area.setPreferredSize(new Dimension(miMarco.getWidth() / 1, miMarco.getHeight() ));
		area.setFont(mifuente);
		area.setText("\n");
		area.setBorder(new MatteBorder(2, 2, 2, 2, Color.white));
		area.setEditable(false);

		areaEscribir = new JTextArea();
		areaEscribir.setBackground(micolor3);
		areaEscribir.setForeground(Color.white);
		areaEscribir.setPreferredSize(new Dimension((miMarco.getWidth() / 2 + 60), miMarco.getHeight() / 10));
		areaEscribir.setFont(mifuente);
		areaEscribir.setBorder(new MatteBorder(2, 2, 2, 2, Color.white));
		areaEscribir.addKeyListener(new EventoTeclado());

		boton = new JButton("Enviar mensaje");
		boton.setPreferredSize(new Dimension(160, 70));
		boton.addActionListener(new Escuchador());
		boton.setFont(new Font("Courier", Font.BOLD, 14));

		footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		footerPanel.add(areaEscribir);
		footerPanel.add(boton);
		footerPanel.setBackground(micolor);

		JScrollPane scroll = new JScrollPane(area);
		scroll.setOpaque(true);
		mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(scroll);
		mainPanel.add(area, BorderLayout.CENTER);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);

		boton2 = new JButton("General");
		boton2.setFont(new Font("Courier", Font.BOLD, 16));
		boton2.addActionListener(new Escuchador());
		boton2.setBackground(fondoBotonSala(boton2.getText()));
		boton2.setForeground(colorBtnSala(boton2.getText()));
		boton3 = new JButton("Juegos");
		boton3.setFont(new Font("Courier", Font.BOLD, 16));
		boton3.addActionListener(new Escuchador());
		boton3.setBackground(fondoBotonSala(boton3.getText()));
		boton3.setForeground(colorBtnSala(boton3.getText()));

		asideIz = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
		asideIz.setBounds(650, 75, 300, 500);
		asideIz.add(boton2);
		asideIz.add(boton3);
		asideIz.setBackground(micolor2);
		asideIz.setBorder(new MatteBorder(1, 3, 1, 1, Color.white));
		asideIz.setPreferredSize(new Dimension(miMarco.getWidth() / 5, miMarco.getHeight() ));
		boton2.setPreferredSize(new Dimension((asideIz.getWidth() / 2) + 85, asideIz.getHeight() / 14));
		boton3.setPreferredSize(new Dimension((asideIz.getWidth() / 2) + 85, asideIz.getHeight() / 14));

		usuarios = new JTextArea();
		usuarios.setBackground(micolor2);
		usuarios.setForeground(Color.white);

		usuarios.setFont(mifuente);
		usuarios.setBorder(new MatteBorder(1, 1, 1, 3, Color.white));
		usuarios.setEditable(false);

		asidePanel = new JPanel(new BorderLayout(10, 10));
		asidePanel.add(usuarios, BorderLayout.CENTER);
		asidePanel.setBackground(micolor);
		asidePanel.setPreferredSize(new Dimension(miMarco.getWidth() / 5, miMarco.getHeight() ));

		principal.add(nav, BorderLayout.NORTH);
		principal.add(mainPanel, BorderLayout.CENTER);
		principal.add(asidePanel, BorderLayout.EAST);
		principal.add(asideIz, BorderLayout.WEST);
		principal.add(footerPanel, BorderLayout.SOUTH);
		miMarco.add(principal);
		miMarco.pack();
		miMarco.setDefaultCloseOperation(miMarco.EXIT_ON_CLOSE);
		miMarco.setVisible(true);

	}

	private Color fondoBotonSala(String btn) {
		if (sala.equals(btn)) {
			return getMicolor5();
		} else
			return getMicolor4();
	}
	private Color colorBtnSala(String btn) {
		if (sala.equals(btn)) {
			return Color.white;
		} else
			return Color.black;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public JTextArea getUsuarios() {
		return usuarios;
	}

	public JTextArea getArea() {
		return area;
	}

	public Color getMicolor4() {
		return micolor4;
	}

	public Color getMicolor5() {
		return micolor5;
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

	public JFrame getMiMarco() {
		return miMarco;
	}

	public void setMiMarco(JFrame miMarco) {
		Chat.miMarco = miMarco;
	}

	///////////////////////////////////////////////////////// CLASES DE EVENTOS
	///////////////////////////////////////////////////////// /////////////////////////////////////////////////////
	class Escuchador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Enviar mensaje") {
				Socket.enviar();
			} else if (e.getActionCommand() == "Juegos") {
				Socket.cierraSocket();
				Uso.getMichat().getMiMarco().dispose();
				Uso.iniciaChat("Juegos");
				Chat.miMarco.setTitle(e.getActionCommand());
			} else if (e.getActionCommand() == "General") {
				Socket.cierraSocket();
				Uso.getMichat().getMiMarco().dispose();
				Uso.iniciaChat("General");
				Chat.miMarco.setTitle("General");
			}

		}
	}

	class EventoTeclado implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 10)
				Socket.enviar();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}

}
