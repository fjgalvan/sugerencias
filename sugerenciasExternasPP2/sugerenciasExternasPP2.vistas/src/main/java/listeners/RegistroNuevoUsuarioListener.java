package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Enumeration;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import dao.mongoDB.MongoConcrete;
import mvc.Vista2;
import mvc_modelo_observable.Modelo;
import bo.UsuariosBo;

public class RegistroNuevoUsuarioListener implements ActionListener{
	Vista2 v;
	Modelo m;
	
	public RegistroNuevoUsuarioListener(Vista2 v, Modelo m){//se agrego el modelo para obtener la db
		this.v= v;
		this.m= m;
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
					
//					MongoConcrete mongoUsuario= new MongoConcrete("UsuarioDB");// VEERRR
//					//mongoUsuario.eliminarTodaLaColeccion();
					//Reviso si el usuario tiene Twitter
					String twitterUser=v.getTextField_twitter().getText();
					if(v.getTextField_twitter().getText().isEmpty()){
						twitterUser="vacio";
					}
					System.out.println("twitterUser: "+twitterUser);
					DBCollection collUsuarios=m.getMongo().db.getCollection("UsuarioDB");
					BasicDBObject doc1;
					doc1 = new BasicDBObject();
					//doc1.append(v.getTextField_usuario().getText(), v.getTextField_Email().getText());
					doc1.append("usuario",v.getTextField_usuario().getText());
					doc1.append("email", v.getTextField_Email().getText());
					doc1.append("twitter", twitterUser);//v.getTextField_twitter().getText());
					collUsuarios.insert(doc1);
					System.out.println("collUsuarios.count().count(): "+collUsuarios.count());
					
				}else{
					v.getTextArea_registroNuevoUsuario().setText("Error");
				}
			}
		});
	}
}