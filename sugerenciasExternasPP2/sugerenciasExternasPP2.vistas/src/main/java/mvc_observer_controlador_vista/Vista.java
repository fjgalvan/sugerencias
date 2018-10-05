package mvc_observer_controlador_vista;

 import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.mongodb.DBObject;

import java.awt.Color;
import java.awt.Label;

/**
 *  Vista de nuestro programa
 *  Utilizamos la libreria swing
 *  
 * @author fon
 */
public class Vista extends JFrame implements mvc_modelo_observable.ModeloObserver{

    private Controlador c;
    
    /*Los siguientes atributos no son necesarios que existan como tal, pero los he creado para que se vean bien mis componentes 
     * de la vista*/
    private JButton botonSuma, botonResta;  //No es necesario tenerlos como atributos ya que no lo vamos a modificar
    private JPanel panel;                   // No es necesario tenerlos como atributos ya que no vamos a estar modificandolo
    private JTextField textField_Recomendaciones;
    private JLabel lblRecomendaciones;
    private JLabel lblPreferencias;
    private JTextArea textArea_Preferencias;
    private JTextArea textArea_Usuario;
    
    
    /**
     * Crea la vista, se le asigna un controlador
     * @param c : controlador asociado a la vista.
     */
    public Vista(final Controlador c){
        //Asignamos controlador a la vista
        this.c = c;
        //Lo mismo que con el boton de sumar, pero con la resta...
        botonResta = new JButton();
        botonResta.setForeground(Color.BLUE);
        botonResta.setBounds(358, 177, 116, 23);
        botonResta.setText("filtrar B");
        botonResta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c.filtroUsuarioB();
            }
        });
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
        
        //Creamos el boton suma
        botonSuma = new JButton();
        botonSuma.setForeground(Color.RED);
        botonSuma.setBounds(198, 177, 116, 23);
        //Añadimos su texto correspondiente
        botonSuma.setText("filtrar A");
        //Añadimos un listener para los eventos de raton.
        botonSuma.addMouseListener(new MouseAdapter() {
            /*Es más comodo usar MouseAdapter, ya que solo necesitamos un metodo de la interfaz MouseListener*/
            //Cuando clickemos sobre el boton, queremos que realice...
            @Override
            public void mouseClicked(MouseEvent e) {
                //... al clickar, llamamos al controlador y le indicamos que acción realizar. 
            	//El controlador se encargará de notificar a la lógica según la acción.
                c.filtroUsuarioA();
            }
        });
        panel.setLayout(null);
        panel.add(botonSuma);
        panel.add(botonResta);
        //Añadimos el panel a nuestra ventana.
        getContentPane().add(panel);
        
        textField_Recomendaciones = new JTextField();
        textField_Recomendaciones.setBounds(126, 117, 804, 32);
        textField_Recomendaciones.setText("Elija un usuario!");
        panel.add(textField_Recomendaciones);
        textField_Recomendaciones.setColumns(10);
        
        lblRecomendaciones = new JLabel("Recomendaciones:");
        lblRecomendaciones.setBounds(10, 122, 103, 23);
        panel.add(lblRecomendaciones);
        
        lblPreferencias = new JLabel("Preferencias:");
        lblPreferencias.setBounds(10, 64, 103, 23);
        panel.add(lblPreferencias);
        
        textArea_Preferencias = new JTextArea();
        textArea_Preferencias.setBounds(126, 63, 804, 22);
        panel.add(textArea_Preferencias);
        
        Label label = new Label("Usuario: ");
        label.setBounds(10, 10, 62, 22);
        panel.add(label);
        
        textArea_Usuario = new JTextArea();
        textArea_Usuario.setBounds(126, 10, 144, 22);
        panel.add(textArea_Usuario);
        //Hacemos visible nuestra ventana.
        this.setVisible(true);
        
    }
    
    /**
     * Metodo de la interfaz del observador. Este método será llamado por el Observable para que cada
     * observador sepa qué ha pasado respecto al valor cambiado.
     * @param valor : valor que recibimos nuevo del modelo.
     */
    //@Override
    public void valorCambiado(int valor, String preferencias, String usuario) {
        //Nos llega el valor cambiado del modelo, que es el estado que nos interesa para cambiar la vista.
        //Actuamos en consecuencia de ese valor que nos ha llegado del modelo, actualizando el texto(JLabel) de nuestra vista.
        //texto.setText(Integer.toString(valor));
        textField_Recomendaciones.setText(Integer.toString(valor));
        textArea_Preferencias.setText(preferencias);
        textArea_Usuario.setText(usuario);
    }

	@Override
	public void valorCambiado(DBObject valor2, String preferencias, String usuario) {
		// TODO Auto-generated method stub
		//texto.setText((valor2.toString()));
		textField_Recomendaciones.setText((valor2.toString()));
		textArea_Preferencias.setText(preferencias);
		textArea_Usuario.setText(usuario);
	}
}
