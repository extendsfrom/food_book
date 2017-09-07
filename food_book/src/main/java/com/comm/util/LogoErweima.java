package com.comm.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class LogoErweima {
	public static void main(String[] args) {
		try {  
			/*QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bitm = writer.encode("weixin://wxpay/bizpayurl?pr=jO5NG1A", BarcodeFormat.QR_CODE, 200, 200);
			bitm = deleteWhite(bitm);
			File outputFile = new File("F:" + File.separator + "new-1.png");//指定输出路径  
			writeToFile(bitm, "png", outputFile);*/
//			encode("weixin://wxpay/bizpayurl?pr=ObIWS1i", "F:\\345.png", "F:");
//			encode("weixin://wxpay/bizpayurl?pr=ObIWS1i", "F:\\lvmama1.png", "F:");
//			encode("weixin://wxpay/bizpayurl?pr=jO5NG1A", "F:\\lvmama.png", "F:");
			Long a = null;
			Long b = 4998357L;
			System.out.println(String.valueOf(a).equals(String.valueOf(b)));
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}
	public static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
	
	
	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {  
		BufferedImage image = toBufferedImage(matrix);  
		//设置logo图标  
		image = LogoMatrix(image);  
		
		if (!ImageIO.write(image, format, file)) {  
			throw new IOException("Could not write an image of format " + format + " to " + file);  
		}else{  
			System.out.println("图片生成成功！");  
		}  
	} 

	public static BufferedImage LogoMatrix(BufferedImage matrixImage) throws IOException{  
        /** 
         * 读取二维码图片，并构建绘图对象 
         */  
        Graphics2D g2 = matrixImage.createGraphics();  
          
        int matrixWidth = matrixImage.getWidth();  
        int matrixHeigh = matrixImage.getHeight();  
          
        /** 
         * 读取Logo图片 
         */  
        BufferedImage logo = ImageIO.read(new File("F:\\lvmama.png"));  
 
        //开始绘制图片  
        g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制       
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g2.setStroke(stroke);// 设置笔画对象  
        //指定弧度的圆角矩形  
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);  
        g2.setColor(Color.white);  
        g2.draw(round);// 绘制圆弧矩形  
          
        //设置logo 有一道灰色边框  
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g2.setStroke(stroke2);// 设置笔画对象  
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);  
        g2.setColor(new Color(128,128,128));  
        g2.draw(round2);// 绘制圆弧矩形  
          
        g2.dispose();  
        matrixImage.flush() ;  
        return matrixImage ;  
    }  
	
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));  
            }  
        }  
        return image;  
    }
	private static final int BLACK = 0xFF000000;//用于设置图案的颜色  
    private static final int WHITE = 0xFFFFFFFF; //用于背景色 
	
    
    
    
    
    
    public static void encode(String content, String logoPath, String destPath) throws Exception {
        BufferedImage image = createImage(content, logoPath, true);
        ImageIO.write(image, FORMAT, new File("F:\\123.png"));
    }
    
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress, int widthx, int heighty) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo file not found.");
        }
        Image src = ImageIO.read(new File(logoPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (widthx - width) / 2;
        int y = (heighty - height) / 2;
        System.out.println("x" + x);
        System.out.println("y" + y);
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
    
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
    	QRCodeWriter writer = new QRCodeWriter();
    	Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();   
		//设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        // 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		BitMatrix bitm = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
		bitm = deleteWhite(bitm);
        int width = bitm.getWidth();
        int height = bitm.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitm.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, logoPath, needCompress, width, height);
        return image;
    }
    
    private static final String CHARSET = "utf-8";
    private static final String FORMAT = "png";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 200;
    // LOGO宽度
    private static final int LOGO_WIDTH = 36;
    // LOGO高度
    private static final int LOGO_HEIGHT = 36;
}
