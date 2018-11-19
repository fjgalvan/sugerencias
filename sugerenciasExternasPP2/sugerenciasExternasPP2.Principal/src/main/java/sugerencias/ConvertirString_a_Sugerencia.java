package sugerencias;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import util_.Date;

public class ConvertirString_a_Sugerencia {
	String twitterString;
	int posicion=0;
	int posicion2=0;
	private String local;
	private String ubicacion;
	private Date fechaDeVigencia;
	private HashMap<String,Double> listaProductosPrecios = new HashMap<String,Double>();//(producto, precio)
	private Date date;
	SugerenciaTwitter st;
	
	
	public ConvertirString_a_Sugerencia(String twitterString){
		this.twitterString= twitterString;
	}
	
	public void convertirLocal(){
		int posicion=0;
		String local="";
		
		for(int i=0; i<twitterString.length(); i++){
			if(twitterString.charAt(i) == '_'){
				posicion=i;
				break;
			}
		}
		for(int j=7; j<posicion; j++){
			local= local + twitterString.charAt(j);
		}
		this.local= local;
	}
	
	public void convertirUbicacion(){
		
		int cont=0;
		String ubicacion="";
		
		for(int i=0; i<twitterString.length(); i++){
			if(twitterString.charAt(i) == '_'){
				posicion=i;
				break;
			}
		}
		for(int j=posicion+1; j<twitterString.length(); j++){
			if(twitterString.charAt(j) == '_'){
				posicion2=j;
				break;
			}
		}
		
		for(int k=posicion+1; k<posicion2; k++){
			ubicacion= ubicacion + twitterString.charAt(k);
		}
		this.ubicacion= ubicacion;
	}
	
	public void convertirLista(){//HAY QUE VACIAR EL PRODUCTO Y GUARDARLO EN EL HASHMAP
		int posicion3=0;
		int posicion4=0;
		int aux1=0;
		int aux2=0;
		
		
		String producto="";
		String precioString="";
		double precio= 0.0;
		
		
		for(int i=0; i<twitterString.length(); i++){//busco el inicio de la lista de promo
			if(twitterString.charAt(i) == '('){
				posicion3=i;
				break;
			}
		}
		for(int i=0; i<twitterString.length(); i++){//busco el fin de la lista de promo
			if(twitterString.charAt(i) == ')'){
				posicion4=i;
				break;
			}
		}
		for(int i=posicion3+1; i<posicion4; i++){//Cuento la cantidad de productos de promo de la lista
			if(twitterString.charAt(i) == '/'){
				aux1=aux1 +1;
			}
		}
		//System.out.println("aux1: " + aux1);
		int cantCiclos=posicion3+1;
		
		for(int i=cantCiclos; i<posicion4; i++){//Guardo la lista en un Hashmap
			if(aux2==0){//PRODUCTO String
				if(twitterString.charAt(i) != '/'){
				producto= producto + twitterString.charAt(i);
				} if(twitterString.charAt(i) == '/'){
					aux2= 1;
				}
			}
				
			if(aux2==1){//PRECIO Double
				if((twitterString.charAt(i) != ',') && (twitterString.charAt(i) != '/')){
					precioString= precioString + twitterString.charAt(i); 
				} if((twitterString.charAt(i) == ',')||(i==posicion4-1)){
					aux2= 0;
					precio= Double.parseDouble(precioString);
					listaProductosPrecios.put(producto, precio);
					producto="";
					precioString="";
				}
			}		
		}
		// Recorremos el hashMap y mostramos por pantalla el par valor y clave
		Iterator it = listaProductosPrecios.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry e = (Map.Entry)it.next();
		    //System.out.println(e.getKey() + " " + e.getValue());
		}	
		this.listaProductosPrecios= listaProductosPrecios;
	}

	
	
	public void convertirFecha(){
		int posInicio=0;
		String ddString="";
		String mmString="";
		String aaaaString="";
		Integer dd=0;
		Integer mm=0;
		Integer aaaa=0;
		int cont=0;
		
		for(int i=0; i<twitterString.length(); i++){//busco el inicio de la lista de promo
			if(twitterString.charAt(i) == ')'){
				posInicio=i+2;
				break;
			}
		}
		for(int i=posInicio; i<twitterString.length(); i++){
			if((i==posInicio) || (i==posInicio+1)){ //dd
				ddString=ddString + twitterString.charAt(i);
			}
			
			if((i==posInicio+3) || (i==posInicio+4)){ //mm
				mmString=mmString + twitterString.charAt(i);
			}
			
			if((i==posInicio+6) || (i==posInicio+7) || (i==posInicio+8) || (i==posInicio+9)){ //aaaa
				aaaaString=aaaaString + twitterString.charAt(i);
			}
		}	
		
		date= new Date(Integer.parseInt(ddString),Integer.parseInt(mmString),Integer.parseInt(aaaaString));
		this.fechaDeVigencia= date;
	}
	
	public SugerenciaTwitter getSugerenciaTwitter(){
		st= new SugerenciaTwitter(local, ubicacion, listaProductosPrecios, fechaDeVigencia);
		return st;
	}
	
	//Esta lista sirve para taggear comidas (sanas, chatarras, postres, pastas)
	public ArrayList<Sugerencias> getListSugerenciaTwitter(){
		ArrayList<Sugerencias> l= new ArrayList<Sugerencias>();
		//InterfazDeSugerencias i= new InterfazDeSugerencias(local, ubicacion, listaProductosPrecios.get(i).);

		Iterator iterador = listaProductosPrecios.entrySet().iterator();
		 Map.Entry producto;
		    while (iterador.hasNext()) {
		        producto = (Map.Entry) iterador.next();
		        //producto = iterador.next(); Si se usase tambien la otra linea comentada.
		        System.out.println(producto.getKey() + " - " + producto.getValue());
		        Sugerencias i= new Sugerencias(local, ubicacion, producto.getKey(), producto.getValue(), fechaDeVigencia);
		        l.add(i);
		    }
		
		return l;
		
	}

	public Date getDate() {
		return date;
	}
	
}
