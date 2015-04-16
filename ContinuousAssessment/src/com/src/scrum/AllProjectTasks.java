package com.src.scrum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class AllProjectTasks
 */
@WebServlet("/AllProjectTasks")
public class AllProjectTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllProjectTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String monthname=request.getParameter("month");
		System.out.println("got month name" +monthname);
		String projectName = request.getParameter("projectName");
		GetProjectInfo getProjectInfo = new GetProjectInfo();
		
		ArrayList allResults = getProjectInfo.getData(projectName);
		
		
		System.out.println("ajax hit! "+ projectName);
		PrintWriter pr = response.getWriter();
		String dataTest = "['test','test2']";
		
				
		ArrayList tesst = new ArrayList();
		
		// ['data1', -30, 200, 200, 400, -150, 250],
		tesst.add("\"Data 1\"");
		tesst.add(-30);
		tesst.add(200);
		tesst.add(200);
		tesst.add(400);
		tesst.add(-150);
		tesst.add(250);
		
		ArrayList test2 = new ArrayList();
		
		// ['data2', 130, 100, -100, 200, -150, 50],
		test2.add("\"Data 2\"");
		test2.add("130");
		test2.add("100");
		test2.add("-100");
		test2.add("200");
		test2.add("-150");
		test2.add("50");
		
		
		
		ArrayList test3 = new ArrayList();
		
		// ['data3', -230, 200, 200, -300, 250, 250]
		test3.add("\"Data 3\"");
		test3.add(-230);
		test3.add(200);
		test3.add(200);
		test3.add(-300);
		test3.add(250);
		test3.add(250);
		
		ArrayList all = new ArrayList();
		all.add(tesst);
		all.add(test2);
		//all.add(test3);
		
		
		System.out.println(allResults);
		pr.print(allResults);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
