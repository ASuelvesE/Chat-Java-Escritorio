package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Chat {

	private Cliente cliente;
	private String chat = "";
	JFrame miMarco;
	JPanel principal,nav, mainPanel, asidePanel, footerPanel, asideIz;
	JLabel titulo, titulo_usuarios, titulo_salas;
	JTextArea usuarios, salas;
	JTextArea area;
	JTextArea areaEscribir;
	JButton boton;
	public Chat() {
		Color micolor = new Color(75, 34, 109);
		Font mifuente = new Font("Courier", Font.BOLD, 16);
		Border border = BorderFactory.createLineBorder(Color.BLACK);	
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		
		miMarco = new JFrame();
		miMarco.setBounds(400, 300, tamanoPantalla.width / 2, tamanoPantalla.height / 2);
		miMarco.setLayout(new BorderLayout(10, 10));

		principal = new JPanel(new BorderLayout(10, 10));
		principal.setBackground(micolor);
		
		titulo_salas = new JLabel("-SALAS:", JLabel.CENTER);
		titulo_salas.setFont(mifuente);
		titulo_salas.setForeground(Color.white);
		titulo_salas.setBackground(micolor);
		titulo_salas.setOpaque(true);
		
		titulo = new JLabel("BIENVENIDOS AL CHAT:", JLabel.CENTER);
		titulo.setFont(mifuente);
		titulo.setForeground(Color.white);
		titulo.setBackground(micolor);
		titulo.setOpaque(true);
		
		titulo_usuarios = new JLabel("-Usuarios conectados:", JLabel.CENTER);
		titulo_usuarios.setFont(mifuente);
		titulo_usuarios.setForeground(Color.white);
		titulo_usuarios.setBackground(micolor);
		titulo_usuarios.setOpaque(true);

		nav = new JPanel(new BorderLayout(0, 0));
		nav.add(titulo_salas, BorderLayout.WEST);
		nav.add(titulo, BorderLayout.CENTER);
		nav.add(titulo_usuarios, BorderLayout.EAST);
		


		area = new JTextArea(30, 75);
		area.setFont(mifuente);
		area.setText("");
		area.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		areaEscribir = new JTextArea();
		areaEscribir.setPreferredSize(new Dimension(miMarco.getWidth() / 2,miMarco.getHeight() / 10));
		areaEscribir.setFont(mifuente);
		areaEscribir.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		boton = new JButton("Enviar mensaje");
		boton.setPreferredSize(new Dimension(130, 70));
		boton.addActionListener(new Escuchador());

		footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		footerPanel.add(areaEscribir);
		footerPanel.add(boton);
		footerPanel.setBackground(micolor);

		JScrollPane scroll = new JScrollPane(area);
		mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(scroll);
		mainPanel.add(area, BorderLayout.CENTER);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);

		salas = new JTextArea(5, 15);
		salas.setPreferredSize(new Dimension(miMarco.getWidth() / 5,miMarco.getHeight()));
		salas.setFont(new Font("Courier", Font.BOLD, 14));
		salas.setBounds(650, 75, 300, 500);
		salas.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		asideIz = new JPanel(new BorderLayout(10, 10));
		asideIz.add(salas);
		asideIz.setBackground(micolor);

		usuarios = new JTextArea();
		usuarios.setPreferredSize(new Dimension(miMarco.getWidth() / 5,miMarco.getHeight()));
		usuarios.setFont(mifuente);
		usuarios.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));


		asidePanel = new JPanel(new BorderLayout(10, 10));
		asidePanel.add(usuarios, BorderLayout.CENTER);
		asidePanel.setBackground(micolor);
		
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
