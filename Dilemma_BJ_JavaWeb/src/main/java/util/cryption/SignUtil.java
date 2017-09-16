package util.cryption;

import java.util.Arrays;

public class SignUtil {

	public static String sign(String... paras) {
		String parasSorted = sort(paras);
		System.out.println("未签名串：" + parasSorted);
		String sign = MD5.encrypt(parasSorted);
		return sign;
	}

	public static String sort(String... paras) {
		// 按照字典序逆序拼接参数
		Arrays.sort(paras, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paras.length; i++) {
			sb.append(paras[i]);
		}
		return sb.toString();
	}
}
