package com.jsp.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Registration")
public class Regisrtation extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
	    String useremail = req.getParameter("useremailid");
	    String usermobile = req.getParameter("usermobilenumber");
	    String userpassword = req.getParameter("userpassword");
	    String useraddress = req.getParameter("useraddress");
	    String userid = req.getParameter("userid");
	    

	    String insert = "insert into user_information(User_Name, Email_id,Mobile_number,Password, Address,User_id )"
		        + "values(?,?,?,?,?,?)";
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advancejava?user=root&password=12345");
			PreparedStatement ps = connection.prepareStatement(insert);
		      ps.setString(1, username);
		      ps.setString(2, useremail);
		      ps.setString(3, usermobile);
		      ps.setString(4, userpassword);
		      ps.setString(5, useraddress);
		      ps.setString(6, userid);
		     
		      int update = ps.executeUpdate();
		      PrintWriter pw = res.getWriter();
		      res.setContentType("text/html");
		      if (update != 0) {
		        //pw.println("<center><h1>Registration Successfull</h1></center>");
		    	  RequestDispatcher rd=req.getRequestDispatcher("Login.html");
				  rd.forward(req, res);
		      } else {
		    	RequestDispatcher rd=req.getRequestDispatcher("Registration.html");
				rd.include(req, res);
		        pw.println("<center><h1> Invalid Details</h1></center>");

		      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


			
	}

}
