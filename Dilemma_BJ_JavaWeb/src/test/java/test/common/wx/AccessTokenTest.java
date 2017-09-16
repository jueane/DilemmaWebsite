package test.common.wx;

import static org.junit.Assert.*;

import org.junit.Test;

import util.HttpClient;
import util.wx.TicketUtil;

public class AccessTokenTest {

	public static final String APPID = "wx53794324f4c96a0b";
	public static final String SECRET = "2dbe42761175b3c710ae3afc1bbfe895";

	public void test2() {
		// accUrl += "&appid=" + appid;
		// accUrl += "&secret=" + key;

		String resp = HttpClient.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential" + "&appid=" + APPID + "&secret=" + SECRET);
		System.out.println(resp);
	}

	@Test
	public void test3() {
		String acc = "WPs_ctb3PYAEK1jcEezymZUmqtlvqQhjvPqPMIYfn2XB_b6ISzt1ft97US6F0ZgNfp_VZvev-WL_ekAJpRUaMRdnja-koLXeP7NC81H2FeroaAJIk5c-vnjxdntIAt3EUXZfAEAHKM";
		String resp = HttpClient.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "type=jsapi&access_token=" + acc);
		System.out.println(resp);
	}

	public void test4() {
		String ticket = TicketUtil.getTicket();
		System.out.println(ticket);
	}

	public void test() {
		fail("Not yet implemented");
	}

}
