package mvc_modelo_observable;

import java.awt.Dimension;   
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mvc_modelo_observable.Modelo;

import java.awt.Color;
import java.util.Observer;
import java.util.Observable;

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
        botonFiltroB.setBounds(358, 177, 116, 23);
        botonFiltroB.setText("filtrar B");
        
      //Creamos el boton suma
        botonFiltroA = new JButton();
        botonFiltroA.setForeground(Color.RED);
        botonFiltroA.setBounds(198, 177, 116, 23);
        botonFiltroA.setText("filtrar A");
        
        lblPreferencias = new JLabel("Preferencias:");
        lblPreferencias.setBounds(10, 64, 103, 23);
        textArea_Preferencias = new JTextArea();
        textArea_Preferencias.setBounds(126, 63, 804, 22);
        
        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setBounds(10, 10, 62, 22);
        textArea_Usuario = new JTextArea();
        textArea_Usuario.setBounds(126, 10, 144, 22);
        
        lblRecomendaciones = new JLabel("Recomendaciones:");
        lblRecomendaciones.setBounds(10, 122, 103, 23);
        textArea_Recomendaciones = new JTextArea();
        textArea_Recomendaciones.setBounds(126, 121, 804, 22);
        
        
        //Creamos el JPanel sobre el que estara el flowlayout y los componentes de la vista.
        panel = new JPanel();
        panel.setForeground(Color.BLACK);
        
        //Añadimos titulo a nuestra ventana
        this.setTitle("Sugerencias de Promos de Comidas");
        //Indicamos a la ventana que se pueda cerrar. (La acción de cerrar)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Le damos un tamaño por defecto a la ventana.
        this.setSize(956,300);
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
