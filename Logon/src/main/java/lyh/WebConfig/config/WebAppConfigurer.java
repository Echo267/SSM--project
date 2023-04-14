package lyh.WebConfig.config;/*
 * @Auther:刘宇航
 * @Date:2023/4/9
 * @VERSON:1.0
 */

import lyh.WebConfig.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // 设置主界面 拦截
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/API/userMain");
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/sign")
//                .excludePathPatterns("/user/API/reg")
//                .excludePathPatterns("/user/API/login")
//                .excludePathPatterns("/css/**") ;
    }
}
