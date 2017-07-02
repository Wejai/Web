package com.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cbs.demo.Accounts;
import com.cbs.demo.Transaction;
import com.cbs.demo.Transactions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.sql.*;

public class Welcome extends HttpServlet {
	
	HttpSession session = null;
	StringBuilder strBuf = new StringBuilder(); 
	PrintWriter out = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        out.println("Welcome user");
        session=request.getSession(false);
        String userId = (String)session.getAttribute("userId"); 
        String balURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/"+userId+"/transactions?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e ";
        
        
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        try{  
            //Declare the connection to weather api url
            URL url = new URL(balURL);  
            conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("apikey",apiKey);
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                              + conn.getResponseCode());
            }
            
            //Read the content from the defined connection
            //Using IO Stream with Buffer raise highly the efficiency of IO
	        reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;  
            while ((output = reader.readLine()) != null)  
                strBuf.append(output);  
            
            
            try{
                Gson gson = new GsonBuilder().create();
                Transactions trns = gson.fromJson(strBuf.toString().replace("[", "").replace("]", ""), Transactions.class);   
                
                
                String balAmt = "", tid = trns.getTid();
                System.out.println("here");
                Transaction[] trn = trns.getData().getTransaction();
                
                for (int i=0;i<trn.length;i++) {
                	balAmt = trn[i].getBalance().getAmount().getAmount().toString();
                }
                
                out.println("Trns Id : " + tid);
                out.println("balAmt Id : " + balAmt);
            }
            catch(JsonSyntaxException ex)
            {
                ex.printStackTrace();
            }
            
            
            
        }catch(MalformedURLException e) {  
            e.printStackTrace();   
        }catch(IOException e){  
            e.printStackTrace();   
        }finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }

      }  
    
    public static void main(String arg[]) {
    	StringBuilder strBuf = new StringBuilder(); 
    	String userId = "123"; 
        String balURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/"+userId+"/transactions?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e ";
        
        
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        try{  
            //Declare the connection to weather api url
            URL url = new URL(balURL);  
            conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("apikey",apiKey);
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                              + conn.getResponseCode());
            }
            
            //Read the content from the defined connection
            //Using IO Stream with Buffer raise highly the efficiency of IO
	        reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;  
            while ((output = reader.readLine()) != null)  
                strBuf.append(output);  
            
            
            try{
                Gson gson = new GsonBuilder().create();
                String str = strBuf.toString().replace("[", "").replace("]", "");
                System.out.println("here : " + str);
                Transactions trns = gson.fromJson(str, Transactions.class);   
                
                
                String balAmt = "", tid = trns.getTid();
                System.out.println("here");
                Transaction[] trn = trns.getData().getTransaction();
                
                for (int i=0;i<trn.length;i++) {
                	balAmt = trn[i].getBalance().getAmount().getAmount().toString();
                }
                
                System.out.println("Trns Id : " + tid);
                System.out.println("balAmt Id : " + balAmt);
            }
            catch(JsonSyntaxException ex)
            {
                ex.printStackTrace();
            }
            
            
            
        }catch(MalformedURLException e) {  
            e.printStackTrace();   
        }catch(IOException e){  
            e.printStackTrace();   
        }finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
    }
}