package net.lab1024.smartadmin.config;

import net.lab1024.smartadmin.interceptor.SmartAuthenticationInterceptor;
import net.lab1024.smartadmin.interceptor.StudentAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SmartAdminWebAppConfig implements WebMvcConfigurer {

    @Autowired
    private SmartAuthenticationInterceptor smartAuthenticationInterceptor;
    @Autowired
    private StudentAuthenticationInterceptor studentAuthenticationInterceptor;
    @Value("${file-upload-service.path}")
    private String filePath1 = "";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //前后端分离拦截器
        List<String> excludePaths = new ArrayList<>();
        excludePaths.add("/service/**");
        excludePaths.add("*.html");
        //前后端不分离拦截器
        registry.addInterceptor(studentAuthenticationInterceptor).addPathPatterns("/service/**")
                .excludePathPatterns("/static/**");
        registry.addInterceptor(smartAuthenticationInterceptor).addPathPatterns("/**")
                .excludePathPatterns(excludePaths);

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //项目欢迎页
        registry.addViewController("/").setViewName("redirect:/service/home");
        registry.addViewController("/druidMonitor").setViewName("redirect:/druid/index.html");
        registry.addViewController("/swaggerApi").setViewName("redirect:/swagger-ui.html");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String staticMapping="/upload/**";
        String localDirectory = "file:"+filePath1;
        registry.addResourceHandler(staticMapping).addResourceLocations(localDirectory);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
