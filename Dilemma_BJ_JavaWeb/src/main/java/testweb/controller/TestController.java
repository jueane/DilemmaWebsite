package testweb.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {

	Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@RequestMapping("test")
	@ResponseBody
	public byte[] test(HttpServletResponse response) {
		try {
			log.info("hello~~");
			log.error("hi!!");
			return "你好，哦呵呵~~".getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "error".getBytes();
	}

	@RequestMapping(value = "test2", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public byte[] test2(HttpServletResponse response) {
		try {
			log.info("hello~~");
			log.error("hi!!");
			return "你好，哦呵呵~~".getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "error".getBytes();
	}

	@RequestMapping("lbtest")
	@ResponseBody
	public byte[] lbtest(HttpServletRequest request) {
		
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("getRequestURL="+request.getRequestURL()+"<br/>");
		sBuilder.append("getRequestURI="+request.getRequestURI()+"<br/>");
		sBuilder.append("getContextPath="+request.getContextPath()+"<br/>");
		sBuilder.append("getServletPath="+request.getServletPath()+"<br/>");
		sBuilder.append("getPathInfo="+request.getPathInfo()+"<br/>");
		
		
		return sBuilder.toString().getBytes();
	}

	@RequestMapping("")
	@ResponseBody
	public byte[] lbtest2(HttpServletRequest request) {
		
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("getRequestURL="+request.getRequestURL()+"<br/>");
		sBuilder.append("getRequestURI="+request.getRequestURI()+"<br/>");
		sBuilder.append("getContextPath="+request.getContextPath()+"<br/>");
		sBuilder.append("getServletPath="+request.getServletPath()+"<br/>");
		sBuilder.append("getPathInfo="+request.getPathInfo()+"<br/>");
		
		
		return sBuilder.toString().getBytes();
	}
}
