package top.mnilsy.cup.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.mnilsy.cup.utils.ResponMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mnilsy on 19-5-6 下午5:17.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userInfo")==null){
            response.setCharacterEncoding("utf8");
            response.getWriter().print(JSON.toJSONString(ResponMessage.not_login()));
            return false;
        }
        return true;
    }
}
