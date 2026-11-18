package com.coders;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Registartion
 */
@WebServlet("/Registartion")
public class Registartion extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Registartion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String f_name=request.getParameter("f_name");
		String l_name=request.getParameter("l_name");
		String email=request.getParameter("email");
		String loaction=request.getParameter("loc");
		
		
		
		String url="jdbc:mysql://localhost:3306/registartion";
		String user="root";
		String password="tiger";
		String query="insert into student(fname,lname,location,email,id) values(?,?,?,?,1)";
		
		
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");   //loading jdbc Driver class
			//stage-1
			Connection con=DriverManager.getConnection(url, user, password);
			
			//stage-2
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, f_name);
			ps.setString(2, l_name);
			ps.setString(3, loaction);
			ps.setString(4, email);
			
			
			//stage-3
			int rows=ps.executeUpdate();
			out.println("<h3>"+rows +" rows inserted </h3>");
			out.println("<a href='Registration.html'>"+"goback"+"</a>");
			
			//stage-5
			con.close();
			ps.close();
			
		}
		catch(Exception e) {
			out.println("<h3>"+ e.getMessage()+"</h3>");
		}
	}

}
