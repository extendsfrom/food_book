package com.mahy.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mahy.vo.Constant;

@Controller
@RequestMapping("/index")
public class InedxAction extends BaseAction {
	/*private static final Logger LOG = Logger.getLogger(InedxAction.class);
	
	@Autowired
	private UserServiceImpl userService;
	
	private Map<String, String> model = new HashMap<String, String>();
	
	@RequestMapping(value="index.do")
	public String index() {
		LOG.info("index begin ....");
		UserUser user = new UserUser();
		user.setName("test3");
		user.setPassword("test3");
		userService.addUser(user);
		System.out.println("index.do");
		LOG.info("index end ....");
		return "index2";
	}
	
	@ResponseBody
	@RequestMapping(value="memtest.do")
	public List<Map<String, String>> memTest() {
		List<UserUser> userList = new ArrayList<UserUser>();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for(int i=0; i<5; i++) {
			UserUser user = new UserUser();
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "111");
			map.put("name", "mahuayang");
			map.put("password", "1234567");
			user.setId("111");
			user.setName("mahuayang");
			user.setPassword("123456");
			userList.add(user);
			mapList.add(map);
		}
		return mapList;
	}
	
	

	@RequestMapping(value="json.do")
	public String json(HttpServletResponse response) {
		model = Constant.CARD_OUT_PURPOSE.getAllMap();
		return "index2";
	}

	@ResponseBody
	@RequestMapping(value="getImg.do")
	public void getImg(HttpServletResponse response) throws IOException {
		//同一IP访问次数限制
		//com.lvmama.comm.strtus2.interceptor.VisitLimitInterceptor.doIntercept(ActionInvocation);
		OutputStream out= null;
		try {
			out = response.getOutputStream();
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bitm = writer.encode("weixin://wxpay/bizpayurl?pr=vcetEPn", BarcodeFormat.QR_CODE, 200, 200);
			MatrixToImageWriter.writeToStream(bitm, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public Map<String, String> getModel() {
		return model;
	}

	public void setModel(Map<String, String> model) {
		this.model = model;
	}*/
}
