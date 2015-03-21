package com.src.scrum;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.annotation.WebServlet;

import org.json.JSONArray;
import org.json.JSONObject;


public class GetData {
	private String 	firstName="";

	public Hashtable fetchData(String username)
	{
		
		String teamarray[]= new String[5];
		String projectarray[]=new String[10];
		String taskarray[]= new String[30];
		String statusarray[]=new String[10];
		
		HashMap<String, HashMap<String, String>> hasher= new HashMap();
		firstName=username;
		String json="";
		//String fname="Sylvia";
		String idty="";
		String projectname="";
		String projectid="";
		String teamname="";
		try
		{
			BufferedReader br=new BufferedReader(new FileReader("D:/scrumwise.json"));
			String words=br.readLine();
			StringBuilder sb=new StringBuilder();
			while (words!=null)
			{
				sb.append(words);
				sb.append(System.lineSeparator());
				words=br.readLine();
			}
			json=sb.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
		JSONObject allData=new JSONObject(json);
		JSONObject allResult= allData.getJSONObject("result");
		JSONArray allPersons= allResult.getJSONArray("persons");
		int x1=0;
		int x2=0;
		int x3=0;
		int x4=0;
		
		
		for (int x=0;x<allPersons.length();x++)
		{
			JSONObject temp=allPersons.getJSONObject(x);
			String fname1=temp.getString("firstName");
			String lname=temp.getString("lastName");
			String mail=temp.getString("emailAddress");
			 idty=temp.getString("id");
			if(firstName.equals(fname1))
			{
				//System.out.println("Sylvia found");
				//System.out.println("Syl id" +idty);
				
				
				break;
			}
		}
		JSONArray arr=allResult.getJSONArray("projects");
		for (int i=0; i<arr.length();i++)
		{
			JSONObject obj=arr.getJSONObject(i);
			projectname=obj.getString("name");
			projectid=obj.getString("id");
			//String id=obj.getString("id");
			JSONArray json4=obj.getJSONArray("teams");
			//System.out.println(json4);
			for (int j=0 ; j<json4.length();j++)
			{
				JSONObject obj1=json4.getJSONObject(j);
				 teamname=obj1.getString("name");
				
				JSONArray json5=obj1.getJSONArray("teamMemberIDs");
				//System.out.println(json5.length());
				for (int k=0; k<json5.length();k++)
				{
					String personids=json5.getString(k);
					//System.out.println(k);
					if (personids.equalsIgnoreCase(idty))
					{
						//System.out.println("her project found!");
						//System.out.println("Her team name is " +teamname);
						teamarray[x1]=teamname;
						//System.out.println("her project name is" +projectname);
						projectarray[x2]=projectname;
						//System.out.println("her project id is" +projectid);
						
						
						x1=x1+1;
						x2=x2+1;
						break;
					}

				}
				
			}
			
			JSONArray assigned=obj.getJSONArray("backlogItems");
			for (int y=0;y<assigned.length();y++)
			{
				JSONObject temp12=assigned.getJSONObject(y);
				String bcktaskname=temp12.getString("name");
				String status=temp12.getString("status");
				JSONArray assigned1=temp12.getJSONArray("assignedPersonIDs");
				for(int z=0;z<assigned1.length();z++)
				{
					String assigned_per=assigned1.getString(z);
					if (assigned_per.equalsIgnoreCase(idty))
					{
						System.out.println("her tasks are!" +bcktaskname);
						//taskarray[x3]=bcktaskname;
						//System.out.println("the work status is" +status);
						//[x4]=pending;
						x3++;
						x4++;
						
						hasher.put("Tasks",new HashMap<String, String>(){{put(bcktaskname,status);}});
					}
				}
					
			}
					
			

			
		}
		//System.out.println("her tasks are!" +taskarray[0]);
		//System.out.println("her project status is!" +statusarray[0]);
		//System.out.println("her tasks are!" +teamarray[0]);
		//System.out.println("her projects are!" +projectarray[0]);
		/*System.out.println(taskarray.length);
		
		for (int m=0;m<taskarray.length;m++)
		{
			if(taskarray[m]!=null)
			{
		System.out.println(taskarray[m]);
			}
		}
		
		System.out.println(arr.length());
		
		*/
		//hasher.put("Name", firstName);
		//hasher.put("Project Name", projectname);
		//hasher.put("Team Name", teamname);
		System.out.println(hasher);
		JSONObject finalobj=new JSONObject(hasher);
		FileWriter file=new FileWriter("D:/takeone1.json");
		try{
			file.write(finalobj.toString());
			//finalobj.write(file);
			file.flush();
			file.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Hashtable ht = new Hashtable();
		ht.put("name", username);
		ht.put("projectName", projectname);
		ht.put("taskName", taskarray);
		ht.put("taskStatus", statusarray);
		
		return ht;
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}

}
