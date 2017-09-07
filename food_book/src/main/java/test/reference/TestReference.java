package test.reference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestReference {
	public static void main(String[] args) {
		/*File file = new File("D:/bcss-verify.log");
		BufferedReader reader = null;
		Set<String> failOrders = new HashSet<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1,currentLine =-11;
			String order = "";
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
			//System.out.println("line " + line + ": " + tempString);
				if(tempString.startsWith("<out_trade_no>")){
					order = tempString.substring("<out_trade_no><![CDATA[".length(),"<out_trade_no><![CDATA[".length()+24);
					currentLine = line;
				}
				if(line == currentLine + 12){
					if(tempString.contains("【验签】失败")){
						failOrders.add(order);
					}
				}
				line++;
			}
			reader.close();
			Iterator<String> iter = failOrders.iterator();
			while(iter.hasNext()){
				System.out.println(iter.next());
			}
			System.out.println(failOrders.size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}*/
		
		Long a = 0L;
		System.out.println(a.toString());
	}
	
	public void testFile() {
		File file = new File("D:/bcss-verify.log");
		BufferedReader reader = null;
		Set<String> failOrders = new HashSet<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1,currentLine =-11;
			String order = "";
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
			//System.out.println("line " + line + ": " + tempString);
				if(tempString.startsWith("<out_trade_no>")){
					order = tempString.substring("<out_trade_no><![CDATA[".length(),"<out_trade_no><![CDATA[".length()+24);
					currentLine = line;
				}
				if(line == currentLine + 12){
					if(tempString.contains("【验签】失败")){
						failOrders.add(order);
					}
				}
				line++;
			}
			reader.close();
			Iterator<String> iter = failOrders.iterator();
			while(iter.hasNext()){
				System.out.println(iter.next());
			}
			System.out.println(failOrders.size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
