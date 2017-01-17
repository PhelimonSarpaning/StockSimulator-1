<%-- 
    Document   : home
    Created on : 7 Sep, 2015, 9:12:46 PM
    Author     : Aniket
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.ani.stockdownloader"%>
<%@page import="java.util.GregorianCalendar"%>
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
            String username = (String)request.getAttribute("username");
            //out.println(username);
            session.setAttribute("username",username);
            String balance = (String)request.getAttribute("balance");
            %>
        <form action="get_value_servlet" method="post">
            <input type="hidden" name="username" value=<% out.println(username); %>  /><br />
            <input type="hidden" name="balance" value=<% out.println(balance); %>  /><br />
           Company Symbol: <input type="text" name="company" /><br /><br />
           daily: <input type="radio" name="result_type"  value="d" />
            weekly: <input type="radio" name="result_type" value="w" />
            monthly:  <input type="radio" name="result_type"  value="m" /><br /><br />
            Date:  from: <input type="text" name="from_date" placeholder="yyyy/mm/dd">  to <input type="text" name="to_date" placeholder="yyyy/mm/dd"> 
         <br /><br />   <input type="submit" value="Get Stocks" />
        </form>
       
         <br /><br />  
            <form action="user_profile.jsp" method="post" >
        <input type="submit" value="Check Stock Profile" /><br />
        </form>
    </body>
</html>
