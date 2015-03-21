package com.src.scrum;
import java.io.*;
import org.json.*;
import java.util.*;
public class RetrieveProjectName {

	public static void main(String[] args) 
	{
	}
		
	public ArrayList<String> getNames()
	{
		
		ArrayList<String> projectNames= new ArrayList<String>();
		try{
		BufferedReader br=new BufferedReader(new FileReader("D:/scrumwise.json"));
		StringBuilder sb= new StringBuilder();
		String words=br.readLine();
		while(words!=null)
		{
			sb.append(words);
			words=br.readLine();
		}
		String json=sb.toString();
		JSONObject obj=new JSONObject(json);
		JSONObject all=obj.getJSONObject("result");
		JSONArray projects=all.getJSONArray("projects");
		for (int i=0;i<projects.length();i++)
		{
			JSONObject proj=projects.getJSONObject(i);		
			String name=proj.getString("name");
			projectNames.add(name);
				
		}
		
		System.out.println(projectNames);
	
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return projectNames;
	}

}
