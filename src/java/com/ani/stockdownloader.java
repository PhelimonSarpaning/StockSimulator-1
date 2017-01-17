/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ani;

import static java.lang.System.out;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Aniket
 */
public class stockdownloader {
    public static final int DATE = 0;
     public static final int OPEN = 1;
      public static final int HIGH= 2;
       public static final int LOW =3;
        public static final int CLOSE = 4;
         public static final int VOLUME = 5;
          public static final int ADJCLOSE = 6;
          
          
          private ArrayList<GregorianCalendar> dates;
          private ArrayList<Double> opens;
          private ArrayList<Double> closes;
          private ArrayList<Double> highs;
          private ArrayList<Double> lows;
          private ArrayList<Integer> volumes;
          private ArrayList<Double> adjcloses;
          
          
          public  stockdownloader (String symbol,GregorianCalendar start, GregorianCalendar end)
          {
             dates = new ArrayList<GregorianCalendar>();
              opens = new ArrayList<Double>();
              highs = new ArrayList<Double>();
              lows = new ArrayList<Double>();
              closes = new ArrayList<Double>();
              volumes = new ArrayList<Integer>();
              adjcloses = new ArrayList<Double>();    
              
              ////http://real-chart.finance.yahoo.com/table.csv?s=GE&d=8&e=13&f=2015&g=d&a=0&b=2&c=1962&ignore=.csv
            String url = "http://real-chart.finance.yahoo.com/table.csv?s="+symbol+"&a="+start.get(Calendar.MONTH)+"&b="+start.get(Calendar.DAY_OF_MONTH)+"&c="+start.get(Calendar.YEAR)+"&d="+end.get(Calendar.MONTH)+"&e="+end.get(Calendar.DAY_OF_MONTH)+""
         +"&f="+end.get(Calendar.YEAR)+"&g=d&ignore=.csv";
              //String url = "http://real-chart.finance.yahoo.com/table.csv?s="+symbol+"";
              ///http://real-chart.finance.yahoo.com/table.csv?s=GE&a=08&b=11&c=2015&d=08&e=12&f=2015&g=d&ignore=.csv
            
            //http://real-chart.finance.yahoo.com/table.csv?s=GE&d=9&e=23&f=2015&g=d&a=0&b=2&c=1962&ignore=.csv
            
            
              try{
                  URL yhoofin = new URL(url);
                  URLConnection data = yhoofin.openConnection();
                  Scanner input = new Scanner(data.getInputStream());
                  
                  
                  if(input.hasNext())
                  {
                      input.nextLine();
                      
                  }
                  
                  while(input.hasNextLine())
                  {
                      String line = input.nextLine();
                      System.out.println(line);
                      
                      
                  }
              }
              
              catch(Exception e)
              {
                  System.err.println(e);
              }
             
              
          }
          
          
          public ArrayList<GregorianCalendar> getDates()
          {
              return dates;
          }
          
          
          public ArrayList<Double> getOpens()
          {
              return opens;
          }
}
