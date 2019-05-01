package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnilsy.cup.VO.FollwVO;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.RelationshipService;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by mnilsy on 19-4-21 下午6:51.
 */

/**
 * 用户关系控制器
 */
@RestController
public class RelationshipContrller {

    @Resource(name = "relationshipService")
    private RelationshipService relationshipService;

    /**
     * 关注用户
     *
     * @param user_Name 用户名
     * @return 请求状态码status
     */
    @GetMapping("/follow{user_Name}.api")
    public ResponMessage follow(@PathVariable String user_Name, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        boolean flag = relationshipService.fans(userPojo.getUser_Id(), user_Name);
        return flag ? ResponMessage.ok() : ResponMessage.error("关注失败");
    }

    /**
     * 获取关注的人列表
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注人列表data.List<follwVO>
     */
    @GetMapping("/getFollowList.api")
    public ResponMessage getFollowList(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        int count = Integer.parseInt((String) requestMessage.getData().get("count"));
        List<FollwVO> list = relationshipService.getFollwlist(userPojo.getUser_Id(), count);
        return list.isEmpty() ? ResponMessage.error("获取失败") : ResponMessage.ok(list);
    }

    /**
     * 获取粉丝列表
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注人列表data.follwListVO[]
     */
    @GetMapping("/getFans.api")
    public ResponMessage getFans(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 查看黑名单
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注人列表data.follwListVO[]
     */
    @GetMapping("/getBlackList.api")
    public ResponMessage getBlackList(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 加入黑名单
     *
     * @param requestMessage
     * @param user_Name      用户名
     * @return 请求状态码status，失败信息 message
     */
    @PostMapping("/setBlackList{user_Name}.api")
    public ResponMessage setBlackList(RequestMessage requestMessage, @PathVariable String user_Name) {
        return new ResponMessage();
    }

    /**
     * 移出黑名单
     *
     * @param requestMessage
     * @param user_Name
     * @return 请求状态码status，失败信息 message
     */
    @PostMapping("/clearBlackList{user_Name}.api")
    public ResponMessage clearBlackList(RequestMessage requestMessage, @PathVariable String user_Name) {
        return new ResponMessage();
    }

}
