package com.test.standalone.apis.http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

	private static String SOAP_ENDPOINT = "http://localhost:7001/standalone-apis/orders-api";
	private static String REQ_XML_LIST = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"	xmlns:web=\"http://globant/web-apis\"><soapenv:Header/><soapenv:Body><web:listOrders/></soapenv:Body></soapenv:Envelope>";
	private static String REQ_XML_PURCHASE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://globant/web-apis\"><soapenv:Header/><soapenv:Body><web:purchaseOrder><!--Optional:--><order><cost>?</cost><!--Optional:--><description>?</description><totalItems>?</totalItems></order></web:purchaseOrder></soapenv:Body></soapenv:Envelope>";

	public static void main(String[] args) throws Exception {
		listOrders();
		purchaseOrder();

	}

	private static void purchaseOrder() throws IOException {
		URL url = new URL(SOAP_ENDPOINT);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		connection.setRequestProperty("SOAPAction", "listOrders");
		connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
		connection.setRequestProperty("Host", "localhost:7001");
		connection.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
		OutputStream reqStream = connection.getOutputStream();
		reqStream.write(REQ_XML_PURCHASE.getBytes());

		int responseCode = connection.getResponseCode();
		System.out.println(responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());

		connection.disconnect();

	}

	private static void listOrders() throws IOException {

		URL url = new URL(SOAP_ENDPOINT);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		connection.setRequestProperty("SOAPAction", "listOrders");
		connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
		connection.setRequestProperty("Host", "localhost:7001");
		connection.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
		OutputStream reqStream = connection.getOutputStream();
		reqStream.write(REQ_XML_LIST.getBytes());

		int responseCode = connection.getResponseCode();
		System.out.println(responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());

		connection.disconnect();

	}

}
