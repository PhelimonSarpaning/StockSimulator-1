<%-- 
    Document   : user_profile
    Created on : 18 Dec, 2015, 6:29:32 PM
    Author     : Aniket
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           session = request.getSession(true);
            String username = (String)session.getAttribute("username");
            session.setAttribute("username", username);
           // Double Net_stocks_worth = 0.0;
            
             Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/stock_master", "root" ,"password");
           String query = "Select * from bucket_list where username = ?";
		PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,username);
             
               ResultSet rs = pst.executeQuery();
               
               PreparedStatement pst2 = con.prepareStatement("Select * from stock_profile where username = ?");
               pst2.setString(1, username);
               
               ResultSet rs2 = pst2.executeQuery();
               
               
               
               %>
               
               <table cellpadding="10" border="3"><th>Company Symbol</th><th>No. of Stocks</th><th>Net worth of stocks</th>
                       
                
               <%
               while(rs.next())
               {
                    out.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr><br />");
                     //Net_stocks_worth = Net_stocks_worth + Double.parseDouble(rs.getString(3));
               }
                
               
        %>
               </table>
               
      <br /><br />  
      <h4>Your Remaining balance is :<% if(rs2.next()) 
      {
          session.setAttribute("remaining_balance", rs2.getDouble(2));
          out.println(rs2.getString(2)); 
        } %> </h4>
      <br /><br /> <h4><a href="stock_updater">Update Stock Profile</a></h4>
      
       <br /><br /> 
       <form action= "stock_seller" method="post">
          Enter company Symbol: <input type="text" name="comp_symbol" placeholder="Company Symbol" /><br /><br />
          Enter no of stocks: <input type="text" name="no_of_stocks_2_sell" placeholder="Number of stocks" /><br /><br />
           <input type="submit" value="Sell" />
       </form>
    </body>
</html>
