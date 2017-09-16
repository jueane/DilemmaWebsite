package util.wx;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.HttpClient;

public class TicketUtil {
	static Logger log = LoggerFactory.getLogger(TicketUtil.class.getSimpleName());

	public static final String APPID = "wx53794324f4c96a0b";
	public static final String SECRET = "2dbe42761175b3c710ae3afc1bbfe895";
	public static String accessToken = null;
	public static String ticketCurrent = null;
	public static long lastTimeGotToken = 0;

	public static void getToken() {
		String resp = HttpClient.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential" + "&appid=" + APPID + "&secret=" + SECRET);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = objectMapper.readTree(resp);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (node != null) {
			lastTimeGotToken = System.currentTimeMillis();
			String tokenTemp = node.get("access_token").toString();
			accessToken = tokenTemp.replace("\"", "");
		}
	}

	public static String getTicket() {
		long nowTime = System.currentTimeMillis();
		if (accessToken == null || lastTimeGotToken == 0 || (nowTime - lastTimeGotToken) > 7000) {
			getToken();
			String resp = HttpClient.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "type=jsapi&access_token=" + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = null;
			try {
				node = objectMapper.readTree(resp);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (node == null) {
				log.info("解析ticket json失败。json：" + resp);
				return null;
			}
			JsonNode ticketNode = node.get("ticket");
			if (ticketNode == null) {
				log.info("无ticket字段，json：" + resp);
				return null;
			}
			String ticket = ticketNode.toString();
			ticketCurrent = ticket;
			return ticket;
		}

		return ticketCurrent;
	}

}
