package net.lab1024.smartadmin.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExcpHandler {
    // 捕获全局异常
    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("msg", e.getMessage());
        map.put("url", request.getRequestURL());
        return map;
    }
}
