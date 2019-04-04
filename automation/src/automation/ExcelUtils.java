package automation;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.File;
import java.io.FileInputStream;


public class ExcelUtils {
	//读取文件并返回workbook
	public static Workbook getWorkbook(String filepath) {
		try {
			if(filepath.endsWith("xls")) {
				File file = new File(filepath);
				FileInputStream fileInputStream = new FileInputStream(file);
				//System.out.println(fileInputStream.toString());
				Workbook workbook = new HSSFWorkbook(fileInputStream);
				return workbook;
			}else if(filepath.endsWith("xlsx")){
				File file = new File(filepath);
				FileInputStream fileInputStream = new FileInputStream(file);
				//System.out.println(fileInputStream.toString());
				Workbook workbook = new XSSFWorkbook(fileInputStream);
				return workbook;
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("File not found");
		}
		return null;
		
		
	}
	
	//读取workbook返回list
	public static List<List<String>> getContent(Workbook workbook){
		if(workbook == null) {
			return null;
		}
		
		List<List<String>> list = new ArrayList<List<String>>();
		Sheet sheet = workbook.getSheetAt(0);
		int rownum = sheet.getLastRowNum();
		for(int r=0;r<=rownum;r++) {
			List<String> row = new ArrayList<String>();
			int colnum = sheet.getRow(r).getPhysicalNumberOfCells();
			//System.out.println(colnum);
			Cell cell = null;
			String cellValue = "";
			for(int col=0;col<colnum;col++) {
				cell = sheet.getRow(r).getCell(col);
				int type = cell.getCellType();
				if(type==Cell.CELL_TYPE_NUMERIC){
					cellValue = String.valueOf(cell.getNumericCellValue());
				}else{
					cellValue = cell.getStringCellValue();
				}
				row.add(cellValue);
			}
			list.add(row);
		}
		return list;
	}
	
	public static List<List<String>> getListFromFile(String path){
		return getContent(getWorkbook(path));
	}
}
