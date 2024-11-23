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

public class AddCarDetailsServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int number=Integer.parseInt(req.getParameter("num"));

		String carModel=req.getParameter("model");

		String carBrand=req.getParameter("brand");

		int price=Integer.parseInt(req.getParameter("price"));
		//JDBC LOGIC
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_cardb","root","root");
			PreparedStatement pst=conn.prepareStatement("INSERT INTO car VALUES(?,?,?,?)");
			pst.setInt(1, number);
			pst.setString(2, carModel);
			pst.setString(3, carBrand);
			pst.setInt(4, price);

			int resl=pst.executeUpdate();
			PrintWriter out=res.getWriter();
			if(resl>0) {
				out.print("<h1>"+ resl + " data has successfully inserted</h1>");
			}else {
				out.print("<h1> data not inserted successfully </h1>");

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

