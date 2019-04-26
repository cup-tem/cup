package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

/**
 * Created by mnilsy on 19-4-21 下午6:51.
 */

/**
 * 用户关系控制器
 */
@RestController
public class RelationshipContrller {

    /**
     * 关注用户
     *
     * @param requestMessage
     * @param user_Name      用户名
     * @return 请求状态码status
     */
    @GetMapping("/follow{user_Name}.api")
    public ResponMessage follow(RequestMessage requestMessage, @PathVariable String user_Name) {
        String firstParty_User_Name=(String) requestMessage.getData().get("user_Name");

        String firstParty_User_Id=relationshipService.getUserId(firstParty_User_Name);
        String secondParty_User_Id=relationshipService.getUserId(user_Name);

        Date time=new Date();
        Timestamp fans_Date = new Timestamp(time.getTime());

        boolean flag=relationshipService.addFans(firstParty_User_Id,secondParty_User_Id,fans_Date);
        if (flag) {
            return ResponMessage.ok();
        } else {
            return ResponMessage.error("关注失败。");
        }

    }

    /**
     * 取消关注用户
     *
     * @param requestMessage
     * @param user_Name      用户名
     * @return
     */
    @GetMapping("/unFollow{user_Name}.api")
    public ResponMessage unFollow(RequestMessage requestMessage, @PathVariable String user_Name) {
         String firstParty_User_Name=(String) requestMessage.getData().get("user_Name");

        String firstParty_User_Id=relationshipService.getUserId(firstParty_User_Name);
        String secondParty_User_Id=relationshipService.getUserId(user_Name);

        boolean flag=relationshipService.deleteFollow(firstParty_User_Id,secondParty_User_Id);
        if (flag){
            return ResponMessage.ok();
        }else {
            return ResponMessage.error("取消关注失败。");
        }
    }

    /**
     * 获取关注的人列表
     *
     * @param requestMessage 请求次数data.get("count")
     * @return 请求状态码status，关注人列表data.follwListVO[]
     */
    @GetMapping("/getFollowList.api")
    public ResponMessage getFollowList(RequestMessage requestMessage) {
        return new ResponMessage();
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
       String firstParty_User_Name=(String) requestMessage.getData().get("user_Name");

        String firstParty_User_Id=relationshipService.getUserId(firstParty_User_Name);
        String secondParty_User_Id=relationshipService.getUserId(user_Name);

        Date time=new Date();
        Timestamp blacklist_Date = new Timestamp(time.getTime());

        boolean flag=relationshipService.addBlacklist(firstParty_User_Id,secondParty_User_Id,blacklist_Date);
        if (flag){
            return ResponMessage.ok();
        }else {
            return ResponMessage.error("加入黑名单失败。");
        }

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
       String firstParty_User_Name=(String) requestMessage.getData().get("user_Name");

        String firstParty_User_Id=relationshipService.getUserId(firstParty_User_Name);
        String secondParty_User_Id=relationshipService.getUserId(user_Name);

        boolean flag=relationshipService.moveBlacklist(firstParty_User_Id,secondParty_User_Id);
        if (flag){
            return ResponMessage.ok();
        }else {
            return ResponMessage.error("移出黑名单失败。");
        }

    }

}
