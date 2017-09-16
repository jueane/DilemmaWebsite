package util.cryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行DES加密后，在进行Base64编码转化；
 */
public class DES {

	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private String sKey = "BLUE#Key$M0BiLE!WS@DG_1@";// key，可自行修改
	private String ivParameter = "BLUE@DG#";// 偏移量,可自行修改
	private static DES instance = null;

	private DES() {
	}

	public static DES getInstance() {
		if (instance == null)
			instance = new DES();
		return instance;
	}

	// 加密
	public String encrypt(String sSrc) throws Exception {
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return Base64.encodeBase64URLSafeString(encrypted);// 此处使用BASE64做转码。
	}

	// 解密
	public String decrypt(String sSrc) throws Exception {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");// DES/ECB/NoPadding
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String encodeBytes(byte[] bytes) {
		StringBuffer strBuf = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {
			strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
			strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
		}

		return strBuf.toString();
	}

	public static void main(String[] args) throws Exception {
		// 需要加密的字串
		String cSrc = "{\"interfacecode\":\"N1008\",\"intefacemethod\":\"authPermission\",\"key\":\"d41d8cd98f00b204e9800998ecf8427e\"}";

		// 加密
		long lStart = System.currentTimeMillis();
		String enString = DES.getInstance().encrypt(cSrc);
		System.out.println("加密后的字串是：" + enString);

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		// String DeString = AESOperator.getInstance().decrypt(enString);
		String DeString = DES.getInstance().decrypt("x36uAHxSfYSBFzt2ku4KVxZ0k99pq9u63szl-3aHRJwb3X57jVOv-h_S9z2eMQzNzQQlX4XYCdz7376bxVQOhKH8HBZYiDSRI93v8eiOTdaecdFmWlMWGlRk8kmEH8eYgvzwBmncnOA");
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}

}