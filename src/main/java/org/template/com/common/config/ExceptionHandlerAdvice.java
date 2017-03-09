package org.template.com.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class ExceptionHandlerAdvice {

	private Boolean isJson(WebRequest request){
        String header = request.getHeader("content-type");
        return header != null && header.contains("json");
    }

    @ExceptionHandler(Exception.class)
    public Object handleBaseException(WebRequest request, Exception e) {
    	
    	return null;
    }
	
}
