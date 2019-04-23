package top.mnilsy.cup.contrller;

/**
 * Created by mnilsy on 19-4-22 上午1:04.
 */

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

/**
 * 系统管理控制器
 */
@RestController
public class SystemManageContrller {

    /**
     * 停封账号
     *
     * @param requestMessage
     * @param user_Name      用户名
     * @return 请求状态码status，失败信息 message
     */
    @PostMapping("/sotpUser{user_Name}.api")
    public ResponMessage sotpUser(RequestMessage requestMessage, @PathVariable String user_Name) {
        return new ResponMessage();
    }

    /**
     * 发布公告
     * @param requestMessage 公告内容data.get("proclamation_Vlue")
     * @return 请求状态码status，失败信息 message
     */
    @PostMapping("/atAllUser.api")
    public ResponMessage atAllUser(RequestMessage requestMessage){
        return new ResponMessage();
    }

    /**
     * 系统删除非法推文
     *
     * @param requestMessage
     * @param tweet_Id       推文id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/systemDeleteTweet{tweet_Id}.api")
    public ResponMessage systemDeleteTweet(RequestMessage requestMessage, @PathVariable String tweet_Id) {
        return new ResponMessage();
    }

    /**
     * 系统删除非法评论
     *
     * @param requestMessage
     * @param discuss_Id     评论的id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/systemDeleteDiscuss{discuss_Id}.api")
    public ResponMessage systemDeleteDiscuss(RequestMessage requestMessage, @PathVariable String discuss_Id) {
        return new ResponMessage();
    }

    /**
     * 系统删除非法评论回复
     *
     * @param requestMessage
     * @param writeBack_Id   评论回复的id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/systemDeleteWriteBack{writeBack_Id}.api")
    public ResponMessage systemDeleteWriteBack(RequestMessage requestMessage, @PathVariable String writeBack_Id) {
        return new ResponMessage();
    }


}
