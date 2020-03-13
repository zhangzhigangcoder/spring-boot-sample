package org.spring.boot.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author zhangzhigang
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.addObject("url", req.getRequestURL());
		mv.setViewName(DEFAULT_ERROR_VIEW);
		return mv;
	}
	
	/**
	 * 返回异常页面
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = MyException.class)
	public ModelAndView exceptionPage(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.addObject("url", req.getRequestURL());
		mv.setViewName("500_page");
		return mv;
	}
	
	/**
	 * 返回json数据
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
//	@ResponseBody
//	@ExceptionHandler(value = Exception.class)
//	public ErrorInfo<String> jsonErrorHandler (HttpServletRequest req, MyException e) throws Exception {
//		ErrorInfo<String> error = new ErrorInfo<>();
//		error.setMessage(e.getMessage());
//		error.setCode(ErrorInfo.ERROR);
//		error.setDate("some data");
//		error.setUrl(req.getRequestURL().toString());
//		return error;
//	}

}
