package top.mnilsy.cup.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.mnilsy.cup.enums.ResponMessageTypeEnum;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.RelationshipService;
import top.mnilsy.cup.utils.ResponMessage;
import top.mnilsy.cup.utils.SpringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mnilsy on 19-5-6 下午5:17.
 */
public class DiscussInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tweet_Id = request.getServletPath().replace("/putDiscuss", "").replace(".api", "");
        String user_Id = ((UserPojo) request.getSession().getAttribute("userInfo")).getUser_Id();
        RelationshipService relationshipService = (RelationshipService) SpringUtil.getBean("relationshipService");
        if (relationshipService.isBalcklistByTweet(user_Id, tweet_Id)) {
            response.setCharacterEncoding("utf8");
            response.getWriter().print(JSON.toJSONString(new ResponMessage(ResponMessageTypeEnum.JURISDICTION.vlue, "对方拒绝你的评论")));
            return false;
        }
        return true;
    }
}
