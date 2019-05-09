package top.mnilsy.cup.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mnilsy.cup.interceptor.DiscussInterceptor;
import top.mnilsy.cup.interceptor.LoginInterceptor;
import top.mnilsy.cup.interceptor.WritebackInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/open/**");*/
        registry.addInterceptor(new DiscussInterceptor()).addPathPatterns("/putDiscuss*");
        registry.addInterceptor(new WritebackInterceptor()).addPathPatterns("/putWriteBack*");
    }

}
