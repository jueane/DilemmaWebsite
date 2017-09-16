package test.cryption;

import java.util.Arrays;

import org.junit.Test;

import util.cryption.AES;
import util.cryption.MD5;

public class ClientTest {

	public static final String PART_ID = "1";
	public static final String AES_KEY = "1234567890123456";
	public static final String iv = "0000000000000000";

	public void request(String json) {
		String data = AES.encrypt(AES_KEY, iv, json);
		System.out.println("加密后："+data);
		long time = System.currentTimeMillis();
		String sort = sortDesc(String.valueOf(PART_ID), data, String.valueOf(time));
		// 打印签名前的串
		String sign = MD5.encrypt(sort);
		// 打印签名
		new TestService().resp(PART_ID, data, time, sign);
	}

	public static String sortDesc(String... paras) {
		// 按照字典序逆序拼接参数
		Arrays.sort(paras,String.CASE_INSENSITIVE_ORDER);		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paras.length; i++) {
			sb.append(paras[i]);
		}
		return sb.toString();
	}
	@Test
	public void testws() {
		String json = "{\"phone\":\"18697794946\",\"partId\":\"1\",\"orderId\":\"8000000000003370\"}";
		new ClientTest().request(json);
	}

}
