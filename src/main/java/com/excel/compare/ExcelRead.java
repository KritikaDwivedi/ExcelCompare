package com.excel.compare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelRead {
	static String path;
	static FileInputStream fIP;
    static XSSFWorkbook workBook;
    static XSSFSheet spreadsheet;
    static Row row;
    static Iterator<String> s;
    static Cell cell;
    static String cellData;
    static List<String> data;
    static String sheetName ;
    public static ArrayList<String> testIdNumber=new ArrayList<String>();
    final static String  testIdColumnName="TestId";
    static LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> outerMap;
	public static ArrayList<String> tags= new ArrayList<String>();
	static String cellValue;
	public static LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> loadExcelFileData(String path)
    {
		
        // Used the LinkedHashMap and LikedList to maintain the order
        outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();

        LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
        
        // Create an ArrayList to store the data read from excel sheet.
        // List sheetData = new ArrayList();
        String fileSeparator=System.getProperty("file.separator");
       
        Path fPath=Paths.get(path.replace("\\", fileSeparator));
       
        try
        {
        	File fileName = fPath.toAbsolutePath().toFile();
        	fIP = new FileInputStream(fileName);
            // Create an excel workbook from the file system
            workBook = new XSSFWorkbook(fIP);
   
            
            // Get the first sheet on the workbook.
            for (int i = 0; i < workBook.getNumberOfSheets(); i++)
            {
                XSSFSheet sheet = workBook.getSheetAt(i);
                // XSSFSheet sheet = workBook.getSheetAt(0);
                sheetName = workBook.getSheetName(0);
                XSSFCellStyle my_style = workBook.createCellStyle(); 
         
                my_style.setBorderLeft(BorderStyle.THIN);
                my_style.setBorderRight(BorderStyle.THIN);
                my_style.setBorderTop(BorderStyle.THIN);
                my_style.setBorderBottom(BorderStyle.THIN);
                my_style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                my_style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                my_style.setRightBorderColor(IndexedColors.BLACK.getIndex());
                my_style.setTopBorderColor(IndexedColors.BLACK.getIndex());
               
                Iterator<Row> rows = sheet.iterator();
                while (rows.hasNext())
                {
                     row = rows.next();
                    Iterator<Cell> cells = row.iterator();

                    data = new LinkedList<String>();
                 
                    while (cells.hasNext())
                    {                       	
					hashMap.put(row.getRowNum(), data);
                              	
                        cell =  cells.next();
                        cell.setCellStyle(my_style); 
                        switch(cell.getCellType()) {
                        case BOOLEAN: 
                        String b=String.valueOf(cell.getBooleanCellValue()); 
                        data.add(b); 
                        break; 
                        case NUMERIC: 
                         String values =String.valueOf((int)cell.getNumericCellValue()); 
                         data.add(values);
                         break; 
                        case STRING: 
                        String s=cell.getStringCellValue(); 
                        data.add(s); 
                        break;
                        case FORMULA: 
                         String f=cell.getStringCellValue(); 
                            data.add(f);
                             break;
                        case BLANK: 
                            String g="NA"; 
                               data.add(g);
                                break;
						default:
							break;
                        
                        }
                        
                    }
					 
                    // sheetData.add(data);
                 
                }
                outerMap.put(sheetName, hashMap);
                System.out.println("Reading Excel file.................................");
                System.out.println("Reading Excel file......................................");
                hashMap = new LinkedHashMap<Integer, List<String>>();
                System.out.println("loading excel file data from excel file named: "+ fileName.getName().toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fIP != null)
            {
                try
                {
                    fIP.close();
                    System.out.println("Excel file is closed..... " );
                    File fileName = fPath.toAbsolutePath().toFile();
                    FileOutputStream fileOut = new FileOutputStream(fileName);
                    workBook.write(fileOut);
                    fileOut.close();

                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        return outerMap;

    }
}
