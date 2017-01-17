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
public class stock_seller extends HttpServlet {

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
        
        try {
           
            HttpSession session = request.getSession(true);
             String username = (String)session.getAttribute("username");
             
             String comp_symbol = request.getParameter("comp_symbol");
             
             Integer no_of_stocks_2_sell = Integer.parseInt(request.getParameter("no_of_stocks_2_sell"));
             
               Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/stock_master", "root" ,"password");
         
            PreparedStatement pst = con.prepareStatement("Select * from bucket_list where username=? and buckets=?");
            pst.setString(1,username);
            pst.setString(2,comp_symbol);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                if(rs.getInt(3)>=no_of_stocks_2_sell)
                {
                   
                    /////////////////////
                    
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
              
 String url = "http://real-chart.finance.yahoo.com/table.csv?s="+comp_symbol+"&a="+start.get(Calendar.MONTH)+"&b="+start.get(Calendar.DAY_OF_MONTH)+"&c="+start.get(Calendar.YEAR)+"&d="+end.get(Calendar.MONTH)+"&e="+end.get(Calendar.DAY_OF_MONTH)+""
         +"&f="+end.get(Calendar.YEAR)+"&g=d"+"&ignore=.csv";
        
        //String url = "http://real-chart.finance.yahoo.com/table.csv?s=GE&a=11&b=23&c=2015&d=11&e=26&f=2015&g=d&ignore=.csv";
     
             
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
                  
                  Double bucket_amount = no_of_stocks_2_sell*Double.parseDouble(result_adj_close);
                  
                  //out.println("no_of_stocks_2_sell= "+no_of_stocks_2_sell);
                  //out.println("no_of_stocks= "+rs.getInt(3));
                  
                Integer no_of_stocks_remain = rs.getInt(3) - no_of_stocks_2_sell;
                //out.println("no_of_stocks_remain= "+no_of_stocks_remain);
                Double bucket_amount_remain = no_of_stocks_remain*Double.parseDouble(result_adj_close);
                //out.println("bucket_amount_remain= "+bucket_amount_remain);
                Double new_final_balance = bucket_amount +  37.20;
                  
                   PreparedStatement pst2 = con.prepareStatement("Update bucket_list set stocks = ? , bucket_amount=? where username=? and buckets=?");
            pst2.setInt(1,no_of_stocks_remain);
            pst2.setDouble(2,bucket_amount_remain);       
            pst2.setString(3,username);
            pst2.setString(4,comp_symbol);
             
            Integer rows2 = pst2.executeUpdate();
            
            
            
            PreparedStatement pst3 = con.prepareStatement("Update stock_profile set balance = ? where username=?");
            pst3.setDouble(1,new_final_balance);
            pst3.setString(2,username);       
       
             Integer rows3 = pst3.executeUpdate();
             
             if(rows2>0&&rows3>0)
             {
                // out.println("<h3>Success</h3>");
                   response.sendRedirect("user_profile.jsp");
             }
            
             else{
                 out.println("<h3>Sorry, an error occurred.</h3>");
             }
                  
                    ////////////////////
                }
                
                else{
                    /*Invalid Input data */
                    out.println("<h3>Invalid Input data.</h3>");
                }
            }
             
            else{
                
                /*Invalid Input data */
                out.println("<h3>Invalid Input data.</h3>");
            }
            
              
            
            
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
