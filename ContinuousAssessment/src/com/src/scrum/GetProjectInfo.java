package com.src.scrum;
import java.io.*;
import java.util.ArrayList;

import org.json.*;
public class GetProjectInfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ArrayList getData(String projectName)
	{
		String projectNames=projectName;
		File dir= new File("D:/Cron/");
		File files[]=dir.listFiles();
		int done_count=0;
		int inprogress_count=0;
		int totest_count=0;
		int todo_count=0;
		int new_count=0;
		double done_work=0;
		double inprogress_work=0;
		double totest_work=0;
		double todo_work=0;
		double new_work=0;
		//double remaining_work=0;
		
		ArrayList dates=new ArrayList();
		dates.add("\"Date\"");
		
		ArrayList allResult = new ArrayList();
		
		ArrayList newTask = new ArrayList();
		newTask.add("\"New Task\"");
		
		ArrayList toDo = new ArrayList();
		toDo.add("\"To do\"");
		
		ArrayList inProgress = new ArrayList();
		inProgress.add("\"In Progress\"");
		
		ArrayList toTest = new ArrayList();
		toTest.add("\"To Test\"");
		
		
		ArrayList done = new ArrayList();
		done.add("\"Done\"");	
		
		ArrayList work_inprogress = new ArrayList();
		work_inprogress.add("\"Remaining hours for In Progress tasks\"");
		
		ArrayList work_totest = new ArrayList();
		work_totest.add("\"Remaining hours for To Test tasks\"");
		
		for (File f: files)
		{
			String filename=f.getName();
			
			try{
			BufferedReader br= new BufferedReader(new FileReader(f));
			
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
			//JSONObject persons=first.getJSONObject("persons");
			JSONArray all_projects=first.getJSONArray("projects");
			for (int i=0;i<all_projects.length();i++)
			{
				JSONObject obj1=all_projects.getJSONObject(i);
				String projName=obj1.getString("name");
				
				//System.out.println(projName);
				if (projName.equalsIgnoreCase(projectName))
				{
				//	String date= filename;
					JSONArray bckItems=obj1.getJSONArray("backlogItems");
					for (int j=0;j<bckItems.length();j++)
						{
							JSONObject obj2=bckItems.getJSONObject(j);
							String task_status=obj2.getString("status");
							String bckItemName=obj2.getString("name");
							double remaining_work=obj2.getDouble("remainingWork");
							//System.out.println(bckItemName);
							//System.out.println(task_status);
							if (task_status.equalsIgnoreCase("Sprint completed") || task_status.equalsIgnoreCase("Ready for sprint"))
							{
								done_count=done_count+1;
								//done_work=done_work+remaining_work;
								//break;
							}
							if (task_status.equalsIgnoreCase("To do"))						
							{
								todo_count=todo_count+1;
								//todo_work=todo_work+remaining_work;
							}
							if (task_status.equalsIgnoreCase("To test"))
							{
								totest_count=totest_count+1;
								totest_work=totest_work+remaining_work;
							}
							if (task_status.equalsIgnoreCase("In progress"))
							{
								inprogress_count=inprogress_count+1;
								inprogress_work=inprogress_work+remaining_work;
							}
							if (task_status.equalsIgnoreCase("Ready for estimation") || task_status.equalsIgnoreCase("New") || task_status.equalsIgnoreCase("Assigned to sprint"))
							{
								new_count=new_count+1;
								//new_work=new_work+remaining_work;
							}
						}
						break;
						
					}
									
				}
			filename = filename.replace(".json", "");
			dates.add("\""+filename+"\"");
			System.out.println(filename);
			System.out.println("Count of Done tasks in this project are " + done_count);
			System.out.println("Count of Done remaining work in this project are " + done_work);
			done.add(done_count);
			
			
			System.out.println("Count of To DO tasks in this project are " + todo_count);
			System.out.println("Count of to do remaining work in this project are " + todo_work);
			toDo.add(todo_count);
			
			
			System.out.println("Count of TO test tasks in this project are " + totest_count);
			System.out.println("Count of To test remaining work in this project are " + totest_work);
			toTest.add(totest_count);
			work_totest.add(totest_work);
			
			
			System.out.println("Count of In Progress tasks in this project are " + inprogress_count);
			System.out.println("Count of in progress remaining work in this project are " + inprogress_work);
			inProgress.add(inprogress_count);
			work_inprogress.add(inprogress_work);
			
			System.out.println("Count of New tasks in this project are " + new_count);
			System.out.println("Count of new remaining work in this project are " + new_work);
			newTask.add(new_count);
			
			done_count=0;
			todo_count=0;
			totest_count=0;
			inprogress_count=0;
			new_count=0;
			totest_work=0;
			inprogress_work=0;
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			
				
		}
		allResult.add(dates);
		allResult.add(done);
		allResult.add(toDo);
		allResult.add(toTest);
		allResult.add(inProgress);
		allResult.add(newTask);
		allResult.add(work_totest);
		allResult.add(work_inprogress);
		
		return allResult;
		
	}

}
