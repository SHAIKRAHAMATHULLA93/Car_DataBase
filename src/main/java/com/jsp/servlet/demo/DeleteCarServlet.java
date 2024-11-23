package com.jsp.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DeleteCarServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int carId=Integer.parseInt(req.getParameter("num"));
		
		//JDBC LOGIC
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root");
			PreparedStatement pst=conn.prepareStatement("delete from car where carId=?");
			pst.setInt(1, carId);
			int res1=pst.executeUpdate();
			PrintWriter out=res.getWriter();
			if(res1>0) {
				out.print("<h1>" +res1+ "Data has been Deleted</h1>");
			}else {
				out.print("No Data has been Deleted");
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
