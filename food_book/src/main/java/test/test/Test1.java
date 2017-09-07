package test.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;

public class Test1 {
	public static void main(String[] args) {
		TestData data = new TestData();
		data.setName("※	赠送黄山上下景区环保车特别安排远方的客人体验黄山茶农的活，在万亩茶园进行采摘体验");
		Map<String, String> map = data.getRequestData(true);
		System.out.println(createLinkString(map, true));
	}
	
	 public static String createLinkString(Map<String, String> params, boolean encode) {

	        //        params = paraFilter(params);

	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);

	        String prestr = "";

	        /*String charset = params.get("_input_charset");*/
	        Charset charset = Charset.forName("UTF-8");
	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            String value = params.get(key);
	            if (null == value || "".equals(value)){
	                continue;
	            }
	            if (encode) {
	                try {
	                    if (!"beforeURL".equals(key) && !"serverNotifyURL".equals(key)){
	                        value = URLEncoder.encode(value, charset.name());
	                    }
	                } catch (UnsupportedEncodingException e) {
	                    e.printStackTrace();
	                }
	            }

	            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + value;
	            } else {
	                prestr = prestr + key + "=" + value + "&";
	            }
	        }

	        if (prestr.endsWith("&")){
	            return prestr.substring(0, prestr.length() - 1);
	        }
	        return prestr;
	    }
}
