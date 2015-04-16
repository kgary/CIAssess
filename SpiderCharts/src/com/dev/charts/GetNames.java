package com.dev.charts;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.scene.control.Cell;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.rowset.internal.Row;

public class GetNames 
{

	public ArrayList getListNames()
	{	
		ArrayList students=new ArrayList();
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
				    	  XSSFCell cell1=row1.getCell(0);
				    	  String name=cell1.toString();
				    		 students.add(name);  
				    		// System.out.println(name);
				    	  
				      }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return students;
	}
}
