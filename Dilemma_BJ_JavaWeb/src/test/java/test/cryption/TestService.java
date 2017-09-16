package test.cryption;

import java.util.Arrays;

import util.cryption.AES;
import util.cryption.MD5;


public class TestService {
	public static final String PART_ID = "1";
	public static final String AES_KEY = "1234567890123456";
	public static final String iv = "0000000000000000";
	public String resp(String partId,String data,long time,String sign){
		String reqdata=null;
		try {
			String signb=MD5.encrypt(sortDesc(String.valueOf(partId),data,String.valueOf(time)));
			System.out.println("sign1:"+sign);
			System.out.println("sign2:"+signb);
			if (signb.equals(sign)) {
				reqdata =AES.decrypt(AES_KEY, iv, data);
				System.out.println("解密报文："+reqdata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
	
	
}
