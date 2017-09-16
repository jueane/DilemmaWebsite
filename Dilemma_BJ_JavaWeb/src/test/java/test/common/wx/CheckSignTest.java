package test.common.wx;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CheckSignTest {

	public String signature = "d027f19284d05ab149da0d26aa4f34207319f90b";
	public String timestamp = "1458810688";
	public String nonce = "1661922240";
	public String echostr = "2682572726110777198";

	public String token = "pin66";

	@Test
	public void test1() {

		sign(sort(timestamp, nonce, token));

	}

	void sign(List<String> strList) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < strList.size(); i++) {
			strBuilder.append(strList.get(i));
		}

		String sbString = strBuilder.toString();
		System.out.println("拼接后：" + sbString);
	}

	List<String> sort(String timestamp, String nonce, String echostr) {
		List<String> strList = new ArrayList<String>();
		strList.add(timestamp);
		strList.add(nonce);
		strList.add(echostr);
		Collections.sort(strList);

		return strList;
	}

	public void test() {
		fail("Not yet implemented");
	}

}
