package com.indi.student;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.json.*;
public class GetGitSWData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GetGitSWData obj=new GetGitSWData();
		//obj.getAllData("Sumit Nair");
		
	}
	public ArrayList getAllData(String inpstudentname)
	{
		String teamname="";
		String gitname="";
		ArrayList dates=new ArrayList();
		dates.add("\"Date\"");
		ArrayList commits=new ArrayList();
		commits.add("\"Commits\"");
		ArrayList additions=new ArrayList();
		additions.add("\"Lines of code added\"");
		ArrayList deletions=new ArrayList();
		deletions.add("\"Lines of code deleted\"");
		ArrayList allResult=new ArrayList();

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
				    	  if (name.equalsIgnoreCase(inpstudentname))
				    	  {	
				    		  
				    		  Cell cell2=row1.getCell(1);
				    		  Cell cell3=row1.getCell(2);
				    		   teamname=cell2.toString();
				    		   gitname=cell3.toString();   
				    	  }
				      }
				      System.out.println("teamname "+teamname);
				    	 System.out.println(gitname);
				    	
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
				    				String all_data=sb.toString();
				    				System.out.println(all_data);
				    				JSONArray root= new JSONArray(all_data);
				    				int count=0;
				    				for (int i = 0; i < root.length(); i++) {			
				    					JSONObject weekData = root.getJSONObject(i);
				    					for (int j=0;j<weekData.length();j++)
				    					{
				    						JSONObject author=weekData.getJSONObject("author");
				    						String name=author.getString("login");
				    						if (name.equalsIgnoreCase(gitname))
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
				    		
				    	
				    	 
		//BufferedReader br=new BufferedReader(new FileReader("D:/team_a.json"));
		
		
		//JSONObject json=new JSONObject(all_data);
	

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		allResult.add(dates);
        allResult.add(commits);
        allResult.add(additions);
        allResult.add(deletions);
       return allResult;
	}

}
