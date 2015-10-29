package visadirect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VisaDirectMethodsImpl implements VisaDirectMethods {
	
	private static SimpleDateFormat retrievalReferenceFormat = new SimpleDateFormat("Dwwhhmmss");
	private static SimpleDateFormat localTransactionDateTimeFormat = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss");
	private static String KEY_STORE_PATH = "/keys/clientkeystore.jks";
	private static String STORE_PASSWORD = "password";
	private static String KEY_PASSWORD = "password";
	private static String VDP_U_ID = "O83CSVTPWHCD9WH6W9J321UvQ5JzMNc_8s6xWnvc-yXKshhYo";
	private static String VDP_PASSWORD = "7lUXlvBwAFV120yyk4UIY";
	private static String API_PUSH_POST = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/pushfundstransactions";
	private static String API_MULTI_PULL_POST = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/multipullfundstransactions";
	private static String API_MULTI_PULL_GET = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/multipullfundstransactions/";
	
	CloseableHttpClient httpClient = null;
	
	public VisaDirectMethodsImpl() {
		File keyStore;
		URL url = Main.class.getResource(KEY_STORE_PATH);
		keyStore = new File(url.getPath());
		
		SSLContext sslContext;
		try {
			sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, STORE_PASSWORD.toCharArray(), KEY_PASSWORD.toCharArray()).build();
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
			        SSLConnectionSocketFactory.getDefaultHostnameVerifier());

			httpClient = HttpClients.custom()
			            .setSSLSocketFactory(sslSocketFactory).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean Transfer(List<Transaction> sendTransactions, Account receiverAccount) {
		if (httpClient == null) {
			System.out.println("Client is not created");
			return false;
		}
		
		double amount = 0;
		for (Transaction transaction : sendTransactions) {
			amount += transaction.getAmount();
		}
		
		if (multiPullFundsTransactionsPost(sendTransactions)) {
			boolean result = pushFundsTransactionsPost(new Transaction(receiverAccount, amount, false), "234234234234234", "05");
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	private boolean multiPullFundsTransactionsPost(List<Transaction> senderAccounts) {
		HttpPost request = new HttpPost(API_MULTI_PULL_POST);
		this.processPostRequest(request);
		
		PayloadMultiPullFound body = new PayloadMultiPullFound(retrievalReferenceFormat, localTransactionDateTimeFormat, new Date(), senderAccounts);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String bodystr = gson.toJson(body);
//		System.out.println(bodystr);
		try {
			StringEntity params = new StringEntity(bodystr);
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
					
			String result = "";
			String line = "";
			while ((line = rd.readLine()) != null) {
				result += line += "\n";
			}
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean pushFundsTransactionsPost(Transaction transaction, String transactionId, String sourceofFundsCode) {
		Account sender = new Account("1234567890123456", "2013-03", "USD");
		HttpPost request = new HttpPost(API_PUSH_POST);
		this.processPostRequest(request);
		
		PayloadPushFound body = new PayloadPushFound(retrievalReferenceFormat, localTransactionDateTimeFormat, new Date(), sourceofFundsCode, transactionId, transaction, sender);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String bodystr = gson.toJson(body);
//		System.out.println(bodystr);
		try {
			StringEntity params = new StringEntity(bodystr);
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
					
			String result = "";
			String line = "";
			while ((line = rd.readLine()) != null) {
				result += line += "\n";
			}
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	private boolean processPostRequest(HttpPost request) {
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		request.setHeader(HttpHeaders.ACCEPT, "application/json");
		String autho = VDP_U_ID + ":" + VDP_PASSWORD;
		String authoBase64 = new String(Base64.encodeBase64(autho.getBytes()));
		request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authoBase64);
		request.setHeader("x-client-transaction-id", "234234234234234"); // This is required by multiple pull
		return true;
	}
}
