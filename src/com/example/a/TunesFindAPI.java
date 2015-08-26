package com.example.a;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class TunesFindAPI {

	private static final String API_USERNAME = "60caa0b163697df123c46df8264bcc6e";
	private static final String API_PASSWORD = "49905b1f6fd63e0a4d86a81ba14fc719";

	private String response;
	private HttpClient httpClient;
	private HttpGet httpGet;
	private HttpResponse httpResponse;

	String url="https://www.tunefind.com/api/v1/artist/" ;
	
		public String getAlias(String songNAme){
					
		String newurl=url.concat(songNAme);
		Log.i("TunesFindAPI","url " + newurl);
		
	    httpClient = new DefaultHttpClient();
		httpGet = new HttpGet(newurl);
		httpGet.addHeader(BasicScheme.authenticate(
		 new UsernamePasswordCredentials(API_USERNAME,API_PASSWORD),
		 "UTF-8", false));
		try {
			httpResponse = httpClient.execute(httpGet);
			HttpEntity responseEntity = httpResponse.getEntity();
			response = EntityUtils.toString(responseEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
		
	}
}
