package com.jsp.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DisplayDataSevlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int carId=Integer.parseInt(req.getParameter("num"));

		//JDBC LOGIC
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root");
			PreparedStatement pst=conn.prepareStatement("Select * from car where carId=?");
			pst.setInt(1, carId);
			ResultSet rs=pst.executeQuery();
			PrintWriter out=res.getWriter();

			while(rs.next())
			{
				out.print("<h1> The CarId is :"+ rs.getInt(1)+".</h1>");

				out.print("<h1> The CarModel is :"+ rs.getString(2)+".</h1>");
				out.print("<h1> The CarBrand is :"+ rs.getString(3)+".</h1>");
				out.print("<h1> The CarPrice is :"+ rs.getInt(4)+".</h1>");

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
			{
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

