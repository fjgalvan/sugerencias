package conexiones.conexionExel;


import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.DBCollection;

import dao.mongoDB.MyConstants;

public class  ExcelParserJSON{
	static DBCollection collection;
	
//	public static void main(String[] args) {
//		String ej= ExcelJSONformat();
//		System.out.println(ej);
//	}

	public static String ExcelJSONformat() {
		File input = new File(MyConstants.input_excel);
		JsonObject jo = null;
		
		try {
			jo=getExcelDataAsJsonObject(input);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(jo.toString());
	}

@SuppressWarnings({ "resource", "deprecation" })
public static JsonObject getExcelDataAsJsonObject(File excelFile) throws InvalidFormatException {

    JsonObject sheetsJsonObject = new JsonObject();
    Workbook workbook = null;

    try {
        workbook = new XSSFWorkbook(excelFile);
    }
    catch(IOException e){
    	
    }

    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

        JsonArray sheetArray = new JsonArray();
        ArrayList<String> columnNames = new ArrayList<String>();
        Sheet sheet = workbook.getSheetAt(i);
        Iterator<Row> sheetIterator = sheet.iterator();

        while (sheetIterator.hasNext()) {

            Row currentRow = sheetIterator.next();
            JsonObject jsonObject = new JsonObject();

            if (currentRow.getRowNum() != 0) {

                for (int j = 0; j < columnNames.size(); j++) {

                    if (currentRow.getCell(j) != null) {
                        if (currentRow.getCell(j).getCellTypeEnum() == CellType.STRING) {
                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getStringCellValue());
                        } else if (currentRow.getCell(j).getCellTypeEnum() == CellType.NUMERIC) {
                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getNumericCellValue());
                        } else if (currentRow.getCell(j).getCellTypeEnum() == CellType.BOOLEAN) {
                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getBooleanCellValue());
                        } else if (currentRow.getCell(j).getCellTypeEnum() == CellType.BLANK) {
                            jsonObject.addProperty(columnNames.get(j), "");
                        }
                    } else {
                        jsonObject.addProperty(columnNames.get(j), "");
                    }
                }
                sheetArray.add(jsonObject);
            } else {
                // store column names
                for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
                    columnNames.add(currentRow.getCell(k).getStringCellValue());
                }
            }
        }
        sheetsJsonObject.add(workbook.getSheetName(i), sheetArray);
    }
    return sheetsJsonObject;

	}




	
}