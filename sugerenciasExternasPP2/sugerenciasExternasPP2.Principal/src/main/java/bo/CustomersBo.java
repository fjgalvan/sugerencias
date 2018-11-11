package bo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Customer> getListaDeCustomers(){
		ArrayList<Preferencias> listaPreferencias;
		Enumeration<Object> keys = customersProperties.keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			//System.out.println(key + " = " + customersProperties.get(key));
			String[] parts = customersProperties.getProperty(key.toString())
					.split(",");
			Usuario userName= new Usuario(parts[0], parts[1]);
			//System.out.println("User: "+userName.getUsuario()+", Email:"+ userName.geteMail());
			listaPreferencias= new ArrayList<Preferencias>();
			//System.out.println("parts.length: "+parts.length);
			for(int i=2; i<parts.length; i++){
				if(parts[i].toString().equals("chatarras")){
					Preferencias pref= new Preferencias(1,"chatarras");
					listaPreferencias.add(pref);
					//System.out.println("prefiere chatarras");
				}
				if(parts[i].toString().equals("postres")){
					Preferencias pref= new Preferencias(2,"postres");
					listaPreferencias.add(pref);
					//System.out.println("prefiere postres");
				}
				if(parts[i].toString().equals("sanas")){
					Preferencias pref= new Preferencias(3,"sanas");
					listaPreferencias.add(pref);
					//System.out.println("prefiere sanas");
				}
				if(parts[i].toString().equals("pastas")){
					Preferencias pref= new Preferencias(4,"pastas");
					listaPreferencias.add(pref);
					//System.out.println("prefiere pastas");
				}
			}
			Customer customer = new Customer(key.toString(), userName, listaPreferencias);
			//System.out.println(customer.getId()+", "+ customer.getUserName().getUsuario()+", "+ customer.getUserName().geteMail()+", "+ customer.getListaPreferencias().get(0).getDescripcion());
			listaCustomers.add(customer);
		}
		return listaCustomers;
	}
	
	public void mostrarListaDeCustomers() {
		System.out.println("------------------------INICIO-----------------------");
		for (Customer user : listaCustomers) {
			System.out.println(user.getId());
			System.out.println(user.getUserName().getUsuario()); 
			System.out.println(user.getUserName().geteMail());
			System.out.println(user.getListaPreferencias().get(0).getDescripcion());

		}
		System.out.println("------------------------FIN-------------------------");
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
