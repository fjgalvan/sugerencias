package mvc_modelo_observable;
//
//import java.awt.Dimension;
//import java.awt.Image;
//import java.awt.event.ActionListener;
//import javax.swing.Icon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//
//import mvc_modelo_observable.Modelo;
//
//import java.awt.Color;
//import java.util.Observer;
//import java.util.Observable;
//
//import javax.swing.ImageIcon;
//import javax.swing.JCheckBox;
//
//@SuppressWarnings("serial")
//public class Vista extends JFrame implements Observer {
//	private JButton botonFiltroA, botonFiltroB;
//	private JPanel panel;
//	private JLabel lblRecomendaciones;
//	private JLabel lblPreferencias;
//	private JLabel lblUsuario;
//	private JTextArea textArea_Usuario;
//	private JTextArea textArea_Preferencias;
//	private JTextArea textArea_Recomendaciones;
//	private JCheckBox chckbx_filtrosChatarras;
//	private JCheckBox chckbx_filtrosPostres;
//	private JCheckBox chckbx_filtrosSanas;
//	private JCheckBox chckbx_filtrosPastas;
//	private JLabel lblElijaFiltros;
//
//	public Vista() {
//
//		botonFiltroB = new JButton();
//		botonFiltroB.setForeground(Color.BLUE);
//		botonFiltroB.setBounds(367, 298, 116, 23);
//		botonFiltroB.setText("filtrar B");
//
//		// Creamos el boton suma
//		botonFiltroA = new JButton();
//		botonFiltroA.setForeground(Color.RED);
//		botonFiltroA.setBounds(214, 298, 116, 23);
//		botonFiltroA.setText("filtrar A");
//
//		lblPreferencias = new JLabel("Preferencias:");
//		lblPreferencias.setBounds(10, 64, 103, 23);
//		textArea_Preferencias = new JTextArea();
//		textArea_Preferencias.setBounds(126, 63, 878, 22);
//		textArea_Preferencias.setLineWrap(true);
//		textArea_Preferencias.setWrapStyleWord(true);
//
//		lblUsuario = new JLabel("Usuario: ");
//		lblUsuario.setBounds(10, 10, 62, 22);
//		textArea_Usuario = new JTextArea();
//		textArea_Usuario.setBounds(126, 10, 144, 22);
//		textArea_Usuario.setLineWrap(true);
//		textArea_Usuario.setWrapStyleWord(true);
//
//		lblRecomendaciones = new JLabel("Recomendaciones:");
//		lblRecomendaciones.setBounds(10, 122, 116, 23);
//		textArea_Recomendaciones = new JTextArea();
//		textArea_Recomendaciones.setBounds(126, 121, 878, 145);
//		textArea_Recomendaciones.setLineWrap(true);
//		textArea_Recomendaciones.setWrapStyleWord(true);
//
//		// Creamos el JPanel sobre el que estara el flowlayout y los componentes
//		// de la vista.
//		panel = new JPanel();
//		panel.setForeground(Color.BLACK);
//
//		// Añadimos titulo a nuestra ventana
//		this.setTitle("Sugerencias de Promos de Comidas");
//		// Indicamos a la ventana que se pueda cerrar. (La acción de cerrar)
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		// Le damos un tamaño por defecto a la ventana.
//		this.setSize(1030, 409);
//		// Indicamos su tamaño mínimo, y máximo, ya que no vamos a "bloquearla"
//		// y permitiremos que sea redimensionable.
//		this.setMinimumSize(new Dimension(100, 100));
//		this.setMaximumSize(new Dimension(2000, 2000));
//
//		panel.setLayout(null);
//
//		// Añadimos componentes visuales al panel
//		panel.add(botonFiltroA);
//		panel.add(botonFiltroB);
//
//		panel.add(lblRecomendaciones);
//		panel.add(lblPreferencias);
//		panel.add(lblUsuario);
//
//		panel.add(textArea_Usuario);
//		panel.add(textArea_Preferencias);
//		panel.add(textArea_Recomendaciones);
//
//		// Añadimos el panel a nuestra ventana.
//		getContentPane().add(panel);
//
//		JLabel lbl_imagen = new JLabel(" ");
//		lbl_imagen.setBounds(10, 164, 103, 59);
//
//		ImageIcon imagen = new ImageIcon("src\\main\\resources\\McDonalds.png");
//		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(
//				lbl_imagen.getWidth(), lbl_imagen.getHeight(),
//				Image.SCALE_DEFAULT));
//		lbl_imagen.setIcon(icono);
//		this.repaint();
//
//		panel.add(lbl_imagen);
//
//		chckbx_filtrosChatarras = new JCheckBox("1_ chatarras");
//		chckbx_filtrosChatarras.setBounds(508, 298, 116, 23);
//		panel.add(chckbx_filtrosChatarras);
//
//		chckbx_filtrosPostres = new JCheckBox("2_ postres");
//		chckbx_filtrosPostres.setBounds(626, 298, 103, 23);
//		panel.add(chckbx_filtrosPostres);
//
//		chckbx_filtrosSanas = new JCheckBox("3_ sanas");
//		chckbx_filtrosSanas.setBounds(748, 298, 97, 23);
//		panel.add(chckbx_filtrosSanas);
//
//		chckbx_filtrosPastas = new JCheckBox("4_ pastas");
//		chckbx_filtrosPastas.setBounds(847, 298, 97, 23);
//		panel.add(chckbx_filtrosPastas);
//
//		lblElijaFiltros = new JLabel("Elija Filtros:");
//		lblElijaFiltros.setBounds(10, 302, 116, 14);
//		panel.add(lblElijaFiltros);
//
//		// setLayout(new GridLayout(3, 1));
//		// CheckboxGroup cbg = new CheckboxGroup();
//		// add(new Checkbox("one", cbg, true));
//		// add(new Checkbox("two", cbg, false));
//		// add(new Checkbox("three", cbg, false));
//
//		// Hacemos visible nuestra ventana.
//		this.setVisible(true);
//
//	}
//
//	public void update(Observable obs, Object obj) {
//		System.out.println("update");
//
//		textArea_Recomendaciones.setText(String.valueOf(((Modelo) obs)
//				.getValorString()));
//		textArea_Preferencias.setText(String.valueOf(((Modelo) obs)
//				.getPreferencias()));
//		textArea_Usuario.setText(String.valueOf(((Modelo) obs).getUsuario()));
//
//	}
//
//	public void addFiltroListeners(ActionListener listenForCalcButton) {
//		botonFiltroA.addActionListener(listenForCalcButton);
//		botonFiltroB.addActionListener(listenForCalcButton);
//		chckbx_filtrosChatarras.addActionListener(listenForCalcButton);
//	}
//
//	public JButton getBotonFiltroA() {
//		return botonFiltroA;
//	}
//
//	public void setBotonFiltroA(JButton botonFiltroA) {
//		this.botonFiltroA = botonFiltroA;
//	}
//
//	public JButton getBotonFiltroB() {
//		return botonFiltroB;
//	}
//
//	public void setBotonFiltroB(JButton botonFiltroB) {
//		this.botonFiltroB = botonFiltroB;
//	}
//
//	public JCheckBox getChckbx_filtrosChatarras() {
//		return chckbx_filtrosChatarras;
//	}
//
//	public void setChckbx_filtrosChatarras(JCheckBox chckbx_filtrosChatarras) {
//		this.chckbx_filtrosChatarras = chckbx_filtrosChatarras;
//	}
//
//	public JCheckBox getChckbx_filtrosPostres() {
//		return chckbx_filtrosPostres;
//	}
//
//	public JTextArea getTextArea_Recomendaciones() {
//		return textArea_Recomendaciones;
//	}
//
//	public JCheckBox getChckbx_filtrosSanas() {
//		return chckbx_filtrosSanas;
//	}
//
//	public JCheckBox getChckbx_filtrosPastas() {
//		return chckbx_filtrosPastas;
//	}
//
//	public void setTextArea_Recomendaciones(JTextArea textArea_Recomendaciones) {
//		this.textArea_Recomendaciones = textArea_Recomendaciones;
//	}
//
//}
