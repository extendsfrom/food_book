package com.mahy.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * 本地银联扣款
 * @author mahuayang
 *
 */
public class ChinaPayPre {
	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int SO_TIMEOUT = 60000;
	public final static String[] reqQueryVo = new String[]{
		"version",
		"charset",
		"transType",
		"merId",
		"orderNumber",
		"orderTime",
		"merReserved"
	};
	public final static String[] reqSuccVo = new String[]{
		"acqCode",
		"backEndUrl",
		"charset",
		"commodityDiscount",
		"commodityName",
		"commodityQuantity",
		"commodityUnitPrice",
		"commodityUrl",
		"customerIp",
		"customerName",
		"defaultBankNumber",
		"defaultPayType",
		"frontEndUrl",
		"merAbbr",
		"merCode",
		"merId",
		"merReserved",
		"orderAmount",
		"orderCurrency",
		"orderNumber",
		"orderTime",
		"origQid",
//		"signMethod",
//		"signature",
		"transTimeout",
		"transType",
		"transferFee",
		"version"
	};
	public static void main(String[] args) {
		String orderIds = "38626425";//U
		Long orderId = 6425L;//U
		System.out.println("================================================");
		System.out.println("orderId : " + orderIds);
		String serial = generate24ByteSerialAttaObjectId(orderId); 
		
		String[] valueVo = new String[] { 
				"",// acqCode c
				"http://vpay.lvmama.com/fcw/server/UNIONPRE10030-VS_PAD.htm",// backEndUrl
				"UTF-8",
				"",// commodityDiscount o
				"",// commodityName o
				"",// commodityQuantity o
				"",// commodityUnitPrice o
				"",// commodityUrl o
				"127.0.0.1",// customerIp m
				"",// customerName o
				"",// defaultBankNumber o
				"",// defaultPayType o
				"http://vpay.lvmama.com/fcw/page/UNIONPRE10030-VS_PAD.htm",// frontEndUrl
				"上海驴妈妈旅游网",// merAbbr m
				"",// merCode c
				"301310048990068",// merId m
				"",// merReserved o
				"359800",// orderAmount  U
				"156",// orderCurrency m
				serial,// orderNumber m   
				toDate(),// orderTime   
				"201612121016127189428",// origQid c   U
				"",// transTimeout o
				"03",// transType
				"",// transferFee 0
				"1.0.0" // version m
		};
		System.out.println("reqesting............");
		for(String str : valueVo) {
			if(null != str && !"".equals(str)) {
				System.out.println(str);
			}
		}
		System.out.println("reqesting............");
		String key = "ST45UTYITYGHY56ITGYH64IGH46IYGH5Y6";
		String backStagegateWay = "https://unionpaysecure.com/api/BSPay.action";
		String res = getUnionPaySyncRes(key, backStagegateWay, valueVo);
		System.out.println("response:" + res);
		if (res != null && !"".equals(res)) {
			String[] arr = getResArr(res);
			if (checkSecurity(key, arr)) {// 验证签名
				String resultString = getRespCode(arr);// 商户业务逻辑
				System.out.println(resultString);
				if ("00".equals(resultString)) {
					//System.out.println("扣款申请发起成功");
				}
			}
		}
		System.out.println("================================================");
		
		/*String[] valueVo = new String[] { "1.0.0", "UTF-8", "01", "301310048990068", "201612121902574793448",
				"20161212190257", "" };
		String key = "ST45UTYITYGHY56ITGYH64IGH46IYGH5Y6";
		String backStagegateWay = "https://unionpaysecure.com/api/Query.action";
		String res = getUnionPaySyncRes(key, backStagegateWay, valueVo);
		System.out.println(res);
		String[] arr = getResArr(res);
		String respCode = getRespCode(arr);
		System.out.println(respCode);*/
		
	}
	
	public static String toDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	private static int checkSecurityString(String key,String[] valueVo) {
		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < valueVo.length; i++) {
			String[] keyValue = valueVo[i].split("=");
			map.put(keyValue[0], keyValue.length >= 2 ? valueVo[i].substring(keyValue[0].length() + 1) : "");
		}
		if ("".equals(map.get("signature"))) {
			return 2;
		}
		String signature = map.get("signature");
		boolean isValid = false;
		if ("MD5".equalsIgnoreCase(map.get("signMethod"))) {
			map.remove("signature");
			map.remove("signMethod");
			String returnStringMd5 = md5(joinMapValue(map, '&') + md5(key,"MD5", "UTF-8"), "MD5", "UTF-8");
			isValid = signature.equals(returnStringMd5);
		} 

		return (isValid ? 1 : 0);
	}
	public static boolean checkSecurity(String key,String[] arr){
		//验证签名
		int checkedRes = checkSecurityString(key,arr);
		if(checkedRes==1){
			return true;
		}else if(checkedRes == 0){
			return false;
		}else if(checkedRes == 2){
			return false;
		}
		return false;
	}
	
	public static String getRespCode(String[] arr) {
		// 以下是商户业务处理
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			String[] resultArr = arr[i].split("=");
			// 处理商户业务逻辑
			if (resultArr.length >= 2 && "respCode".equals(resultArr[0])) {
				result = arr[i].substring(resultArr[0].length() + 1);
				break;
			}
		}
		return result;
	}
	
	public static String[] getResArr(String str) {
		String regex = "(.*?cupReserved\\=)(\\{[^}]+\\})(.*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);

		String reserved = "";
		if (matcher.find()) {
			reserved = matcher.group(2);
		}

		String result = str.replaceFirst(regex, "$1$3");
		String[] resArr = result.split("&");
		for (int i = 0; i < resArr.length; i++) {
			if ("cupReserved=".equals(resArr[i])) {
				resArr[i] += reserved;
			}
		}
		return resArr;
	}
	
	public static String generate24ByteSerialAttaObjectId(Long randomId) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String serial = f.format(new Date()) + randomId;
		return serial;
	}
	
	public static String getUnionPaySyncRes(String key,String requestUrl,String[] valueVo) {
		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < reqSuccVo.length; i++) {
			map.put(reqSuccVo[i], valueVo[i]);
		}
		map.put("signature", signMap(map,key));
		map.put("signMethod", "MD5");
		return requestPostForm(requestUrl, map, "UTF-8", "UTF-8");
	}
	
	public static String requestPostForm(String url, Map<String, String> requestParas, String requestCharacter, String responseCharacter) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			httpResponseWrapper = requestPostFormResponse(url, requestParas, requestCharacter);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			httpResponseWrapper.close();
		}
		return null;
	}
	public static HttpResponseWrapper requestPostFormResponse(String url, Map<String, String> requestParas, String requestCharacter) throws ClientProtocolException, IOException {
		HttpClient client = null;
		client =createHttpsClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = initNameValuePair(requestParas);
		httpPost.setEntity(new UrlEncodedFormEntity(formParams, requestCharacter));
		HttpResponse httpResponse = client.execute(httpPost); //执行POST请求
		return new HttpResponseWrapper(client, httpResponse);
	}
	
	public static List<NameValuePair> initNameValuePair(Map<String, String> params) {
    	List<NameValuePair> formParams = new ArrayList<NameValuePair>();
    	if (params != null && params.size() > 0) {
    		// 对key进行排序
    		List<String> keys = new ArrayList<String>(params.keySet());
    		Collections.sort(keys);
	    	for (String key : keys) {
	    		//LOG.info(key+" = " +params.get(key));
	    		formParams.add(new BasicNameValuePair(key, params.get(key))); 
			}
    	}
    	return formParams;
    }
	
	public static HttpClient createHttpsClient(int connectionTimeout,int soTimeout)  {
        try { 
        	HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例 
            HttpParams params = httpClient.getParams();
    		HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
    		HttpConnectionParams.setSoTimeout(params, soTimeout);
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext 
            SSLContext ctx = SSLContext.getInstance("TLS"); 
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用 
            ctx.init(null, new TrustManager[]{new TrustAnyTrustManager()}, null); 
            //创建SSLSocketFactory 
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx); 
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上 
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory)); 
            return httpClient;
        } catch (Exception e) {
			System.out.println(e);
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
	
    public static HttpClient createHttpsClient()  {
		return createHttpsClient(CONNECTION_TIMEOUT,SO_TIMEOUT);
    }
	
	public static class HttpResponseWrapper {
		private HttpResponse httpResponse;
		private HttpClient httpClient;
		
		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse) {
			this.httpClient = httpClient;
			this.httpResponse = httpResponse;
		}
		
		public HttpResponseWrapper(HttpClient httpClient) {
			this.httpClient = httpClient;
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
			return getResponseString("UTF-8");
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
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	private static String signMap(Map<String, String> map,String key) {
  		String keyString="";
		String strBeforeMd5 = joinMapValue(map, '&')+md5(key, "MD5", "UTF-8");
		keyString = md5(strBeforeMd5, "MD5", "UTF-8");
   		return keyString;
  	}
	private static  String joinMapValue(Map<String, String> map, char connector) {
		StringBuffer b = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			b.append(entry.getKey());
			b.append('=');
			if (entry.getValue() != null) {
				b.append(entry.getValue());
			}
			b.append(connector);
		}
		return b.toString();
	}
	public static String md5(String str,String signType,String charset) {
		if (str == null) {
			return null;
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(signType);
			messageDigest.reset();
			messageDigest.update(str.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			return str;
		} catch (UnsupportedEncodingException e) {
			return str;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	
	
}
