package conexiones.conexionExel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
 







import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import conexiones.Interfaz.InterfaceConexion;
import properties.Constants;
 
public class LeerFicherosExcel implements InterfaceConexion{
	static Integer aux=0;
	
	@Override
	public void conectarse() {
		LeerExcelXLSX();
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static void LeerExcelXLSX() {
		String rutaArchivo= Constants.ROUTE_EXCEL_xlsx;
		String hoja = "mcDonald";
		
 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					System.out.print(cell.getStringCellValue()+" | ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		validarExcel();
	}
	
	
	
	@SuppressWarnings({ "unused", "resource" })
	public static boolean validarExcel(){
		String nombreArchivo = "Sugerencias.xlsx";
		String rutaArchivo = "C:\\Ficheros-Excel\\" + nombreArchivo;
		String hoja = "mcDonald";//= "Hoja1";
		boolean valido= false;
		Integer cont=0; 
		Integer contFilas=0;
		Integer contCeldas=0;
		
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				contFilas= contFilas+1;
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					ValidarColumnas(contCeldas, cell);
					//System.out.print(cell.getStringCellValue()+" | ");
					contCeldas= contCeldas+1;
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		cont= contCeldas/contFilas;
		if((cont==5) &&(aux==5)){
			valido= true;
		}
		//System.out.println("cont: "+cont);
		System.out.println("valido: "+valido);
		return valido;
	}
	public static void ValidarColumnas(Integer cont, Cell cell){
		if((cont==0) && (cell.getStringCellValue().equals("local"))) {aux= aux+1; cont= cont+1;} 
		if((cont==1) && (cell.getStringCellValue().equals("Ubicacion"))) {aux= aux+1; cont= cont+1;} 
		if((cont==2) && (cell.getStringCellValue().equals("Producto"))) {aux= aux+1; cont= cont+1;} 
		if((cont==3) && (cell.getStringCellValue().equals("Precio"))) {aux= aux+1; cont= cont+1;} 
		if((cont==4) && (cell.getStringCellValue().equals("fechaDeVigencia"))) {aux= aux+1; cont= cont+1;} 
		
	}
	@SuppressWarnings({ "unused", "resource" })
	public static boolean validarExcel(String ruta){
		String rutaArchivo = ruta;
		String hoja = "mcDonald";
		boolean valido= false;
		Integer cont=0; 
		Integer contFilas=0;
		Integer contCeldas=0;
		
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				contFilas= contFilas+1;
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					ValidarColumnas(contCeldas, cell);
					//System.out.print(cell.getStringCellValue()+" | ");
					contCeldas= contCeldas+1;
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		cont= contCeldas/contFilas;
		if((cont==5) &&(aux==5)){
			valido= true;
		}
		//System.out.println("cont: "+cont);
		//System.out.println("valido: "+valido);
		return valido;
	}
	
	static { 
		LeerExcelXLSX();
    }
}