package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnilsy.cup.VO.UserListVO;
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
     * @author mnilsy
     */
    @GetMapping("/follow{user_Name}.api")
    public ResponMessage follow(@PathVariable String user_Name, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        boolean flag = relationshipService.fans(userPojo.getUser_Id(), user_Name);
        return flag ? ResponMessage.ok() : ResponMessage.error("操作失败");
    }

    /**
     * 获取关注的人列表
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注人列表data.List<UserListVO>
     * @author mnilsy
     */
    @GetMapping("/getFollowList.api")
    public ResponMessage getFollowList(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        int count = Integer.parseInt((String) requestMessage.getData().get("count"));
        List<UserListVO> list = relationshipService.getFollwlist(userPojo.getUser_Id(), count);
        return list.isEmpty() ? ResponMessage.error("获取失败") : ResponMessage.ok(list);
    }

    /**
     * 获取粉丝列表
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注你的人列表data.List<UserListVO>
     * @author mnilsy
     */
    @GetMapping("/getFans.api")
    public ResponMessage getFans(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        int count = Integer.parseInt((String) requestMessage.getData().get("count"));
        List<UserListVO> list = relationshipService.getFans(userPojo.getUser_Id(), count);
        return list.isEmpty() ? ResponMessage.error("获取失败") : ResponMessage.ok(list);
    }

    /**
     * 查看黑名单
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注你的人列表data.List<UserListVO>
     * @author mnilsy
     */
    @GetMapping("/getBlackList.api")
    public ResponMessage getBlackList(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        int count = Integer.parseInt((String) requestMessage.getData().get("count"));
        List<UserListVO> list = relationshipService.getBalcklist(userPojo.getUser_Id(), count);
        return list.isEmpty() ? ResponMessage.error("获取失败") : ResponMessage.ok(list);
    }

    /**
     * 加入黑名单
     *
     * @param user_Name      用户名
     * @return 请求状态码status，失败信息 message
     * @author mnilsy
     */
    @PostMapping("/setBlackList{user_Name}.api")
    public ResponMessage setBlackList(@PathVariable String user_Name,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        boolean flag = relationshipService.blacklist(userPojo.getUser_Id(), user_Name);
        return flag ? ResponMessage.ok() : ResponMessage.error("操作失败");
    }

}
