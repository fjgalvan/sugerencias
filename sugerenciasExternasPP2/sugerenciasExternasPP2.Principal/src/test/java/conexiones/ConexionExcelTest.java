package conexiones;

import org.junit.Test;

import conexiones.conexionExel.ExcelParserJSON;
import conexiones.conexionExel.LeerExcelCSV;
import conexiones.conexionExel.LeerFicherosExcel;
import configurables.MyConstantsConexiones;

public class ConexionExcelTest {
	@Test
    public void excelcsvTest(){
		LeerExcelCSV csv= new LeerExcelCSV();
		csv.conectarse();
		csv.getNextLine();
	}
	
	@Test
    public void excelparserjsonTest(){
		ExcelParserJSON.ExcelJSONformat();
	}
	
	@Test
    public void excelxslxTest(){
		LeerFicherosExcel xlsx= new LeerFicherosExcel();
		xlsx.conectarse();
		LeerFicherosExcel.LeerExcelXLSX();
		LeerFicherosExcel.validarExcel();//MyConstantsConexiones.rutaArchivoExcelXlsx
		String nombreArchivo = MyConstantsConexiones.nombreArchivoExcelXlsx;
		String rutaArchivo =  MyConstantsConexiones.rutaArchivoExcelXlsx+ nombreArchivo;
		LeerFicherosExcel.validarExcel(rutaArchivo);
	}
}
