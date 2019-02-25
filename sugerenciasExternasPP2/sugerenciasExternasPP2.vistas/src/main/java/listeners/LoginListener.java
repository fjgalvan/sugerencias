package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import mvc.Vista2;
import mvc_modelo_observable.Modelo;
import bo.UsuariosBo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class LoginListener implements ActionListener{
	Vista2 v;
	Modelo m;
	
	public LoginListener(Vista2 v, Modelo m){//se agrego el modelo para obtener la db
		this.v= v;
		this.m= m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		v.getBtnValidarLogin().addMouseListener(new MouseAdapter() {
			String emailDelUsuarioElejido="";
			@Override
            public void mouseClicked(MouseEvent e) {
				DBCollection collUsuarios=m.getMongo().db.getCollection("UsuarioDB");
		        BasicDBObject filtroReco = new BasicDBObject();
		        System.out.println("nombreUsuarioComboBox: "+v.getComboBox_eleccionDeUsuario().getSelectedItem().toString());
		        filtroReco.put("usuario", v.getComboBox_eleccionDeUsuario().getSelectedItem().toString());
		        DBCursor curReco = collUsuarios.find(filtroReco);//(filtro);
		        while (curReco.hasNext()){
		        	emailDelUsuarioElejido= curReco.next().get("email").toString();
		        	System.out.println("email MongoDB: "+emailDelUsuarioElejido);//me devuelve la clave
		        }
		        System.out.println("email Vista: "+v.getTextField_validarEmail().getText().toString());
				if(emailDelUsuarioElejido.equals(v.getTextField_validarEmail().getText().toString())){
					v.getTextField_validezLogin().setText("OK Login!");
				}else{
					v.getTextField_validezLogin().setText("Error Login!");
				}
			}
		});
	}
}
