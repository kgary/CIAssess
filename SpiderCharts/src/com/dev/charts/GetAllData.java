package com.dev.charts;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetAllData 
{
	public ArrayList getData(String inpstudentname)
	{	
		String teamname="";
		Double ind_code_activity=0.0;
		Double ind_project_activity=0.0;
		Double ind_team_activity=0.0;
		Double ind_module_activity=0.0;

		Double class_code_activity=0.0;
		Double class_project_activity=0.0;
		Double class_team_activity=0.0;
		Double class_module_activity=0.0;
		
		Double team_code_activity=0.0;
		Double team_project_activity=0.0;
		Double team_team_activity=0.0;
		Double team_module_activity=0.0;
		
		ArrayList all_data=new ArrayList();
		
	
		try
		{
				  FileInputStream fis = new FileInputStream(
			      new File("D:/Team_GitSheet.xlsx"));
			      XSSFWorkbook workbook = new XSSFWorkbook(fis);
			      XSSFSheet spreadsheet = workbook.getSheet("Sheet1");
			      int noofrows=spreadsheet.getLastRowNum();
			    //  System.out.println("no of rows is"+noofrows);
			      for (int i=1; i<=spreadsheet.getLastRowNum();i++)
			      {
			    	  Row row1 = spreadsheet.getRow(i);
			    	  Cell cell1=row1.getCell(0);
			    	  String name=cell1.toString();
			    	  if (name.equalsIgnoreCase(inpstudentname))
			    	  {	
			    		    Cell cell2=row1.getCell(1);
			    		   teamname=cell2.toString();
			    	  }
			      }
			      fis.close();
			      workbook.close();
			     
			      System.out.println("teamname "+teamname);
			    	
			    	
			    	 	
			    	 	// Getting data for the spider chart begins here
			    	 FileInputStream fis1 = new FileInputStream(new File("D:/Final.xlsx"));
			    	 XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
				      XSSFSheet spreadsheet1 = workbook1.getSheet(teamname);
				      System.out.println(workbook);
				      System.out.println(spreadsheet1);
				      String firstName[]=inpstudentname.split(" ");
				      int noofrows1=spreadsheet.getLastRowNum();
					      System.out.println("no of rows is"+noofrows1);
				     // System.out.println("fname is "+firstName[0]);
				     // System.out.println("fname is "+firstName[1]);
				      
				      for (int j=2; j<=spreadsheet1.getLastRowNum();j++)
				      {
				    	  Row currrow = spreadsheet1.getRow(j);
				    	  Cell  currcell = currrow.getCell(1);
				    	  if (currcell==null)
				    	  {
				    		  continue;
				    	  }
				    		
				    	  String currentCellVal=currcell.toString();
				    	  
				    	// System.out.println("current cell is "+currentCellVal);
				    	//  System.out.println(" File fname is "+currcell);
				    	  if(currentCellVal.equalsIgnoreCase(firstName[0]))
				    	  {			    		
				    		  System.out.println("I found him!");
				    		  ind_code_activity=(double)Math.round(currrow.getCell(19).getNumericCellValue()*100)/100;
				    		   ind_project_activity=(double)Math.round(currrow.getCell(20).getNumericCellValue()*100)/100;
				    		   ind_module_activity=(double)Math.round(currrow.getCell(21).getNumericCellValue()*100)/100;
				    		  // ind_team_activity=(double)Math.round(currrow.getCell(22).getNumericCellValue()*100)/100;
				    		  System.out.println("CA "+ind_code_activity);
				    		  System.out.println("PA "+ind_project_activity);
				    		  System.out.println("MA "+ind_module_activity);
				    		  continue;			    		  
				    	  }
				    	  if (currentCellVal.equalsIgnoreCase("Average"))
				    	  {
				    		  team_code_activity=(double)Math.round(currrow.getCell(2).getNumericCellValue()*100)/100;
				    		  team_project_activity=(double)Math.round(currrow.getCell(5).getNumericCellValue()*100)/100;
				    		  team_module_activity=(double)Math.round(currrow.getCell(6).getNumericCellValue()*100)/100;
				    		  team_team_activity=(double)Math.round(currrow.getCell(7).getNumericCellValue()*100)/100;
				    		  System.out.println("TCA "+team_code_activity);
				    		  System.out.println("TPA "+team_project_activity);
				    		  System.out.println("TMA "+team_module_activity);
				    		  System.out.println("TTA "+team_team_activity);
				    		  

				    		  continue;
				    	  }
				    	  if (currentCellVal.equalsIgnoreCase("Class_Avg"))
				    	  {
				    		  class_code_activity=(double)Math.round(currrow.getCell(2).getNumericCellValue()*100)/100;
				    		  class_project_activity=(double)Math.round(currrow.getCell(5).getNumericCellValue()*100)/100;
				    		  class_module_activity=(double)Math.round(currrow.getCell(6).getNumericCellValue()*100)/100;
				    		  class_team_activity=(double)Math.round(currrow.getCell(7).getNumericCellValue()*100)/100;
				    		  System.out.println("CCA "+class_code_activity);
				    		  System.out.println("CPA "+class_project_activity);
				    		  System.out.println("CMA "+class_module_activity);
				    		  System.out.println("CTA "+class_team_activity);
				    		  
				    		  continue;
				    	  }
				      }
				      

		}
		
		
		
		
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		all_data.add(ind_code_activity);
		all_data.add(ind_project_activity);
		all_data.add(ind_module_activity);
		all_data.add(team_team_activity);
		
		all_data.add(team_code_activity);
		all_data.add(team_project_activity);
		all_data.add(team_module_activity);
		all_data.add(team_team_activity);
		
		all_data.add(class_code_activity);
		all_data.add(class_project_activity);
		all_data.add(class_module_activity);
		all_data.add(class_team_activity);
		return all_data;
		//System.out.println(all_data);
	}
}
