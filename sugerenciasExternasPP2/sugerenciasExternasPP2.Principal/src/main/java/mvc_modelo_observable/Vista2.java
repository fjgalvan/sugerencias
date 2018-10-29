package mvc_modelo_observable;


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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vista2() {

		lblRecomendaciones = new JLabel("Recomendaciones:");
		lblRecomendaciones.setBounds(10, 258, 116, 23);
		textArea_Recomendaciones = new JTextArea();
		textArea_Recomendaciones.setBounds(126, 258, 878, 214);
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
		this.setSize(1030, 550);
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
		chckbx_filtrosChatarras.setBounds(219, 203, 116, 23);
		panel.add(chckbx_filtrosChatarras);

		chckbx_filtrosPostres = new JCheckBox("2_ postres");
		chckbx_filtrosPostres.setForeground(new Color(0, 0, 255));
		chckbx_filtrosPostres.setBounds(380, 203, 103, 23);
		panel.add(chckbx_filtrosPostres);

		chckbx_filtrosSanas = new JCheckBox("3_ sanas");
		chckbx_filtrosSanas.setForeground(new Color(0, 0, 255));
		chckbx_filtrosSanas.setBounds(528, 203, 97, 23);
		panel.add(chckbx_filtrosSanas);

		chckbx_filtrosPastas = new JCheckBox("4_ pastas");
		chckbx_filtrosPastas.setForeground(new Color(0, 0, 255));
		chckbx_filtrosPastas.setBounds(686, 203, 97, 23);
		panel.add(chckbx_filtrosPastas);

		lblElijaFiltros = new JLabel("Elija Filtros de Preferencias:");
		lblElijaFiltros.setBounds(10, 207, 186, 14);
		panel.add(lblElijaFiltros);
		
		JLabel lbl_registrarUsuario = new JLabel("Usuario:");
		lbl_registrarUsuario.setBounds(10, 11, 78, 14);
		panel.add(lbl_registrarUsuario);
		
		JLabel lbl_registrarEmail = new JLabel("Email:");
		lbl_registrarEmail.setBounds(10, 36, 62, 14);
		panel.add(lbl_registrarEmail);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setForeground(new Color(0, 128, 0));
		btnRegistrarse.setBounds(123, 67, 116, 23);
		panel.add(btnRegistrarse);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(123, 6, 344, 22);
		panel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(123, 31, 344, 22);
		panel.add(textArea_1);
		
		JTextArea textArea_ValidezUsuario = new JTextArea();
		textArea_ValidezUsuario.setBounds(696, 6, 126, 22);
		panel.add(textArea_ValidezUsuario);
		
		JLabel lbl_ValidezUsuario = new JLabel("Validez Nombre de Usuario:");
		lbl_ValidezUsuario.setBounds(516, 11, 175, 14);
		panel.add(lbl_ValidezUsuario);
		
		JLabel lbl_ValidezEmail = new JLabel("Validez Email de Usuario: ");
		lbl_ValidezEmail.setBounds(516, 31, 175, 14);
		panel.add(lbl_ValidezEmail);
		
		JTextArea textArea_validezEmail = new JTextArea();
		textArea_validezEmail.setBounds(696, 31, 126, 22);
		panel.add(textArea_validezEmail);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(505, 5, -33, 103);
		panel.add(verticalStrut);
		
		lbl_ElijaUsuario = new JLabel("Elija Usuario con Preferencias: ");
		lbl_ElijaUsuario.setBounds(10, 131, 186, 14);
		panel.add(lbl_ElijaUsuario);
		
		comboBox_eleccionDeUsuario = new JComboBox();
		comboBox_eleccionDeUsuario.setModel(new DefaultComboBoxModel(new String[] {"Usuario A", "Usuario B"}));
		comboBox_eleccionDeUsuario.setBounds(236, 128, 241, 20);
		panel.add(comboBox_eleccionDeUsuario);
		
		label_lineaHorizontal = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_lineaHorizontal.setBounds(10, 99, 991, 14);
		panel.add(label_lineaHorizontal);
		
		btnFiltrarPreferencia = new JButton("Filtrar Preferencia!");
		
		btnFiltrarPreferencia.setForeground(new Color(0, 0, 255));
		btnFiltrarPreferencia.setBounds(517, 127, 156, 23);
		panel.add(btnFiltrarPreferencia);

		// Hacemos visible nuestra ventana.
		this.setVisible(true);

	}

	public void update(Observable obs, Object obj) {
		System.out.println("update");

		textArea_Recomendaciones.setText(String.valueOf(((Modelo) obs)
				.getValorString()));

	}

	public void addFiltroListeners(ActionListener listenForCalcButton) {
		chckbx_filtrosChatarras.addActionListener(listenForCalcButton);
		btnFiltrarPreferencia.addActionListener(listenForCalcButton);
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
	
}
