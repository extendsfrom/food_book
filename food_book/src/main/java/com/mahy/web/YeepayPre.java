package com.mahy.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class YeepayPre {
	
	public static void main(String[] args) {
		Map<String,Object> requestData = new HashMap<String, Object>();
		requestData.put("p0_Cmd", "EposConf");
		requestData.put("p1_MerId", "10012416564");
		requestData.put("p3_Amt", "2128.0");	//单位:元，精确到分.
		requestData.put("p4_Cur", "CNY");
		requestData.put("pb_TrxId", "218610406852272J");	//易宝支付平台产生的交易流水号，每笔订单唯一
		requestData.put("hmac", "9a8c2ade56623ce0f8e8f08b3d1d3d2f");
		//
		System.out.println(" params="+requestData);
		String responseStr = requestGet("https://api.yeepay.com/app-merchant-proxy/command", requestData, getHttpParams(), "GB2312");
		System.out.println(responseStr);
	}
	
	public static Map<String, Object> getHttpParams() {
		Map<String, Object> httpParams = new HashMap<String, Object>();
		httpParams.put(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpParams.put(CoreConnectionPNames.SO_TIMEOUT, 60000);
		httpParams.put("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		return httpParams;
	}
	
	public static String requestGet(String url, Map<String, Object> params, Map<String, Object> httpParams, String responseCharacter) {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?").append(mapToUrl(params));

		HttpResponseWrapper httpResponseWrapper = null;
		HttpGet httpGet = null;
		HttpClient client = null;
		try {

			if (url.startsWith("https")) {
				client = createHttpsClient(httpParams);
			} else {
				client = createHttpClient(httpParams);
			}
			httpGet = new HttpGet(urlBuilder.toString());
			HttpResponse httpResponse = client.execute(httpGet);

			if ("".equals(responseCharacter) || responseCharacter == null) {
				responseCharacter = "UTF-8";
			}
			return new HttpResponseWrapper(client, httpResponse, httpGet).getResponseString(responseCharacter);
		} catch (Exception e) {
		} finally {
			if (httpResponseWrapper != null) {
				httpResponseWrapper.close();
			}
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
		}
		return null;
	}
	
	public static class HttpResponseWrapper {
		private HttpResponse httpResponse;
		private HttpClient httpClient;
		private HttpRequestBase httpRequest;

		public HttpResponseWrapper(HttpClient httpClient) {
			this.httpClient = httpClient;
		}

		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse) {
			this.httpClient = httpClient;
			this.httpResponse = httpResponse;
		}

		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse, HttpRequestBase httpRequest) {
			this(httpClient, httpResponse);
			this.httpRequest = httpRequest;
		}

		public HttpRequestBase getHttpRequest() {
			return httpRequest;
		}

		public void setHttpRequest(HttpRequestBase httpRequest) {
			this.httpRequest = httpRequest;
		}

		public HttpResponse getHttpResponse() {
			return httpResponse;
		}

		public void setHttpResponse(HttpResponse httpResponse) {
			this.httpResponse = httpResponse;
		}

		/**
		 * 获得流类型的响应
		 */
		public InputStream getResponseStream() throws IllegalStateException, IOException {
			return httpResponse.getEntity().getContent();
		}

		/**
		 * 获得字符串类型的响应
		 */
		public String getResponseString(String responseCharacter) throws ParseException, IOException {
			HttpEntity entity = getEntity();
			String responseStr = EntityUtils.toString(entity, responseCharacter);
			if (entity.getContentType() == null) {
				responseStr = new String(responseStr.getBytes("iso-8859-1"), responseCharacter);
			}
			EntityUtils.consume(entity);
			return responseStr;
		}

		public String getResponseString() throws ParseException, IOException {
			return getResponseString("utf-8");
		}

		/**
		 * 获得响应状态码
		 */
		public int getStatusCode() {
			return httpResponse.getStatusLine().getStatusCode();
		}

		/**
		 * 获得响应状态码并释放资源
		 */
		public int getStatusCodeAndClose() {
			close();
			return getStatusCode();
		}

		public HttpEntity getEntity() {
			return httpResponse.getEntity();
		}

		/**
		 * 释放资源
		 */
		public void close() {
			if (httpRequest != null) {
				httpRequest.releaseConnection();
			}
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	
	
	public static HttpClient createHttpClient(Map<String, Object> httpParams) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		if (params != null && httpParams != null) {
			for (String key : httpParams.keySet()) {
				params.setParameter(key, httpParams.get(key));
			}
		}
		return httpClient;
	}
	
	public static HttpClient createHttpsClient(Map<String, Object> httpParams) {
		try {
			HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
			HttpParams params = httpClient.getParams();
			if (params != null && httpParams != null) {
				for (String key : httpParams.keySet()) {
					params.setParameter(key, httpParams.get(key));
				}
			}
			// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { new TrustAnyTrustManager() }, null);
			// 创建SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			// 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
			return httpClient;
		} catch (Exception e) {
		}
		return null;
	}
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	
	public static String mapToUrl(Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		try {
			boolean isFirst = true;
			for (String key : params.keySet()) {
				String value = (String) params.get(key);
				if (isFirst) {
					sb.append(key + "=" + URLEncoder.encode(value, "utf-8"));
					isFirst = false;
				} else {
					if (value != null) {
						sb.append("&" + key + "=" + URLEncoder.encode(value, "utf-8"));
					} else {
						sb.append("&" + key + "=");
					}
				}
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}
}
