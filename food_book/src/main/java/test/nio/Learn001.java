package test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Learn001 {
	public static void main(String[] args) throws Exception {
		String filePath = "F:\\nio\\testcharset.txt";
		String charset = "utf-8";
		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, charset);
		writer.write("中文");
		writer.close();
		
		FileInputStream fileInputStream = new FileInputStream(filePath);
		InputStreamReader reader = new InputStreamReader(fileInputStream);
		StringBuffer sBuffer = new StringBuffer();
		char[] buf = new char[1024];
		int count = 0;
		while((count = reader.read(buf)) != -1) {
			sBuffer.append(buf);
		}
		reader.close();
		System.out.println(sBuffer);
	}
}
