package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.TweetService;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by mnilsy on 19-4-21 上午12:43.
 * 推文控制器
 */

@RestController
public class TweetContrller {

    @Resource(name = "tweetService")
    private TweetService tweetService;

    /**
     * 发布推文
     *
     * @param requestMessage 推文类型data.get("tweet_Type")，推文文字data.get("tweet_Text")，
     *                       推文附件data.get("accessory")[]，@的用户data.get("user_Name")[]
     * @return 请求状态码 status
     */
    @PostMapping("/putTweet.api")
    public ResponMessage putTweet(RequestMessage requestMessage, HttpSession session) {
        UserPojo userInfo = (UserPojo) session.getAttribute("userInfo");
        int tweet_Type = Integer.parseInt((String) requestMessage.getData().get("tweet_Type"));
        String tweet_Text = (String) requestMessage.getData().get("tweet_Text");
        String accessory[] = (String[]) requestMessage.getData().get("accessory");
        String user_Name[] = (String[]) requestMessage.getData().get("user_Name");
        boolean flag = tweetService.addTweet(tweet_Type, tweet_Text, accessory, user_Name, userInfo.getUser_Id());
        return flag ? ResponMessage.ok() : ResponMessage.error();
    }

    /**
     * 查看推文，即点开推文
     *
     * @param requestMapping 推文id data.get("tweet_Id")
     * @return 请求状态码status，失败信息message，推文data.tweetVO，推文评论data.discussVO
     */
    @GetMapping("/openTweet.api")
    public ResponMessage openTweet(RequestMapping requestMapping) {
        return new ResponMessage();
    }

    /**
     * 评论推文
     *
     * @param requestMessage 评论内容data.get("discuss_Vlue")
     * @param tweet_Id       推文的id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/putDiscuss{tweet_Id}.api")
    public ResponMessage putDiscuss(RequestMessage requestMessage, @PathVariable String tweet_Id) {
        return new ResponMessage();
    }

    /**
     * 回复评论
     *
     * @param requestMessage 回复内容data.get("write_Vlue")
     * @param discuss_Id     评论的id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/putWriteBack{discuss_Id}.api")
    public ResponMessage putWriteBack(RequestMessage requestMessage, @PathVariable String discuss_Id) {
        return new ResponMessage();
    }

    /**
     * 点赞
     *
     * @param tweet_Id 推文id
     * @return 请求状态码status，失败信息message
     * @author mnilsy
     */
    @PostMapping("/putLike{tweet_Id}.api")
    public ResponMessage putLike(@PathVariable String tweet_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        boolean flag = tweetService.putLike(tweet_Id, userPojo.getUser_Id());
        return flag ? ResponMessage.ok() : ResponMessage.error("推文被删除");
    }

    /**
     * 删除推文
     *
     * @param requestMessage
     * @param tweet_Id       推文id
     * @return 请求状态码status
     */
    @PostMapping("/deleteTweet{tweet_Id}.api")
    public ResponMessage deleteTweet(RequestMessage requestMessage, @PathVariable String tweet_Id) {
        return new ResponMessage();
    }

    /**
     * 删除评论
     *
     * @param requestMessage
     * @param discuss_Id     评论的id
     * @return 请求状态码status
     */
    @PostMapping("/deleteDiscuss{discuss_Id}.api")
    public ResponMessage deleteDiscuss(RequestMessage requestMessage, @PathVariable String discuss_Id) {
        return new ResponMessage();
    }

    /**
     * 删除评论回复
     *
     * @param requestMessage
     * @param writeBack_Id   评论回复的id
     * @return 请求状态码status
     */
    @PostMapping("/deleteWriteBack{writeBack_Id}.api")
    public ResponMessage deleteWriteBack(RequestMessage requestMessage, @PathVariable String writeBack_Id) {
        return new ResponMessage();
    }


}
