package visadirect;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	
	private static SimpleDateFormat retrievalReferenceFormat = new SimpleDateFormat("Dwwhhmmss");
	private static SimpleDateFormat localTransactionDateTimeFormat = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss");
	private static String KEY_STORE_PATH = "/keys/clientkeystore.jks";
	private static String STORE_PASSWORD = "password";
	private static String KEY_PASSWORD = "password";
	private static String VDP_U_ID = "O83CSVTPWHCD9WH6W9J321UvQ5JzMNc_8s6xWnvc-yXKshhYo";
	private static String VDP_PASSWORD = "7lUXlvBwAFV120yyk4UIY";
	private static boolean useProxy = false;
	private static String PROXY_HOST = "internet.visa.com";
	private static int PROXY_PORT = 80;
	private static String PROXY_USERNAME = "weilliu";
	private static String PROXY_PASSWORD = "";

	public static void main(String[] args) throws Exception {
		
		Account account = new Account("4957030420210454", "2013-03", "USD");
		Account sender = new Account("1234567890123456", "2013-03", "USD");
		Transaction transaction = new Transaction(sender, 112, true);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		
		VisaDirectMethods methods = new VisaDirectMethodsImpl();
		methods.Transfer(transactions, account);
		
//		File keyStore;
//		
//		URL url = Main.class.getResource(KEY_STORE_PATH);
//		keyStore = new File(url.getPath());
//		
//		SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, STORE_PASSWORD.toCharArray(), KEY_PASSWORD.toCharArray()).build();
//		
//		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
//		        SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//
//		CloseableHttpClient httpClient = null;
//		
//		if (useProxy) {
//			CredentialsProvider credsProvider = new BasicCredentialsProvider();
//			credsProvider.setCredentials(new AuthScope(PROXY_HOST, PROXY_PORT),
//					new UsernamePasswordCredentials(PROXY_USERNAME, PROXY_PASSWORD));
//			
//			HttpHost proxy = new HttpHost(PROXY_HOST, PROXY_PORT);
//			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//			httpClient = HttpClients.custom()
//					.setDefaultCredentialsProvider(credsProvider)
//					.setRoutePlanner(routePlanner)
//					.setSSLSocketFactory(sslSocketFactory).build();
//		} else {
//			httpClient = HttpClients.custom()
//		            .setSSLSocketFactory(sslSocketFactory).build();
//		}
//		
//		String pullApi = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/pullfundstransactions";
//		String pushApi = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/pushfundstransactions";
//		String multiPullApi = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/multipullfundstransactions";
//		String multiPullGetApi = "https://sandbox.api.visa.com/visadirect/fundstransfer/v1/multipullfundstransactions/";
		
//		HttpPost request = new HttpPost(pushApi);
////		HttpGet request = new HttpGet(multiPullGetApi+"42360173_WSI");
//		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//		request.setHeader(HttpHeaders.ACCEPT, "application/json");
//		String autho = VDP_U_ID + ":" + VDP_PASSWORD;
//		String authoBase64 = new String(Base64.encodeBase64(autho.getBytes()));
//		request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authoBase64);
//		request.setHeader("x-client-transaction-id", "234234234234234"); // This is required by multiple pull
		
		
//		Account account = new Account("4957030420210454", "2013-03", "USD");
//		Account sender = new Account("1234567890123456", "2013-03", "USD");
//		Transaction transaction = new Transaction(account, 112, true);
//		List<Transaction> transactions = new ArrayList<Transaction>();
//		transactions.add(transaction);
//		PayloadMultiPullFound body = new PayloadMultiPullFound(retrievalReferenceFormat, localTransactionDateTimeFormat, new Date(), transactions);
////		PayloadPullFound body = new PayloadPullFound(retrievalReferenceFormat, localTransactionDateTimeFormat, new Date(), transaction);
////		PayloadPushFound body = new PayloadPushFound(retrievalReferenceFormat, localTransactionDateTimeFormat, new Date(), "05", "234234322342343", transaction, account);
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String bodystr = gson.toJson(body);
//		System.out.println(bodystr);
//		StringEntity params = new StringEntity(bodystr);
//		request.setEntity(params);
//		HttpResponse response = httpClient.execute(request);
//
//		// Get the response
//		BufferedReader rd = new BufferedReader
//		  (new InputStreamReader(response.getEntity().getContent()));
//		
//		String result = "";
//		String line = "";
//		while ((line = rd.readLine()) != null) {
//			result += line += "\n";
//		}
//		System.out.println(result);
//		
//		httpClient.close();
	}
}
//String bodystr = "{\"localTransactionDateTime\":\"2015-10-29T09:25:20\",\"acquiringBin\":409999,\"acquirerCountryCode\":111,\"businessApplicationId\":\"AA\",\"merchantCategoryCode\":\"6012\",\"request\":[{\"systemsTraceAuditNumber\":300259,\"retrievalReferenceNumber\":\"404006300259\",\"senderCardExpiryDate\":\"2011-11\",\"cardAcceptor\":{\"idCode\":\"VMT200911026070\",\"address\":{\"zipCode\":\"94404\",\"state\":\"CA\",\"county\":\"081\",\"country\":\"USA\"},\"name\":\"Acceptor1\",\"terminalId\":\"365539\"},\"senderCurrencyCode\":\"USD\",\"localTransactionDateTime\":\"2021-10-26T21:32:52\",\"cavv\":\"0000010926000071934977253000000000000000\",\"pointOfServiceData\":{\"panEntryMode\":\"90\",\"posConditionCode\":\"0\",\"motoECIIndicator\":\"0\"},\"pointOfServiceCapability\":{\"posTerminalType\":\"4\",\"posTerminalEntryCapability\":\"2\"},\"feeProgramIndicator\":\"122\",\"senderPrimaryAccountNumber\":\"1234567891234567\",\"amount\":\"100.00\",\"surcharge\":\"2.00\",\"foreignExchangeFeeTransaction\":\"12.00\",\"magneticStripeData\":{\"track1Data\":\"1010101010101010101010101010\"},\"pinData\":{\"pinDataBlock\":\"1cd948f2b961b682\",\"securityRelatedControlInfo\":{\"pinBlockFormatCode\":1,\"zoneKeyIndex\":1}}}]}";
//String pullstr = "{\"businessApplicationId\": \"AA\",\"merchantCategoryCode\": 6012,\"pointOfServiceCapability\": {\"posTerminalType\": \"4\",\"posTerminalEntryCapability\": \"2\"},\"feeProgramIndicator\": \"123\",\"systemsTraceAuditNumber\": 300259,\"retrievalReferenceNumber\": \"407509300259\",\"foreignExchangeFeeTransaction\": \"10.00\",\"cardAcceptor\": {\"name\": \"Acceptor 1\",\"terminalId\": \"365539\",\"idCode\": \"VMT200911026070\",\"address\": {\"state\": \"CA\",\"county\": \"081\",\"country\": \"USA\",\"zipCode\": \"94404\"}},\"magneticStripeData\": {\"track1Data\": \"1010101010101010101010101010\"},\"senderPrimaryAccountNumber\": \"4005520000011126\",\"senderCurrencyCode\": \"USD\",\"surcharge\": \"2.00\",\"localTransactionDateTime\": \"2021-10-26T21:32:52\",\"senderCardExpiryDate\": \"2013-03\",\"pinData\": {\"pinDataBlock\": \"1cd948f2b961b682\",\"securityRelatedControlInfo\": {\"pinBlockFormatCode\": 1,\"zoneKeyIndex\": 1}},\"cavv\": \"0000010926000071934977253000000000000000\",\"pointOfServiceData\": {\"panEntryMode\": \"90\",\"posConditionCode\": \"0\",\"motoECIIndicator\": \"0\"},\"acquiringBin\": 409999,\"acquirerCountryCode\": \"101\",\"amount\": \"112.00\"}";
//String pushstr = "{\"systemsTraceAuditNumber\":350420,\"retrievalReferenceNumber\":\"401010350420\",\"localTransactionDateTime\":\"2015-10-302T08:52:00\",\"acquiringBin\":409999,\"acquirerCountryCode\":\"101\",\"senderAccountNumber\":\"1234567890123456\",\"senderCountryCode\":\"USA\",\"transactionCurrencyCode\":\"USD\",\"senderName\":\"John Smith\",\"senderAddress\":\"44 Market St.\",\"senderCity\":\"San Francisco\",\"senderStateCode\":\"CA\",\"recipientName\":\"Adam Smith\",\"recipientPrimaryAccountNumber\":\"4957030420210454\",\"amount\":\"112.00\",\"businessApplicationId\":\"AA\",\"transactionId\":234234322342343,\"merchantCategoryCode\":6012,\"sourceOfFundsCode\":\"03\",\"cardAcceptor\":{\"name\":\"John Smith\",\"terminalId\":\"13655392\",\"idCode\":\"VMT200911026070\",\"address\":{\"state\":\"CA\",\"county\":\"081\",\"country\":\"USA\",\"zipCode\":\"94105\"}},\"feeProgramIndicator\":\"123\"}";
