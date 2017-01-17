<%-- 
    Document   : StockDownloader_jsp
    Created on : 23 Oct, 2015, 8:05:55 PM
    Author     : Aniket
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/css">
            body{
                background-color:#27AE60;
            }
            
        </script>
    </head>
    <body>
       <%
         //session = request.getSession(true);
                String username = (String)request.getAttribute("username");
                //request.setAttribute("username",username);
            
           String to_date = (String)request.getAttribute("to_date");
         //GregorianCalendar start = (GregorianCalendar) request.getAttribute("start");
           //String op_code = (String) request.getAttribute("op_code");
           String balance = (String) request.getAttribute("balance");
            String str_cal = (String) request.getAttribute("str_cal");
              String company = (String) request.getAttribute("company");
              
              String result_type = (String) request.getAttribute("result_type");
              /* string calendar to be converted in valid gregorian calendar after splitting in start and end date */
              // Calendar cal = Calendar.getInstance();
              String[] start_date_parts = str_cal.split("/");
          String y1 = start_date_parts[0];
          //System.out.println(y1);
          String m1 = start_date_parts[1];
          //System.out.println(m1);
          String d1 = start_date_parts[2];
           //System.out.println(d1);
        GregorianCalendar start = new GregorianCalendar(Integer.parseInt(y1),Integer.parseInt(m1)-1,Integer.parseInt(d1));
        
       // cal.add(Integer.parseInt(from_date),1);
       // System.out.println(currdate.format(cal.getTime()));
       // DateFormat currdate = new SimpleDateFormat("yyyy/MM/dd"); 
         String[] end_date_parts = to_date.split("/");
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
         +"&f="+end.get(Calendar.YEAR)+"&g="+result_type+"&ignore=.csv";
            
          /* if file not found exception is coming then either check expression or holidays are there */
        //String url = "http://real-chart.finance.yahoo.com/table.csv?s=GE&a=11&b=23&c=2015&d=11&e=26&f=2015&g=d&ignore=.csv";
     
              //String url = "http://real-chart.finance.yahoo.com/table.csv?s="+symbol+"";
              ///http://real-chart.finance.yahoo.com/table.csv?s=GE&a=08&b=11&c=2015&d=08&e=12&f=2015&g=d&ignore=.csv
             
                  URL yhoofin = new URL(url);
                  URLConnection data = yhoofin.openConnection();
                  Scanner input = new Scanner(data.getInputStream());
                  
                
                  if(input.hasNext())
                  {
                      input.nextLine(); //so that headers are removed
                      
                  }
               
                  
                  %>
                  
                  <table cellpadding="10" border="3"><th>Date</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Avg Volume</th><th>Adj Close</th>                  
                  <%
                  String result_adj_close= "0.0";
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
                     result_adj_close = lineparts[6];
             
                     out.println("<tr><td>"+result_date+"</td><td>"+result_open+"</td><td>"+result_high+"</td><td>"+result_low+"</td>"
                             + "<td>"+result_close+"</td><td>"+result_avg_vol+"</td><td>"+result_adj_close+"</td></tr><br />");
                     
                  }     
                  String stock_price = result_adj_close;
       //Integer stock_price = Integer.parseInt(result_adj_close);
                  
        %>
        
        </table>
        
        <br /><br />
        <form action="stockPurchase_Servlet" method="post" >
        <input type="hidden" name="username" value=<% out.println(username); %>  /><br />
            <input type="hidden"  name="balance" value=<% out.println(balance);%> />
            <input type="hidden" name="comp_symbol" value=<% out.println(company);%> />
            <input type="hidden" name="stock_price" value = <% out.println(stock_price);%> />
        Enter number of stocks you want to purchase:&nbsp;&nbsp;&nbsp;<input type="text" name="stocks_amt" placeholder="Enter no of stocks"/>
        <input type="submit" value="Purchase stocks" />
        </form>
        
        <form action="user_profile.jsp" method="post" >
        <input type="submit" value="Check Stock Profile" /><br />
        </form>
    </body>
</html>
