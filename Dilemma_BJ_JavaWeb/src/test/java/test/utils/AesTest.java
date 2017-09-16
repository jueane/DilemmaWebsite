package test.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import util.cryption.AES;

public class AesTest {

	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void test2() {
		String initVector="1234567890123456";
		String key = "0123456789abcdef";
		String value = "hello";
		
		String result=AES.encrypt(key, initVector, value);

		System.out.println(result);
		
		String deResult=AES.decrypt(key, initVector, result);
		
		System.out.println("decrypt: " + deResult);

	}

}
