package com.h2k.teachersApp.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.h2k.teachersApp.DAO.TeacherDAO;
import com.h2k.teachersApp.DTO.TeacherDTO;



public class TeacherServlet extends HttpServlet{

	private TeacherDAO teacherDAO=null;
	
 @Override
public void init() throws ServletException {
	// TODO Auto-generated method stub
System.out.println("servlet is initialized");
teacherDAO = new TeacherDAO();
}
 
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	System.out.println("Servlet hits this method");
		String teacherId = req.getParameter("teacherId");
		// MIME type - https://www.freeformatter.com/mime-types-list.html
		// resp.setContentType("");
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		
		out.println("req.getContentType() :: " + req.getContentType());
		Enumeration<String> params = req.getParameterNames();
		String eachParam = null;
		out.println("<p>");
		while(params.hasMoreElements()) {
			eachParam = params.nextElement();
			out.println("Parameter Name : " + eachParam + " Parameter Value " + req.getParameter(eachParam));
			out.println("<br>");
		}
		out.println("</p>");
//HEADERS		
		Enumeration<String> headers = req.getHeaderNames();
		String eachHeader = null;
		
		out.println("<p>");
		while(headers.hasMoreElements()) {
			eachHeader = headers.nextElement();
			out.println("Header Name : " + eachHeader + " Header Value " + req.getHeader(eachHeader));
			out.println("<br>");
		}
		out.println("</p>");
		
		
		  out.println("<p>"); Cookie[] cookies = req.getCookies(); for(Cookie
		  eachCookie : cookies) { out.println("Cookiee Name : " + eachCookie.getName()
		  + " Cookiee Value " + eachCookie.getValue()); out.println("<br>"); }
		  out.println("</p>");
		 
		out.println("<p>");
		out.println("TeacherId Received :: " + teacherId);
		TeacherDTO teacher = teacherDAO.getTeacherById(teacherId);
		if(teacher!=null)
		{
			out.println("<p>" + teacher.getFirstName() + "</p>" );
			out.println("<p>" + teacher.getLastName() + "</p>" );
			out.println("<p>" + teacher.getSkill() + "</p>" );
		}
		
		out.println("</p>");
		out.println("<p>");
		out.println("Checking Attribute Information::");
		out.println("</p>");
		
		Cookie cookie1=new Cookie("Walmart","UnitedStates");
		resp.addCookie(cookie1);
		Cookie cookie2=new Cookie("Target","UnitedStates");
		resp.addCookie(cookie2);
		
		out.println("</body></html>");
		}		

  @Override
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String firstName = req.getParameter("firstName");
	String lastName = req.getParameter("lastName");
	String skill = req.getParameter("skill");
	TeacherDTO teacher = new TeacherDTO();
	teacher.setFirstName(firstName);
	teacher.setLastName(lastName);
	teacher.setSkill(skill);
	PrintWriter out = resp.getWriter();
	out.println("<html><body>");
	
int	teacherId = teacherDAO.saveTeacher(teacher);
	if(teacher !=null)
	{
		
		out.println("<p>" + teacherId + "</p>" );
		out.println("<p>"+teacher.getFirstName()+"</p>");
	out.println("<p>"+teacher.getLastName()+"</p>");
	out.println("<p>"+teacher.getSkill()+"</p>");
	}
	out.println("</body></html>");
	
  }
@Override
	public void destroy() {
		// TODO Auto-generated method stub
	System.out.println("sevlet is destroyed");
	}
}
