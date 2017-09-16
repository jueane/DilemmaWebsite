package testweb.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.wx.Sha1Util;
import util.wx.TicketUtil;

@Controller
@RequestMapping("")
public class GeneralController {

	Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public String token = "pin66";

	@RequestMapping("checkSign")
	@ResponseBody
	public byte[] checkSign(String signature, String timestamp, String nonce, String echostr) {

		log.info("signature：" + signature);
		log.info("timestamp：" + timestamp);
		log.info("nonce：" + nonce);
		log.info("echostr：" + echostr);

		String nosigned = sign(sort(timestamp, nonce, token));

		String signed = null;
		try {
			signed = Sha1Util.encrypt(nosigned);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (signed != null && signed.equals(signature)) {
			return echostr.getBytes();
		}

		return "err".getBytes();
	}

	String sign(List<String> strList) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < strList.size(); i++) {
			strBuilder.append(strList.get(i));
		}

		String sbString = strBuilder.toString();
		log.info("拼接后：" + sbString);
		return sbString;
	}

	List<String> sort(String timestamp, String nonce, String echostr) {
		List<String> strList = new ArrayList<String>();
		strList.add(timestamp);
		strList.add(nonce);
		strList.add(echostr);
		Collections.sort(strList);

		return strList;
	}

	@RequestMapping("")
	public String index(ModelMap mm, HttpServletRequest request) {
//		String url = request.getRequestURL().toString();
//		String jsapi_ticket = TicketUtil.getTicket();
//		if (jsapi_ticket == null) {
//			log.info("获取ticket失败");
//			return null;
//		}
//		jsapi_ticket = jsapi_ticket.replace("\"", "");
//		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
//		String nonce_str = UUID.randomUUID().toString();
//
//		// 注意这里参数名必须全部小写，且必须有序
//		String unsigned = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
//		log.info(unsigned);
//
//		String signature = null;
//		try {
//			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//			crypt.reset();
//			crypt.update(unsigned.getBytes("UTF-8"));
//			signature = byteToHex(crypt.digest());
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		mm.put("appid", TicketUtil.APPID);
//		mm.put("noncestr", nonce_str);
//		mm.put("timestamp", timestamp);
//		mm.put("signature", signature);

		return "index";
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
