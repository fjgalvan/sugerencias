package mvc;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mvc_modelo_observable.Modelo;

import java.awt.Color;
import java.util.Observer;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Vista2 extends JFrame implements Observer {
	private JPanel panel;
	private JLabel lblRecomendaciones;
	private JTextArea textArea_Recomendaciones;
	private JCheckBox chckbx_filtrosChatarras;
	private JCheckBox chckbx_filtrosPostres;
	private JCheckBox chckbx_filtrosSanas;
	private JCheckBox chckbx_filtrosPastas;
	private JLabel lblElijaFiltros;
	private JLabel lbl_ElijaUsuario;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_eleccionDeUsuario;
	private JLabel label_lineaHorizontal;
	private JButton btnFiltrarPreferencia;
	private JButton btn_refrescar;
	private JTextArea textArea_ValidezUsuario;
	private JTextArea textArea_validezEmail;
	private JTextField textField_usuario;
	private JTextField textField_Email;
	private JButton btnRegistrarse;
	private JTextArea textArea_registroNuevoUsuario;
	private JLabel lbl_registrarUsuario;
	private JLabel lbl_registrarEmail;
	private JLabel lbl_ValidezUsuario;
	private JLabel lbl_ValidezEmail;
	private JLabel lblElijaIdioma;
	private JComboBox comboBox_Idioma;
	private JButton btnCambiarIdioma;
	private JButton btn_GuardarPreferencia;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vista2() {

		lblRecomendaciones = new JLabel("Recomendaciones:");
		lblRecomendaciones.setBounds(10, 297, 116, 23);
		textArea_Recomendaciones = new JTextArea();
		textArea_Recomendaciones.setBounds(126, 296, 878, 176);
		textArea_Recomendaciones.setLineWrap(true);
		textArea_Recomendaciones.setWrapStyleWord(true);

		// Creamos el JPanel sobre el que estara el flowlayout y los componentes
		// de la vista.
		panel = new JPanel();
		panel.setForeground(Color.BLACK);

		// Añadimos titulo a nuestra ventana
		this.setTitle("Sugerencias de Promos de Comidas");
		// Indicamos a la ventana que se pueda cerrar. (La acción de cerrar)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Le damos un tamaño por defecto a la ventana.
		this.setSize(1030, 606);
		// Indicamos su tamaño mínimo, y máximo, ya que no vamos a "bloquearla"
		// y permitiremos que sea redimensionable.
		this.setMinimumSize(new Dimension(100, 100));
		this.setMaximumSize(new Dimension(2000, 2000));

		panel.setLayout(null);

		panel.add(lblRecomendaciones);
		panel.add(textArea_Recomendaciones);

		// Añadimos el panel a nuestra ventana.
		getContentPane().add(panel);

		JLabel lbl_imagen = new JLabel(" ");
		lbl_imagen.setBounds(10, 413, 103, 59);

		ImageIcon imagen = new ImageIcon("src\\main\\resources\\McDonalds.png");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(
				lbl_imagen.getWidth(), lbl_imagen.getHeight(),
				Image.SCALE_DEFAULT));
		lbl_imagen.setIcon(icono);
		this.repaint();

		panel.add(lbl_imagen);

		chckbx_filtrosChatarras = new JCheckBox("1_ chatarras");
		chckbx_filtrosChatarras.setForeground(new Color(0, 0, 255));
		chckbx_filtrosChatarras.setBounds(220, 263, 116, 23);
		panel.add(chckbx_filtrosChatarras);

		chckbx_filtrosPostres = new JCheckBox("2_ postres");
		chckbx_filtrosPostres.setForeground(new Color(0, 0, 255));
		chckbx_filtrosPostres.setBounds(383, 263, 103, 23);
		panel.add(chckbx_filtrosPostres);

		chckbx_filtrosSanas = new JCheckBox("3_ sanas");
		chckbx_filtrosSanas.setForeground(new Color(0, 0, 255));
		chckbx_filtrosSanas.setBounds(527, 263, 97, 23);
		panel.add(chckbx_filtrosSanas);

		chckbx_filtrosPastas = new JCheckBox("4_ pastas");
		chckbx_filtrosPastas.setForeground(new Color(0, 0, 255));
		chckbx_filtrosPastas.setBounds(683, 263, 97, 23);
		panel.add(chckbx_filtrosPastas);

		lblElijaFiltros = new JLabel("Elija Filtros de Preferencias:");
		lblElijaFiltros.setBounds(10, 272, 186, 14);
		panel.add(lblElijaFiltros);
		
		lbl_registrarUsuario = new JLabel("Usuario:");
		lbl_registrarUsuario.setBounds(10, 11, 78, 14);
		panel.add(lbl_registrarUsuario);
		
		lbl_registrarEmail = new JLabel("Email:");
		lbl_registrarEmail.setBounds(10, 36, 62, 14);
		panel.add(lbl_registrarEmail);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setForeground(new Color(0, 128, 0));
		btnRegistrarse.setBounds(123, 67, 116, 23);
		panel.add(btnRegistrarse);
		
		textArea_ValidezUsuario = new JTextArea();
		textArea_ValidezUsuario.setForeground(new Color(0, 128, 0));
		textArea_ValidezUsuario.setBounds(725, 6, 126, 22);
		panel.add(textArea_ValidezUsuario);
		
		textArea_validezEmail = new JTextArea();
		textArea_validezEmail.setForeground(new Color(0, 128, 0));
		textArea_validezEmail.setBounds(725, 31, 126, 22);
		panel.add(textArea_validezEmail);
		
		lbl_ValidezUsuario = new JLabel("Formato Usuario:");
		lbl_ValidezUsuario.setBounds(516, 11, 199, 14);
		panel.add(lbl_ValidezUsuario);
		
		lbl_ValidezEmail = new JLabel("Formato Email: ");
		lbl_ValidezEmail.setBounds(516, 31, 199, 14);
		panel.add(lbl_ValidezEmail);
		
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(505, 5, -33, 103);
		panel.add(verticalStrut);
		
		lbl_ElijaUsuario = new JLabel("Elija Usuario con Preferencias: ");
		lbl_ElijaUsuario.setBounds(10, 212, 186, 14);
		panel.add(lbl_ElijaUsuario);
		
		comboBox_eleccionDeUsuario = new JComboBox();
		comboBox_eleccionDeUsuario.setModel(new DefaultComboBoxModel(new String[] {"Usuario A", "Usuario B"}));
		comboBox_eleccionDeUsuario.setBounds(245, 206, 241, 20);
		panel.add(comboBox_eleccionDeUsuario);
		
		label_lineaHorizontal = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_lineaHorizontal.setBounds(10, 99, 991, 14);
		panel.add(label_lineaHorizontal);
		
		btnFiltrarPreferencia = new JButton("Filtrar Preferencia!");
		
		btnFiltrarPreferencia.setForeground(new Color(0, 0, 255));
		btnFiltrarPreferencia.setBounds(516, 203, 156, 23);
		panel.add(btnFiltrarPreferencia);
		
		btn_refrescar = new JButton("Refrescar Filtros");
		btn_refrescar.setForeground(Color.BLUE);
		btn_refrescar.setBounds(707, 203, 156, 23);
		panel.add(btn_refrescar);
		
		textField_usuario = new JTextField();
		textField_usuario.setBounds(123, 8, 344, 20);
		panel.add(textField_usuario);
		textField_usuario.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(123, 33, 344, 20);
		panel.add(textField_Email);
		textField_Email.setColumns(10);
		
		textArea_registroNuevoUsuario = new JTextArea();
		textArea_registroNuevoUsuario.setBounds(266, 66, 199, 22);
		panel.add(textArea_registroNuevoUsuario);
		
		JLabel label_lineaHorizontal2 = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_lineaHorizontal2.setBounds(10, 168, 991, 14);
		panel.add(label_lineaHorizontal2);
		
		lblElijaIdioma = new JLabel("Elija idioma:");
		lblElijaIdioma.setBounds(10, 124, 103, 14);
		panel.add(lblElijaIdioma);
		
		comboBox_Idioma = new JComboBox();
		comboBox_Idioma.setModel(new DefaultComboBoxModel(new String[] {"Espanol", "Ingles"}));
		comboBox_Idioma.setBounds(126, 121, 175, 20);
		panel.add(comboBox_Idioma);
		
		btnCambiarIdioma = new JButton("Cambiar Idioma");
		btnCambiarIdioma.setForeground(new Color(153, 51, 0));
		btnCambiarIdioma.setBounds(319, 120, 148, 23);
		panel.add(btnCambiarIdioma);
		
		btn_GuardarPreferencia = new JButton("Guardar Preferencia");
		btn_GuardarPreferencia.setForeground(new Color(0, 0, 255));
		btn_GuardarPreferencia.setBounds(791, 485, 213, 23);
		panel.add(btn_GuardarPreferencia);

		// Hacemos visible nuestra ventana.
		this.setVisible(true);

	}

	public void update(Observable obs, Object obj) {
		System.out.println("update");

		textArea_Recomendaciones.setText(String.valueOf(((Modelo) obs)
				.getValorString()));

	}

	public void addFiltroListeners(ActionListener listen) {
		chckbx_filtrosChatarras.addActionListener(listen);
		chckbx_filtrosPostres.addActionListener(listen);
		chckbx_filtrosSanas.addActionListener(listen);
		chckbx_filtrosPastas.addActionListener(listen);
		btnFiltrarPreferencia.addActionListener(listen);
	}
	
	public void addRegistroListener(ActionListener listen) {
		textField_usuario.addActionListener(listen);
		textField_Email.addActionListener(listen);
		
	}
	
	public void addRegistroNuevoUsuarioListener(ActionListener listen) {
		btnRegistrarse.addActionListener(listen);
	}
	
	public void addCambiarIdiomaListener(ActionListener listen) {
		btnCambiarIdioma.addActionListener(listen);
	}
	
	public void addGuardarPreferenciasListener(ActionListener listen) {
		btn_GuardarPreferencia.addActionListener(listen);
	}
	
	public JButton getBtnCambiarIdioma() {
		return btnCambiarIdioma;
	}

	public JCheckBox getChckbx_filtrosChatarras() {
		return chckbx_filtrosChatarras;
	}

	public void setChckbx_filtrosChatarras(JCheckBox chckbx_filtrosChatarras) {
		this.chckbx_filtrosChatarras = chckbx_filtrosChatarras;
	}

	public JCheckBox getChckbx_filtrosPostres() {
		return chckbx_filtrosPostres;
	}

	public JTextArea getTextArea_Recomendaciones() {
		return textArea_Recomendaciones;
	}

	public JCheckBox getChckbx_filtrosSanas() {
		return chckbx_filtrosSanas;
	}

	public JCheckBox getChckbx_filtrosPastas() {
		return chckbx_filtrosPastas;
	}

	public void setTextArea_Recomendaciones(JTextArea textArea_Recomendaciones) {
		this.textArea_Recomendaciones = textArea_Recomendaciones;
	}

	public JButton getBtnFiltrarPreferencia() {
		return btnFiltrarPreferencia;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox_eleccionDeUsuario() {
		return comboBox_eleccionDeUsuario;
	}

	public JButton getBtn_refrescar() {
		return btn_refrescar;
	}


	public JTextArea getTextArea_ValidezUsuario() {
		return textArea_ValidezUsuario;
	}

	public void setTextArea_ValidezUsuario(JTextArea textArea_ValidezUsuario) {
		this.textArea_ValidezUsuario = textArea_ValidezUsuario;
	}

	public JTextArea getTextArea_validezEmail() {
		return textArea_validezEmail;
	}

	public void setTextArea_validezEmail(JTextArea textArea_validezEmail) {
		this.textArea_validezEmail = textArea_validezEmail;
	}

	public JTextField getTextField_usuario() {
		return textField_usuario;
	}
	
	public JTextField getTextField_Email() {
		return textField_Email;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

	public void setTextArea_registroNuevoUsuario(
			JTextArea textArea_registroNuevoUsuario) {
		this.textArea_registroNuevoUsuario = textArea_registroNuevoUsuario;
	}

	public JTextArea getTextArea_registroNuevoUsuario() {
		return textArea_registroNuevoUsuario;
	}

	public JLabel getLblRecomendaciones() {
		return lblRecomendaciones;
	}

	public JLabel getLblElijaFiltros() {
		return lblElijaFiltros;
	}

	public JLabel getLbl_ElijaUsuario() {
		return lbl_ElijaUsuario;
	}

	public JLabel getLbl_registrarUsuario() {
		return lbl_registrarUsuario;
	}

	public JLabel getLbl_registrarEmail() {
		return lbl_registrarEmail;
	}

	public JLabel getLbl_ValidezUsuario() {
		return lbl_ValidezUsuario;
	}

	public JLabel getLbl_ValidezEmail() {
		return lbl_ValidezEmail;
	}

	public JLabel getLblElijaIdioma() {
		return lblElijaIdioma;
	}

	public JComboBox getComboBox_Idioma() {
		return comboBox_Idioma;
	}

	public JButton getBtn_GuardarPreferencia() {
		return btn_GuardarPreferencia;
	}
	
	
}
