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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aniket
 */
public class StockDownloader_Servlet extends HttpServlet {

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
        try{
            
         //GregorianCalendar start = (GregorianCalendar) request.getAttribute("start");
            String str_cal = (String) request.getAttribute("str_cal");
              String company = (String) request.getAttribute("company");
              /* string calendar to be converted in valid gregorian calendar after splitting in start and end date */
               Calendar cal = Calendar.getInstance();
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
        DateFormat currdate = new SimpleDateFormat("yyyy/MM/dd"); 
         String[] end_date_parts = currdate.format(cal.getTime()).split("/");
          String y2 = end_date_parts[0];
          String m2 = end_date_parts[1];
          String d2 = end_date_parts[2];
        GregorianCalendar end = new GregorianCalendar(Integer.parseInt(y2),Integer.parseInt(m2)-1,Integer.parseInt(d2));
              
              
              
              
              
//        dates = new ArrayList<GregorianCalendar>();
//              opens = new ArrayList<Double>();
//              highs = new ArrayList<Double>();
//              lows = new ArrayList<Double>();
//              closes = new ArrayList<Double>();
//              volumes = new ArrayList<Integer>();
//              adjcloses = new ArrayList<Double>();    
//              
              ////http://real-chart.finance.yahoo.com/table.csv?s=GE&d=8&e=13&f=2015&g=d&a=0&b=2&c=1962&ignore=.csv
            String url = "http://real-chart.finance.yahoo.com/table.csv?s="+company+"&a="+start.get(Calendar.MONTH)+"&b="+start.get(Calendar.DAY_OF_MONTH)+"&c="+start.get(Calendar.YEAR)+"&d="+end.get(Calendar.MONTH)+"&e="+end.get(Calendar.DAY_OF_MONTH)+""
         +"&f="+end.get(Calendar.YEAR)+"&g=d&ignore=.csv";
              //String url = "http://real-chart.finance.yahoo.com/table.csv?s="+symbol+"";
              ///http://real-chart.finance.yahoo.com/table.csv?s=GE&a=08&b=11&c=2015&d=08&e=12&f=2015&g=d&ignore=.csv
             
                  URL yhoofin = new URL(url);
                  URLConnection data = yhoofin.openConnection();
                  Scanner input = new Scanner(data.getInputStream());
                  
                /*  
                  if(input.hasNext())
                  {
                      input.nextLine(); //so that headers are removed
                      
                  }
                  */
                  
                  while(input.hasNextLine())
                  {
                      String line = input.nextLine();
                    //  out.println(line);
                     String[]  lineparts= line.split(",");
                     String result_date = lineparts[0];
                     String  result_open = lineparts[1];
                     String  result_high = lineparts[2];
                     String  result_low  = lineparts[3];
                     String  result_close = lineparts[4];
                     String result_avg_vol = lineparts[5];
                     String result_adj_close = lineparts[6];
                     
                    
                      
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
