package com.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cbs.demo.Account;
import com.cbs.demo.Accounts;
import com.cbs.demo.Balance;
import com.cbs.demo.RootObject;
import com.cbs.demo.Transaction;
import com.cbs.demo.Transactions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Welcome extends HttpServlet {

	HttpSession session = null;

	PrintWriter out = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder htmlContent = new StringBuilder();
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");
		
		String balURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/" + userId
				+ "/balances?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e";
		String benURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/" + userId
				+ "/beneficiaries?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e​";
		String transURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/123/transactions?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e ";
		
		
		/*String balURL = "http://134.168.54.14:8080/micro/balances?aid=%2222290%22";
		String benURL = "http://134.168.54.14:8080/micro/beneficiaries?bid=%2222290%22";
		String transURL = "http://134.168.54.14:8080/micro/transactions?tid=%22123%22";*/
		
		RootObject trans = fromJson(callService(transURL));
		RootObject balance = fromJson(callService(balURL));
		//RootObject beneficary = fromJson(callService(benURL));
		Account acc = balance.getData().getAccount();
		htmlContent.append("<html><head><meta charset='utf-8'><title>Account Details</title><script src='https://code.jquery.com/jquery-1.10.2.js'>"
				+ "</script><style>th, td {text-align: left;padding: 8px;}tr:nth-child(even){background-color: #f2f2f2}th "
				+ "{background-color: #4CAF50;color: white;}</style></head><body>");
		htmlContent.append("<div align='left' valign='middle'>Welcome <b>" + acc.getNickname() + " !</b>  "
				+new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(new java.util.Date())+"</div>");
		htmlContent.append("<div align='right' valign='middle'>(<a href='Logout'>Logout</a>)</div>");
		htmlContent.append("<div align='center' valign='middle'><img src='Logo.png'/></div>");
		htmlContent.append("<br/><hr/><table border=0><tr><td>");
		htmlContent.append("<b>Account ID</b></td><td>" + acc.getAccountId() + "</td></tr><tr><td>");
		htmlContent.append("<b>Name</b></td><td>" + acc.getAcc().getName());
		Balance bal = trans.getData().getTransaction().getBalance();
		htmlContent.append("</td></tr><td><b>Balance</b></td><td>" + bal.getAmount().getAmount() + " " + bal.getAmount().getCurrency());
		htmlContent.append("</td></tr>"
				+ "<tr><td><b><input type='button' value ='View Transaction Details' id='vTransDetails'/></b></td>"
				+ "<td><b><input type='button' value ='View Beneficiary Details' id='vBeneficiary'/></b></td></tr></table>");
		htmlContent.append("<div id='transDetails'><hr/><h2>Transaction Details</h2>");
		htmlContent.append("API Invoked : " + transURL);
		htmlContent.append("<table width='80%' cellspacing='5' cellpadding='5' border=0>");
		htmlContent.append("<tr><th>Date</th><th>Type</th><th>Code</th><th>Amount</th><th>Description</th></tr>");
		htmlContent.append("<tr><td>"+trans.getData().getTransaction().getBookingDateTime()+"</td>"
				+ "<td>"+trans.getData().getTransaction().getCreditDebitIndicator()+"</td>"
						+ "<td>"+trans.getData().getTransaction().getProprietaryBankTransactionCode().getCode()+"</td>"
								+ "<td>"+trans.getData().getTransaction().getAmount().getAmount()+"</td>"
										+ "<td>"+trans.getData().getTransaction().getTransactionInformation()+"</td></tr>");
		htmlContent.append("</table></div>");
		htmlContent.append("<div id='benDetails'><hr/><h2>Beneficiary Details</h2>");
		htmlContent.append("API Invoked : https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/22290/beneficiaries?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e");
		htmlContent.append("<table width='80%' cellspacing='5' cellpadding='5' border='0'>"
				+ "<tr><th>Name</th><th>Sort Code</th><th>Account</th></tr>"
				+ "<tr><td>Mrs Juniper</td><td>SC802001</td><td>22290</td></tr>"
				+ "<tr><td>Mr Vinoth</td><td>SC802001</td><td>22291</td></tr></table>");
		htmlContent.append("</div>");
		htmlContent.append("<script>$('#transDetails').hide();$('#benDetails').hide();$('#vTransDetails').click(function(event) {"
				+ "event.preventDefault(); $('#benDetails').hide(); $('#transDetails').show();}); $('#vBeneficiary').click(function(event) "
				+ "{event.preventDefault(); $('#transDetails').hide();$('#benDetails').show();});"
				+ "</script>");
		System.out.println(htmlContent.toString());
		htmlContent.append("</body></html>");
		out.println(htmlContent.toString());
	}
	
	public static RootObject fromJson(String jsonStr) {
		try {
			Gson gson = new GsonBuilder().create();
			RootObject trns = gson.fromJson(jsonStr, RootObject.class);
			return trns;
		} catch (JsonSyntaxException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String callService(String endPointURL) {
		HttpURLConnection conn = null;
		StringBuilder strBuf = new StringBuilder();
		BufferedReader reader = null;
		try {
			// Declare the connection to weather api url
			URL url = new URL(endPointURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			// conn.setRequestProperty("apikey",apiKey);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP GET Request Failed with Error code : " + conn.getResponseCode());
			}

			// Read the content from the defined connection
			// Using IO Stream with Buffer raise highly the efficiency of IO
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String output = null;
			while ((output = reader.readLine()) != null)
				strBuf.append(output.replace("[", "").replace("]", ""));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return strBuf.toString();
	}

	public static void main(String arg[]) {
		StringBuilder strBuf = new StringBuilder();
		String userId = "22289";
		//String balURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/" + userId
		//		+ "/beneficiaries?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e";
		
		String balURL = "http://134.168.54.14:8080/micro/transactions?tid=%22123%22";

		HttpURLConnection conn = null;
		BufferedReader reader = null;
		try {
			// Declare the connection to weather api url
			URL url = new URL(balURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			// conn.setRequestProperty("apikey",apiKey);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP GET Request Failed with Error code : " + conn.getResponseCode());
			}

			// Read the content from the defined connection
			// Using IO Stream with Buffer raise highly the efficiency of IO
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String output = null;
			while ((output = reader.readLine()) != null)
				strBuf.append(output.replace("[", "").replace("]", ""));
			
			//strBuf.append("{\"root\": {\"-rev\": \"10-1d1063dbdecd21682321c8353a0c1057\",\"-id\": \"f2c1a175236b061ac60b6db621371091\",\"aid\": [\"22291\",\"22291\"],\"data\": [{\"account\": {\"nickname\": \"Bills\",\"accountId\": \"22291\",\"acc\": {\"secondaryIdentification\": \"00021\",\"identification\": \"10203345\",\"schemeName\": \"BBAN\",\"name\": \"Mr Vinoth\"},\"servicer\": {\"identification\": \"SC802001\",\"schemeName\": \"UKSortCode\"},\"currency\": \"GBP\"}},{\"account\": {\"nickname\": \"Bills\",\"accountId\": \"22291\",\"acc\": {\"secondaryIdentification\": \"00021\",\"identification\": \"10203345\",\"schemeName\": \"BBAN\",\"name\": \"Mr Vinoth\"},\"servicer\": {\"identification\": \"SC802001\",\"schemeName\": \"UKSortCode\"},\"currency\": \"GBP\"}}],\"links\": [{ \"self\": \"/accounts/22291\" },{ \"self\": \"/accounts/22291\" }],\"meta\": [{ \"totalpages\": \"1\" },{ \"totalpages\": \"1\" }]}}");
			//strBuf.append("{\"_rev\":\"8-247a4dbd8f1bb6a80e1a7d41147c839d\",\"aid\":\"22290\",\"_id\":\"00672024c16f121896ab6189082b2bda\",\"data\":{\"transaction\":null,\"account\":[{\"nickname\":\"Bills\",\"accountId\":\"22290\",\"acc\":{\"secondaryIdentification\":\"00021\",\"identification\":\"10203345\",\"schemeName\":\"BBAN\",\"name\":\"Mr Vijay\"},\"servicer\":{\"identification\":\"SC802001\",\"schemeName\":\"UKSortCode\"},\"currency\":\"GBP\"}],\"beneficiary\":null},\"links\":{\"self\":\"/accounts/22290\"},\"meta\":{\"totalpages\":\"1\"}}");
			try {
				Gson gson = new GsonBuilder().create();
				String str = strBuf.toString();
				System.out.println("here : " + str);
				RootObject trns = gson.fromJson(strBuf.toString(), RootObject.class);

				String balAmt = "", tid = trns.getAid();
				System.out.println("here");

				System.out.println("Trns Id : " + tid);
				//System.out.println("balAmt Id : " + trns.getData().getAccount().getNickname());
			} catch (JsonSyntaxException ex) {
				ex.printStackTrace();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
}