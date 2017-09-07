package com.comm.util;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


public class RSAUtil {
	public static final String CHARSET = "utf-8";
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGN_ALGORITHM = "SHA1WithRSA"; 
	public static final String CIPHER_TRANSFORMATION = "RSA";
	private static final int MAX_ENCRYPT_BLOCK = 117;  
    
    /** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 128; 
	
	public static String decrypt(String content, String privateKey) {
        try {  
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));	//对于 Private key 是用 PKCS#8 编码 
			KeyFactory keyf = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			//
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);  
            cipher.init(Cipher.DECRYPT_MODE, priKey);  
            byte[] data = Base64.decodeBase64(content);  
            int inputLen = data.length;  
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] cache;  
            // 对数据分段解密
            for (int offSet = 0;  offSet<inputLen; offSet += MAX_DECRYPT_BLOCK) {  
            	byte[] subData = subArray(data, offSet, MAX_DECRYPT_BLOCK);
            	cache = cipher.doFinal(subData);  
                out.write(cache, 0, cache.length);  
            }  
            byte[] decryptBytes = out.toByteArray();  
            out.close();
            return new String(decryptBytes, CHARSET);
        } catch (Exception e) {
            return null;  
        }  
    } 
	
	private static byte[] subArray(byte[] source, int offset, int limit) {
    	int targetLen = 0;
    	if(source==null || offset>=source.length || offset<0 || limit<=0) {
    		return new byte[]{};
    	}else if(source.length-offset>limit) {
    		targetLen = limit;
    	}else {
    		targetLen = source.length-offset;
    	}
    	byte[] target = new byte[targetLen];
    	for(int i=0; i<targetLen; i++) {
    		target[i] = source[offset+i];
    	}
    	return target;
    }
	
	public static void main(String[] args) {
		String str = "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";
//		String str = "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";
		String atStr = RSAUtil.decrypt(str, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJSAvble8aB3pZNV1pLTghTl4YRBrE6gXB2kJseBWs+6tOZ4EgmiLoJqR6PG7Z3Q2EnH+PtVkj2NifS0Xry0mKDgynCulwOe6SFhEsPvn0oht3NqYh5UHJOK8oc9d1m1hI4eFlR9ZZynhjRHB5pFcx1bO6lGEWvwSJ8slC7tHjJtAgMBAAECgYEAk+CjjO1DtaSPZ+kpdCpUtdS4vkfbeqxrLkUhLLfeI09sBrh4Vcl/gPSuhCN6yRWH2+jbQgRTTSIdx2S5ljZMaRs5ZxCBoKflt7VtfeHahRHcA1eGdfDmAIO3mLpQFDeubaXIgEDTc0VAAO64td1Mr8oMD3NknGzJZGCqcxjCP60CQQDEx3/ZG00IGziO/NndQqm/770vhNttvimqEnZ6qhzRalBJVEoDlNCgTNb8J2RrtQRh9cjb+ERyFLH1gzDDh7uTAkEAwTHf9nNviPOnww2Qyxyvxe83dyc44kAL2CSS6LMkU/TAOp9RI2VCabHejwLCYLCCSatj9vVoRmUo8CoAQ6MZ/wJAPOzTvaGBSSg8a7EHt0TDj+SnO4Yz9hdxiPGsczAmOgRjlUCepds2eNsxmOKNafeiwr25kbmdX4De1Hfo6jElNwJATrPMsw9vnnD0JMe/9p5nFNFQ3R+DUtgYeyLbHiTsZa9NKBSC71Ju7bPiIIxVNyJw0SYbAR4ULqlDNiNGHEezbQJAHSTYJoDbZrjGmM/q84dndEoaDQbDajd6EntKCraw58QTbJ8K9KbYMLNgnEHzonv6SiSND6vf7JcrhMAGNKyJvg==");
		System.out.println(atStr);
		Map<String, Object> map = new HashMap<String, Object>();
		
	}
	
}
