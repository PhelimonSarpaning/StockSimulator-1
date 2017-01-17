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
public class loginaction extends HttpServlet {

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
            
            String u = request.getParameter("username");
            String p = request.getParameter("password");
            Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/stock_master", "root" ,"password");
		PreparedStatement pst = con.prepareStatement("Select * from stock_login where username = ? and password=?");
                pst.setString(1,u);
                pst.setString(2,p);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {//also get the balance of user
                    PreparedStatement pst2 = con.prepareStatement("Select balance from stock_profile where username = ? ");
                    pst2.setString(1, u);
                     ResultSet rs2 = pst2.executeQuery();
                     if(rs2.next())
                     {
                          HttpSession session = request.getSession(true);
                // session.setAttribute("user",u);
                   request.setAttribute("username",u);
                   request.setAttribute("password",p);
                   request.setAttribute("balance",rs2.getString(1));
                   RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
                   rd.forward(request,response);
                   
                     }
                
                }
                else{
                    response.sendRedirect("index.jsp");
                }
                    
                    
                
        } 
        catch (Exception e)
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
