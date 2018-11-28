package bo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import configurables.MyConstantsBo;
import modelo.Customer;
import modelo.Preferencias;
import modelo.Usuario;
import properties.Constants;

public class CustomersBo {
	private Properties customersProperties;
	private List<Customer> listaCustomers;
	
	public CustomersBo() {
		listaCustomers= new ArrayList<Customer>();
		customersProperties= new Properties();
		try {
			customersProperties.load(new FileReader(Constants.ROUTE_CUSTOMERS));
		} catch (IOException e) {e.printStackTrace();}
	}
	public List<Customer> getListaDeCustomers(){
		ArrayList<Preferencias> listaPreferencias;
		Enumeration<Object> keys = customersProperties.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			String[] parts = customersProperties.getProperty(key.toString())
					.split(",");
			Usuario userName= new Usuario(parts[0], parts[1]);
			listaPreferencias= new ArrayList<Preferencias>();
			for(int i=2; i<parts.length; i++){
				if(parts[i].toString().equals(MyConstantsBo.tagChatarras)){
					Preferencias pref= new Preferencias(1,MyConstantsBo.tagChatarras);
					listaPreferencias.add(pref);
				}
				if(parts[i].toString().equals(MyConstantsBo.tagPostres)){
					Preferencias pref= new Preferencias(2,MyConstantsBo.tagPostres);
					listaPreferencias.add(pref);
				}
				if(parts[i].toString().equals(MyConstantsBo.tagSanas)){
					Preferencias pref= new Preferencias(3,MyConstantsBo.tagSanas);
					listaPreferencias.add(pref);
				}
				if(parts[i].toString().equals(MyConstantsBo.tagPastas)){
					Preferencias pref= new Preferencias(4,MyConstantsBo.tagPastas);
					listaPreferencias.add(pref);
				}
			}
			Customer customer = new Customer(key.toString(), userName, listaPreferencias);
			listaCustomers.add(customer);
		}
		return listaCustomers;
	}
	
	public void mostrarListaDeCustomers() {
		for (Customer user : listaCustomers) {
			System.out.println(user.getId());System.out.println(user.getUserName().getUsuario()); 
			System.out.println(user.getUserName().geteMail());System.out.println(user.getListaPreferencias().get(0).getDescripcion());
		}
	}
	public List<Customer> getListaCustomers() {
		return listaCustomers;
	}
	public void agregarCostumer(Customer c){
		String preferencias="";
		for(int i=0; i< c.getListaPreferencias().size(); i++){
			preferencias= preferencias+c.getListaPreferencias().get(i)+",";
		}
		String valor= c.getUserName().getUsuario()+","+c.getUserName().geteMail()+","+preferencias; 
		customersProperties.setProperty(c.getId().toString(),valor );
	}
	
}
