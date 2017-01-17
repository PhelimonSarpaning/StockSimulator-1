/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ani;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aniket
 */
public class stock_updater extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            HttpSession  session = request.getSession(true);
            String username = (String)session.getAttribute("username");
            
             DateFormat currdate = new SimpleDateFormat("yyyy/MM/dd");
         Calendar cal = Calendar.getInstance();
         cal.add(Calendar.DATE,-1);    
          //System.out.println(currdate.format(cal.getTime()));
         String str_cal =currdate.format(cal.getTime());
         
          String[] start_date_parts = str_cal.split("/");
          String y1 = start_date_parts[0];
          //System.out.println(y1);
          String m1 = start_date_parts[1];
          //System.out.println(m1);
          String d1 = start_date_parts[2];
           //System.out.println(d1);
          
            GregorianCalendar start = new GregorianCalendar(Integer.parseInt(y1),Integer.parseInt(m1)-1,Integer.parseInt(d1));
        
        cal.add(Calendar.DATE,1);
       // System.out.println(currdate.format(cal.getTime()));
       String[] end_date_parts = currdate.format(cal.getTime()).split("/");
          String y2 = end_date_parts[0];
          String m2 = end_date_parts[1];
          String d2 = end_date_parts[2];
        GregorianCalendar end = new GregorianCalendar(Integer.parseInt(y2),Integer.parseInt(m2)-1,Integer.parseInt(d2));
              
            
            Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/stock_master", "root" ,"password");
           String query = "Select * from bucket_list where username = ?";
		PreparedStatement pst = con.prepareStatement(query);
                   pst.setString(1,username);
                   
                   ResultSet rs = pst.executeQuery();
                   while(rs.next())
                   {
                       
                        
             String url = "http://real-chart.finance.yahoo.com/table.csv?s="+rs.getString(2)+"&a="+start.get(Calendar.MONTH)+"&b="+start.get(Calendar.DAY_OF_MONTH)+"&c="+start.get(Calendar.YEAR)+"&d="+end.get(Calendar.MONTH)+"&e="+end.get(Calendar.DAY_OF_MONTH)+""
         +"&f="+end.get(Calendar.YEAR)+"&g=d"+"&ignore=.csv";
             
             URL yhoofin = new URL(url);
                  URLConnection data = yhoofin.openConnection();
                  Scanner input = new Scanner(data.getInputStream());
                  
                
                  if(input.hasNext())
                  {
                      input.nextLine(); //so that headers are removed
                      
                  }
               
                   String result_adj_close = "0.0";
                  
                  while(input.hasNextLine())
                  {
                      String line = input.nextLine();
                    //  out.println(line);
                     String[]  lineparts= line.split(",");
//                     String result_date = lineparts[0];
//                     String  result_open = lineparts[1];
//                     String  result_high = lineparts[2];
//                     String  result_low  = lineparts[3];
//                     String  result_close = lineparts[4];
//                     String result_avg_vol = lineparts[5];
                      result_adj_close = lineparts[6];
                  
                   }
                  
                 Double bucket_amount = rs.getDouble(3)*Double.parseDouble(result_adj_close);
                  
            PreparedStatement pst2 = con.prepareStatement("Update bucket_list set bucket_amount = ? where buckets = ? and username = ?");
             pst2.setString(1,Double.toString(bucket_amount));
             pst2.setString(2,rs.getString(2));
             pst2.setString(3,username);
               
             Integer col = pst2.executeUpdate();
             
                   
          
           
            
        }
                   
                 response.sendRedirect("user_profile.jsp");
        }
        
        catch(Exception e)
        {
            out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
