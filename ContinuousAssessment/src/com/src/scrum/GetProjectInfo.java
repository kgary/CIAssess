package com.src.scrum;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.*;
public class GetProjectInfo {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//GetProjectInfo obj=new GetProjectInfo();
		//obj.getData("CST316 Entrepreneurship A");
	}
	
	public ArrayList getData(String inpProjectName)
	{
		int team_count=0;
		ArrayList teammembers=new ArrayList();
		Enumeration pnames; 
		Hashtable ht=new Hashtable();
		String sprintId="";
		ht.put("CST316 Entrepreneurship A", "27628-7064-1");
		ht.put ("CST316 Gamers C", "27628-7083-1");
		ht.put ("CST316 Gamers D", "27628-6910-1");
		ht.put ("CST316 Embedded E", "27628-6443-7");
		ht.put ("CST316 Entrepreneurship F", "27628-6220-29");
		ht.put ("CST316 Sports G", "27628-7052-1");
		ht.put ("CST316 Entrepreneurship I", "27628-7071-1");
		ht.put ("CST316 Sports J", "27628-7054-12");
		ht.put ("CST316 FAF", "27628-6917-1");
		
		int done_count=0;
		int inprogress_count=0;
		int totest_count=0;
		int todo_count=0;
		
		ArrayList dates=new ArrayList();
		dates.add("\"Date\""); 
		
		ArrayList allResult = new ArrayList();
		
		ArrayList toDo = new ArrayList();
		toDo.add("\"To Do\"");
		
		
		ArrayList inProgress = new ArrayList();
		inProgress.add("\"In Progress\"");
		
		ArrayList toTest = new ArrayList();
		toTest.add("\"To Test\"");
				
		ArrayList done = new ArrayList();
		done.add("\"Done\"");
		
		File dir= new File("D:/Cron_April/");
		File files[]=dir.listFiles();
		Arrays.sort(files);
		for (File f: files)
		{
			String filename=f.getName();
			System.out.println("First file is: " + filename);			
			try{
			BufferedReader br= new BufferedReader(new FileReader(f));
			//System.out.println("filename now "+filename);
			StringBuilder sb= new StringBuilder();
			String word=br.readLine();			
			while(word!=null)
			{
				sb.append(word);
				word=br.readLine();				
			}
			String final_word=sb.toString();
			JSONObject obj=new JSONObject(final_word);
			JSONObject first=obj.getJSONObject("result");
			JSONArray all_projects=first.getJSONArray("projects");
			for (int i=0;i<all_projects.length();i++)
			{
				String sprint="";
				JSONObject obj1=all_projects.getJSONObject(i);
				String projName=obj1.getString("name");
				//System.out.println("proj name is" +projName);
				if (projName.equalsIgnoreCase(inpProjectName))
				{	
					pnames=ht.keys();
					while(pnames.hasMoreElements())
					{
						String str=(String)pnames.nextElement();
						if (inpProjectName.equalsIgnoreCase(str))
						{
					
							sprintId=(String)ht.get(str);
						}
						System.out.println("sprint id is "+sprintId);
					}
					JSONArray bckItems=obj1.getJSONArray("backlogItems");
					for (int j=0;j<bckItems.length();j++)
						{
							JSONObject obj2=bckItems.getJSONObject(j);
							//String bkcitemstatus=obj2.getString("status");
							try{
							 sprint=obj2.getString("sprintID");
							}
							catch (Exception e)
							{
								//System.out.println("null spr id, moving on");
							}
							if (sprint.equalsIgnoreCase(sprintId))
							{
							JSONArray tasks=obj2.getJSONArray("tasks");
							for (int x=0;x<tasks.length();x++)
							{
								JSONObject task_obj=tasks.getJSONObject(x);
								String task_status=task_obj.getString("status");
								String bckItemName=task_obj.getString("name");
							//	System.out.println("item name"+bckItemName);
							//	System.out.println("its status"+task_status);
							//	System.out.println("this count is imp "+done_count);
								if (task_status.equalsIgnoreCase("Done"))
								{
									done_count=done_count+1;
								}
								if (task_status.equalsIgnoreCase("To do"))						
								{
									todo_count=todo_count+1;
								}
								if (task_status.equalsIgnoreCase("To test"))
								{
									totest_count=totest_count+1;
								}
								if (task_status.equalsIgnoreCase("In progress"))
								{
									inprogress_count=inprogress_count+1;
								}
								
								
						}
						}
						
					}
					//System.out.println(done_count);
					//System.out.println(todo_count);
					//System.out.println(totest_count);
					//System.out.println(inprogress_count);
					filename = filename.replace(".json", "");
					dates.add("\""+filename+"\"");
					done.add(done_count);
					toDo.add(todo_count);
					inProgress.add(inprogress_count);
					toTest.add(totest_count);
					done_count=0;
					todo_count=0;
					totest_count=0;
					inprogress_count=0;	
				}

			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		allResult.add(dates);
		allResult.add(toDo);
		allResult.add(inProgress);
		allResult.add(toTest);	
		allResult.add(done);

			}
		return allResult;
	}

}
