/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ani;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;

/**
 *
 * @author Aniket
 */
public class test {
   

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
    BigInteger n = sc.nextBigInteger();
        BigInteger f=n;
        //System.out.println(f);
        while(n.compareTo(BigInteger.ONE)>0)
            {
               // System.out.println("hi");
                 n=n.subtract(BigInteger.ONE);
          //  BigInteger i=n.subtract(BigInteger.ONE);  
            f=f.multiply(n);
          
          // System.out.println("-->"+n);
        }
        
        System.out.println(f);
        
    
}
}
