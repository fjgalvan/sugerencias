package mvc;

import listeners.CambioDeIdiomaListener; 
import listeners.FiltroListener;
import listeners.GuardarPreferenciaListener;
import listeners.LoginListener;
import listeners.RegistroEmailUsuarioListener;
import listeners.RegistroNombreUsuarioListener;
import listeners.RegistroNuevoUsuarioListener;
import mvc_modelo_observable.Modelo;

import javax.swing.JTextArea;

/**
 * Controlador del MVC. Se encuentra dentro de la vista y es llamado por esta
 * para controlar a la lógica. El controlador contiene un modelo, el cual es la
 * logica de nuestro programa, y se encarga de llamar a este modelo para que
 * realice las acciones necesarias sobre el programa.
 * 
 * @author fon
 */
public class Controlador {
	Modelo m;
	Vista2 v;
	static JTextArea t;
	
	public Controlador(Modelo m, Vista2 v) {
		// Asignamos un modelo a nuestro controlador.
		this.m = m;
		this.v = v;

		//m.addObserver(v);
		v.addFiltroListeners(new FiltroListener(v, m));
		v.addRegistroListener(new RegistroNombreUsuarioListener(v));
		v.addRegistroListener(new RegistroEmailUsuarioListener(v));
		v.addRegistroNuevoUsuarioListener(new RegistroNuevoUsuarioListener(v,m));//Se agrega el modeloz
		v.addCambiarIdiomaListener(new CambioDeIdiomaListener(v,m));
		v.addGuardarPreferenciasListener(new GuardarPreferenciaListener(m,v));
		v.addLoginListener(new LoginListener(v,m));
	}
	public void filtroUsuarioA() {
		m.filtroA();
	}
	public void filtroUsuarioB() {
		m.filtroB();
	}

	// Utilizo una tabla de Verdad con 4 Variables!!
	public static void controladorDeCheckbox(Modelo m, Vista2 v, boolean c,
			boolean po, boolean s, boolean pa) {
		c = v.getChckbx_filtrosChatarras().isSelected();
		po = v.getChckbx_filtrosPostres().isSelected();
		s = v.getChckbx_filtrosSanas().isSelected();
		pa = v.getChckbx_filtrosPastas().isSelected();
		t = v.getTextArea_Recomendaciones();
		if (c && po && s && pa) {// 1
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroPostres()
					+ "\n" + m.getFiltroSanas() + "\n" + m.getFiltroPastas());
		}
		if (c && po && s && !pa) {// 2
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroPostres()
					+ "\n" + m.getFiltroSanas());
		}
		if (c && po && !s && pa) {// 3
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroPostres()
					+ "\n" + m.getFiltroPastas());
		}
		if (c && po && !s && !pa) {// 4
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroPostres());
		}
		if (c && !po && s && pa) {// 5
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroSanas() + "\n"
					+ m.getFiltroPastas());
		}
		if (c && !po && s && !pa) {// 6
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroSanas());
		}
		if (c && !po && !s && pa) {// 7
			t.setText(m.getFiltroChatarras() + "\n" + m.getFiltroPastas());
		}
		if (c && !po && !s && !pa) {// 8
			t.setText(m.getFiltroChatarras());
		}
		if (!c && po && s && pa) {// 9
			t.setText(m.getFiltroPostres() + "\n" + m.getFiltroSanas() + "\n"
					+ m.getFiltroPastas());
		}
		if (!c && po && s && !pa) {// 10
			t.setText(m.getFiltroPostres() + "\n" + m.getFiltroSanas());
		}
		if (!c && po && !s && pa) {// 11
			t.setText(m.getFiltroPostres() + "\n" + m.getFiltroPastas());
		}
		if (!c && po && !s && !pa) {// 12
			t.setText(m.getFiltroPostres());
		}
		if (!c && !po && s && pa) {// 13
			t.setText(m.getFiltroSanas() + "\n" + m.getFiltroPastas());
		}
		if (!c && !po && s && !pa) {// 14
			t.setText(m.getFiltroSanas());
		}
		if (!c && !po && !s && pa) {// 15
			t.setText(m.getFiltroPastas());
		}
		if (!c && !po && !s && !pa) {// 16
			t.setText("");
		}
		
	}
	public static String getT() {
		return t.toString();
	}
	
	

}
