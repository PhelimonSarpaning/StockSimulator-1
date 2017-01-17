/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ani;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Aniket
 */
public class Stocks {
    
    
    
    
    public static void main(String[] args)
    {
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
        
        stockdownloader  test = new stockdownloader("GE",start,end);
               
        
        
    }
}
