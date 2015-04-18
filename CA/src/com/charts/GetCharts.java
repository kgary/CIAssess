package com.charts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCharts 
{
	public ArrayList createChartData(String posting_id)
	{
		String teamname="";
		String name="";
		String gitname="";
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
		
		ArrayList dates=new ArrayList();
		dates.add("\"Date\"");
		
		ArrayList commits=new ArrayList();
		commits.add("\"Commits\"");
		
		ArrayList additions=new ArrayList();
		additions.add("\"Lines of code added\"");
		
		ArrayList deletions=new ArrayList();
		deletions.add("\"Lines of code deleted\"");
		try
		{  
				  InputStream is = this.getClass().getClassLoader().getResourceAsStream("Team_GitSheet.xlsx");
			      XSSFWorkbook workbook = new XSSFWorkbook(is);
			      XSSFSheet spreadsheet = workbook.getSheet("Sheet1");
			      int noofrows=spreadsheet.getLastRowNum();
			    //  System.out.println("no of rows is"+noofrows);
			      for (int i=1; i<=spreadsheet.getLastRowNum();i++)
			      {
			    	  Row row1 = spreadsheet.getRow(i);
			    	  Cell cell1=row1.getCell(4);
			    	  System.out.println("does it match"+cell1);
			    	  System.out.println("inpvalue"+posting_id);
			    	  String id=cell1.toString();
			    	  if (id.equalsIgnoreCase(posting_id))
			    	  {	
			    		    Cell cell2=row1.getCell(1);
			    		    Cell cell3=row1.getCell(0);
			    		    Cell cell4=row1.getCell(2);
			    		   teamname=cell2.toString();
			    		   name=cell3.toString();
			    		   gitname=cell4.toString();
			    		   
			    		   System.out.println("teamname "+teamname);
						      System.out.println("name "+name);
						      System.out.println("git "+gitname);
			    	  }
			      }
			      
			      workbook.close();
			      
			    	 	// Getting data for the spider chart begins here
			      InputStream fis1 = this.getClass().getClassLoader().getResourceAsStream("Final.xlsx");
			    	 //FileInputStream fis1 = new FileInputStream(new File("Final.xlsx"));
			    	 XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
				      XSSFSheet spreadsheet1 = workbook1.getSheet(teamname);
				      System.out.println(workbook);
				      System.out.println(spreadsheet1);
				      String firstName[]=name.split(" ");
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
				      
				    	 File file=new File("D:/Cron_Git/");
				    	File dir[]=file.listFiles();
				    	for (File allfiles : dir)
				    	{
				    		String gitfilename=allfiles.getName();
				    		System.out.println("am here");
				    		if (gitfilename.endsWith(teamname + ".json"))
				    		{
				    			System.out.println("now here");
				    			BufferedReader br=new BufferedReader(new FileReader("D:\\Cron_Git\\"+gitfilename));
				    			StringBuilder sb= new StringBuilder();
				    			String words=br.readLine();
				    			while(words!=null)
				    			{
				    				sb.append(words);
				    				words=br.readLine();
				    			}	
				    				String all_vals=sb.toString();
				    				System.out.println(all_vals);
				    				JSONArray root= new JSONArray(all_vals);
				    				int count=0;
				    				for (int i = 0; i < root.length(); i++) {			
				    					JSONObject weekData = root.getJSONObject(i);
				    					for (int j=0;j<weekData.length();j++)
				    					{
				    						JSONObject author=weekData.getJSONObject("author");
				    						String username=author.getString("login");
				    						if (username.equalsIgnoreCase(gitname))
				    						{
				    							JSONArray weeks=weekData.getJSONArray("weeks");			
				    							for (int k=0;k<weeks.length();k++)
				    							{
				    								JSONObject gitData=weeks.getJSONObject(k);
				    								int currentWeek=gitData.getInt("w");
				    								int adds=gitData.getInt("a");
				    								//adds=adds/100;
				    								int dels=gitData.getInt("d");
				    								//dels=dels/100;
				    								int commit=gitData.getInt("c");
				    								
				    								System.out.println("add is "+adds);
				    								System.out.println("del is "+dels);
				    								System.out.println("co is "+commit);
				    								Date date = new Date(currentWeek*1000L);
				    						        SimpleDateFormat format = new SimpleDateFormat("MMM/dd");
				    						        String formatted = format.format(date);
				    						        System.out.println("week is "+formatted);
				    						        dates.add("\""+formatted+"\"");
				    						        commits.add(commit);
				    						        additions.add(adds);
				    						        deletions.add(dels);
				    							}
				    						
				    						break;
				    						}
				    					}
				    				}
				    				
				    				
				    			}
				    		}
				    	
						
						all_data.add(dates);
						all_data.add(commits);
						all_data.add(additions);
						all_data.add(deletions);
						
						
		}
		
		
		
		
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
		System.out.println(all_data);
		return all_data;	
		
	}
}
