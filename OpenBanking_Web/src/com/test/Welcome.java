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

public class Welcome extends HttpServlet {

	HttpSession session = null;

	PrintWriter out = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");
		String balURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/" + userId
				+ "/balances?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e";
		String benURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/" + userId
				+ "/beneficiaries?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e​";
		String transURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/123/transactions?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e ";
		RootObject trans = fromJson(callService(transURL));
		RootObject balance = fromJson(callService(balURL));
		//RootObject beneficary = fromJson(callService(benURL));
		Account acc = balance.getData().getAccount();
		out.println("Welcome <b>" + acc.getNickname() + " !</b>");
		out.println("<br/><hr/><table border=0><tr><td>");
		out.println("<b>Account ID</b></td><td>" + acc.getAccountId() + "</td><tr><td>");
		out.println("<b>Name</b></td><td>" + acc.getAcc().getName());
		Balance bal = trans.getData().getTransaction().getBalance();
		out.println("</td></tr><td><b>Balance</b></td><td>" + bal.getAmount().getAmount() + " " + bal.getAmount().getCurrency());
		out.println("</td></tr>");
		out.println("</table><br/><hr/><h4>Transaction Details</h4><hr/>");
		out.println("<table width='80%' cellspacing='5' cellpadding='5' border=0>");
		out.println("<tr><th>Date</th><th>Type</th><th>Code</th><th>Amount</th><th>Description</th></tr>");
		out.println("<tr><td>"+trans.getData().getTransaction().getBookingDateTime()+"</td>"
				+ "<td>"+trans.getData().getTransaction().getCreditDebitIndicator()+"</td>"
						+ "<td>"+trans.getData().getTransaction().getProprietaryBankTransactionCode().getCode()+"</td>"
								+ "<td>"+trans.getData().getTransaction().getAmount().getAmount()+"</td>"
										+ "<td>"+trans.getData().getTransaction().getTransactionInformation()+"</td></tr>");
		out.println("</table>");
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
		String balURL = "https://api.eu.apiconnect.ibmcloud.com/jackjonesbluemix-dev/sb/accounts/v1/" + userId
				+ "/beneficiaries?client_id=2ab78b89-7e24-4925-8f90-cb9fe71b533e";

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

			try {
				Gson gson = new GsonBuilder().create();
				String str = strBuf.toString();
				System.out.println("here : " + str);
				RootObject trns = gson.fromJson(strBuf.toString(), RootObject.class);

				String balAmt = "", tid = trns.getBid();
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