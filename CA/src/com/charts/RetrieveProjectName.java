package com.charts;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RetrieveProjectName 
{
	public ArrayList<String> getNames()
	{
		

		ArrayList projects=new ArrayList();
		try
		{
			FileInputStream fis = new FileInputStream(
				      new File("D:/Team_GitSheet.xlsx"));
				      XSSFWorkbook workbook = new XSSFWorkbook(fis);
				      XSSFSheet spreadsheet = workbook.getSheet("Sheet1");
				      int noofrows=spreadsheet.getLastRowNum();
				      System.out.println("no of rows is"+noofrows);
				      for (int i=1; i<=spreadsheet.getLastRowNum();i++)
				      {
				    	  XSSFRow row1 = spreadsheet.getRow(i);
				    	  XSSFCell cell1=row1.getCell(3);
				    	  String name=cell1.toString();
				    	  if (!projects.contains(name))
				    	  {
				    	  projects.add(name);  
				    	  }
				          
				    	  
				      }
				      System.out.println(projects);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return projects;
	}
}
