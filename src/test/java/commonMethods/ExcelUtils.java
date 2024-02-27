package commonMethods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;  
	public static CellStyle style;   

	
	public static String[][] getCellData(String xlfile,String xlsheet) throws IOException
	{
		
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		
		int totalrows=ws.getLastRowNum();
		int totalcells=ws.getRow(1).getLastCellNum();
		
		System.out.println(totalrows +"  "+ totalcells);
		
		String[][] data = new String [totalrows][totalcells];
		DataFormatter dataFormat = new DataFormatter();
		
		for(int r=1;r<=totalrows;r++)
		{
			XSSFRow currentRow=ws.getRow(r);
					
			for(int c=0;c<totalcells;c++)
			{
				XSSFCell cell = currentRow.getCell(c);
				data[r-1][c] = dataFormat.formatCellValue(cell);
			}
//			System.out.println();
		}
		
		wb.close();
		fi.close();
		return data;
	}
	
	public static List<String> getExpectedData(String xlfile,String xlsheet) throws IOException {
		
		List<String> expectedResultsFromExcel = new ArrayList<String>();
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		
		int totalrows=ws.getLastRowNum();
		int totalcells=ws.getRow(1).getLastCellNum();
				
		DataFormatter dataFormat = new DataFormatter();
		
		for(int r=1;r<=totalrows;r++)
		{
			XSSFRow currentRow=ws.getRow(r);
			
			XSSFCell cell = currentRow.getCell(2);
			expectedResultsFromExcel.add(dataFormat.formatCellValue(cell));
			
		}
		
		wb.close();
		fi.close();
		return expectedResultsFromExcel;
	}

	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi = new FileInputStream(xlfile);

		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);

		XSSFRow row = ws.getRow(rownum);

		if (row == null) {
			// Create the row if it does not exist
			row = ws.createRow(rownum);
		}

		XSSFCell cell = row.getCell(colnum);

		if (cell == null) {
			// Create the cell if it does not exist
			cell = row.createCell(colnum);
		}
		cell.setCellValue(data);
		fi.close();

		fo = new FileOutputStream(xlfile);

		wb.write(fo);
		wb.close();
		fo.close();
				
	}
	
	
	public static void fillGreenColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		
		if (row == null) {
			// Create the row if it does not exist
			row = ws.createRow(rownum);
		}
		
		cell=row.getCell(colnum);
		
		if (cell == null) {
			// Create the cell if it does not exist
			cell = row.createCell(colnum);
		}
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
				
		cell.setCellStyle(style);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	public static void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		
		if (row == null) {
			// Create the row if it does not exist
			row = ws.createRow(rownum);
		}
		cell=row.getCell(colnum);
		
		if (cell == null) {
			// Create the cell if it does not exist
			cell = row.createCell(colnum);
		}
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		cell.setCellStyle(style);		
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
}
