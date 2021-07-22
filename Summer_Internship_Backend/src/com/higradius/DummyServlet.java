package com.higradius;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Servlet implementation class dummyServlet
 */
@WebServlet("/DummyServlet")
public class DummyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	   // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost/sys";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyServlet() {
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connection conn=null;
		Statement stmt=null;
		String sql=null;
		ResultSet rs=null;
		  ArrayList<Response> ar=new ArrayList<Response>();
		  
		  
		  try {
			  Class.forName("com.mysql.cj.jdbc.Driver");

		         // Open a connection
		          conn = DriverManager.getConnection(DB_URL, USER, PASS);

		         // Execute SQL query
		       stmt = conn.createStatement();
		      
		         sql = "SELECT * FROM mytable";
		        rs = stmt.executeQuery(sql);
		         
		         while (rs.next()) {
		        	 
		         }
		        	  String custno = rs.getString("cust_number");
		        	  String custname = rs.getString("name_customer");
		        	  String  invoiceid = rs.getString ("invoice_id");
		        	  String  amount = rs.getString("total_open_amount");
		        	  String  duedate = rs.getString( "due_in_date");
		        	  String  paydate = rs.getString ("predicted_payment_date");
		              
		              
		             
		              
		             
		              Response cd=new Response();
		             
		              cd.setCustno(custno);
		              cd.setCustname(custname);
		              cd.setAmount(amount);
		              cd.setDuedate(duedate);
		              cd.setPaydate(paydate);
		              cd.setInvoiceid(invoiceid);
		              
		             
		              ar.add(cd); // Adding all the objects to array list
		 	  
			  
			  
		 
		         
		         Gson gson=new GsonBuilder().setPrettyPrinting().create();
		         String json=gson.toJson(ar);
		     
		    response.getWriter().write(json);
		         response.setContentType("application/json");
                                                 response.setContentType("application/json");
		        System.out.println(json);
		        
		         
		         
		
		         rs.close();
		         stmt.close();
		         conn.close();
		
	}catch(SQLException se) {
        //Handle errors for JDBC
        se.printStackTrace();
     }  //end finally try
     //end try
 catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	

}
}