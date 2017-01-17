package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.net.URLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

public final class StockDownloader_005fjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <script type=\"text/css\">\n");
      out.write("            body{\n");
      out.write("                background-color:#27AE60;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       ");

         //GregorianCalendar start = (GregorianCalendar) request.getAttribute("start");
            String str_cal = (String) request.getAttribute("str_cal");
              String company = (String) request.getAttribute("company");
              
              String result_type = (String) request.getAttribute("result_type");
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
         +"&f="+end.get(Calendar.YEAR)+"&g="+result_type+"&ignore=.csv";
              //String url = "http://real-chart.finance.yahoo.com/table.csv?s="+symbol+"";
              ///http://real-chart.finance.yahoo.com/table.csv?s=GE&a=08&b=11&c=2015&d=08&e=12&f=2015&g=d&ignore=.csv
             
                  URL yhoofin = new URL(url);
                  URLConnection data = yhoofin.openConnection();
                  Scanner input = new Scanner(data.getInputStream());
                  
                
                  if(input.hasNext())
                  {
                      input.nextLine(); //so that headers are removed
                      
                  }
               
                  
                  
      out.write("\n");
      out.write("                  \n");
      out.write("                  <table cellpadding=\"10\" border=\"3\"><th>Date</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Avg Volume</th><th>Adj Close</th>                  \n");
      out.write("                  ");

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
             
                     out.println("<tr><td>"+result_date+"</td><td>"+result_open+"</td><td>"+result_high+"</td><td>"+result_low+"</td>"
                             + "<td>"+result_close+"</td><td>"+result_avg_vol+"</td><td>"+result_adj_close+"</td></tr><br />");
                     
                  }            
        
                  
        
      out.write("\n");
      out.write("        \n");
      out.write("        </table>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
