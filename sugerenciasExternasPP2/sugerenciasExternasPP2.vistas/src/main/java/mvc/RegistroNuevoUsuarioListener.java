package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import bo.UsuariosBo;

public class RegistroNuevoUsuarioListener implements ActionListener{
	Vista2 v;
	
	public RegistroNuevoUsuarioListener(Vista2 v){
		this.v= v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		v.getBtnRegistrarse().addMouseListener(new MouseAdapter() {
			boolean registro= false;
			@Override
            public void mouseClicked(MouseEvent e) {
				UsuariosBo uBo= new UsuariosBo();
				try {
					registro=uBo.agregarNuevoUsuario(v.getTextField_usuario().getText(), v.getTextField_Email().getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(registro){
					v.getTextArea_registroNuevoUsuario().setText("OK");
				}else{
					v.getTextArea_registroNuevoUsuario().setText("Error");
				}
			}
		});
	}
}