package testweb.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import testweb.controller.GeneralController;

public class CustomFreeMarkerView extends FreeMarkerView {

	Logger log = LoggerFactory.getLogger(GeneralController.class.getSimpleName());

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		// 页面提示
		Object noticeObj = session.getAttribute("notice");
		if (noticeObj != null) {
			model.put("notice", noticeObj.toString());
			session.removeAttribute("notice");
		}
		// 页面错误提示
		Object errorObj = session.getAttribute("error");
		if (errorObj != null) {
			model.put("error", errorObj.toString());
			session.removeAttribute("error");
		}
		// log.info("Notice and error has been clean.");

		super.exposeHelpers(model, request);
	}
}
