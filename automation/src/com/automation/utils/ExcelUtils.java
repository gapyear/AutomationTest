package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONObject;

public class ExcelUtils {
	
	public static List<JSONObject> getData(String filePath) {
		
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell col = null;
		List<JSONObject> list = null;  
		List<String> args = null; 
		JSONObject data = null;
		//String filePath ="D:\\automation\\data.xlsx";
		
		wb = readExcel(filePath);
		
		if(wb != null){
			//用来存放表中的数据
			list = new ArrayList<JSONObject>();
			//获取第一个sheet
			sheet = wb.getSheetAt(0);
			//获取最大行
			int rownum = sheet.getPhysicalNumberOfRows();
			//row = sheet.getRow(0);
			int colnum = 0;
			
			for(int i=0;i<rownum;i++){
				
				row = sheet.getRow(i);
				if(row!=null){
					colnum = row.getPhysicalNumberOfCells();
					args = new ArrayList<String>();
					data = new JSONObject();
					for(int j=2;j<colnum;j++){
						col = row.getCell(j);
						//System.out.println(col.getStringCellValue());
						col.setCellType(CellType.STRING);
						args.add(col.getStringCellValue());
						//System.out.println(args);
					}
					data.put("description",row.getCell(0).getStringCellValue());
					data.put("keyword", row.getCell(1).getStringCellValue());
					data.put("args", args);
					
					
				}
				list.add(data);
			}			
			
		}
		
		return list;
		
	}

	private static Workbook readExcel(String filePath) {
		// TODO Auto-generated method stub
		Workbook wb = null;
		if(filePath == null){
			return null;
		}
		
		String extString = filePath.substring(filePath.lastIndexOf("."));
		
		InputStream is = null;
		
		try {
			is = new FileInputStream(filePath);
			if(".xls".equals(extString)){
				return wb = new HSSFWorkbook(is);
			}else if(".xlsx".equals(extString)){
				return wb = new XSSFWorkbook(is);
			}else{
				return wb = null;
			}
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		return wb;
		
	}
	
	/*public static void main(String[] args){
		
		List<JSONObject> data = getData();
		System.out.println(data);
	}
	
	*/

}
