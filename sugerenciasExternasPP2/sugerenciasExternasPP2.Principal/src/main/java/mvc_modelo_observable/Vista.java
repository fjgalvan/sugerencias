package mvc_modelo_observable;

import java.awt.Dimension;   
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

@SuppressWarnings("serial")
public class Vista extends JFrame implements Observer{

    private Modelo modelo= null;
    private Observable o;
    
    private JButton botonFiltroA, botonFiltroB;  
    private JPanel panel;                   
    private JLabel lblRecomendaciones;
    private JLabel lblPreferencias;
    private JLabel lblUsuario;
    private JTextArea textArea_Usuario;
    private JTextArea textArea_Preferencias;
    private JTextArea textArea_Recomendaciones;
    
    /**
     * Crea la vista, se le asigna un controlador
     * @param c : controlador asociado a la vista.
     */
    public Vista(final Modelo modelo){
    	this.modelo= modelo;

        botonFiltroB = new JButton();
        botonFiltroB.setForeground(Color.BLUE);
        botonFiltroB.setBounds(367, 298, 116, 23);
        botonFiltroB.setText("filtrar B");
        
      //Creamos el boton suma
        botonFiltroA = new JButton();
        botonFiltroA.setForeground(Color.RED);
        botonFiltroA.setBounds(214, 298, 116, 23);
        botonFiltroA.setText("filtrar A");
        
        lblPreferencias = new JLabel("Preferencias:");
        lblPreferencias.setBounds(10, 64, 103, 23);
        textArea_Preferencias = new JTextArea();
        textArea_Preferencias.setBounds(126, 63, 878, 22);
        textArea_Preferencias.setLineWrap(true);
        textArea_Preferencias.setWrapStyleWord(true);
        
        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setBounds(10, 10, 62, 22);
        textArea_Usuario = new JTextArea();
        textArea_Usuario.setBounds(126, 10, 144, 22);
        textArea_Usuario.setLineWrap(true);
        textArea_Usuario.setWrapStyleWord(true);
        
        lblRecomendaciones = new JLabel("Recomendaciones:");
        lblRecomendaciones.setBounds(10, 122, 103, 23);
        textArea_Recomendaciones = new JTextArea();
        textArea_Recomendaciones.setBounds(126, 121, 878, 145);
        textArea_Recomendaciones.setLineWrap(true);
        textArea_Recomendaciones.setWrapStyleWord(true);
        
        //Creamos el JPanel sobre el que estara el flowlayout y los componentes de la vista.
        panel = new JPanel();
        panel.setForeground(Color.BLACK);
        
        //Añadimos titulo a nuestra ventana
        this.setTitle("Sugerencias de Promos de Comidas");
        //Indicamos a la ventana que se pueda cerrar. (La acción de cerrar)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Le damos un tamaño por defecto a la ventana.
        this.setSize(1030,409);
        //Indicamos su tamaño mínimo, y máximo, ya que no vamos a "bloquearla" y permitiremos que sea redimensionable.
        this.setMinimumSize(new Dimension(100,100));
        this.setMaximumSize(new Dimension(2000,2000));
        
        panel.setLayout(null);
        
        //Añadimos componentes visuales al panel
        panel.add(botonFiltroA);
        panel.add(botonFiltroB);
        
        panel.add(lblRecomendaciones);
        panel.add(lblPreferencias);
        panel.add(lblUsuario);
        
        panel.add(textArea_Usuario);
        panel.add(textArea_Preferencias);
        panel.add(textArea_Recomendaciones);
       
		//Añadimos el panel a nuestra ventana.
        getContentPane().add(panel);
        
        JLabel lbl_imagen = new JLabel(" ");
//        lbl_imagen.setIcon(new ImageIcon("src\\main\\resources\\McDonalds.png"));
        lbl_imagen.setBounds(10, 164, 103, 59);
        
      
        ImageIcon imagen= new ImageIcon("src\\main\\resources\\McDonalds.png");
        Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(lbl_imagen.getWidth(), lbl_imagen.getHeight(), Image.SCALE_DEFAULT));
        lbl_imagen.setIcon(icono);
        this.repaint();
        
        panel.add(lbl_imagen);
        
        //Hacemos visible nuestra ventana.
        this.setVisible(true);
        
    }

	public void update(Observable obs, Object obj) {
    	System.out.println("update");
		if( obs == this.modelo ){
			textArea_Recomendaciones.setText(String.valueOf(this.modelo.getValorString()));
			textArea_Preferencias.setText(String.valueOf(this.modelo.getPreferencias()));
			textArea_Usuario.setText(String.valueOf(this.modelo.getUsuario()));
		}
	}
    
    public void addFiltroListeners(ActionListener listenForCalcButton){
    	botonFiltroA.addActionListener( listenForCalcButton);
    	botonFiltroB.addActionListener( listenForCalcButton);
    }

	public JButton getBotonFiltroA() {
		return botonFiltroA;
	}
	public void setBotonFiltroA(JButton botonFiltroA) {
		this.botonFiltroA = botonFiltroA;
	}
	public JButton getBotonFiltroB() {
		return botonFiltroB;
	}
	public void setBotonFiltroB(JButton botonFiltroB) {
		this.botonFiltroB = botonFiltroB;
	}
}
