package com.jsp.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/booking")
public class Booking_details extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pickup = request.getParameter("pickup");
		String destination = request.getParameter("destination");
		String date = request.getParameter("date");
		
		String insert = "insert into booking_info(Pickup, Destination,Booking_Date,Bookingi_d,User_id)"
		        + "values(?,?,?,?,?)";
		System.out.println(pickup);
		System.out.println(destination);
		System.out.println(date);
		
		Random random = new Random();
		HttpSession session=request.getSession();
		//To get the value from the session object
		Integer userid=(Integer) session.getAttribute("userid");
		System.out.println(userid);
		int bookingid = random.nextInt(10000);
		if(bookingid<1000) {
			bookingid+=1000;
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/advancejava?user=root&password=12345");
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setString(1, pickup);
			ps.setString(2, destination);
			ps.setDate(3, Date.valueOf(date));
			ps.setInt(4, bookingid);
			ps.setInt(5, userid);
			
			int result=ps.executeUpdate();
			PrintWriter write= response.getWriter();
			response.setContentType("text/html");
			if(result!=0) {
				write.println("<center><h1> Booking Sucessfull</h1></center>");
				write.println("<center><h1> Booking Id:  "+bookingid+"</h1></center>");
				write.println("<center><h1> Booking UserId:  "+userid+"</h1></center>");
				write.println("<center><h1> Pickup Point:  "+pickup+"</h1></center>");
				write.println("<center><h1> Destination:  "+destination+"</h1></center>");
				write.println("<center><h1> Date to Travell:  "+date+"</h1></center>");
			}
			else {
				write.println("<center><h1> Booking Unsucessfull</h1></center>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
