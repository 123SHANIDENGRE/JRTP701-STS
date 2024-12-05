import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dateurl")
public class DateServlet extends  HttpServlet
{
	
	public void doGet(HttpServletRequest  req ,HttpServletResponse res)throws Exception
	{
		
		//get PrintWriter 
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//write b.logic
		Date d=new Date();
		
		//write response 
		pw.println("<h1 style ='color:red' >Date and Time ::"+d+"</h1>");
		
		//add home hyperlink 
		pw.println("<br> <a href ='index.jsp'>home </a>");
		//close the stream
		 pw.close();
		 
		
		
		
		
	}
	
	public void doPost(HttpServletRequest  req ,HttpServletResponse res)throws Exception
	{
		doGet(req, res);
	}
	
	
	
	
	
}