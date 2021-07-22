package com.higradius;

import java.io.IOException;    
import java.io.PrintWriter;    
import java.sql.*;    
import javax.servlet.ServletException;    
import javax.servlet.annotation.WebServlet;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;    
    
/**  
 * Servlet implementation class MovieServlet  
 */    
@WebServlet("/addServlet")    
public class AddServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;    
           
    /**  
     * @see HttpServlet#HttpServlet()  
     */    
    public AddServlet() {    
        super();    
        // TODO Auto-generated constructor stub    
    }    
    
    /**  
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)  
     */    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
        // TODO Auto-generated method stub    
        response.getWriter().append("Served at: ").append(request.getContextPath());    
    }    
    
    /**  
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)  
     */    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
            
        PrintWriter pw;    
        response.setContentType("text/html");    
        pw=response.getWriter();    
            
            
        String custname=request.getParameter("name_customer");    
        String custno=request.getParameter("cust_number");    
        String amount=request.getParameter("total_open_amount");    
        String duedate=request.getParameter("due_in_date");    
        String paydate=request.getParameter("paredicted_payment_date");    
        String invoiceid=request.getParameter("invoice_id");    
         
            
            
        try    
        {    
            Class.forName("com.mysql.jdbc.Driver");    
            String url="jdbc:mysql://localhost/sys";    
            String user="root";    
            String password="root";    
                
            Connection con=DriverManager.getConnection(url, user, password);    
            String query="Insert into sql (?,?,?,?,?,?);";    
            PreparedStatement pstmt=con.prepareStatement(query);    
            pstmt.setString(1, custno);    
            pstmt.setString(2, custname);    
            pstmt.setString(3,invoiceid);    
            pstmt.setString(4, amount);    
            pstmt.setString(5, duedate);    
            pstmt.setString(6,paydate);    
                
            int x=pstmt.executeUpdate();    
                
            if(x==1)    
            {    
            pw.println("Values Inserted Successfully");    
            }    
                
        }    
        catch(Exception e)    
        {    
                e.printStackTrace();    
        }    
            
            
        pw.close();    
    }    
    
}    