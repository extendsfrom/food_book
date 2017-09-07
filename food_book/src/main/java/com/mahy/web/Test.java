package com.mahy.web;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {
	public static String httpPostWithJSON(String url) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        
        JSONObject jsonParam = new JSONObject();  
        jsonParam.put("orderMainId", "60000012238");
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");    
        httpPost.setEntity(entity);
        System.out.println();
        
        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }

    
    public static void main(String[] args) throws Exception {
        String result = httpPostWithJSON("http://10.200.2.129:8086/openapi-app/rest/queryPaymentByOrderId");
        JSONObject json = JSONObject.fromObject(result);
        JSONObject jsonResult = JSONObject.fromObject(json.get("result"));
        System.out.println(jsonResult.get("payedAmount"));
        System.out.println(json);
    }
}
