package testweb.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import testweb.config.Config;
import testweb.controller.GeneralController;

public class CustomInterceptor implements HandlerInterceptor {

	Logger log = LoggerFactory.getLogger(GeneralController.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 过滤URL
		// String controller = handler.getClass().getName();
		// if(controller.contains("cruel.ui")){
		// return true;
		// }
		// if((!controller.equals("cruel.ui.manage.controller.LoginManageController"))
		// && request.getSession().getAttribute("user")==null){
		// response.sendRedirect(request.getContextPath()+"/manage/login");
		// }

		// 获取baseURL
		if (Config.getIntance().rootPath == null) {
			String requestURL = request.getRequestURL().toString();
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			Config.getIntance().rootPath = requestURL.replace(requestURI, "") + contextPath + "/";
			log.info("rootPath=" + Config.getIntance().rootPath);
		}

		return true;
	}

}
