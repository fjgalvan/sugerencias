package conexiones.conexionExel;

import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mongodb.DBCollection;

import conexiones.Interfaz.InterfaceConectores;
import configurables.MyConstantsConexiones;
import promo.Excel.PromoExcel;
import properties.Constants;
import sugerencias.SugerenciaExcel;
import util_.Date;
 
public class LeerFicherosExcel implements InterfaceConectores{
	public LeerFicherosExcel(){
		
	}
	static Integer aux=0;
	private static List<String> listaCeldasExcel;
	@Override
	public void conectarse() {
		LeerExcelXLSX();
		listaCeldasExcel= new ArrayList<String>();
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static List<String> LeerExcelXLSX() {
		String rutaArchivo= Constants.ROUTE_EXCEL_xlsx;
		String hoja = MyConstantsConexiones.hojaExcelXlsx;
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			XSSFSheet sheet = worbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					//System.out.print(cell.getStringCellValue()+" | ");
					listaCeldasExcel.add(cell.getStringCellValue());
				}//System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		validarExcel();
		return listaCeldasExcel;
	}
	
	
	
	@SuppressWarnings({ "unused", "resource" })
	public static boolean validarExcel(){
		String nombreArchivo = MyConstantsConexiones.nombreArchivoExcelXlsx;
		String rutaArchivo =  MyConstantsConexiones.rutaArchivoExcelXlsx+ nombreArchivo;
		String hoja = MyConstantsConexiones.hojaExcelXlsx;
		boolean valido= false;
		Integer cont=0; 
		Integer contFilas=0;
		Integer contCeldas=0;
		
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			XSSFSheet sheet = worbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			while (rowIterator.hasNext()) {
				contFilas= contFilas+1;
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					ValidarColumnas(contCeldas, cell);
					//System.out.print(cell.getStringCellValue()+" | ");
					contCeldas= contCeldas+1;
				}
			}
		} catch (Exception e) {e.getMessage();
		}
		cont= contCeldas/contFilas;
		if((cont==6) &&(aux==6)){valido= true;}
		return valido;
	}
	public static void ValidarColumnas(Integer cont, Cell cell){
		if((cont==0) && (cell.getStringCellValue().equals(MyConstantsConexiones.promoLocal))) {aux= aux+1; cont= cont+1;} 
		if((cont==1) && (cell.getStringCellValue().equals(MyConstantsConexiones.promoUbicacion))) {aux= aux+1; cont= cont+1;} 
		if((cont==2) && (cell.getStringCellValue().equals(MyConstantsConexiones.promoProducto))) {aux= aux+1; cont= cont+1;} 
		if((cont==3) && (cell.getStringCellValue().equals(MyConstantsConexiones.promoPrecio))) {aux= aux+1; cont= cont+1;} 
		if((cont==4) && (cell.getStringCellValue().equals(MyConstantsConexiones.promoFechaDeVigencia))) {aux= aux+1; cont= cont+1;} 
		if((cont==5) && (cell.getStringCellValue().equals(MyConstantsConexiones.promolComidas))) {aux= aux+1; cont= cont+1;}
	}
	@SuppressWarnings({ "unused", "resource" })
	public static boolean validarExcel(String ruta){
		String rutaArchivo = ruta;
		String hoja = MyConstantsConexiones.hojaExcelXlsx;
		boolean valido= false;
		Integer cont=0; 
		Integer contFilas=0;
		Integer contCeldas=0;
		
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			XSSFSheet sheet = worbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			while (rowIterator.hasNext()) {
				contFilas= contFilas+1;
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					ValidarColumnas(contCeldas, cell);
					//System.out.print(cell.getStringCellValue()+" | ");
					contCeldas= contCeldas+1;
				}
			}
		} catch (Exception e) {e.getMessage();}
		cont= contCeldas/contFilas;
		if((cont==6) &&(aux==6)){valido= true;}
		return valido;
	}
	
	static { 
		LeerExcelXLSX();
    }

	@Override
	public DBCollection getPromo(DBCollection promosViejas) {
		PromoExcel pe= new PromoExcel();
		pe.leerPromo(promosViejas);
		return pe.getCollection();
	}
}