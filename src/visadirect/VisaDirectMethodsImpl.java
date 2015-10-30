package visadirect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class VisaDirectMethodsImpl implements VisaDirectMethods {
	
	private static String KEY_STORE_PATH = "/keys/clientkeystore.jks";
	private static String STORE_PASSWORD = "password";
	private static String KEY_PASSWORD = "password";
	private static String VDP_U_ID = "O83CSVTPWHCD9WH6W9J321UvQ5JzMNc_8s6xWnvc-yXKshhYo";
	private static String VDP_PASSWORD = "7lUXlvBwAFV120yyk4UIY";
	private static String API_PUSH = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/pushfundstransactions";
	private static String API_MULTI_PULL = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/multipullfundstransactions";
	
	CloseableHttpClient httpClient = null;
	String transactionNumber = null;
	
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
		
		String xTransactionId = Utility.generateXTransactionId();
		
		String multiPullResult = multiPullFundsTransactionsPost(sendTransactions, xTransactionId);
		
		if (multiPullResult != null) {
			// Execute get
			String transactionNumber = null;
			Type type = new TypeToken<Map<String, String>>(){}.getType();
			Map<String, String> multiPullResultMap = new Gson().fromJson(multiPullResult, type);
			for (String key : multiPullResultMap.keySet()) {
				if (key.equals("transactionNumber")) {
					transactionNumber = multiPullResultMap.get(key);
				}
			}
			
			if (transactionNumber == null) {
				return false;
			}
			
			boolean multiPullGetResult = multiPullFundsTransactionGet(transactionNumber, xTransactionId);
			
			if (multiPullGetResult == false) {
				return multiPullGetResult;
			}
			
			pushFundsTransactionsPost(new Transaction(receiverAccount, amount, false), "234234234234234", "05");
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
	
	private String multiPullFundsTransactionsPost(List<Transaction> senderAccounts, String xTransactionId) {
		HttpPost request = new HttpPost(API_MULTI_PULL);
		this.processRequest(request);
		request.setHeader("x-client-transaction-id", xTransactionId); // This is required by multiple pull
		
		PayloadMultiPullFound body = new PayloadMultiPullFound(new Date(), senderAccounts);
		return executePostRequestWithPayload(request, body);
	}
	
	private boolean multiPullFundsTransactionGet(String transactionNumber, String xTransactionId) {
		if (transactionNumber == null) {
			return false;
		}
		
		String requestUrl = API_MULTI_PULL + "/" + transactionNumber;
		System.out.println(transactionNumber);
		System.out.println(xTransactionId);
		HttpGet request = new HttpGet(requestUrl);
		this.processRequest(request);
		request.setHeader("x-client-transaction-id", xTransactionId);
		
		return executeGetRequest(request);
	}
	
	private String pushFundsTransactionsPost(Transaction transaction, String transactionId, String sourceofFundsCode) {
		Account sender = new Account("1234567890123456", "2013-03", "USD");
		HttpPost request = new HttpPost(API_PUSH);
		this.processRequest(request);
		
		PayloadPushFound body = new PayloadPushFound(new Date(), sourceofFundsCode, transactionId, transaction, sender);
		return executePostRequestWithPayload(request, body);
	}
	
	private boolean executeGetRequest(HttpGet get) {
		try {
			HttpResponse response = httpClient.execute(get);
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
	
	private String executePostRequestWithPayload(HttpPost post, PayloadCommon payload) {
		String result = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String bodystr = gson.toJson(payload);
		try {
			StringEntity params = new StringEntity(bodystr);
			post.setEntity(params);
			HttpResponse response = httpClient.execute(post);
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
			
			String line = "";
			while ((line = rd.readLine()) != null) {
				result += line += "\n";
			}
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean processRequest(HttpRequest request) {
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		request.setHeader(HttpHeaders.ACCEPT, "application/json");
		String autho = VDP_U_ID + ":" + VDP_PASSWORD;
		String authoBase64 = new String(Base64.encodeBase64(autho.getBytes()));
		request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authoBase64);
		return true;
	}
}
