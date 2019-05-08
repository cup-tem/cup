package top.mnilsy.cup.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.mnilsy.cup.enums.ResponMessageTypeEnum;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.RelationshipService;
import top.mnilsy.cup.utils.ResponMessage;
import top.mnilsy.cup.utils.SpringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mnilsy on 19-5-6 下午5:17.
 */
public class WritebackInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String writeBack_User_Name = request.getServletPath().replace("/putWriteBack", "").replace(".api", "");
        String user_Id = ((UserPojo) request.getSession().getAttribute("userInfo")).getUser_Id();
        String discuss_Id = request.getParameter("data[discuss_Id]");

        RelationshipService relationshipService = (RelationshipService) SpringUtil.getBean("relationshipService");
        if (relationshipService.isBlacklistByTDW(user_Id, writeBack_User_Name,discuss_Id)) {
            response.setCharacterEncoding("utf8");
            response.getWriter().print(JSON.toJSONString(new ResponMessage(ResponMessageTypeEnum.JURISDICTION.vlue, "对方拒绝你的回复")));
            return false;
        }
        return true;
    }
}
