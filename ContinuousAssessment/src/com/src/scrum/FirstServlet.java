package com.src.scrum;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName=request.getParameter("firstName");
		GetData gd=new GetData();
		
		Hashtable ht = gd.fetchData(firstName);
		String name = (String)ht.get("name");
		String projectName = (String)ht.get("projectName");
		String[] taskName = (String[])ht.get("taskName");
		String[] taskStatus= (String[])ht.get("taskStatus");
		
		
		request.setAttribute("name", name);
		request.setAttribute("projectName", projectName);
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskStatus", taskStatus);
		
		
		
		
		request.setAttribute("task1d1", 0);
		request.setAttribute("task1d2", 0.5);
		request.setAttribute("task1d3", 1);
		
		request.setAttribute("task2d1", 0);
		request.setAttribute("task2d2", 0.2);
		request.setAttribute("task2d3", 0.9);
		
		request.setAttribute("task3d1", 0);
		request.setAttribute("task3d2", 0.6);
		request.setAttribute("task3d3", 0.7);
		
		RequestDispatcher rd = request.getRequestDispatcher("/status.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
