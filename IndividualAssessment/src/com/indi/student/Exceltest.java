package com.indi.student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;
import java.io.*;
public class Exceltest {
	static XSSFRow row;
	public static void main(String[] args)
		{
		// TODO Auto-generated method stub
		String teamname="";
		String gitname="";
		try{
				FileInputStream fis = new FileInputStream(
			      new File("D:/Team_GitSheet.xlsx"));
			      XSSFWorkbook workbook = new XSSFWorkbook(fis);
			      XSSFSheet spreadsheet = workbook.getSheet("Sheet1");
			      for (int i=1; i<spreadsheet.getLastRowNum();i++)
			      {
			    	  Row row1 = spreadsheet.getRow(i);
			    	  Cell cell1=row1.getCell(0);
			    	  String name=cell1.toString();
			    	  if (name.equalsIgnoreCase("David Mierke"))
			    	  {
			    		  Cell cell2=row1.getCell(1);
			    		  Cell cell3=row1.getCell(2);
			    		   teamname=cell2.toString();
			    		   gitname=cell3.toString();
			    		   
			    	  }

			      }
			      System.out.println(teamname);
			    	 System.out.println(gitname);
			    		
			      
			      
			      
			      
		
	}
		catch (Exception e)
		{
			e.printStackTrace();
		}

}
}
