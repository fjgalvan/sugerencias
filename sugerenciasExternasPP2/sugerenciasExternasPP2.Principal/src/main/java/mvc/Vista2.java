package mvc;

import java.awt.Button;  
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import modelo.Customer;
import modelo.Preferencias;
import modelo.Recomendacion;
import modelo.Usuario;
import mvc_modelo_observable.Modelo;
import twitter4j.TwitterException;

import com.mongodb.DBCollection;

import conexiones.Interfaz.RecolectorPromos;
import dao.mongoDB.MongoConcreteStub;
import estadisticas.PromosOpuestosEnPrecio;

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
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_Idioma;
	private JButton btnCambiarIdioma;
	private JButton btn_GuardarPreferencia;
	private Button button;
	private JLabel label;
	private Timer tm;
	private MongoConcreteStub basePromosActual;
	private String fechaActualizacion="";
	JTextArea textArea_fechaActualizacion;
	private JLabel lbl_TagpromoMasCaro;
	private JLabel lblPromoDelTag;
	private JLabel lblElijaTagsPara; 
	private JButton btnCompararTags;
//	private JScrollPane jsp;
	private JTextArea textArea_tagMasCaro;
	private JTextArea textArea_TagMasEconomico;
	private JComboBox comboBox_tag1;
	private JComboBox comboBox_tag2;
	private JLabel lblHorarioltimaActualizacin;
	private String tag1="";
	private String tag2="";
	private JButton btnPararActualizacion;
	
	private Modelo m;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vista2(Modelo modelo) {
		this.m= modelo;
		
		lblRecomendaciones = new JLabel("Recomendaciones:");
		lblRecomendaciones.setBounds(10, 262, 116, 23);
		textArea_Recomendaciones = new JTextArea();
		textArea_Recomendaciones.setBounds(123, 261, 878, 176);
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
		this.setSize(1030, 678);
		// Indicamos su tamaño mínimo, y máximo, ya que no vamos a "bloquearla"
		// y permitiremos que sea redimensionable.
		this.setMinimumSize(new Dimension(100, 100));
		this.setMaximumSize(new Dimension(2000, 2000));

		panel.setLayout(null);

		panel.add(lblRecomendaciones);
		panel.add(textArea_Recomendaciones);
//		jsp= new JScrollPane(textArea_Recomendaciones, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		panel.add(jsp);

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
		chckbx_filtrosChatarras.setBounds(234, 233, 116, 23);
		panel.add(chckbx_filtrosChatarras);

		chckbx_filtrosPostres = new JCheckBox("2_ postres");
		chckbx_filtrosPostres.setForeground(new Color(0, 0, 255));
		chckbx_filtrosPostres.setBounds(383, 233, 103, 23);
		panel.add(chckbx_filtrosPostres);

		chckbx_filtrosSanas = new JCheckBox("3_ sanas");
		chckbx_filtrosSanas.setForeground(new Color(0, 0, 255));
		chckbx_filtrosSanas.setBounds(516, 233, 97, 23);
		panel.add(chckbx_filtrosSanas);

		chckbx_filtrosPastas = new JCheckBox("4_ pastas");
		chckbx_filtrosPastas.setForeground(new Color(0, 0, 255));
		chckbx_filtrosPastas.setBounds(669, 233, 97, 23);
		panel.add(chckbx_filtrosPastas);

		lblElijaFiltros = new JLabel("Elija Filtros de Preferencias:");
		lblElijaFiltros.setBounds(10, 237, 186, 14);
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
		comboBox_eleccionDeUsuario.setModel(new DefaultComboBoxModel(
				new String[] { "Usuario A", "Usuario B" }));
		comboBox_eleccionDeUsuario.setBounds(245, 206, 241, 20);
		panel.add(comboBox_eleccionDeUsuario);

		label_lineaHorizontal = new JLabel(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_lineaHorizontal.setBounds(10, 99, 991, 14);
		panel.add(label_lineaHorizontal);

		btnFiltrarPreferencia = new JButton("Filtrar Preferencia!");

		btnFiltrarPreferencia.setForeground(new Color(0, 0, 255));
		btnFiltrarPreferencia.setBounds(503, 203, 156, 23);
		panel.add(btnFiltrarPreferencia);

		btn_refrescar = new JButton("Refrescar Filtros");
		btn_refrescar.setForeground(Color.BLUE);
		btn_refrescar.setBounds(669, 203, 144, 23);
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

		JLabel label_lineaHorizontal2 = new JLabel(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_lineaHorizontal2.setBounds(10, 149, 991, 14);
		panel.add(label_lineaHorizontal2);

		lblElijaIdioma = new JLabel("Elija idioma:");
		lblElijaIdioma.setBounds(10, 124, 103, 14);
		panel.add(lblElijaIdioma);

		comboBox_Idioma = new JComboBox();
		comboBox_Idioma.setModel(new DefaultComboBoxModel(new String[] {
				"Espanol", "Ingles" }));
		comboBox_Idioma.setBounds(126, 121, 175, 20);
		panel.add(comboBox_Idioma);

		btnCambiarIdioma = new JButton("Cambiar Idioma");
		btnCambiarIdioma.setForeground(new Color(153, 51, 0));
		btnCambiarIdioma.setBounds(319, 120, 148, 23);
		panel.add(btnCambiarIdioma);

		btn_GuardarPreferencia = new JButton("Guardar Preferencia");
		btn_GuardarPreferencia.setForeground(new Color(0, 0, 255));
		btn_GuardarPreferencia.setBounds(791, 448, 213, 23);
		panel.add(btn_GuardarPreferencia);

		button = new Button("PressMe");
		panel.add(button);
		
		label = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		label.setBounds(10, 470, 991, 14);
		panel.add(label);
		
		lblHorarioltimaActualizacin = new JLabel("Horario Última Actualización: ");
		lblHorarioltimaActualizacin.setBounds(10, 180, 186, 14);
		panel.add(lblHorarioltimaActualizacin);
		
		textArea_fechaActualizacion = new JTextArea();
		textArea_fechaActualizacion.setBackground(new Color(127, 255, 212));
		textArea_fechaActualizacion.setBounds(245, 173, 241, 22);
		panel.add(textArea_fechaActualizacion);
		textArea_fechaActualizacion.setText(fechaActualizacion);
		
		lblElijaTagsPara = new JLabel("Elija Tags Para Comparar:");
		lblElijaTagsPara.setBounds(10, 495, 156, 14);
		panel.add(lblElijaTagsPara);
		
		comboBox_tag1 = new JComboBox();
		comboBox_tag1.setModel(new DefaultComboBoxModel(new String[] {"chatarras", "postres", "sanas", "pastas"}));
		comboBox_tag1.setBounds(188, 495, 156, 20);
		panel.add(comboBox_tag1);
		
		comboBox_tag2 = new JComboBox();
		comboBox_tag2.setModel(new DefaultComboBoxModel(new String[] {"chatarras", "postres", "sanas", "pastas"}));
		comboBox_tag2.setBounds(383, 495, 156, 20);
		panel.add(comboBox_tag2);
		
		btnCompararTags = new JButton("comparar tags");
		btnCompararTags.setBackground(Color.GREEN);
		btnCompararTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compararTagsElegidos();
			}
		});
		btnCompararTags.setBounds(579, 495, 125, 23);
		panel.add(btnCompararTags);
		
		lbl_TagpromoMasCaro = new JLabel("Promo del Tag mas caro:");
		lbl_TagpromoMasCaro.setBounds(10, 583, 186, 14);
		panel.add(lbl_TagpromoMasCaro);
		
		lblPromoDelTag = new JLabel("Promo del Tag mas economico:");
		lblPromoDelTag.setBounds(10, 608, 186, 14);
		panel.add(lblPromoDelTag);
		
		textArea_tagMasCaro = new JTextArea();
		textArea_tagMasCaro.setBounds(201, 578, 800, 22);
		panel.add(textArea_tagMasCaro);
		
		textArea_TagMasEconomico = new JTextArea();
		textArea_TagMasEconomico.setBounds(203, 603, 798, 22);
		panel.add(textArea_TagMasEconomico);
		
		
		// Hacemos visible nuestra ventana.
		this.setVisible(true);
		// Cargamos controlador y le asignamos qué modelo controlar
		@SuppressWarnings("unused")
		Controlador c = new Controlador(modelo, this);

	}

	protected void compararTagsElegidos() {
		tag1= comboBox_tag1.getSelectedItem().toString();
		tag2= comboBox_tag2.getSelectedItem().toString();
		DBCollection coll = null;
		ArrayList<Preferencias> listaPreferencias= new ArrayList<Preferencias>();
		Preferencias p1 = null;
		Preferencias p2 = null;
		//Preferecia 1: "chatarras", 2:"postres", 3:"sanas", 4:"pastas"
		if(tag1.equals("chatarras")){p1= new Preferencias(1,"chatarras");}
		if(tag1.equals("postres")){p1= new Preferencias(2,"postres");}
		if(tag1.equals("sanas")){p1= new Preferencias(3,"sanas");}
		if(tag1.equals("pastas")){p1= new Preferencias(4,"pastas");}
		
		if(tag2.equals("chatarras")){p2= new Preferencias(1,"chatarras");}
		if(tag2.equals("postres")){p2= new Preferencias(2,"postres");}
		if(tag2.equals("sanas")){p2= new Preferencias(3,"sanas");}
		if(tag2.equals("pastas")){p2= new Preferencias(4,"pastas");}
		
		listaPreferencias.add(p1);
		listaPreferencias.add(p2);
		Usuario u= new Usuario("u","usuario@yahoo.com.ar");
		try {
			Customer c1= new Customer("10", u,listaPreferencias);
			Recomendacion r= new Recomendacion(c1);
			coll= cargarUnaRecomendacion(r, c1);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | TwitterException e1) {
			e1.printStackTrace();
		}
		
		PromosOpuestosEnPrecio estadistica= new PromosOpuestosEnPrecio();
		textArea_tagMasCaro.setText(estadistica.getPromoMasCara(coll).toString());
		textArea_TagMasEconomico.setText(estadistica.getPromoMasEconomica(coll).toString());
		
	}
	public DBCollection cargarUnaRecomendacion(Recomendacion reco, Customer user) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TwitterException{
		//RecolectorPromos c = new RecolectorPromos();//sin argumento con mongoStub
		RecolectorPromos c = new RecolectorPromos(this.m.getMongo());
		
		c.cargarListaConectores();
		c.buscarPromociones();
		c.promosConFiltros(tag1, tag2);
		//c.getMongoDB().leerColeccion();
		reco.leerPreferencias();
		DBCollection coll=reco.mostrarRecomendaciones(c.getMongoDB().getPromos());
		return coll;
	}

	public void update(Observable obs, Object obj) {
		//System.out.println("update");

		textArea_Recomendaciones.setText(String.valueOf(((Modelo) obs)
				.getValorString()));
		textArea_fechaActualizacion.setText(String.valueOf(((Modelo) obs)
				.getHoraActualizacion()));//(fechaActualizacion);
	}

	public JTextArea getTextArea_fechaActualizacion() {
		return textArea_fechaActualizacion;
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

	public void addController(ActionListener controller) {
		button.addActionListener(controller);
	} // addController()

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

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox_Idioma() {
		return comboBox_Idioma;
	}

	public JButton getBtn_GuardarPreferencia() {
		return btn_GuardarPreferencia;
	}

	public MongoConcreteStub getBasePromosActual() {
		return basePromosActual;
	}

	public JButton getBtnCompararTags() {
		return btnCompararTags;
	}

	public JComboBox getComboBox_tag1() {
		return comboBox_tag1;
	}

	public JComboBox getComboBox_tag2() {
		return comboBox_tag2;
	}

	public JTextArea getTextArea_tagMasCaro() {
		return textArea_tagMasCaro;
	}

	public JTextArea getTextArea_TagMasEconomico() {
		return textArea_TagMasEconomico;
	}

	public JLabel getLbl_TagpromoMasCaro() {
		return lbl_TagpromoMasCaro;
	}

	public JLabel getLblPromoDelTag() {
		return lblPromoDelTag;
	}

	public JLabel getLblElijaTagsPara() {
		return lblElijaTagsPara;
	}

	public JLabel getLblHorarioltimaActualizacin() {
		return lblHorarioltimaActualizacin;
	}
}
