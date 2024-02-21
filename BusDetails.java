package com.jsp.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BusDetails")
public class BusDetails extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String start = request.getParameter("from");
		String end = request.getParameter("to");

		ArrayList<BusBean> al = new ArrayList();

		String url = "jdbc:mysql://localhost:3306/advancejava?user=root&password=12345";
		String select = "select  * from bus_details where Booking_From=? and Booking_To=?";

		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, start);
			ps.setString(2, end);
			int count = 1;
			System.out.println("Available buses are : ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(count++);
				BusBean b = new BusBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				b.busDetails();
				al.add(b);
			}
			Thread t = new Thread();
			Scanner sc = new Scanner(System.in);
			System.out.println("Select the bus");
			int n = sc.nextInt();
			System.out.println("Loading");
			for (int i = 0; i < 5; i++) {
				Thread.sleep(2000);
				System.out.print(".");
			}
			System.out.println();
			BusBean b = al.get(n - 1);
			if (b == null) {
				System.out.println("booking failed");
			} else {
				String insert = "insert into bus_booking_details( boarding, destination, date, prize) values(?,?,?,?)";
				PreparedStatement psi = connection.prepareStatement(insert);
				psi.setString(1, b.getFrom());
				psi.setString(2, b.getTo());
				Date d = new Date();
				psi.setString(3, "" + d.getDate() + "-" + d.getMonth() + "-" + d.getYear());
				psi.setInt(4, b.getPrice());
				int rows = psi.executeUpdate();
				if (rows != 0) {

					b.busDetails();
					System.out.println("-------------------------");																																																																																							
					System.out.println("Booking successful");

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
