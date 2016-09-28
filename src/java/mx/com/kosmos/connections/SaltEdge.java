package mx.com.kosmos.connections;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.entity.StringEntity;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.google.gson.Gson;
import grails.converters.JSON;

public class SaltEdge {

	public final static int REQUEST_EXPIRES_MINUTES = 3;
	public final static String CLIENT_ID = "2cM5lSif8pkggDETQc193g";
	public final static String SERIVICE_SECRET = "N8KbiMK2BETvB8kWDBGA70H3XNl33DJSYEcmVvrgNcg";
	private final static String CONTENT_TYPE_JSON="application/json; charset=ISO-8859-1";
	public final String PRIVATE_KEY_PATH = "src/main/resources/private.pem";
	private static PEMKeyPair PRIVATE_KEY = null;
	
	

	public SaltEdge() {
		PRIVATE_KEY = readPrivateKey(PRIVATE_KEY_PATH);
	}

	// HTTP GET request
	public String get(String url) {
		return request("GET", url,null);
	}

	// HTTP POST request
	public String post(String url, Object payload) {
		return "Not Supported";
	}

	private String processResponse(HttpURLConnection con) {
		BufferedReader in;
		StringBuffer response = new StringBuffer();
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Exception : " + e);
			e.printStackTrace();
		}
		return response.toString();
	}

	private String request(String method, String url,String json) {
		HttpPost post;HttpGet get;HttpPut put; HttpDelete delete;
		HttpClient client = new DefaultHttpClient();
		String resp = "";String line = "";
		HttpResponse response= null;
		StringEntity params = null;
		try {
			switch(method){
			
			case "POST":
				post = new HttpPost(url);
				post.setHeader("Content-type",CONTENT_TYPE_JSON);
				post.setHeader("Client-id", CLIENT_ID);
				post.setHeader("Service-secret", SERIVICE_SECRET);
				System.out.println("PETICION JSON:::::>"+json);
				params =new StringEntity(json);
				post.setEntity(params);
				response = client.execute(post);
				break;
			case "GET":
				get = new HttpGet(url);
				get.setHeader("Content-type",CONTENT_TYPE_JSON);
				get.setHeader("Client-id", CLIENT_ID);
				get.setHeader("Service-secret", SERIVICE_SECRET);
				long expires = generateExpiresAt();
				//get.setHeader("Signature",generateSignature("GET", expires,url, ""));
				//System.out.println("EXPIRES::::>" + expires);
				//get.setHeader("Expires-at",String.valueOf(expires));
				response = client.execute(get);
				break;
			case "PUT":
				put = new HttpPut(url);
				put.setHeader("Content-type",CONTENT_TYPE_JSON);
				put.setHeader("Client-id", CLIENT_ID);
				put.setHeader("Service-secret", SERIVICE_SECRET);
				System.out.println("PETICION JSON:::::>"+json);
				params =new StringEntity(json);
				put.setEntity(params);
				response = client.execute(put);
				break;
			case "DELETE":
				delete = new HttpDelete(url);
				delete.setHeader("Content-type",CONTENT_TYPE_JSON);
				delete.setHeader("Client-id", CLIENT_ID);
				delete.setHeader("Service-secret", SERIVICE_SECRET);
				response = client.execute(delete);
				break;
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(
			response.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {resp = resp + line;}
			System.out.println("JSON Response::>" + resp);
		} catch (ProtocolException e) {
			System.out.println("ProtocolException : " + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException : " + e);
			e.printStackTrace();
		}
		return resp;
	}

	private long generateExpiresAt() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, REQUEST_EXPIRES_MINUTES);
		return calendar.getTimeInMillis();
	}

	/*
	private String generateSignature(String method, long expires, String url,String postBody) {
		String signature = String.format("%d|%s|%s|%s", expires, method, url,
				postBody);
		byte[] bytes = signature.getBytes();
		byte[] shaSignature = null;
		try {
			shaSignature = sign(bytes);
		} catch (SignatureException e) {
			System.out.println("SignatureException : " + e);
			e.printStackTrace();
		} catch (PEMException e) {
			System.out.println("PEMException : " + e);
			e.printStackTrace();
		}

		return Base64.toBase64String(shaSignature);
	}
	*/

	private byte[] sign(byte[] bytes) throws SignatureException, PEMException {
		KeyPair keyPair = null;
		Signature signature = null;
		try {
			signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(new JcaPEMKeyConverter()
					.getPrivateKey(PRIVATE_KEY.getPrivateKeyInfo()));
			signature.update(bytes);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException : " + e);
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signature.sign();
	}

	public PEMKeyPair readPrivateKey(String privateKeyFileName) {
		AsymmetricCipherKeyPair keyPair = null;
		File f = new File(privateKeyFileName);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(f);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : " + e);
			e.printStackTrace();
			return null;
		}
		PEMKeyPair obj = null;
		try {
			obj = (PEMKeyPair) new PEMParser(fileReader).readObject();
		} catch (IOException e) {
			System.out.println("IOException : " + e);
			e.printStackTrace();
			return null;
		}
		return obj;
	}

}
