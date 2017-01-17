/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ani;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aniket
 */
public class get_value_servlet extends HttpServlet {

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
            
//        HttpSession session = request.getSession(true);
                String username = request.getParameter("username");
//                request.setAttribute("username",username);
            
            
            String balance = request.getParameter("balance");
            String company = request.getParameter("company");
            String result_type = request.getParameter("result_type");
            String to_date = request.getParameter("to_date");
            String from_date = request.getParameter("from_date");
            
            
            //DateFormat currdate = new SimpleDateFormat("yyyy/MM/dd");
        // Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DATE,-1);   
          //cal.add( Integer.parseInt(to_date),-1); 
          //System.out.println(currdate.format(cal.getTime()));
        // String str_cal =currdate.format(cal.getTime());
              String str_cal =from_date;
        
         
        //Calendar start_s = start;
        //String start_n = (String)start_s;
         request.setAttribute("username",username);
         request.setAttribute("balance",balance);
        request.setAttribute("company", company);
        request.setAttribute("str_cal", str_cal);
        request.setAttribute("to_date", to_date);
        request.setAttribute("result_type", result_type);
       // request.setAttribute("end", str);
        
        RequestDispatcher rd = request.getRequestDispatcher("/StockDownloader_jsp.jsp");
                   rd.forward(request,response);
       //stockdownloader  test = new stockdownloader(company,start,end);
            //test.stockdownloader("GE",start,end);
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
