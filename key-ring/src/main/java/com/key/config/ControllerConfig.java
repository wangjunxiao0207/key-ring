package com.key.config;

import com.key.common.ApplicationContextHelper;
import com.key.common.RequestHolder;
import com.key.common.ResponseData;
import com.key.common.exception.ParamException;
import com.key.common.exception.PermissionException;
import com.key.service.SysMailService;
import com.key.util.DateTimeUtil;
import com.key.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

/**
 * 应用到所有@RequestMapping中，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute
 * 处理日期转化
 * 捕获异常
 */
@ControllerAdvice
@Slf4j
public class ControllerConfig {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
	    DateFormat sdf = new DateFormat() {
			@Override
			public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
			    return new StringBuffer(DateTimeUtil.dateToStr(date, "yyyy-MM-dd"));
			}

			@Override
            public Date parse(String source) {
		        return parse(source, null);
            }

			@Override
			public Date parse(String source, ParsePosition pos) {
				// yyyy-MM
				if(source.length() == 7) {
                    return DateTimeUtil.strToDate(source, "yyyy-MM");
                }

				return DateTimeUtil.strToDate(source, "yyyy-MM-dd");
			}
		};

	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 处理所有不可知异常
	 *
	 * @param e 异常
	 * @return json结果
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseData handleException(Exception e) {
		HttpServletRequest request = RequestHolder.getCurrentRequest();
		String url = request.getRequestURL().toString();
		log.error("未知错误, url:" + url, e);

		// 未知系统错误，发送邮件通知
		SysMailService sysMailService = ApplicationContextHelper.popBean(SysMailService.class);
		sysMailService.sendSimpleMail(ExceptionUtil.printStackTraceToString(e));

		return ResponseData.error("未知错误，请查看日志输出");
	}

	/**
	 * 处理业务异常
	 * @param e 异常
	 */
	@ExceptionHandler({PermissionException.class, ParamException.class})
	@ResponseBody
	public ResponseData handleBussinessException(Exception e) {
		return ResponseData.error(e.getMessage());
	}
}
