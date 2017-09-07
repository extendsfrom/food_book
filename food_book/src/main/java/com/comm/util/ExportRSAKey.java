package com.comm.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * keytool生成keyStore文件 从keyStore文件中导出公钥私钥文件
 * 
 * @author mahuayang
 * 
 */
public class ExportRSAKey {
	public static void main(String[] args) throws Exception {
		String privatePath = "F:/HeartyPri.key"; // 准备导出的私钥
		String publicPath = "F:/HeartyPub.key"; // 准备导出的公钥
		PrivateKey privateKey = getPrivateKeyFromStore();
		createKeyFile(privateKey, privatePath);
		PublicKey publicKey = getPublicKeyFromCrt();
		createKeyFile(publicKey, publicPath);
		System.out.println("ok");
	}

	private static PrivateKey getPrivateKeyFromStore() throws Exception {
		String alias = "tomcat"; // KeyTool中生成KeyStore时设置的alias
		String storeType = null; // KeyTool中生成KeyStore时设置的storetype
		char[] pw = "123456".toCharArray(); // KeyTool中生成KeyStore时设置的storepass
		String storePath = "F:/tomcat.keystore"; // KeyTool中已生成的KeyStore文件
		storeType = null == storeType ? KeyStore.getDefaultType() : storeType;
		KeyStore keyStore = KeyStore.getInstance(storeType);
		InputStream is = new FileInputStream(storePath);
		keyStore.load(is, pw);
		return (PrivateKey) keyStore.getKey(alias, pw);
	}

	private static PublicKey getPublicKeyFromCrt() throws Exception {
		String crtPath = "F:/tomcat.crt"; // KeyTool中已生成的证书文件
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream(crtPath);
		Certificate crt = cf.generateCertificate(in);
		PublicKey publicKey = crt.getPublicKey();
		return publicKey;
	}

	private static void createKeyFile(Object key, String filePath) throws Exception {
		FileOutputStream fos = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(key);
		oos.flush();
		oos.close();
	}
}
