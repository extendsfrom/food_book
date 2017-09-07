package com.comm.util;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class ImgUtil {
	// ͼƬ��ȵ�һ��  
    private static final int IMAGE_WIDTH = 60;  
    private static final int IMAGE_HEIGHT = 60;  
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;  
    private static final int FRAME_WIDTH = 2;  
    // ��ά��д����  
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();  
	
    public static void main(String[] args) {
    	createQrcode();
    	
    	/*ImgUtil.encode("weixin://wxpay/bizpayurl?pr=tbBDUrC", 300, 300, "", "F:\\testZxing.jpg");  
    	System.out.println("success");  */
	}
    
    public static void createQrcode(){
        try {
            int qrcodeWidth = 200;
            int qrcodeHeight = 200;
            String qrcodeFormat = "png";
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode("weixin://wxpay/bizpayurl?pr=dwi41Kw", BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);

            BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
            File QrcodeFile = new File("F:\\zxing." + qrcodeFormat);
            ImageIO.write(image, qrcodeFormat, QrcodeFile);
            MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, new FileOutputStream(QrcodeFile));
            System.out.println("over");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void createQrcode(OutputStream outStream){
        try {
            int qrcodeWidth = 200;
            int qrcodeHeight = 200;
            String qrcodeFormat = "png";
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode("weixin://wxpay/bizpayurl?pr=dwi41Kw", BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);

            BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
            File QrcodeFile = new File("F:\\zxing." + qrcodeFormat);
            ImageIO.write(image, qrcodeFormat, QrcodeFile);
            MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, new FileOutputStream(QrcodeFile));
            System.out.println("over");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	
	 /** 
     *  
     * @param content 
     *            ��ά����ʾ���ı� 
     * @param width 
     *            ��ά��Ŀ�� 
     * @param height 
     *            ��ά��ĸ߶� 
     * @param srcImagePath 
     *            �м�Ƕ�׵�ͼƬ 
     * @param destImagePath 
     *            ��ά����ɵĵ�ַ 
     */  
	 public static void encode(String content, int width, int height,  
	            String srcImagePath, String destImagePath) {  
	        try {  
	            // ImageIO.write ���� 1��BufferedImage 2������ĸ�ʽ 3��������ļ�  
	            ImageIO.write(genBarcode(content, width, height, srcImagePath), "jpg", new File(destImagePath));  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (WriterException e) {  
	            e.printStackTrace();  
	        }  
	    } 
	 
	 private static BufferedImage genBarcode(String content, int width,  
	            int height, String srcImagePath) throws WriterException,  
	            IOException {  
	        // ��ȡԴͼ��  
	        BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, true);  
	        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];  
	        for (int i = 0; i < scaleImage.getWidth(); i++) {  
	            for (int j = 0; j < scaleImage.getHeight(); j++) {  
	                srcPixels[i][j] = scaleImage.getRGB(i, j);  
	            }  
	        }  
	  
	        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();  
	        // ���ñ����ʽ   
	        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");  
	        // ����QR��ά��ľ��?�𡪡�����ѡ�����H����    
	        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
	        // ��ɶ�ά�룬����˳��ֱ�Ϊ���������ݣ��������ͣ����ͼƬ��ȣ����ͼƬ�߶ȣ����ò���    
	        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hint);  
	  
	        // ��ʼ���ö�ά����ݴ���BitmapͼƬ���ֱ���Ϊ�ڰ���ɫ    
	        // ��ά����תΪһά��������  
	        int halfW = matrix.getWidth() / 2;  
	        int halfH = matrix.getHeight() / 2;  
	        int[] pixels = new int[width * height];  
	  
	        for (int y = 0; y < matrix.getHeight(); y++) {  
	            for (int x = 0; x < matrix.getWidth(); x++) {  
	                // ��ȡͼƬ  
	                if (x > halfW - IMAGE_HALF_WIDTH && x < halfW + IMAGE_HALF_WIDTH &&   
	                        y > halfH - IMAGE_HALF_WIDTH && y < halfH + IMAGE_HALF_WIDTH) {  
	                    pixels[y * width + x] = srcPixels[x - halfW + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];  
	                }  
	                // ��ͼƬ�����γɱ߿�  
	                else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH  
	                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
	                        || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH  
	                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
	                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
	                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
	                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
	                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
	                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
	                                - IMAGE_HALF_WIDTH + FRAME_WIDTH)  
	                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
	                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
	                                && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
	                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {  
	                    pixels[y * width + x] = 0xfffffff;    
	                } else {  
	                    // �˴������޸Ķ�ά�����ɫ�����Էֱ��ƶ���ά��ͱ�������ɫ��  
	                    pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;  
	                }  
	            }  
	        }  
	  
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	        image.getRaster().setDataElements(0, 0, width, height, pixels);  
	  
	        return image;  
	    } 
	 
	 private static BufferedImage scale(String srcImageFile, int height,  
	            int width, boolean hasFiller) throws IOException {  
	        double ratio = 0.0; // ���ű���  
	        File file = new File(srcImageFile);  
	        BufferedImage srcImage = ImageIO.read(file);  
	        Image destImage = srcImage.getScaledInstance(width, height,  
	                BufferedImage.SCALE_SMOOTH);  
	        System.out.println(srcImage.getWidth() + " - " + srcImage.getHeight());  
	        // �������  
	        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {  
	            if (srcImage.getHeight() > height) {  
	                ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();  
	            } else {  
	                ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();  
	            }  
	            AffineTransformOp op = new AffineTransformOp(  
	                    AffineTransform.getScaleInstance(ratio, ratio), null);  
	            destImage = op.filter(srcImage, null);  
	        }  
	        if (hasFiller) {// ����  
	            BufferedImage image = new BufferedImage(width, height,  
	                    BufferedImage.TYPE_INT_RGB);  
	            Graphics2D graphic = image.createGraphics();  
	            graphic.setColor(Color.white);  
	            graphic.fillRect(0, 0, width, height);  
	            if (width == destImage.getWidth(null)){  
	                graphic.drawImage(destImage, 0,  
	                        (height - destImage.getHeight(null)) / 2,  
	                        destImage.getWidth(null), destImage.getHeight(null),  
	                        Color.white, null);  
	            }else{  
	                graphic.drawImage(destImage,  
	                        (width - destImage.getWidth(null)) / 2, 0,  
	                        destImage.getWidth(null), destImage.getHeight(null),  
	                        Color.white, null);  
	            }  
	            graphic.dispose();  
	            destImage = image;  
	        }  
	        return (BufferedImage) destImage;  
	    }  
}
