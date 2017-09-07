package com.comm.util;

import java.awt.Color;

import com.google.zxing.common.BitMatrix;

public class ZxingDemoTest {
	public static void main(String[] args) {
		try {
			int width = 200;
			int height = 200;
			String content = "weixin://wxpay/bizpayurl?pr=vjRHHxL";
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(content, width, height);
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.BLUE, 4);
			MatrixToImageWriterEx.writeToFile(matrix, "jpg", "f:/imgQRCode.png", "f:/345.png", logoConfig);
			System.out.println("生成二维码结束！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
