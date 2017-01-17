/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ani;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class stockPurchase_Servlet extends HttpServlet {

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
//              HttpSession session = request.getSession(true);
//                 String username = (String)request.getAttribute("username");
//            out.println(username);
            String username = request.getParameter("username");
           // out.println(username);
           String comp_symbol = request.getParameter("comp_symbol");
           Double stock_price = Double.parseDouble(request.getParameter("stock_price"));
           Double stock_amt = Double.parseDouble(request.getParameter("stocks_amt"));
            Double balance = Double.parseDouble(request.getParameter("balance"));
           
           Double tot_price = stock_price*stock_amt;
          // Integer op_code;
           Double final_balance = balance;
           Double no_of_stocks = balance/stock_price;
           if(balance<tot_price)
           {
               //Not enough Balance to purchase stocks
              // op_code = 0;
               out.println("<h3><font color='red'>Sorry , you do not have enough balance. You can only purchase maximum "+Double.valueOf(no_of_stocks)+" stocks.</font></h3>");
               
           }
           else{
               final_balance = balance - tot_price;
               //out.println(final_balance);
               Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/stock_master", "root" ,"password");
           String query = "Update stock_profile set balance = ? where username = ?";
		PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,Double.toString(final_balance));
                pst.setString(2,username);
                Integer rows = pst.executeUpdate();
                
            PreparedStatement pst2 = con.prepareStatement("Insert into bucket_list values (?,?,?,?)");
            pst2.setString(1, username);
            pst2.setString(2, comp_symbol);
            pst2.setString(3, Double.toString(stock_amt));
            pst2.setString(4, Double.toString(tot_price));
            Integer rows2 = pst2.executeUpdate();
            
                if(rows>0&&rows2>0)
                {
                    //if balance updated successsfully
                    //op_code = 1;
                    out.println("<h3><font color='green'>"+stock_amt+" stocks of "+comp_symbol+" are purchased successfully.</font></h3>");
               
                }
                else{
                    //op_code = -1;
                    out.println("<h3><font color='red'>Sorry , An error ocurred.Try again later.</font></h3>");
                }
           }
           
//             request.setAttribute("balance",final_balance);
//            request.setAttribute("op_code",op_code);
//                   RequestDispatcher rd = request.getRequestDispatcher("/StockDownloader_jsp.jsp");
//                   rd.forward(request,response);
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
