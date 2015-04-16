package com.indi.student;
import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.*;
public class RetrieveStudentNames {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		RetrieveStudentNames obj1= new RetrieveStudentNames();
		//System.out.println("test");
		obj1.getNames();

	}
	
		public ArrayList getNames()
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
					    	  Row row1 = spreadsheet.getRow(i);
					    	  Cell cell1=row1.getCell(0);
					    	  String name=cell1.toString();
					    		 students.add(name);  
					    	  
					      }
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return students;
		}
		
		
	}



